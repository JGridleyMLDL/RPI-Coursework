#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 19:49:49 2019

@author: jaredgridley
The purpose of this program is to parse a file
"""

fname = input('Please enter the file number ==> ')
print(fname)

parno = input('Please enter the paragraph number ==> ')
print(int(parno))

lineno = input('Please enter the line number ==> ')
print(int(lineno))



def get_line(fname, parno, lineno):
    f = open(fname, encoding = 'utf8')
    o = f.read()
    paragraphs = o.split('\n\n' )
    
    para_number = paragraphs[int(parno)-1]
    line = para_number.split('\n')
    
    line_number = line[int(lineno)-1]
    
    return line_number.strip()
    

print(get_line(fname, parno, lineno))