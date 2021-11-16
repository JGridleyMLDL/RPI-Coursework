#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 20:16:15 2019

@author: jaredgridley
Part three of lab 07
"""
fname = input('Please enter the file number ==> ')
print(fname)

parno = int(input('Please enter the paragraph number ==> '))
print(parno)

lineno = int(input('Please enter the line number ==> '))
print(lineno)


def parse_line(text_line):
    #parses text based on the given parameters
    #ASK ABOUT HOW TO MAKE THIS SHORTER
    if text_line.count('/') <3:
        return None
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
 


def get_line(fname, parno, lineno):
    f = open(fname+'.txt', encoding = 'utf8')
    o = f.read()
    paragraphs = o.split('\n\n' )
    
    para_number = paragraphs[parno-1]
    line = para_number.split('\n')
    
    line_number = line[lineno-1]
    
    return line_number.rstrip()
    


program = []

next_line = get_line(fname, parno, lineno)

while True:
    line = get_line(fname, parno, lineno)
    if line  == 'END/0/0/0':
        break
    
    parsed_line = parse_line(line)
    if parsed_line == None:
        print('There is a parsing error')
        break
    
    program.append(parsed_line[-1])
    
    fname = str(parsed_line[0])
    parno = parsed_line[1]
    lineno = parsed_line[2]
    
f_out = open('program.py', 'w')
for code in program:
    f_out.write(code + '\n')
    
f_out.close()    