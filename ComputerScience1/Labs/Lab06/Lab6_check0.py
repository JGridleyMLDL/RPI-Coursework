#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 15 21:56:48 2019

@author: jaredgridley
These are the excersizes from checkpoint 0. 
"""

line = []
for num in range(0, 9):
    line.append(num)
#If rectangular then you need to make an length and with variable that will determine new indicies in your loops

#Big Square of coordinates
for num in line:
    if num%3 == 0:     #Uses the repeat number to split horizontally
        print(end = '\n')
    for j in line:
        if j % 3 ==0 and j != 0:     #Uses j to find where to split vertically
            print(end = ' ')
        print('{0},{1}'.format(num, j), end = ' ')
    print(end = '\n')

   
#Row 
row = []
for num in line:
    for j in line:
        if num == 2:
            row.append((num, j))
print(row)

#Column
column = []
for num in line:
    for j in line:
        if j == 5:
            column.append((num, j))
print(column)

#Box as a list
box = []
for num in line:
    for j in line:
        if num < 3 and j <3:
            box.append((num, j))
print(box)

#Box as a box
real_box = []
for num in line:
    for j in line:
        if num < 3 and j <3:
            print('{0},{1}'.format(num, j), end = ' ')
            real_box.append((num, j))
    print(end = '\n')
        
