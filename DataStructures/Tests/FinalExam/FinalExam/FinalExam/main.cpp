//
//  main.cpp
//  FinalExam
//
//  Created by Jared Gridley on 5/4/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
#include <list>
#include <map>
#include <queue>
#include <algorithm>
#include <cmath>

using namespace std;
void SequenceA(std::vector<int>& v1){
    std::vector<int> v_tmp;
    std::vector<int*> v2;
    for(std::vector<int>::iterator it = v1.begin(); it != v1.end(); it++){
        v_tmp.push_back(*it);
        v2.push_back(&(v_tmp.back()));
    }
    int sum = 0;
    for(std::vector<int*>::iterator it = v2.begin(); it != v2.end(); it++){
        sum += *(*it);
    }
    std::cout << sum << std::endl;
}

void SequenceB(std::list<int>& l1){
    std::list<int> l_tmp;
    std::list<int*> l2;
    for(std::list<int>::iterator it = l1.begin(); it != l1.end(); it++){
        l_tmp.push_back(*it);
        l2.push_back(&(l_tmp.back()));
    }
    int sum = 0;
    for(std::list<int*>::iterator it = l2.begin(); it != l2.end(); it++){
        sum += *(*it);
    }
    std::cout << sum << std::endl;
}


//void MapsB(std::map<int, int>& m2){
//  if(m2[5] == 0){
//  std::cout << "key 5 value 0" << std::endl;
//  }
//}

void MapsB(std::map<int, int>& m2){
  if(m2.find(5) != m2.end() && m2.find(5)->second == 0){
    std::cout << "key 5 value 0" << std::endl;
  }
}  

int main(int argc, const char * argv[]) {
    std::map<int, int> m2;
    m2[4] = 0;
    
    
    MapsB(m2);
}
