#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 29 19:55:14 2019

@author: jaredgridley
"""

file1 = input('Enter the filename: ')
file2 = input('Enter the second filename: ')

def get_words(description):
    #replaces all the required elements with spaces
    description = description.replace('.', ' ').replace(',', ' ').replace('(', ' ').replace(')', ' ').replace('"', ' ')
    
    words = description.split(' ')
    #Checks if there are more than 4 characters, and its all letters and then adds it to a set. 
    good_words = set()
    for word in words:
        word.strip().lower()  #Ask the TA how to make this more efficient !!!!!!!!!!!
        if len(word)>=4 and word.isalpha():
            good_words.add(word.lower())
    return good_words

f=open(file1, 'r')

s=open(file2,'r')

f_words = get_words(f.read())
s_words = get_words(s.read())

same_words = f_words & s_words

unique_to_f = f_words - s_words

unique_to_s = s_words - f_words

print('Comparing clubs {0} and {1}'.format(file1, file2), end = '\n\n')
print('Same words: {0}'.format(same_words), end = '\n\n')
print('Unique to {0}: {1}'.format(file1, unique_to_f), end = '\n\n')
print('Unique to {0}: {1}'.format(file2, unique_to_s), end = '\n\n')
