//
//  main.cpp
//  Test3-Practice
//
//  Created by Jared Gridley on 4/14/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <iostream>
using namespace std;


//MARK: QUESTION 1.1 -------------------------------------------------------------------------------------------

typedef std::vector<std::string> DonutBox;
DonutBox donuts;
std::vector<DonutBox> boxes;
donuts.push_back("strawberry"); donuts.push_back("chocolate"); donuts.push_back("maple"); findBoxes(donuts, boxes);
printDonutBoxes(boxes);



void findBoxes(const DonutBox& box, DonutBox& current_box, std::vector<DonutBox>& boxes){
    if(current_box.size() == donuts.size()){                //Base Case
        //Check that its not already in boxes
        for(int i = 0; i<boxes.size(); i++){
            if(boxes[i]==current_box){              //*
                return;
            }
        }
        boxes.push_back(current_box);
        return;
    }
    
    for(int i = 0; i<box.size(); i++){
        DonutBox temp_box = current_box;
        //If not element is already in the box then add it an recurse
        bool in_box = false;
        for(int j = 0; j<temp_box; ++j){
            if(box[i] == temp_box[j]){ in_box = true; }
        }
        if(!in_box){
            temp_box.push_back(box[i]);
            findBoxes(box, temp_box, boxes);
        }
    }
    
}
/*
 The Original way you were thinking is the most correct, after you add it to the box before recursion, you can remove it from the box that you are passing through the recrusive call. They did this by adding it to thier possiblity box before recursing and then removing it after their recursive call was finished.

void findBoxes(const DonutBox& box, DonutBox& current_box, std::vector<DonutBox>& boxes){
    if(box.empty()){
        boxes.push_back(current_box);
        return;
    }
    for(unsigned int i=0; i<box.size(); i++){
        DonutBox tmp_box = box;
        current_box.push_back(box[i]);
        tmp_box.erase(tmp_box.begin()+i);
        findBoxes(tmp_box, current_box, boxes);
        current_box.pop_back();
    }
}
 */

//This is right
void findBoxes(const DonutBox& box, std::vector<DonutBox>& boxes){
    DonutBox tmp;
    findBoxes(box, tmp, boxes);
}




//MARK: QUESTION 1.2 -------------------------------------------------------------------------------------------

void reverse(string& old_string, int index = 0){
    int str_size = old_string.size();
    if(index!=0 && index >= str_size/index){
        return;
    }
    char c = old_string[index]
    old_string[index] = old_string[str_size-(index+1)]
    old_string[str_size-(index+1)] = c;
    
    reverse(old_string, ++index);
}


/*
 They created 2 functions for this. The first one checks if its a string more than length 1. REMEMBER, you can split one solution into 2 function if you feel it would be better. If you are thinking of a way to solve the problem with different arguments, then write a second function.
 
 The second function is genius, set one variable to the beginning and one to the end (indexes), when the numbers cross, it stops.
 ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
void reverse(std::string& str, int i, int j){
    if(i>j){
        return;
    }
    char c =  str[i];
    str[i] = str[j];
    str[j] = c;
    reverse(str, i+1, j-1);
}


//MARK: QUESTION 2.1 -------------------------------------------------------------------------------------------

insert(10);
insert(5);
insert(2);
insert(0);
insert(8);
insert(6);
insert(7);
insert(9);
insert(17);
insert(13);
insert(11);
insert(15);
insert(18);

//This is correct, you can also go breath first.


//MARK: QUESTION 2.2 -------------------------------------------------------------------------------------------

0 2 7 6 9 8 5 11 15 13 18 17 10


//MARK: QUESTION 2.3 -------------------------------------------------------------------------------------------

//See drawings.

//Correct but not the most balanced tree. ON EXAM, make sure to specify your assumptions "Assuming that you want the most balanced tree after removal." or "Assuming every decision of left vs right results in right"


//MARK: QUESTION 3.1 -------------------------------------------------------------------------------------------

//See drawings.

O(1)


//These are correct.

//MARK: QUESTION 3.2 -------------------------------------------------------------------------------------------

/* First they made a driver fuction. This starts by getting to the leftmost element. */
bool insert(int val, TreeNode*&p){
    Treenode* start = p;
    //get to the leftmost element
    while(start && start->left){
        start = start->left;}
    return insert(val, p, start);
}


/* In their recursive function they check the value of the elment to insert compared to the tree node and move to find its correct spot. Then when p is NULL (and therefore in the correct spot with its parent pointer already pointing to the correct spot) it resets the NULL to be a new Treenode with the value. then it uses prev to change around the next pointer. */
bool insert(int val, Treenode* &p, Treenode* first, TreeNode* prev = NULL){
    if(!p){
        //set p to a new node (passed by reference, so parent will auto update)
        p = new Treenode(val);
        //this means this element is not the first, so we can just use prev's next
        if(prev){
            p->next = prev->next;           //This is just setting up the next variable in the node
            prev->next = p;
        }
        else{   //P is the first element
            p->next = first;
        }
        return true;
    }
    else if(val < p->value){         //If we go left, prev should not be changed
        return insert(val, p->left, first, prev);
    }
    else if( val > p->value){
        return insert(val, p->right, first, p);
    }
    else{       //The element is already inserted into the set
        return false;
    }
}



/* ON THE EXAM, DO NOT BE AFRAID TO WRITE A DRIVER FUNCTION. IF YOU HAVE A RECURSIVE IDEA THAT USES MORE ARGUMENTS, MAKE 2 SEPERATE FUNCTIONS!!! */

//MARK: QUESTION 5.1 -------------------------------------------------------------------------------------------

s1: O(klog(n))
s2: O(k + n)


//MARK: QUESTION 5.2 -------------------------------------------------------------------------------------------
/* In your solution you do not account for jumps in s1, you would need to consider all of the cases where the first one ends, the s2 is behind, they are equal, and s1 is behind*/
std::vector<int> FastSetDiff(const intset& s1, const intset& s2){
    std::vector<int> ret;
    int_it it1 = s1.begin();
    int_it it2 = s2.begin();
    while(it1 != s1.end()){
        //Nothing more to remove from set a (set b is done)
        if(it2 == s2.end()){
            ret.push_back(*it1);
            ++it1;
        }
        //s2 iterator needs to advance intil is >= *sit1 (set b is behind)
        else if(*it2 < *it1){
            ++it2;
        }
        //They are equal, advance both and don't add (equal)
        else if(*it1 == *it2){
            ++it2;
            ++it1;
        }
        //s1 is begind s2, there are things in s1 not in s2, add to return vec
        else{
            ret.push_back(*it1);
            ++it1;
        }
    }
    return ret;
}



//MARK: QUESTION 6.1 -------------------------------------------------------------------------------------------
TreeNode* TreeChop(TreeNode* root, bool direction){
    if(direction == true){              //Left chop
        //Find the  thing in the tree
        TreeNode* new_root = root->left;
        root->left = NULL;
        TreeNode* insert_node = new_root;
        while(insert_node->right != NULL){
            insert_node = insert_node->right;
        }
        insert_node->right = root;
    }
    else{                               //Right chop
        TreeNode* new_root = root->right;
        root->right = NULL;
        TreeNode* insert_node = new_root;
        while(insert_node->left != NULL){
            insert_node = insert_node->left;
        }
        insert_node->left = root;
    }
}




//MARK: QUESTION 11.1 ------------------------------------------------------------------------------------------
/* My initial thought is that it would only have to go to the far right. THIS IS CORRECT*/

template <class T>
const T& FindLargestInTree(TreeNode<T>* root){
    while(root->right){
        root = root->right;
    }
    return root->value;
}

Running Time: O(h)  //Just the height of the tree for worst case.

//MARK: QUESTION 11.2 ------------------------------------------------------------------------------------------
/* To solve this, I would go the first element and then iterate one more (similar to the oparator++). If the next element is the second one given then I return NULL, otherwise return that. */
template <class T>
TreeNode<T>* FindSmallestInRange(const T& a, const T& b, TreeNode<T>* root, T& best_value){
    if(!root){
        return NULL;
    }
    
    TreeNode<T>* left_subtree = FindSmallestInRange(a,b,root->left,best_value);
    TreeNode<T>* right_subtree = FindSmallestInRange(a,b,root->right,best_value);
    
    if(root->value > a && root->value < best_value){
        best_root = root->value;
        
        return root;
    }
    else if(left_subtree && left_subtree->value == best_value){
        return left_subtree;
    }
    else if (right_subtree){
        return right_subtree;
        
    }
    return NULL;
}


/*  WHEN WRITING FUNCTIONS ON THE EXAM, BE SURE TO CONSDER THE CASES THAT WILL BREAK THE CODE EASILY AND INCORPORATE THAT INTO YOUR FUNCTION*/


//MARK: QUESTION 11.3 ------------------------------------------------------------------------------------------
/* Essentially using the FindLargestInTree nad FindSmallestInRange to go through each */

template <class T>
std::vector<T> TreeSort(TreeNode<T>* root){
    std::vector<T> ret;
    const T& smallest = FindSmallestInTree(root);
    const T& largest = FindLargestInTree(root);
    
    ret.push_back(smallest);
    TreeNode<T>* find = FindSmallestInRange(ret[ret.size()-1],largest,root);
    while(find){                            //Loops through finding every element
        ret.push_back(find->value);
        find = FindSmallestInRange(ret[ret.size()-1],largest,root);
    }
    ret.push_back(largest);
    return ret;
    
}














int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    return 0;
}
