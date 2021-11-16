//
//  main.cpp
//  HW9
//
//  Created by Jared Gridley on 4/25/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <string>
#include "Hash_Vector.hpp"
#include "Query.hpp"
using namespace std;


list<string>* make_movie(ifstream& file, string& movie_title){
    list<string>* l = new list<string>;
    if(movie_title!="?"){l->push_back(movie_title);}
    
    string movie_year, movie_time, num, list_data;
    string actors = ""; string genres = "";     string roles = "";
    
    file >> movie_year;                            //Movie Year
    if(movie_year!="?"){l->push_back(movie_year);}
    
    file >> movie_time;                            //Movie time
    if(movie_time!="?"){l->push_back(movie_time);}
    
    file >> num;                                   //Movie genre(s)
    for(int i = 0; i< stoi(num); ++i){
        file >> list_data;
        genres+=".";
        genres+=list_data;
    }
    l->push_back(genres);
    
    file >> num;                                   //Movie actors
    for(int i = 0; i< stoi(num); ++i){
        file >> list_data;
        actors += ".";
        actors+=list_data;
    }
    l->push_back(actors);
    
    file >> num;                                   //Movie roles
    for(int i = 0; i< stoi(num); ++i){
        file >> list_data;
        roles+=".";             //So I can split it again later
        roles+=list_data;
    }
    l->push_back(roles);
    
    return l;
}

list<string>* make_movie(string& movie_title){
    list<string>* l = new list<string>;
    if(movie_title!="?"){l->push_back(movie_title);}
    
    string movie_year, movie_time, num, list_data;
    string actors = ""; string genres = "";     string roles = "";
    
    cin >> movie_year;                            //Movie Year
    if(movie_year!="?"){l->push_back(movie_year);}
    
    cin >> movie_time;                            //Movie time
    if(movie_time!="?"){l->push_back(movie_time);}
    
    cin >> num;                                   //Movie genre(s)
    for(int i = 0; i< stoi(num); ++i){
        cin >> list_data;
        genres+=".";
        genres+=list_data;
    }
    l->push_back(genres);
    
    cin >> num;                                   //Movie actors
    for(int i = 0; i< stoi(num); ++i){
        cin >> list_data;
        actors += ".";
        actors+=list_data;
    }
    l->push_back(actors);
    
    cin >> num;                                   //Movie roles
    for(int i = 0; i< stoi(num); ++i){
        cin >> list_data;
        roles+=".";             //So I can split it again later
        roles+=list_data;
    }
    l->push_back(roles);
    
    return l;
}


void read_movies(string& file, Hash_Vector& table){
    //----- Checking if the file can be read -----
    ifstream file_stream(file);
    if(!file_stream){
        cerr << "Cannot read movies file" <<endl;
        return;
    }
    
    

    //Reading the data from the file, creating objects, inserting into hash_table
    string movie_title, movie_year, movie_time, num, list_data;
    list<string> movie_genre, actors, roles;
    
        
    while(file_stream >> movie_title){                        //Movie title
        
        //Making a movie object on the heap
        list<string>* m = make_movie(file_stream, movie_title);
        
        table.insert(m);
    }
    
}



void read_actors(string& file, map<string, string>& actors){
    //----- Checking if the file can be read -----
    ifstream a(file);
    if(!a){
        cerr << "Cannot read movies file" <<endl;
        return;
    }
    
    string actor_num, actor_name;
    while(a >> actor_num){
        a >> actor_name;
        actors[actor_num] = actor_name;
    }
    
}



void query_table(Hash_Vector& table, map<string, string>& actors){
    string title;
    cin >> title;
    list<string>* ql = make_movie(title);
    
    int index = table.find(ql);
    
    table.print(index, actors);
}



int main(int argc, const char * argv[]) {
    //Argument parsing
    if(argc != 1){
        cerr << "Usage: "<<argv[0] << " < command_file > outfile" <<endl;
    }
    
    //Reading the table_size and occupancy (if there)
    string command, filename;
    int table_size = 100;
    float occupancy = 0.5;
    
    //Checking if the table_size and occupancy needs to be reset
    while(cin >> command){  //Run max 2x
        if(command == "table_size" || command == "rolestable_size"){                    //Checking to set table_size
            cin >> command;
            table_size = stoi(command);
        }
        else if(command == "occupancy"){                //Checking to set occupancy
            cin >> command;
            occupancy = stof(command);
            break;
        }
        else{
            break;
        }
    }
    
    
    //Creating the empty hash_table
    Hash_Vector table(table_size, occupancy);
    
    //Creating the map of actor data
    map<string, string> actor_names;
    
    while(cin >> command){
        if(command == "movies"){
            cin >> filename;
            read_movies(filename, table);
        }
        else if(command == "actors"){
            cin >> filename;
            read_actors(filename, actor_names);
        }
        else if(command == "query"){             //Query
            query_table(table, actor_names);
        }
        else{
            break;
        }
        
    }
    
    
}
