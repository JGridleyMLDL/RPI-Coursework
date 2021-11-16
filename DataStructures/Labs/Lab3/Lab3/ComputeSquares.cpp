//
//  ComputeSquares.cpp
//  Lab3
//
//  Created by Jared Gridley on 1/29/20.
//
#include <cmath>
#include <iostream>

void ComputeSquares(int *array1, int *array2, int n){
    int *b = array1;
    int *c = array2;
    for( int i = 0; i<n; i++){
        *c = *b * *b;
        c++;
        b++;
    }
}

int main(){
    int original[] = {1,2,3,4,5,6,7,8};
    int square[8];
    
    ComputeSquares(original, square, 8);
    for(int i = 0; i<8; i++){
        std::cout<<square[i]<<std::endl;
    }
    return 0;
    
}
