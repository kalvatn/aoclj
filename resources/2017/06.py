#!/usr/bin/env python
import time

x = [int(x) for x in open('06.txt').readline().split("\t")]
# x = [0, 2, 7, 0]

states = set()

def redistribute(x, index_highest):
    to_redistribute = x[index_highest]
    x[index_highest] = 0
    i = 1
    while to_redistribute > 0:
        x[(index_highest + i) % len(x)] += 1
        to_redistribute -= 1
        i+=1

def add_state(x):
    states.add(" ".join(str(y) for y in x))

steps = 0
while True:



    add_state(x)
    highest = max(x)
    index_highest = x.index(highest)

    # print highest, index_highest
    redistribute(x, index_highest)
    steps += 1


    # print states
    as_str = " ".join(str(y) for y in x)

    # print x

    if as_str in states:
        print steps
        print len(states), list(states).index(as_str)
        print len(states) - list(states).index(as_str)
        break

