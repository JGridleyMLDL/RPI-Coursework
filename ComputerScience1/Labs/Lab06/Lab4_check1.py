#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 15 22:34:27 2019

@author: jaredgridley
The purpose of this program is to represent and build the outline of a sudoku board.
"""


# bd from Check1.py 
bd = [ [ '1', '.', '.', '.', '2', '.', '.', '3', '7'],
       [ '.', '6', '.', '.', '.', '5', '1', '4', '.'],
       [ '.', '5', '.', '.', '.', '.', '.', '2', '9'],
       [ '.', '.', '.', '9', '.', '.', '4', '.', '.'],
       [ '.', '.', '4', '1', '.', '3', '7', '.', '.'],
       [ '.', '.', '1', '.', '.', '4', '.', '.', '.'],
       [ '4', '3', '.', '.', '.', '.', '.', '1', '.'],
       [ '.', '1', '7', '5', '.', '.', '.', '8', '.'],
       [ '2', '8', '.', '.', '4', '.', '.', '.', '6'] ]

#print(len(bd)) Height
#print(len(bd[0])) Length


#Big Square of coordinates
for i in range(0, len(bd)):
    if i%3 == 0:     #Uses the repeat number to split horizontally
        print(25 * '-')
    print('|', end = ' ')    
        
    for j in range(0, len(bd[i])):
        if j % 3 ==0 and j != 0:     #Uses j to find where to split vertically
            print('|', end = ' ')  
        print(bd[i][j], end = ' ') #Prints the value at inex i,j
    print('|')
print('-' *25)
