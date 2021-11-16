#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct 16 14:02:04 2019

@author: jaredgridley
The purpose fo this checkpoint is to chekc to see if a nuber would work in a place according to sudoku rules
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

def ok_to_add(coordinate, c):
    row = coordinate[0]
    column = coordinate[1]
    
    #may need to define our add functions here if they don't work
    
    if (int(c)-1) < 9 and row < 9 and column < 9: #Verifying that the value is valid
        
        for i in bd[row]: #Checking the rows for repeats. 
            if str(c) == i:
                row_add = False
                break
            else:
                row_add = True
                
        j = 0 #Checking the columns and for the value
        while j < 9:
            if str(c) == bd[j][column]: #Compares the value to the already placed value
                column_add = False
                break
            else:
                column_add = True
            j += 1
        
        #This is checking the box. First locating the box their coordinate is in
        if row < 3:
            if column < 3:  #Checks for box (1,1)
                k = 0
                while k < 4:
                    for l in range(0, 3):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
            
            elif column > 2 and column < 6: #Checks for box (2,1)
                k = 4
                while k < 7:
                    for l in range(0, 3):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
                    
            elif column > 5 and column < 9:  #Checks for box (3,1)
                k = 0
                while k < 9:
                    for l in range(0, 3):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
                    
        elif row > 2 and row < 6:
            if column < 3:  #Checks for box (1, 2)
                k = 0
                while k < 4:
                    for l in range(3, 6):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
            elif column > 2 and column < 6: #Checks for box (2,2)
                k = 4
                while k < 7:
                    for l in range(3, 6):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
                    
            elif column > 5 and column < 9:  #Checks for box (3,2)
                k = 0
                while k < 9:
                    for l in range(3, 6):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
        
        else:
            if column < 3:  #Checks for box (1, 3)
                k = 0
                while k < 4:
                    for l in range(6, 9):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
            elif column > 2 and column < 6: #Checks for box (2,3)
                k = 4
                while k < 7:
                    for l in range(6, 9):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
                    
            elif column > 5 and column < 9:  #Checks for box (3,3)
                k = 0
                while k < 9:
                    for l in range(6, 9):
                        if str(c) == bd[k][l]:
                            box_add = False
                            break
                        else:
                            box_add = True
                    k+=1
            
        if column_add == True and row_add == True and box_add == True:  #Final Analysis of the if the value can be placed
            return "it works"
        else:
            return 'It does not work'
    else:
        return 'Invalid values, max value is 9'
    
print(ok_to_add((1,8), 2))



'''
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

'''