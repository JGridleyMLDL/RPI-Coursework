//
//  test_rectangle.cpp
//  Lab3
//
//  Created by Jared Gridley on 1/29/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//
#include "Rectangle.cpp"
#include "Rectangle.h"
#include "Point2D.h"
using namespace std;

int main(){
    //Creating new points (5)
    Point2D pt1(0,0);
    Point2D pt2(10,10);
    Point2D pt3(4,7);
    Point2D pt4(1,2);
    Point2D pt5(11, 13);
    
    //Creating a new rectange to test the constructor.
    Rectangle rect1(pt1, pt2);
    Rectangle rect2(pt1, pt3);
    
    
    //Testing the is_point_within function.
    if (rect1.is_point_within(pt4)){
        cout<<"It's inside"<<endl;
    }
    rect1.is_point_within(pt5);
    
    //Testing the points in both function.
    vector<Point2D> both_rects;
    both_rects = points_in_both(rect1, rect2);
    for(int i = 0; i<both_rects.size(); i++){
        cout<<"("<<both_rects[i].x()<<","<<both_rects[i].y()<<")"<<endl;
    }
    
    //testing the print rectangle function.
    print_rectangle(rect1);
    
}
