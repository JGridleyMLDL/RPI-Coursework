//
//  Problem 3.cpp
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

class Node{
public:
    Node() : left(NULL), right(NULL), clump_left(NULL), clump_right(NULL), clump_parent(NULL) {}
    Node(int v) : left(NULL), right(NULL), clump_left(NULL), clump_right(NULL),
    clump_parent(NULL), value(v) {}
    
    //0 or 1 of these two pointers can be non-NULL
    Node* left;
    Node* clump_left;
    
    //0 or 1 of these two pointers can be non-NULL
    Node* right;
    Node* clump_right;
    
    //Points to the parent of the root of our clump
    Node* clump_parent;
    
    int value;
    
    void insert(Node*& head, int val, bool new_clump){
      if(!head){
        head = new Node(val);
        return;
      }
      insert(head, val, new_clump, NULL);
    }
    
    
    
    void insert(Node* head, int val, bool new_clump, Node* parent){
        if(!head){
            head = new Node(val);
            
            if(new_clump == false){         //Not in new clump
                head->clump_parent = parent->clump_parent;
                head->clump_left = parent->clump_left;
                head->clump_right = parent->clump_right;
                
                if(val > parent->value){        //resetting parent values
                    parent->right = head;
                }
                else if(val < parent->value){
                    parent->left = head;
                }
            }
            else{                           //In new clump
                head->clump_parent = parent;
                
                if(val > parent->value){         //resetting parent values
                    parent->right = head;
                    parent->clump_right = head;
                }
                else if(val < parent->value){
                    parent->left = head;
                    parent->clump_left = head;
                }
            }
        }
        else if(val < parent->value){
            insert(head->left, val, new_clump);
        }
        else if(val>parent->value){
            insert(head->right, val, new_clump);
        }
        else{       //Equal, node already exists.
            return;
        }
    }
};











int ClumpUpHeight(Node* curr){
    int count = 1;
    while(curr->clump_parent != NULL){
        ++count;
        curr = curr->clump_parent;
    }
    return count;
}

Node* findClumpRoot(Node* root, Node* ptr){

    Node* parent = ptr->clump_parent;
    
    if(parent == NULL){         //top clump
        return root;
    }
    else if(ptr->value > parent->value){    //right clump
        return parent->right;
    }
    else{                                   //left clump
        return parent->left;
    }
}




int main(int argc, const char * argv[]) {
    
}
