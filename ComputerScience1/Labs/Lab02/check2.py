#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Sep 18 13:59:31 2019

@author: jaredgridley
The purpose of this program is to make a box around a 4 letter input
"""
word = input("Enter a four letter word: ")

print("*" * 10)
print("** {0} **".format(word))
print("*" * 10)
