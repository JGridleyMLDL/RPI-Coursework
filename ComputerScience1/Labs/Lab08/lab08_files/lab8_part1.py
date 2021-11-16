#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 29 19:25:27 2019

@author: jaredgridley

"""
def get_words(description):
    #replaces all the required elements with spaces
    description = description.replace('.', ' ').replace(',', ' ').replace('(', ' ').replace(')', ' ').replace('"', ' ')
    
    words = description.split(' ')
    #Checks if there are more than 4 characters, and its all letters and then adds it to a set. 
    good_words = set()
    for word in words:
        word.lower()
        if len(word)>=4 and word.isalpha():
            good_words.add(word)
    return good_words

f=open('wrpi.txt', 'r')

print(get_words(f.read()))
