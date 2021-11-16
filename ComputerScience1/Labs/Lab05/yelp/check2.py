#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  9 13:15:20 2019

@author: jaredgridley
The Purpose of this program is to put the data from yelp into a list
and then manipulate that list of yealp restaurants in Rensselaer.

Part 2 will expand on this to create a user input part along with 
an analysis of the average score 
"""

import lab05_util
restaurants = lab05_util.read_yelp('yelp.txt')

entered_id = int(input('Enter the restaurant id ==> '))
actual_id = entered_id - 1



def print_info(restaurant):
    '''
    will organize and print the information about a restaurant
    
    >>> print_info(restaurants[0])
    Meka's Lounge (Bars)
        407 River Street
        Troy, NY 12180 
    Average Score: 3.86
    '''
    #Restaurant Header
    line1 = '{0} ({1})'.format(restaurant[0], restaurant[-2])
    print(line1)
    
    #Address Lines
    address_parts = restaurant[3].split("+")
    for segment in address_parts:
        print('\t{0}'.format(segment))
    
    #Rating
    if len(restaurant[-1]) > 3:
        avg = sum(restaurant[-1]) - max(restaurant[-1]) - min(restaurant[-1]) /(len(restaurant[-1]) - 2)
    else:
        avg = sum(restaurant[-1])/len(restaurant[-1])
    
    if avg <= 2:
        print('This restaurant is rated bad, based on {0} reviews.'.format(len(restaurant[-1])))
    elif avg > 2 and avg <= 3:
        print('This restaurant is rated average, based on {0} reviews.'.format(len(restaurant[-1])))
    elif avg > 3 and avg <=4:
        print('This restaurant is rated above average, based on {0} reviews.'.format(len(restaurant[-1])))
    else:
        print('This restaurant is rated very good, based on {0} reviews.'.format(len(restaurant[-1])))
    
 
if actual_id > len(restaurants):
    print("ID not valid!! Try again.")
else:
    print_info(restaurants[actual_id])