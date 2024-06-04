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

# Validate correct number of arguments
if [ "$#" -lt 2 ]; then
  log_message "Invalid number of arguments. Usage: test_method.sh <project_name> <bfpa arguments>" "error"
  log_message "Example invocation test_method.sh weka -printSignatures" "error"
  exit 1
fi

PROJECT_NAME=$1

PROJECTS_DIR="./benchmarkprojects"

prj_json="projects.json"

count=$(jq '.benchmarks | length' $prj_json)
executed_count=$(jq '[.benchmarks[] | select(.enable == true)] | length' $prj_json)


set -e
setopt EXTENDED_GLOB

function run_cmd() {
   declare -a cmd_args=("java")

   cmd_args+=("-Xmx24G")

   cmd_args+=("-jar" "../$JAR_PATH")

   cmd_args+=("${@:2}")

   [[ $entryPackage != "null" ]] && cmd_args+=("-entryPoint" "$entryPackage" "$entryMethod")
   [[ -n $classpath ]] && cmd_args+=("-classpath" "$classpath")
   cmd_args+=($all_files)

   "${cmd_args[@]}"
}


log_message "Compiling project" "info"
cd ../
./gradlew jar
cd evaluation

log_message "Count is $count" "info"
for ((i = 0; i < $count; i++)); do
  enable=$(jq -r ".benchmarks[$i].enable" $prj_json)

  name=$(jq -r '.benchmarks['$i'].name' $prj_json)
  if [[ $name != $PROJECT_NAME ]]; then
    echo "Skipping '$name' as it does not match '$PROJECT_NAME'"
    continue
  fi
  log_message "Running benchmark $name" "info"

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

  JAR_PATH=bfpa.jar
  # Removes the .jar at the end
  readable_name=${JAR_PATH%.*}
  log_message "RUNNING BENCHMARK ON PROJECT $name WITH $readable_name" "info"
  run_cmd "$@"
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
log_message $elapsed_msg "info"

