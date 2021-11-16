#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  2 14:35:54 2019

@author: jaredgridley
"""

bpop = int(input("Number of bunnies ==> "))
print(bpop)
int(bpop)

fpop = int(input("Number of foxes ==> "))
print(fpop)

def bpop_next(bpop, fpop):
    return int((10*bpop)/(1+0.1*bpop) - 0.05*bpop*fpop)


def fpop_next(bpop, fpop):
    return int(0.4 * fpop + 0.02 * fpop * bpop)


print("Year 1: {0} {1}".format(bpop, fpop))

bpopnext = max(0, bpop_next(bpop, fpop))
fpopnext = max(0, fpop_next(bpop, fpop))
print("Year 2: {0} {1}".format(int(bpopnext), int(fpopnext)))

bpop = max(0, bpop_next(bpopnext, fpopnext))
fpop = max(0, fpop_next(bpopnext, fpopnext))
print("Year 3: {0} {1}".format(int(bpop), int(fpop)))

bpopnext = max(0, bpop_next(bpop, fpop))
fpopnext = max(0, fpop_next(bpop, fpop))
print("Year 4: {0} {1}".format(int(bpopnext), int(fpopnext)))
        
bpop = max(0, bpop_next(bpopnext, fpopnext))
fpop = max(0, fpop_next(bpopnext, fpopnext))
print("Year 5: {0} {1}".format(int(bpop), int(fpop)))
