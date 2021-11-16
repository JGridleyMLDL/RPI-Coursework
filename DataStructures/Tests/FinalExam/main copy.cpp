//
//  main.cpp
//  Test3-Practice
//
//  Created by Jared Gridley on 4/14/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <iostream>
using namespace std;

// ----- 1.1 ----------------------------
Both
Both
Array
Vector
Both

// ----- 1.2 ----------------------------
False, all algorithms that can be written using a for loop can also be written using a while loop.



// ----- 2.1 ----------------------------
Unary Overloading, Binary Ovarloading, Binary Operator as a friend function.

Binary Overloading should be implemented here because the operator will always be declared
in scope of the class its operating on


// ----- 2.2 ----------------------------
//.h file
//IF THEY ASK YOU TO DECALRE A PROTOTYPE THEN SPECIFY WHERE IT SHOULD GO IN THE .H FILE
//This goes in the public section
void operator /= (string& guess) const;


//.cpp file
Superhero& Superhero::operator/=(const string &id){
    if (id == true_identity){
        power = "";
        
    }
    return *this;
}


// ----- 3.1 ----------------------------
//Needs an operator < so it can sort the objects.

bool operator<(const Car &a, const Car &b){
    if(a.getmaker() == b.getMaker()){
        return a.getColor() < b.getColor();
    }else{
        return a.getMaker() < b.getMaker();
    }
}



// ----- 3.3 ----------------------------
void print_cars(const map<Car, string> &cars){
    for(int i =0; i< cars.size(); i++){
        cout<<"People who drive a "<< cars[i].getColor() << " "<< cars[i].getMaker() << ":" << endl;
        for(int j = 0; j<cars[i].size(); j++){
            cout<<cars[i][j]<<endl;
        }
    }
}

//YOU DON'T HANDLE ALL THE CORNER CASES, WHAT IF THE CAR IS NOT IN THERE, WHAT IF THE CAR ONLY HAS ONE NAME
bool remove_car(map<Car,vector<string> > &cars, const string &name, const string &color, const string &maker) {
    map<Car,vector<string> >::iterator itr = cars.find(Car(maker,color));
    if (itr == cars.end())              //Nothing in the map
        return false;
    if (itr->second.size() == 1 && itr->second[0] == name) {    //Only one car in the map
        cars.erase(Car(maker,color));
        return true;
    }
    for (int i = 0; i < itr->second.size(); i++) {              //This is what you had originally, standard case
        if (itr->second[i] == name) {
            itr->second.erase(itr->second.begin() + i);
            return true;
        }
    }
    return false;
}


// ----- 4.0 ----------------------------

//REMEMBER THE BASE CASES, WHAT IF THE THING IS EMPTY???
void insert_in_order(double x, queue<double>& q){
    queue<double> new_queue;
    
    bool entered = false;
    for(int i = 0; i<q.size()+1; i++){
        int small = q.top();
        
        if(small > x && !entered){
            new_queue.push(x);
            entered = true;
        }else{
            new_queue.push(small);
            q.pop();
        }
    }
    
    q = new_queue;
    
}
//Your solution might work but there is the risk for a memory leak, better to make a copy of the queue and then empty the original (through pop). Thencopy over the value until you get the one to insert, insert it, then copy the rest.


// ----- 5.1 ----------------------------       NEEDS FIXING
template <class T>
void dslist<T>::reverse(){
    // Handle empty or single node list
    if (head_ == tail_) return;
    // Swap pointers at each node of the list,
    //using a temporary // pointer q to remember where to go next.
    Node<T>* p = head_;
    while (p) {
        Node<T>*q = p->next_;
        p->next_ = p->prev_;
        p->prev_ = q;
        p = q;
    }
    
    p = head_;
    head_ = tail_;
    tail_ = p;
    
}


// ----- 5.2 ----------------------------
Node<T>* Sublist(Node<T>* head, int low, int high){
    
    Node<T>* mover = head;
    
    for(int i = 1; i< low; i++){
        mover = mover->next;
    }
    //Now the mover node is in the right spot.
    Node<T>* nhead = NULL;
    Node<T>* prev = nhead;
    
    for(int i = low, i<high, i++){
        //Creating the next node
        Node<T> temp = new Node<T>;
        temp->value = mover->value;
        
        //Changing prev
        if(i == low){
            prev = temp;
        }else{
            prev->next = temp;
            prev = prev->next;
        }
        
        mover = mover->next;
        
        (if mover == NULL){     //Safety
            break;
        }
    }
    
    return nhead;
}

Node<T>* Sublist(Node<T>* head, int low, int high) {
    // Skip over the first low-1 nodes in the existing list
    Node<T>*p = head;
    int i;
    for (i=1; i<low; ++i){
        p = p-> next;
    }
    // Make the new head node and make a pointer to the last node in list
    Node<T>* new_head = new Node<T>;
    new_head->value = p->value;
    Node<T> * last = new_head;
    // Copy the remaining nodes, one at a time
    for (++i, p = p->next; i<=high && p; ++i, p = p->next) {
        last->next = new Node<T>;
        last->next->value = p->value;
        last = last->next;
    }
    last->next = 0;
    
    return new_head;
}



// ----- 5.3 ----------------------------
//CHECK BASE CASE
template <class T>
void cs2list<T>::splice(iterator itr, cs2list<T>& second){
    iterator after = (++itr);
    
    //Setting the pointers on the iterator before the splice
    itr->next = second->head;
    itr->next->prev = *itr;
    
    //Setting the pointers on the iterator after the spliced part
    after->prev = second->tail;
    after->prev->next = *after;
    
    second.head = NULL;
    second.tail = second.head;
}

// ----- 7 ------------------------------
//7.1

//7.2

//7.3

//7.4


// ----- 8 ------------------------------



// ----- 9.0 ----------------------------

priority_queue

set, hash_table, priority_queue

set, list, map

set, hash_table

set, map, priority_queue

set, priority_queue

map, set

vector, list


// ----- 10.0 ---------------------------

O(n)

O(log n)

O(2^n)

O(n^2)

O(nlog n)

O(1)


// ----- 11.1 ---------------------------


Office();
~Office();
Office(const Office& office);
Office& operator=(const Office &office);


// ----- 12.0 ---------------------------

void even_array(const list<int>& b, int* & a, int& n){
    n = 0;
    for(list<int>::iterator itr = b.begin(), itr!=b.end(); itr++){
        if(*itr %2 ==0){
            n++;
        }
    }
    
    a = new int[n];
    int* it = a.begin()
    for(iterator itr = b.begin(), itr!=b.end(); itr++){
        if(*itr %2 ==0){
            *it = *itr;
            it++;
        }
    }
}

bool EqualsChildrenSum(Node* root){
    int sum = root->right->value + root->left->value + root->middle->value;
    
    if(sum != root->value){
        return false;
    }else{
        bool r = EqualsChildrenSum(root->right);
        bool m = EqualsChildrenSum(root->middle);
        bool l = EqualsChildrenSum(root->left);
        
        return (r && m && l);
    }
}



