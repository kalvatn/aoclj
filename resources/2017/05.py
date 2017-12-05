#!/usr/bin/env python

# import timeit

x = [ int(x) for x in open("05.txt").readlines()]
i = 0
steps = 0


# start_time = timeit.default_timer()
while i >= 0 and i < len(x):
    v = x[i]
    ni = i + v
    # nv = v + 1
    nv = v - 1 if v >= 3 else v + 1

    x[i] = nv
    i = ni
    steps += 1

# elapsed = timeit.default_timer() - start_time
print steps

