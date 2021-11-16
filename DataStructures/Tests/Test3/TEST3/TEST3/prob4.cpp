//
//  prob4.cpp
//  TEST3
//
//  Created by Jared Gridley on 4/16/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <stdio.h>
#include <stdio.h>
#include <iostream>
#include <string>
#include <map>
using namespace std;

/////////////////TYPEDEFS IN GLOBAL SCOPE///////////////////////
//You can use these typedefs in your solutions
//Used in Parts 1-3
typedef std::map<int, std::string> TimeMap;
typedef std::map<int, TimeMap> CameraMap;

//Used in Part 4
typedef std::map<CTData, std::string> ClassMap;


bool operator< (const CTData& a, const CTData& b){
    if(a.getCamera()==b.getCamera()){
        if(a.getTime == b.getTime()){
            return false;
        }
        return a.getTime < b.getTime();
    }
    else{
        return a.getCamera() < b.getCamera()
    }
}


void InsertA(CameraMap &data, int a, int b, string &image){
    data[a][b] = image;
}





void InsertB(CameraMap &data, int id, int t, string &image){
    CameraMap::iterator itr = data.find(id);
    if(itr == data.end()){
        //Insert
        data.insert(make_pair(id, make_pair(t, image)));
    }
    else{
        //alread exists
    }
    
}

std::pair<int, string> Timemap_pair(CTData & data){
    
}




std::pair<bool, std::string> FindData( CameraMap &data, int id, int t){
    CameraMap::iterator itr = data.find(id);
    if(itr == data.end()){              //Id not found
        return make_pair(false, "");
    }
    
    TimeMap::iterator t_itr = itr->second.find(t);
    if(t_itr == itr->second.end()){
        return make_pair(false, "");    //Time not found
    }
    else{
        return make_pair(true, t_itr->second);
    }
}






////////////////THIS CODE IN MAIN()////////////////////////////
int main(){
    CameraMap data_brackets;
    InsertA(data_brackets, 5, 3, "AAA");
    InsertA(data_brackets, 5, 1, "BBB");
    InsertA(data_brackets, 9, 8, "CCC");
    InsertA(data_brackets, 2, 7, "DDD");
    InsertA(data_brackets, 4, 6, "EEE");
    std::cout << "Insertion by Brackets looks like: " << std::endl;
    PrintByCameraTime(data_brackets);
    std::cout << std::endl;
    
    /* Output from the PrintByCameraTime:
     Insertion by Brackets looks like:
     2 7 DDD
     4 6 EEE
     5 1 BBB
     5 3 AAA
     9 8 CCC  */
    
    CameraMap data_insert;
    InsertB(data_insert, 5, 3, "AAA");
    //Other InsertB calls excluded to save space
    
    std::pair<bool, std::string> find_result = FindData(data_brackets,4,6);
    if(find_result.first){
        std::cout << "Result of camera 4 time 6 is: " << find_result.second << std::endl;
    }
    else{
        std::cout << "Could not find camera 4 time 6." << std::endl;
    }
    
    //Code for Part 4
    ClassMap data_class;
    data_class.insert(std::make_pair(CTData(5, 3), "AAA"));
    data_class.insert(std::make_pair(CTData(5, 1), "BBB"));
    data_class.insert(std::make_pair(CTData(9, 8), "CCC"));
    data_class.insert(std::make_pair(CTData(2, 7), "DDD"));
    data_class.insert(std::make_pair(CTData(4, 6), "EEE"));
    //do not implement, should have same ordering as data_brackets/data_insert
    PrintByCameraTime(data_class);
}
