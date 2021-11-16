//
//  Question2.cpp
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

class PhraseNode {
    public:
      PhraseNode(const std::string& p) : phrase(p), next(NULL) {}
      std::string phrase;
      PhraseNode* next;
      std::vector<PhraseNode*> options;
};

PhraseNode* createSentenceLL(const vector<string>& original_sentence){
    if(original_sentence.size() == 0){
        PhraseNode* head = NULL;
        return head;
    }
    
    int s = original_sentence.size();
    
    PhraseNode* head = new PhraseNode(original_sentence[s-1]);
    
    for(int t = (s-2); t>=0; --t){
        PhraseNode* temp = new PhraseNode(original_sentence[t]);
        temp->next = head;
        head = temp;
    }
    
    return head;
}


void addOptionsToList(map<string, list<string> > subs, PhraseNode* LL){
    while(LL != NULL){
        
        string word = LL->phrase;
        PhraseNode* next = LL->next;
        
        if(subs.find(word) != subs.end()){
            list<string> lst = subs.find(word)->second;
            list<string>::iterator it = lst.begin();
            while(it != lst.end()){
                PhraseNode* temp = new PhraseNode(*it);
                temp->next = next;
                
                LL->options.push_back(temp);
            }
        }
        LL = LL->next;
    }
}





void generateSentences(vector<PhraseNode*> & v, PhraseNode* head){
        //Go through each node
        
    PhraseNode* new_str_head;
        
        //If not in the vector already
            //Add it

    generateSentences(v, new_str_head);
}


void cleanupList(PhraseNode* head){
    PhraseNode* curr = head;
    PhraseNode* prev = curr;
    
    while( curr != NULL){
        prev = curr;
        if(head->options.size() != 0){
            vector<PhraseNode*> optionH = head->options;
            
            for(int i = 0; i<head->options.size(); i++){
                optionH[i]->next = NULL;
                delete optionH[i];
            }
            
            curr = curr->next;
            delete prev;
        }
    }
    head = NULL;
}
