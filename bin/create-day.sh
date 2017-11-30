#!/bin/bash

DIR_SRC="src/aoc"
DIR_TEST="test/aoc"
DIR_RESOURCES="resources"
DIR_TEMPLATES="resources/templates"

YEAR="$1"
DAY="$2"

if [ -z "$YEAR" -o -z "$DAY" -o "${#YEAR}" -ne 4 -o "${#DAY}" -ne 2 ]; then
  echo "usage <YYYY> <DD>"
  exit 1
fi

TEMPLATE_SOURCE="$DIR_TEMPLATES/day.clj.template"
TEMPLATE_TEST="$DIR_TEMPLATES/day_test.clj.template"

FILE_SOURCE="$DIR_SRC/$YEAR/$DAY.clj"
FILE_TEST="$DIR_TEST/$YEAR/${DAY}_test.clj"
FILE_INPUT="$DIR_RESOURCES/$YEAR/$DAY.txt"

function replace_variables {
  sed -i "s/\$YEAR/$YEAR/g" $1
  sed -i "s/\$DAY/$DAY/g" $1
}

if [ ! -f "$FILE_SOURCE" ]; then
  mkdir -p "$DIR_SRC/$YEAR"
  cp "$TEMPLATE_SOURCE" "$FILE_SOURCE"
  replace_variables $FILE_SOURCE
  echo "created $FILE_SOURCE"
else
  echo "$FILE_SOURCE already exists"
fi

if [ ! -f "$FILE_TEST" ]; then
  mkdir -p "$DIR_TEST/$YEAR"
  cp "$TEMPLATE_TEST" "$FILE_TEST"
  replace_variables $FILE_TEST
  echo "created $FILE_TEST"
else
  echo "$FILE_TEST already exists"
fi

if [ ! -f "$FILE_INPUT" ]; then
  mkdir -p "$DIR_RESOURCES/$YEAR"
  touch "$FILE_INPUT"
  echo "created $FILE_INPUT"
else
  echo "$FILE_INPUT already exists"
fi

