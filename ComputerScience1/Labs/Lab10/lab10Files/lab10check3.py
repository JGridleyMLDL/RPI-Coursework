#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Nov 13 13:27:38 2019

@author: jaredgridley
"""

from Date import Date
from Date import month_names


def most_common(lst):
    return (max(set(lst), key = lst.count))

birthday_lst = []
months_common = []

for line in open('birthdays.txt'):
    line = line.strip().split(' ')
    month = int(line[1])
    months_common.append(month)
    d = Date(line[0], line[1], line[2])
    birthday_lst.append(str(d))

sorted_bdays = sorted(birthday_lst, reverse = True)

frequent_month = most_common(months_common)

print('The earliest birthday is: {0}'.format(sorted_bdays[-1]))
print('The latest birthday is: {0}'.format(sorted_bdays[0]))
print('The most frequent month is: {0}'.format(month_names[frequent_month]))