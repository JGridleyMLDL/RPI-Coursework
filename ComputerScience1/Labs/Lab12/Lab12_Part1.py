"""
Created on Tue Dec 10 21:59:56 2019

@author: jaredgridley
"""


def add(m,n):
    if n == 0:
        return m
    else:
        return add(m,n-1) + 1


def mult(x,y):
    if y == 0:
        return 0
    elif y ==1:
        return x
    else:
        return add(mult(x, y-1), x)
    
def power(m,n):
    if n == 0:
        return 1
    elif n ==1:
        return m
    else:
        return mult(power(m, n-1), m)
    
print(add(5,3))
print(mult(8, 3))
print(power(6, 3))


