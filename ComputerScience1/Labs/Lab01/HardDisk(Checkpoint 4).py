#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Sep 11 14:03:54 2019

@author: jaredgridley

The purpose of this program is to find the differece in storage that my hard drive has.
"""
import math
base10size = int(input("Disk size in GB => "))
print(base10size)

base2size = (base10size * pow(10,9)) / pow(2, 30)

lost_size = base10size - math.trunc(base2size)

out_string = "{0:.0f} GB in base 10 is actually {1:.0f} GB in base 2, {2:.0f} GB less than advertised.".format(base10size, math.trunc(base2size), lost_size)
print(out_string)

print("Input:  " + ("*" * int(base10size)))
print("Actual: " + ("*" * math.trunc(base2size)))
