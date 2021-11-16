//
//  Question1.cpp
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


/*
 Sequence A - When the memory location is stored for v2, it stores the memory location for the back of the vector at that point. However, the vector will dynamically resize itself when it gets full, thus corrupting the memory location that was stored. The newly resized vector will potentially hold different memory locations and there is no guarentee that the memory address in v2 will stil point to the same values. Thus the sum can be corrupted because it will be accessing unexpected values.
 */


/*
 Loop B - the problem with Loop B is that it uses an unsigned int for its iteration. This structure will work until it gets to 0. When it tries to subtract 1 from 0, it will not equal negative one, instead it will result in a very large number that will cause the loop push_back() more that it is expecting. This will repeat causing an infinite loop. To fix it, change unisgned int -> int, so that 0 - 1 = -1;
 */

/*
Math A - The problem with MathA is with its return. With the int return type, there must be an iteger to return for any case. So, passing 0 through MathA would break it because it would not go into any of the if statements, so there would be nothing to return, violating the return type for the function.
*/


/*
MapsA - This function is buggy because if will access whatever value is in m1[5] regardless to whether it was added or not. In most cases this probably wouldn't cause a problem, however if the random value in the memory location for m1[5] does happen to equal 0, then it will print even if it's not expected to print. This problem has to do with the fact that if the value 5 didn't exist in the map, it would have been added with whatever value was previously stored in that memory location.
*/



/*
TreeB - the problem with this function is in boolean part of its while loop. The && connector works by first checking the first boolean statement and if that fails then it doesn't even run the second boolean statement. So with this checking the value before it checks that the node exists, if the node does not exist it will crash with a memory error.
*/

/*
CleanupB - The problem with this is that deletes the pointers in x[i] before deleting the pointers in x[i][j]. This will result in a memory error as it is trying to access the memory that has already been freed. It would also cause memory leaks as it no longer can access the pointers x[i][j] memory.
*/
