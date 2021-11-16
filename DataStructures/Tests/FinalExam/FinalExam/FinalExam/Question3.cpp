//
//  Question3.cpp
//  FinalExam
//
//  Created by Jared Gridley on 5/4/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <stdio.h>
#include <iostream>
#include <string>
#include <vector>
#include <list>
#include <map>
#include <queue>
#include <algorithm>
#include <cmath>
using namespace std;


class Node {
public:
  int number;
  string name;
  Node* next;
};


void ConvertHashSet(vector<list<Node*> >& hs, unsigned int new_size){
    vector<list<Node*> > old_hs(hs);            //Assuming copy constructor
    
    
    hs.clear();
    hs.resize(new_size);
    
    for(int i = 0; i<old_hs.size(); i++){
        Node* it = *old_hs[i].begin();
        
        while(it != NULL){
            int new_hash = it->number % new_size;
            
            while(hs[new_hash].begin() != hs[new_hash].end()){
                new_hash+=1;
            }
            //Creating new node
            Node* temp = new Node;
            temp->name = it->name;
            temp->next = NULL;
            temp->number = it->number;
            
            //Adding to list and inserting into hs.
            list<Node*> hash_spot;
            hash_spot.push_back(temp);
            hs[new_hash] = hash_spot;
            
            it = it->next;
        }
    }
}
