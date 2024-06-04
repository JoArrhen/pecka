set -euo pipefail

if [[ -e out.csv ]]; then
    echo "out.csv already exists."
    exit 1
fi

file="../$1" 

cd probed
java antlr.Tool -debug $file > temp1
java antlr.Tool -html $file > temp2
java antlr.Tool -docbook $file > temp3
java antlr.Tool -trace $file > temp4
java antlr.Tool -diagnostic $file > temp5
java antlr.Tool -o out $file > temp6
#java antlr.Tool -o sdjfklasjdklf $file manually to get filenotfoundexception

cat temp* | grep ";" | sort | uniq > ../out.csv
rm temp*
cd ..
