//
//  PROBLEM2.cpp
//  TEST3
//
//  Created by Jared Gridley on 4/16/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <stdio.h>
#include <stdio.h>
#include <iostream>
#include <string>
using namespace std;


//Adapted from DJBX33A, simple hash algorithm.
int shelf_hash(int key, int num_of_shelves){
    int hash = 5; //Real implementations use a larger prime like 5381
    
    std::string s_key = std::to_string(key); // int to string
    int digits = s_key.length();
    for(int i=0; i<digits; i++){
        int digit = std::stoi(s_key.substr(i,1)); //string (size 1) to int
        
        //Add the numeric representation of the digit to the overall hash.
        hash = hash*2 + digit;
    }
    return hash % num_of_shelves;
}

//int main(int argc, const char * argv[]) {
//
//    cout<< shelf_hash(144, 10)<<endl;
//    cout<< shelf_hash(22, 10)<<endl;
//    cout<< shelf_hash(290, 10)<<endl;
//    cout<< shelf_hash(1008, 10)<<endl;
//    cout<< shelf_hash(1372, 10)<<endl;
//}

