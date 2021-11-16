//
//  Hash_Vector.cpp
//  HW9
//
//  Created by Jared Gridley on 4/25/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include "Hash_Vector.hpp"


typedef list<string>::const_iterator lst_itr;

//------ HashF function implementation ----------------------------------------
void HashF::permute_filters(int pos, std::vector<int> filter){
    if(pos == 6){
        p.push_back(filter);
        return;
    }

    filter.push_back(0);
    std::vector<int> filter_new = filter;
    filter_new.back() = 1;
    permute_filters(pos+1, filter_new);
    permute_filters(pos+1, filter);
}



string HashF::create_key( list<string>*& l){
    /*this function takes the possible combinations of the string and makes
     a string out of it*/
    string key = "";
    
    for(int i = 0; i<64; i++){
        lst_itr itr = l->begin();
        for(int j = 0; j<6; j++){
            if(p[i][j] == 1){
                string s = *itr;
                key+= s[0];
            }
            itr++;
        }
    }
    return key;
}


int HashF::create_hash(const string &key){
    unsigned int value = 0;
    for (unsigned int i=0; i<key.size(); ++i){
        value = (value*0.5) + key[i];  // conversion to int is automatic
    }
    return value;
}


//----- Hash_Vector Implementation -------------------------------------------


/*This is a helper function to seperate the string values from the list
 (second part of the pair in the hash table).*/
void Hash_Vector::tokenize(string &str, char delim, vector<string>&out){
    size_t start;
    size_t end = 0;

    //Adding string component (split at delim) to the vector.
    while((start = str.find_first_not_of(delim, end)) != string::npos){
        end = str.find(delim, start);
        out.push_back(str.substr(start, end - start));
    }
}

//Constructor for the Hash Table
Hash_Vector::Hash_Vector(int t_size, float occ){
    //Initializing the variables
    table_size = t_size;
    occupancy = occ;
    table_entries = 0;
    hash_calc = HashF();
    
    //Initializing the table values all to empty
    Query blank = Query();
    list<string>* l;
    hash_table first(t_size, make_pair(blank, l));
    
    table = first;
}

bool Hash_Vector::insert(list<string>* l){
    //First check if the hash table needs to be resized.
    if(table_entries >= (occupancy * table_size)){
        this->resize_table(2*table_size+1);       //Plus one for empty table
    }
    
    //Then create the key and its respective query object
    string key = hash_calc.create_key(l);
    Query q(key);
    
    //Calcuate the index to insert at
    int index = hash_calc.create_hash(key) % (table_size-1);
    
    //Linear probing to find and available index
    while(table[index].first.getPresent()){
        index+=1;
    }
    table[index].first = q;
    table[index].second = l;
    table_entries+=1;
    
    return true;
}


//Functions to calculate the index and find the matching movie data
int Hash_Vector::find(list<string>* l){
    //First calculate the key and index
    string key = hash_calc.create_key(l);
    int index = hash_calc.create_hash(key)%(table_size-1);
    
    //Checking each of the values//accounts for linear probing
    while(table[index].first.getPresent()){
        if(table[index].first.getData().find(key) != string::npos){
            return index;
        }
        index++;
    }
    
    return -1;      //Did not find a matching movie
}

//Function to resize the table after it has exceeded it occupancy
void Hash_Vector::resize_table(unsigned int new_size){
    vector<pair<Query, list<string>* > > old_table = table;
    table.clear();
    table.resize(new_size);
    table_size = new_size;
    
    //Recalculating and inserting values
    for(unsigned int i = 0; i <old_table.size(); i++){
        unsigned int index = hash_calc.create_hash(old_table[i].first.getData())%(table_size-1);
        table[index] = old_table[i];
    }
}


//Print function for the matching movies
void Hash_Vector::print(int index, map<string, string>& names){
    if(index == -1){cout<<"No results for query."<<endl;}
    else{
        cout << "Printing 1 result(s):" << endl;
        list<string>* data = table[index].second;
        
        //Printing out the title, year and runtime
        lst_itr itr = data->begin();
        for(int i = 0; i<3; i++){
            cout<<*itr<<endl;
            itr++;
        }
        
        //Printing out the genres (uses helper function)
        string genres = *itr;
        vector<string> genres_split;
        tokenize(genres, '.', genres_split);
        cout<<genres_split.size();
        for(int i = 0; i<genres_split.size(); i++){
            cout<<" "<< genres_split[i];
        }
        cout<<endl;
        
        //Printing out the actors and their roles (uses map and help function)
        itr++;
        string actors = *itr;
        vector<string> actors_split;
        tokenize(actors, '.', actors_split);
        
        itr++;
        string roles = *itr;
        vector<string> roles_split;
        tokenize(roles, '.', roles_split);
        
        cout<<actors_split.size();
        for(int i =0; i<actors_split.size(); i++){
            string name = names[actors_split[i]];
            cout<<" "<<name<<" ("<<roles_split[i]<<")";
        }cout<<endl;
    }
}






