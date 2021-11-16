//
//  PROBLEM 1.cpp
//  TEST3
//
//  Created by Jared Gridley on 4/16/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <stdio.h>
#include <iostream>
#include <string>

using namespace std;

class SillyString{
public:
  SillyString(const std::string& s) : mystr(s) {}
  std::string getStringA() const { return mystr; }
  std::string& getStringB() { return mystr; }
private:
  std::string mystr;
};

//int main(int argc, const char * argv[]) {
//    
//    std::string s1a = "asdfghjk.sdfghj.hsye.rrr";
//    SillyString h(s1a); //string s1a declared before this code
//    for(int i=0; i<s1a.size(); i++){
//        if(h.getStringB()[i] == '.'){
//            h.getStringB()[i] = '2';
//        }
//    }
//    
//    cout<< s1a<<endl;
//    cout<< h.getStringA()<<endl;
//}
