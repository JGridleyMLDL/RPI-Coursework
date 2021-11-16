#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Sep 18 14:04:32 2019

@author: jaredgridley
The purpose of this program is to make a box around an entered input. 
"""

word = input("Enter a word: ")
length = len(word) + 6

print("*" * length)
print("** {0} **".format(word))
print("*" * length)