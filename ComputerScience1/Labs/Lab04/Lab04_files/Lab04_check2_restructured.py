#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  2 13:50:05 2019

@author: jaredgridley
"""

def find_skew(name, times):
    avg = (sum(times))/5
    var = (times[0] - avg)**2 + (times[1] - avg)**2 + (times[2] - avg)**2 + (times[3] - avg)**2 + (times[4] - avg)**2
    var /= 5
    skew = (times[0] - avg)**3 + (times[1] - avg)**3 + (times[2] - avg)**3 + (times[3] - avg)**3 + (times[4] - avg)**3
    skew /= 5
    skew = skew/var**3**0.5
    return "{0}'s running times have a skew of {1:.2f}".format(name, skew)

def calc_stats(name, times):
    average = (sum(times)-(min(times)+max(times)))/(len(times)-2)
    return "{0}'s stats-- min: {1:.1f}, max: {2:.1f}, avg: {3:.1f}".format(name, min(times), max(times), average)

name_1 = "Stan"
## The following are Stan's 5 latest running times for 3 miles
stan_times = [34, 34, 35, 31, 29]
#print(find_skew(name_1, stan_times))
print(calc_stats(name_1, stan_times))


name_2 = "Kyle"
## The following are Kyle's 5 latest running times for 3 miles
kyle_times = [30, 31, 29, 29, 28]
#print(find_skew(name_2, kyle_times))
print(calc_stats(name_2, kyle_times))


name_3 = "Cartman"
## The following are Cartman's 5 latest running times for 3 miles
cartman_times = [36, 31, 32, 33, 33]
#print(find_skew(name_3, cartman_times))
print(calc_stats(name_3, cartman_times))


name_4 = "Kenny"
## The following are Kenny's 5 latest running times for 3 miles
kenny_times = [33, 32, 34, 31, 35]
#print(find_skew(name_4, kenny_times))
print(calc_stats(name_4, kenny_times))


name_5 = "Bebe"
## The following are Bebe's 5 latest running times for 3 miles
bebe_times = [27, 29, 29, 28, 30]
#print(find_skew(name_5, bebe_times))
print(calc_stats(name_5, bebe_times))
