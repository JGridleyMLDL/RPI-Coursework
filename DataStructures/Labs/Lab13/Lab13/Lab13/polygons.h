//
//  polygons.h
//  Lab13
//
//  Created by Jared Gridley on 4/29/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#ifndef polygons_h
#define polygons_h
#include <string>
#include <vector>
#include "utilities.h"
using namespace std;

#endif /* polygons_h */


class Polygon{
public:
    Polygon(string name, vector<Point> points);
    virtual string getName()const;
    virtual bool HasAllEqualSides();
    
protected:
    string n;
    vector<Point> pts;
    
};

class Triangle : public Polygon{
public:
    Triangle(string name, vector<Point> points);
    
    
protected:
    string n;
    vector<Point> pts;
};


class Quadrilateral : public Polygon{
public:
    Quadrilateral(string name, vector<Point> points);
    
    
protected:
    string n;
    vector<Point> pts;
};

class IsoscelesTriangle : public Triangle{
public:
    IsoscelesTriangle(string name, vector<Point> points);
    
protected:
    string n;
    vector<Point> pts;
    
};

class EquilateralTriangle : public Triangle{
public:
    EquilateralTriangle(string name, vector<Point> points);
    
protected:
    string n;
    vector<Point> pts;
    
};

class Rectangle : public Quadrilateral{
    public:
        Rectangle(string name, vector<Point> points);
        
    protected:
        string n;
        vector<Point> pts;
};

class Square : public Rectangle{
public:
    Square(string name, vector<Point> points);
    
protected:
    string n;
    vector<Point> pts;
};
