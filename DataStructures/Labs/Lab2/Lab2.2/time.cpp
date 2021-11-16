//
//  time.cpp
// Implementation file for the Time class
//
//  Created by Jared Gridley on 1/21/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <iostream>
#include "time.h"
#include <algorithm>
#include <iomanip>

Time::Time(){           //Default Constructor
    hour = 0;
    minute = 0;
    second = 0;
}

Time::Time(int h, int m, int s){           //Parametrized Constructor
    hour = h;
    minute = m;
    second = s;
}

int Time::getHour() const{
    return hour;
}

int Time::getMinute() const{
    return minute;
}

int Time::getSecond()const{
    return second;
}

void Time::setHour(int h){
    hour = h;
}
void Time::setMinute(int m){
    minute = m;
}
void Time::setSecond(int s){
    second = s;
}

void Time::PrintAMPM(){
    if (hour==24){
        hour =0;
    }
    if (hour<12){
        if(hour ==0){
            std::cout<<"12:"<<std::setfill('0')<<std::setw(2)<<minute<<":"<<std::setfill('0')<<std::setw(2)<<second<<" am"<<std::endl;
        }
        else
            std::cout<<hour<<":"<<std::setfill('0')<<std::setw(2)<<minute<<":"<<std::setfill('0')<<std::setw(2)<<second<<" am"<<std::endl;
    }
    else
        if(hour == 12){
            std::cout<<"12:"<<std::setfill('0')<<std::setw(2)<<minute<<":"<<std::setfill('0')<<std::setw(2)<<second<<" pm"<<std::endl;
        }
        else
            std::cout<<(hour-12)<<":"<<std::setfill('0')<<std::setw(2)<<minute<<":"<<std::setfill('0')<<std::setw(2)<<second<<" pm"<<std::endl;
}


bool IsEarlierThan(const Time& t1, const Time& t2){
    if(t1.getHour()<t2.getHour())
        return true;
    else if(t1.getHour()>t2.getHour())
        return false;
    else
        if(t1.getMinute()<t2.getMinute())
            return true;
        else if(t1.getMinute()>t2.getMinute())
            return false;
        else
            if(t1.getSecond()<t2.getSecond())
                return true;
            else
                return false;
}



