//
//  Hash_Vector.hpp
//  HW9
//
//  Created by Jared Gridley on 4/25/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#ifndef Hash_Vector_hpp
#define Hash_Vector_hpp

#include <stdio.h>
#include <iostream>
#include <list>
#include <string>
#include <map>
#include <fstream>
#include <vector>
#include "Query.hpp"
using namespace std;
typedef vector<pair<Query, list<string>* > > hash_table;


/*This is a class that handles all of the calcuations around creating the
 key value and hash index. */
class HashF{
public:
    HashF(){vector<int> i; permute_filters(0, i);}
    
    string create_key(list<string>*& l);        //Key: used in query class
    
    int create_hash(const string& s);           //Index for acessing in vector
    
private:
    friend class Hash_Vector;
    
    void permute_filters(int pos, std::vector<int> filter);
    vector<vector<int> > p;
    
    
};


//This class contains all of the functions related to the hash table (vector) itself
class Hash_Vector{
public:
    Hash_Vector(int t_size, float occ);
    Hash_Vector();

    bool insert(list<string>* l);
    
    int find(list<string>* l);
    
    void print(int index, map<string, string>& names);
    
    void resize_table(unsigned int new_size);
    
    //This is a helper function for printing
    void tokenize(string &str, char delim, vector<string>&out);
    
private:
    int table_size;
    float occupancy;
    int table_entries;
    HashF hash_calc;        //Allows access to HashF function with reconstructing "p"
    
    vector<pair<Query, list<string>* > > table;     //hash thable
    
};



#endif /* Hash_Vector_hpp */
