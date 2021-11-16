//
//  main.cpp
//  Lab9
//
//  Created by Jared Gridley on 4/1/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//
#include <iomanip>
#include <fstream>
#include <map>
#include <string>
#include <algorithm>
#include <iostream>
using namespace std;

typedef map< int, int> mode;

int main(int argc, const char * argv[]) {
    if(argc < 2){
        cout << "Incorrect usage. 1 argument required: input file" << endl;
    }
    
    std::ifstream infile(argv[1]);
    if(!infile){
        std::cerr << "Failed to open input " << argv[2] << " for reading." << std::endl;
    }
    
    map< int, int> find_mode;
    
    string integer;
    int real_int;
    int max_occurances = 0;
    while(infile >> integer){
        real_int = stoi(integer);
        mode::iterator it = find_mode.find(real_int);
        if(it == find_mode.end()){
            find_mode.insert(make_pair(real_int, 1));
        }
        else{
            it->second +=1;
            max_occurances = max(max_occurances, it->second);
        }
    }
    
    for(mode::iterator itr = find_mode.begin(); itr!= find_mode.end(); itr++){
        if(itr->second == max_occurances){
            cout << itr->first << endl;
        }
    }
    
}
