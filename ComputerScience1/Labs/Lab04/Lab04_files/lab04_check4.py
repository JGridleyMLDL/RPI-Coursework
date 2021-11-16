#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  2 14:26:13 2019

@author: jaredgridley
The purpose of this program is to count the population of bunnies and foxes over time.
"""

bpop = int(input("Number of bunnies ==> "))
print(bpop)

fpop = int(input("Number of foxes ==> "))
print(fpop)

bpop_next = (10*bpop)/(1+0.1*bpop) - 0.05*bpop*fpop
fpop_next = 0.4 * fpop + 0.02 * fpop * bpop

print("year 1: {0} {1}".format(bpop, fpop))
print("year 2: {0} {1}".format(int(bpop_next), int(fpop_next)))

