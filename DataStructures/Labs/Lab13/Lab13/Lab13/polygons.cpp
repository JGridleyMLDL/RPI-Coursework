//
//  polygons.cpp
//  Lab13
//
//  Created by Jared Gridley on 4/29/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <stdio.h>
#include "polygons.h"
using namespace std;


//----- Polygon Implementation --------------------------

Polygon::Polygon(string name, vector<Point> points){
    if(points.size() >2){
        n = name;
        pts = points;
    }
    else{
        int i = -1;
        throw i;
    }
}

string Polygon::getName() const{
    return n;
}

bool Polygon::HasAllEqualSides(){
    vector<double> sides;
    for(int i =0; i<(pts.size()-1); i++){
        sides.push_back(DistanceBetween(pts[i], pts[i+1]));
    }
    sides[pts.size()-1] = DistanceBetween(pts[0], pts[pts.size()-1]);
    
    for(int i = 0; i<(sides.size()-1); i++){
        if(!EqualSides(sides[i], sides[i+1])){
            return false;
        }
    }
    return true;
    
}



//----- Triangle Implementation -------------------------

Triangle::Triangle(string name, vector<Point> points) : Polygon(name, points){
    if(points.size() == 3){
        n = name;
        pts = points;
    }
    else{
        int i = -1;
        throw i;
    }
}


//----- Quadrilateral Implementation --------------------
Quadrilateral::Quadrilateral(string name, vector<Point> points) : Polygon(name, points){
    if(points.size() == 4){
        n = name;
        pts = points;
    }
    else{
        int i = -1;
        throw i;
    }
}

//----- Isosceles Implementation ------------------------
IsoscelesTriangle::IsoscelesTriangle(string name, vector<Point> points) : Triangle(name, points){
    //Check number of points and if two angles are the same size.
    
//Distances for the sides:
    double a_b = DistanceBetween(points[0], points[1]);
    double b_c = DistanceBetween(points[1], points[2]);
    double a_c = DistanceBetween(points[0], points[2]);
    
    if(EqualSides(a_b, b_c)||EqualSides(b_c, a_c)||EqualSides(a_c, a_b)){
        n = name;
        pts = points;
    }
    else{
        int i = -1;
        throw i;
    }
}



//----- Equilateral Implementation ----------------------
EquilateralTriangle::EquilateralTriangle(string name, vector<Point> points) : Triangle(name, points){
    double a_b = DistanceBetween(points[0], points[1]);
    double b_c = DistanceBetween(points[1], points[2]);
    double a_c = DistanceBetween(points[0], points[2]);
    
    if(EqualSides(a_b, b_c) && EqualSides(a_b, a_c)){
        n = name;
        pts = points;
    }
    else{
        int i = -1;
        throw i;
    }
}


//----- Rectangle Implementation ------------------------
Rectangle::Rectangle(string name, vector<Point> points) : Quadrilateral(name, points){
    double a_b = DistanceBetween(points[0], points[1]);
    double b_c = DistanceBetween(points[1], points[2]);
    double c_d = DistanceBetween(points[2], points[3]);
    double d_a = DistanceBetween(points[3], points[0]);
    
    if(EqualSides(a_b, c_d)&&EqualSides(b_c, d_a)){
        name = n;
        pts = points;
    }else{
        throw -1;
    }
}



//----- Square Implementation
Square::Square(string name, vector<Point> points) : Rectangle(name, points){
    double a_b = DistanceBetween(points[0], points[1]);
    double b_c = DistanceBetween(points[1], points[2]);
    double c_d = DistanceBetween(points[2], points[3]);
    double d_a = DistanceBetween(points[3], points[0]);
    
    if(EqualSides(a_b, b_c)&&EqualSides(b_c, c_d)&&EqualSides(c_d, d_a)){
        n = name;
        pts = points;
    }else{
        throw -1;
    }
}
