#!/bin/zsh
# Define variables for colors
RED_BOLD="\u001b[31;1m"
CYAN_BOLD="\u001b[36;1m"
YELLOW_BOLD="\u001b[33;1m"
RESET="\u001b[0m"
GREEN_BOLD="\u001b[32;1m"

# Function that logs a message
function log_message() {
  local message="$1"
  local log_type="$2"

  case $log_type in
    error)
      echo -e "${RED_BOLD}[ERROR] ${message}${RESET}"
      ;;
    info)
      echo -e "\n${CYAN_BOLD}[INFO] ${message}${RESET}"
      ;;
    warning)
      echo -e "${YELLOW_BOLD}[WARNING] ${message}${RESET}"
      ;;
      success)
      # echo -e "${GREEN_BOLD}[SUCCESS] ${message}${RESET}"
      ;;
    *)
      echo "Invalid log type"
      exit 1
      ;;
  esac
}

function modify_classpath() {
  local cp="$1"
  IFS=':' read -r -A ADDR <<< "$cp"
  classpath=""

  if [[ -z "$ADDR" ]]; then
    # Classpath is empty, return empty modified classpath
    return
  fi

  for p in "${ADDR[@]}"; do
    if [[ -z "$classpath" ]]; then
      classpath="$PROJECTS_DIR/$p"
    else
      classpath="$classpath:$PROJECTS_DIR/$p"
    fi
  done
}



# if [[ $(uname) != "Darwin" ]]; then
  alias gdate="date"
# fi

script_start_time=$(gdate +%s)

#Checking if the correct number of arguments are passed.
if [ "$#" -le 2 ]; then
  log_message "Not enough arguments passed. Please pass the following arguments:\
   \n\t1. What benchmark to perform (compile, pointsto, activeSet, filterTest) \ 
   \n\t2. How many times to run the evaluation for each project. (How many samples of n random methods)\
   \n\t3. How many analysis invocations to perform during each run (The last invocation will have the jvm warmed up)" "error"
  exit 1
fi

if [ "$2" -le 0 ]; then
  log_message "The first argument must be a positive integer." "error"
  exit 1
fi

if [ "$3" -le 0 ]; then
  log_message "The third argument must be a positive integer." "error"
  exit 1
fi

BENCHMARK=$1
OUTER=$2
INNER=$3
prj_json="projects.json"

count=$(jq '.benchmarks | length' $prj_json)
executed_count=$(jq '[.benchmarks[] | select(.enable == true)] | length' $prj_json)
# Static array with all jars to run.
declare -a JARS=(bfpa.jar)

TOTAL_ITERATIONS=$(( ${#JARS[@]} *$OUTER*2*$count))
EXECUTED_TOTAL_ITERATIONS=$(( ${#JARS[@]} *$OUTER*2*$executed_count))
CURRENT_ITERATION=0


if [ "$BENCHMARK" = "parse" ]; then
  opt=(-parseBenchmark)
  declare -a DISTANCES=(0) # Does not matter for this benchmark
elif [ "$BENCHMARK" = "pointsto" ]; then
  opt=(-pointerBenchmark 100)
  declare -a DISTANCES=(0 1 2 3 4 5 6 7 8)
elif [ "$BENCHMARK" = "activeSet" ]; then
  opt=(-activeSetBenchmark 1)
  declare -a DISTANCES=($(seq 1 10) 20 30 40)
elif [ "$BENCHMARK" = "filterTest" ]; then
  opt=(-filterTest 100000)
  declare -a DISTANCES=(0 1 2 3)
elif [ "$BENCHMARK" = "allMethods" ]; then
  opt=(-allMethods)
  declare -a DISTANCES=(0 1 2 3)
elif [ "$BENCHMARK" = "memory" ]; then
  declare -a DISTANCES=(0 1 2 3 4 5 6 7 8)
  declare -a MEMORIES=($(seq 50 50 5000))
  opt=(-pointerBenchmark 1)
else
  log_message "The benchmark argument must be either 'parse', 'pointsto', 'activeSet', 'filterTest', 'allMethods' or 'memory'." "error"
  exit 1
fi

MEMORY_MB=1000

#set -e
setopt EXTENDED_GLOB


function progress_bar() {
  local current=$1
  local percentage=$((current * 100 / $EXECUTED_TOTAL_ITERATIONS))
  local color=$'\e[32m' 
   if [[ $percentage -gt 66 ]]; then
    color=$'\e[33m'
  fi
  if [[ $percentage -gt 90 ]]; then
    color=$'\e[31m'
  fi
  local completed=$(printf "%.0f" $(echo "scale=2; $percentage / 2" | bc -l))
  local remaining=$((50 - $completed))
  printf "\rProgress: [\e[42m%${completed}s\e[0m%${remaining}s] %s%d%%$RESET (Iteration %d/%d)" \
         '' '' $color $percentage $current $EXECUTED_TOTAL_ITERATIONS
}

function find_memory() {
  local toolCommand=$1
  local distance=$2
  local low=1
  local high=${#MEMORIES[@]}
  local best_memory=-1
  local result_log=""

  while [ $low -le $high ]; do
    local mid=$(( (low + high) / 2 ))
    local MEMORY_MB=${MEMORIES[$mid]}

    completeCommand="java -Xmx${MEMORY_MB}M -jar ../$intraj $toolCommand"
    log_message "Testing memory: ${MEMORY_MB}M" "info"
    
    zsh -c "$completeCommand" > /dev/null

    # Check if the command was successful
    if [ $? -eq 0 ]; then
      result=1
      log_message "Memory ${MEMORY_MB}G ran successfully." "success"
    else
      result=0
      log_message "Memory ${MEMORY_MB}G caused a crash." "error"
    fi

    result_log+="${distance},${MEMORY_MB},${result}\n"

    if [ $result -eq 1 ]; then
      best_memory=$MEMORY_MB
      high=$((mid - 1))
    else
      low=$((mid + 1))
    fi
  done

  res=$result_log
}


function eval_steady() {
   # Run the evaluation
   csvHeader="-csvHeader"
   export results=()
   
   javaCommand="java -jar ../$intraj"

   for a in {1..$OUTER}; do
     # Create a seed to use for all distances
     SEED=$(gdate +%s)
     for dist in "${DISTANCES[@]}"; do
       CURRENT_ITERATION=$((CURRENT_ITERATION + 1))

       toolCommand="$all_files -seed $SEED -csv $csvHeader -niter $INNER ${opt[@]} -runid $a.$dist -distance $dist"

       if [[ $entryPackage != "null" ]]; then
         toolCommand="$toolCommand -entryPoint $entryPackage $entryMethod"
       fi

       if [[ -n $classpath ]]; then
         toolCommand="$toolCommand -classpath $classpath"
       fi

       if [[ $BENCHMARK == "memory" ]]; then
         find_memory "$toolCommand" "$dist"
         results+=($res)
         # Go to next distance
         continue
       fi

       completeCommand="$javaCommand $toolCommand"

       csvHeader="" # do not use the header after the first iteration

       res=$(zsh -c "$completeCommand")
       progress_bar $CURRENT_ITERATION
       # If res is empty, set it to 0
       if [ -z "$res" ]; then
         res=0
       fi
       log_message "Processing time: $res (seconds)  -  Iteration $a" "success"
       results+=($res)
       # Add new line to result
       results+=("\n")
     done
   done
}

log_message "Compiling project" "info"
cd ../
./gradlew clean jar
cd evaluation

# The results of the evaluation are stored in a directory named with a timestamp.
# The timestamp is used to avoid overwriting previous results.
TIMESTAMP=$(date +%Y%m%d%H%M%S)
SCRIPT_DIR=$(cd $(dirname "$0") && pwd)

MACHINE_NAME=$(uname -n | sed 's/\.//g')
EVAL_DIR=$SCRIPT_DIR/results/${MACHINE_NAME}_${BENCHMARK}_${OUTER}_${INNER}_$TIMESTAMP

PROJECTS_DIR=benchmarkprojects
mkdir -p $EVAL_DIR

STATS_FILE=$EVAL_DIR/stats.txt

# Create a symbolic link to the latest results
ln -sfn $EVAL_DIR $SCRIPT_DIR/results/latest

echo "Command: ./eval.sh $BENCHMARK $OUTER $INNER" >> $STATS_FILE
echo "Options: $opt" >> $STATS_FILE
echo "Distances: $DISTANCES" >> $STATS_FILE
echo "Machine: $MACHINE_NAME" >> $STATS_FILE
echo "Commit: $(git rev-parse --short HEAD)" >> $STATS_FILE
echo "Evaluation date: $(date +'%Y-%m-%d %H:%M')" >> $STATS_FILE

log_message "Number of benchmarks: $count" "info"
for ((i = 0; i < $count; i++)); do
  enable=$(jq -r ".benchmarks[$i].enable" $prj_json)

  if [ "$enable" = "false" ]; then
    # Skipping the benchmark if not enabled
    continue
    fi
    name=$(jq -r '.benchmarks['$i'].name' $prj_json)
    dir_to_analyze=$(jq -r '.benchmarks['$i'].dir_to_analyze' $prj_json)
    # if dir_to_analyze is @COMPILE_ARGS, then open $name/COMPILE_ARGS and each line is a file to analyze
    if [ "$dir_to_analyze" = "@COMPILE_ARGS" ]; then
      all_files=()
      while IFS= read -r line; do
        all_files+=($PROJECTS_DIR/$name/$line)
      done <"$PROJECTS_DIR/$name/COMPILE_ARGS"
    else
      all_files=($PROJECTS_DIR/$name/$dir_to_analyze**/*.java)
      all_files_no_dir=()
      for file in "${all_files[@]}"; do
        if [ -d "$file" ]; then
          continue
        else
          all_files_no_dir+=($file)
        fi
      done
      all_files=("${all_files_no_dir[@]}")
    fi
    exclude_dirs=$(jq -r '.benchmarks['$i'].exclude_dirs' $prj_json)
    if [ "$exclude_dirs" != "null" ]; then
      count_dirs=$(jq -r '.benchmarks['$i'].exclude_dirs | length' $prj_json)
      # exclude_dirs is a JSON array containg pairs of directory to exclude and the reason for excluding it.
      # e.g., [["/tmp", "For some reason"], ["/home", "For some other reason"]]
      for ((j = 0; j < $count_dirs; j++)); do
        dir=$(jq -r '.benchmarks['$i'].exclude_dirs['$j'].path' $prj_json)
        reason=$(jq -r '.benchmarks['$i'].exclude_dirs['$j'].motivation' $prj_json)
        # info="Excluding '$dir' because: $reason"
        # log_info
        log_message "Excluding '$dir' because: $reason" "info"
        # removing files from $all_files that starts with $dir
        for file in ${all_files[@]}; do
          if [[ $file == $PROJECTS_DIR/$name/$dir* ]]; then
            all_files=("${all_files[@]/$file/}")

          fi
        done
      done
    fi
    # Run the evaluation process.
    cp=$(jq -r '.benchmarks['$i'].classpath' $prj_json)
    modify_classpath $cp

    entryPackage=$(jq -r '.benchmarks['$i'].entryPackage' $prj_json)
    echo $entryPackage
    entryMethod=$(jq -r '.benchmarks['$i'].entryMethod' $prj_json)

    folder=$name
    iter=0

    elapsed=1
    # iterate over all INTRAJ_ALIASES and run the evaluation
    for jar in "${JARS[@]}"; do
      intraj=$jar
      # Removes the .jar at the end
      readable_name=${intraj%.*}
      log_message "RUNNING BENCHMARK ON PROJECT $name WITH $readable_name" "info"
      # Run the warmup evaluation and save the results to file
      #eval_warmup
      #echo "$results" >>$EVAL_DIR/$name"_"$readable_name"_warmup_results.new"

      eval_steady
     echo "$results" >>$EVAL_DIR/$name"_"$readable_name".new"
   done
 done

log_message "Evaluation finished" "info"
script_end_time=$(gdate +%s)

script_elapsed=$((script_end_time - script_start_time))

# Calculate the components of the duration
elapsed_days=$((script_elapsed / 86400))
elapsed_hours=$(( (script_elapsed % 86400) / 3600 ))
elapsed_minutes=$(( (script_elapsed % 3600) / 60 ))
elapsed_seconds=$((script_elapsed % 60))

script_elapsed_dhms=$(printf "%dd:%02dh:%02dm:%02ds" $elapsed_days $elapsed_hours $elapsed_minutes $elapsed_seconds)
elapsed_msg="Script time: $script_elapsed_dhms"
echo $elapsed_msg >> $STATS_FILE
log_message $elapsed_msg "info"

#python3 genlatex.py $EVAL_DIR 
