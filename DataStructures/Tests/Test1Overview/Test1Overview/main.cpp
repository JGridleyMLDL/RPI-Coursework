//
//  main.cpp
//  Test1Overview
//
//  Created by Jared Gridley on 2/3/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//


#include <iostream>
#include "date.h"
// array to figure out the number of days, it's used by the auxiliary function daysInMonth
const int DaysInMonth[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
Date::Date() { //default constructor
    day = 1;
    month = 1;
    year = 1900; }
int Date::getDay() const {
    return day;
}
bool Date::isLeapYear() const {
  return (year%4 ==0 && year % 100 != 0) || year%400 == 0;
}

 
