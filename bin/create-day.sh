#!/bin/bash

DIR_SRC="src/aoc"
DIR_TEST="test/aoc"
DIR_RESOURES="resources"
DIR_TEMPLATES="resources/templates"

YEAR="$1"
DAY="$2"

echo "year $1"
echo "day $2"

TEMPLATE_SOURCE="$DIR_TEMPLATES/day.clj.template"
TEMPLATE_TEST="$DIR_TEMPLATES/day_test.clj.template"

FILE_SOURCE="$DIR_SRC/$YEAR/$DAY.clj"
FILE_TEST="$DIR_TEST/$YEAR/${DAY}_test.clj"
FILE_INPUT="$DIR_RESOURES/$YEAR/$DAY.txt"

cp "$TEMPLATE_SOURCE" "$FILE_SOURCE"
cp "$TEMPLATE_TEST" "$FILE_TEST"
sed -i "s/\$YEAR/$YEAR/g" $FILE_SOURCE
sed -i "s/\$DAY/$DAY/g" $FILE_SOURCE
sed -i "s/\$YEAR/$YEAR/g" $FILE_TEST
sed -i "s/\$DAY/$DAY/g" $FILE_TEST
touch "$FILE_INPUT"

