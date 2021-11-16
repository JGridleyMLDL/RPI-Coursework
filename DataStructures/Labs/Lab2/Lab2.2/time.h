//
//  time.hpp
//  Lab2.2
//
//  Created by Jared Gridley on 1/22/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//
//This is the class file for the Time class - See Lab 2 Data Structures

#ifndef time_hpp
#define time_hpp

#include <stdio.h>

#endif /* time_hpp */


class Time{
public:
    //Default Constructor
    Time();
    //Parametrized Constructor
    Time(int h, int m, int s);
    
    //Accessors
    int getHour() const;
    int getMinute() const;
    int getSecond() const;
    
    //Modifiers
    void setHour(int h);
    void setMinute(int m);
    void setSecond(int s);
    
    //Functions
    void PrintAMPM();
    
private:
    int hour;
    int minute;
    int second;
    
};

bool IsEarlierThan(const Time& t1, const Time& t2);


