#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 19:20:18 2019

@author: jaredgridley
The purpose of this to write a functiont that parses text
"""

def parse_line(text_line):
    #parses text based on the given parameters
    #ASK ABOUT HOW TO MAKE THIS SHORTER
    
    new_text = text_line.split('/')
    
    second_split = []
    second_split.insert(0, new_text.pop(-1))
    second_split.insert(0, new_text.pop(-1))
    second_split.insert(0, new_text.pop(-1))
    
    text = '/'.join(new_text)
    
    numbers = []
    if len(second_split) >= 3:
        for num in second_split:
            if num.isnumeric():
                numbers.append(int(num))
            else:
                return None
    else:
        return None
    numbers.append(text)
    return tuple(numbers)
 

print(parse_line('Here is some random text, like 5/4=3./12/3/4'))