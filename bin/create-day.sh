#!/bin/bash

DIR_ROOT="$(readlink -sf "$(dirname "${BASH_SOURCE[0]}")/../")"

DIR_SRC="$DIR_ROOT/src/aoc"
DIR_TEST="$DIR_ROOT/test/aoc"
DIR_RESOURCES="$DIR_ROOT/resources"
DIR_TEMPLATES="$DIR_RESOURCES/templates"
FILE_SESSION="$DIR_ROOT/aoc_session.txt"

YEAR="$1"
DAY="$2"

if [ -z "$YEAR" -o -z "$DAY" -o "${#YEAR}" -ne 4 -o "$DAY" -lt 1 -o "$DAY" -gt 25 ]; then
  echo "usage ${BASH_SOURCE[0]} <year> <day> (1-25)"
  exit 1
fi

printf -v DAY_ZEROPAD "%02d" $DAY
echo "generating skeleton for $YEAR - day $DAY"

TEMPLATE_SOURCE="$DIR_TEMPLATES/day.clj.template"
TEMPLATE_TEST="$DIR_TEMPLATES/day_test.clj.template"

FILE_SOURCE="$DIR_SRC/$YEAR/$DAY_ZEROPAD.clj"
FILE_TEST="$DIR_TEST/$YEAR/${DAY_ZEROPAD}_test.clj"
FILE_INPUT="$DIR_RESOURCES/$YEAR/$DAY_ZEROPAD.txt"

function replace_variables {
  sed -i "s/\$YEAR/$YEAR/g" $1
  sed -i "s/\$DAY/$DAY_ZEROPAD/g" $1
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

if [ ! -s "$FILE_INPUT" ]; then
  mkdir -p "$DIR_RESOURCES/$YEAR"
  if [ -s "$FILE_SESSION" ]; then
    URL="https://adventofcode.com/$YEAR/day/$DAY/input"
    echo "$FILE_SESSION exists, downloading $URL > $FILE_INPUT"
    SESSION_KEY="$(cat "$FILE_SESSION")"
    echo "$SESSION_KEY"
    curl --cookie "session=$SESSION_KEY" "$URL" -o "$FILE_INPUT"
  else
    echo "$FILE_SESSION does not exist, creating empty $FILE_INPUT"
    touch "$FILE_INPUT"
  fi
else
  echo "$FILE_INPUT already exists"
fi

