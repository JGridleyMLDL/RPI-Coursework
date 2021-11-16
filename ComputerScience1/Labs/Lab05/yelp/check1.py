#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  9 13:15:20 2019

@author: jaredgridley
The Purpose of this program is to put the data from yelp into a list
and then manipulate that list of yealp restaurants in Rensselaer. 
"""

import lab05_util

restaurants = lab05_util.read_yelp('yelp.txt')

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
    avg = sum(restaurant[-1])/len(restaurant[-1])
    print("Average Score: {0:.2f}\n".format(avg))
    

print_info(restaurants[0])

print_info(restaurants[4])

print_info(restaurants[42])