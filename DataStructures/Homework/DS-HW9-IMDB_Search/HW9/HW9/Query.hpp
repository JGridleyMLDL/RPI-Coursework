//
//  Query.hpp
//  HW9
//
//  Created by Jared Gridley on 4/25/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#ifndef Query_hpp
#define Query_hpp
#include <string>
#include <stdio.h>
using namespace std;

//This is a query class, used as the first part of the pair in the hash table
class Query{
public:
    //Constructors
    Query();
    Query(string d);
    
    //Acessors
    string getData();
    bool getPresent();
    
private:
    string data;
    bool present;
    
};
#endif /* Query_hpp */
