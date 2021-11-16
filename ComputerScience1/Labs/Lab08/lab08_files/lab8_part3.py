#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct 30 13:43:10 2019

@author: jaredgridley
"""

file1 = input('Enter your filename: ')

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

# Gets the words for the file chosen my you
f=open(file1, 'r').read()
f_words = get_words(f)

#Gets the title for the club to be used in the if statement to check if the club is the exact same. 
f_title = f.split('|')
f_title_only = f_title[0]

#Gets the words for all the clubs in allclubs.txt and compares them to the given club and stores the difference in a list
differences = []
s = open('allclubs.txt', 'r').read()
for club in (s.split('\n')):
    name = club.split('|')
    if club != f:
        club_words = get_words(club)
        similar_words = f_words & club_words
        #This is getting the name of the club
        differences.append((len(similar_words), name[0]))


#This sorts the list and then prints the 5 most similar clubs
differences.sort(reverse=True)
print(differences[1:6])

        
    
