//
//  part1.cpp
//  Lab8
//
//  Created by Jared Gridley on 3/3/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <iostream>

int part1(int x, int y){
    int count = 0;
    if(x == 0 && y ==0){
        return 1;
    }
    else if(x==0 && y != 0){
        count += part1(x, y-1);
    }
    else if(x!=0 and y==0){
        count += part1(x-1, y);
    }
    else{
        count += part1(x-1, y);
        count += part1(x, y-1);
    }
    return count;
}


//int main(){
// int num1 = part1(2, 2);
// int num2 = part1(0,0);
// std::cout<<num1<<"\t"<<num2<<std::endl;
// return 0;
// }

