#!/usr/bin/env python

import sys

data = None
if len(sys.argv) >= 2:
    data = sys.argv[1].strip()
else:
    # data = open('09.txt').readline().strip()
    data = open('09_test.txt').readline().strip()
    data = data.replace("!!", "")

in_g = False

group_nest_level = 0
gc = 0

score = 0

count = 0

def not_bang(c):
    return c != "!"

for i in range(0, len(data)):
    c = data[i]
    prev = data[i-1] if i > 0 else ""
    if prev == "!":
        continue

    if in_g and not_bang(c) and not_bang(prev) and c != '>':
        print c
        count+=1
    if c == '<' and not in_g:
        print "in garbage"
        in_g = True
    if c == '>' and in_g:
        print "out of garbage"
        in_g = False
    if c == '{' and not_bang(prev) and not in_g:
        gc += 1
        group_nest_level += 1
        score += group_nest_level
        print "going down", group_nest_level
    if c == '}' and not_bang(prev) and not in_g:
        group_nest_level -= 1
        print "going up", group_nest_level

print gc, score, count
