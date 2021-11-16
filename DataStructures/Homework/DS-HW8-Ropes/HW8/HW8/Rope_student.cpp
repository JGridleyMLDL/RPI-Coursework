/*
Write your Rope implementation in this file.
You do not have to keep any of the code that is already in this file.
Functions that are required that you have not implemented have an assert 0
meaning they will always crash. This should help you make sure you implement
everything.
*/

#include <cassert>
#include "Rope.h"

//Should advance to the next Node using in-order traversal
//It can point at any Node, not just leaves
rope_iterator& rope_iterator::operator++(){
    if(ptr_->right!=NULL){      //Finding leftmost child of the right nide
        ptr_ = ptr_->right;     //Moving into right subtree
        while(ptr_ -> left != NULL){ ptr_ = ptr_->left; }   //Move all the way left
    }
    else{ //Go up along right branches, stop after the first left one
        while(ptr_->parent != NULL && ptr_->parent->right ==ptr_){
            ptr_ = ptr_->parent;}
        ptr_ = ptr_->parent;
    }
    return *this;
}

//Point to the first Node for in-order traversal
rope_iterator Rope::begin() const{
    Node* temp = root;
    while(temp->left != NULL){      //Traverses to the leftmost node
        temp = temp->left;
    }
    rope_iterator begin_itr(temp);  //Initialized iterator at that node
    
    return begin_itr;               //return the new rope_iterator for begin
}

Rope::Rope(){
    Node* new_root = new Node;      //Create new node on heap
    root = new_root;                    //Set this is as the root
    size_ = 0;                          //Initialize size to 0 (empty Rope)
}

//Should make the root = p and adjust any member variables
//This should not make a copy of the rope with root at p,
//it should just "steal" the Node*
Rope::Rope(Node* p){
    root = p;                       //Set the root to point to p (no copy)
    int sum = 0;
    while(p){                       //Calculating the weight
        sum+=p->weight;
        p=p->right;
    }
    size_ = sum;                    //Setting the size_
}

Rope::~Rope(){
    this->destroy_tree(root);       //Calls helper function to destroy the tree
    root = NULL;                        //Set empty tree back to null
    size_ = 0;                          //Set empty size
}

Rope::Rope(const Rope& r){
    root = new Node;
    Node* original = r.expose_root();
    root->value = original->value;
    root->weight = original->weight;
    size_ = r.size();
    
    copyRope(original, root, true, true);
}

Rope& Rope::operator= (const Rope& r){
    this->destroy_tree(root);                   //Destroys the old tree
    root = NULL;
    
    root = new Node;                            //Sets rope to a deep copy of other 1
    Node* original = r.expose_root();       //Setting the root
    root->value = original->value;
    root->weight = original->weight;
    size_ = r.size();
    copyRope(original, root, true, true);   //Recursive function call
    
    return *this;
    
}

//MUST BE ITERATIVE
//Get a single character at index i
bool Rope::index(int i, char& c) const{
    if(i>=size_){ return false; }                       //Invalid Index
    Node* p = root;
    while(p->left != NULL || p->right != NULL){
        if(i < p->weight && p->left != NULL){           //Checks moving left
            p = p->left;
        }
        else if(i >= p->weight && p->right != NULL){    //Checks moving right
            i -= p->weight;
            p = p->right;
        }
    }
    c = p->value[i];
    return true;
}

//Add the other rope (r)'s string to the end of my string
void Rope::concat(const Rope& r){
    if(r.size() == 0){ return; }        //Special case: concat with empty string
    
    Node* new_root = new Node;          //Create new top of the Rope
    int new_size = size_ + r.size();
    
    root->parent = new_root;            //Connect left to new_node
    root->parent->left = root;          //Connect new_node to left
    new_root->weight = size_;           //Set new_root's weight
    
    Node* copy = r.expose_root();       //Copying the top of Rope r
    Node* right = new Node;
    right->weight = copy->weight;
    right->value = copy->value;
    copyRope(copy, right, true, true);  //Copying the rest of Rope r
    new_root->right = right;
    
    root = new_root;                    //Set new_root to Rope root
    size_ = new_size;                   //Adjust the size
}

//Get a substring from index i to index j.
//Includes both the characters at index i and at index j.
//String can be one character if i and j match
//Returns true if a string was returned, and false otherwise
//Function should be written with efficient running time.
bool Rope::report(int i, int j, std::string& s) const{
    if(i>=size_ || j>=size_){return false;}             //Invalid index case
    std::string found = "";
    char c;
    for(; i<(j+1); i++){                //J+1 because they are indexes
        index(i, c);                    //Finding all of the string parts
        found = found + c;
    }
    if(found != ""){ s = found; return true;}   //If something then return T
    else{ return false;}                        //If nothing return F
}

//The first i characters should stay in the current rope, while a new
//Rope (rhs) should contain the remaining size_-i characters.
//A valid split should always result in two ropes of non-zero length.
//If the split would not be valid, this rope should not be changed,
//and the rhs rope should be an empty rope.
//The return value should be this rope (the "left") part of the string
//This function should move the nodes to the rhs instead of making new copies.
Rope& Rope::split(int i, Rope& rhs){
    if(i>=size_){return *this; }            //Checks invalid split
    check_split(i);                         //Checks if leaf node needs to be split
    
    recursive_split(root, i, rhs);          //Splits the Ropes
    
    int rhs_size = size_ - i;               //Resetting the size variables
    rhs.reset_size(rhs_size);
    size_ = i;
    
    return *this;
}


//NEW Helper Function implementation
void Rope::destroy_tree(Node* p){
//  This function recurses through a tree and destroys the tree from the bottom up.
    if(!p){ return; } //Checks if node is already null
    destroy_tree(p->right);
    destroy_tree(p->left);
    delete p;               //delete the node
}


void Rope::check_split(int i){
    /*This is a helper function for split. Function checks if node needs to be split
     in order to split the Rope, if it does then splits the node when the Rope split
     will start */
    Node* p = root;
    while(!(p->left == NULL and p->right == NULL)){     //Getting to the node
        if(i < p->weight && p->left != NULL){           //Checks moving left
            p = p->left;
        }
        else if(i >= p->weight && p->right != NULL){    //Checks moving right
            i -= p->weight;
            p = p->right;
        }
    }
    if(i==0){ return; }         //If leaf already starts at the index
    else{
        std::string l = p->value.substr(0,i);
        std::string r = p->value.substr(i);
        
        Node* temp_left = new Node;             //Setting up left node
        temp_left->weight = i;
        temp_left->value = l;
        p->left = temp_left;
        p->left->parent = p;
        
        Node* temp_right = new Node;            //Setting up right node
        temp_right->weight = p->weight - i;
        temp_right->value = r;
        p->right = temp_right;
        p->right->parent = p;
        
        p->weight = i;                          //Resetting the new not-leaf node
        p->value = "";
        
    }
}

void Rope::recursive_split(Node* curr, int i, Rope& rhs){
    /* This is the split function, runs after the check_split is called. Recursively
     finds the node to split at, and then travels back up the tree to alter any of
     the other data for the new split trees. */
    if(i == 0){                                 //Found leaf to split at
        Node* rhs_root = rhs.expose_root();     //Adding to left branch of new "rhs"
        rhs_root->left = curr;
        curr->parent->right = NULL;
        curr->parent = rhs_root;
        rhs_root->weight = curr->weight;
    }
    else if(i < curr->weight){ //-------------------------------"GO LEFT" condition
        recursive_split(curr->left, i, rhs);
        curr->weight = i;                                 //Resetting the weight
        
        Node* rhs_root = rhs.expose_root();
        if(rhs_root->right != NULL &&curr->right!= NULL){ //Splitting other branches
            Node* new_RL = rhs_root->right;
            rhs_root->right->left = new_RL;
            rhs_root->right->right = curr->right;
            rhs_root->right->right->parent = rhs_root;
        }
        else if(rhs_root->right == NULL && curr->right != NULL){
            rhs_root->right = curr->right;           //Checks if more than 2 branches
            rhs_root->right->parent = rhs_root;      //need to be added.
            curr->right = NULL;
        }
    }
    else if(i >= curr->weight){ //------------------------------"GO RIGHT" Condition
        recursive_split(curr->right, (i- curr->weight), rhs);
    }
}


void Rope::reset_size(int s){           //Size function to help reset split functions
    size_ = s;
}


void Rope::copyRope(Node* original, Node* copy, bool side, bool first){
    /* Copy function that makes a deep copy of all the nodes and assigns them */
    if(original == NULL){
        return; }
    if(!first){                                 //Only run when not the first turn
        Node* new_copy = new Node;
        new_copy->parent = copy;                //Make new node and copy the data
        new_copy->weight = original->weight;
        new_copy->value = original->value;
    
        if(side){                               //Setting parent
            new_copy->parent->left = new_copy;
            copy = new_copy;
        }else{
            new_copy->parent->right = new_copy;
            copy = new_copy;
        }
    }
    
    copyRope(original->left, copy, true, false);    //Recurse Left
    copyRope(original->right, copy, false, false);  //Recurse Right
}
