#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Sep 18 14:30:10 2019

@author: jaredgridley
"""

first_name = input("Please enter your first name: ")
last_input = input("Please enter your last name: ")
last_name = last_input + "!"

if len(first_name) > 6 and len(first_name) > len(last_name):
    word_length = len(first_name) + 6

    

elif len(last_name) > 6 and len(last_name) > len(first_name):
    word_length = len(last_name) + 6

    
else:
    word_length = 12
 

hello_space = " " * ((word_length - (12)))
first_space = " " * ((word_length - (len(first_name) + 6)))
last_space = " " * ((word_length - (len(last_name) + 6)))

print("*" * (word_length))
print("** Hello,{4} **\n** {0}{1} **\n** {2}{3} **".format(first_name, first_space, last_name, last_space, hello_space))
print("*" * (word_length))
