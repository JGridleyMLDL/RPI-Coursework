HOMEWORK 8: ROPES


NAME:  Jared Gridley


COLLABORATORS AND OTHER RESOURCES:
List the names of everyone you talked to about this assignment
(classmates, TAs, ALAC tutors, upperclassmen, students/instructor via
LMS, etc.), and all of the resources (books, online reference
material, etc.) you consulted in completing this assignment.

Cplusplus.com

Remember: Your implementation for this assignment must be done on your
own, as described in "Academic Integrity for Homework" handout.


ESTIMATE OF # OF HOURS SPENT ON THIS ASSIGNMENT:  11

BALANCED/WORST CASE RUNNING TIME ANALYSIS:
n = characters in the string of the current Rope (*this)
m = characters in the string of the Rope passed in (for functions that take
    a second rope)
v = longest "value" of a leaf
p = longest path from root to a leaf
Using these variables, state and explain the big-O running time of each 
of the following operations two ways: 1) with a balanced Rope, 2) with an 
extremely unbalanced Rope. Explain what you considered an extremely 
unbalanced rope for the string "Hello my name is Simon".


Copy Constructor: 	Balanced: O(2^(m)) 	Unbalanced: O(2^(m))
Recurses through the entire Rope passed through, which in the worst case 
would be each letter has its own node.

Construct from Node*:	Balanced: O(2^(p))	Unbalanced: O(2^(p))
Only needs to down the right side of the Rope passed through, which worst 
case would be the longest path to a leaf.

Index:			Balanced: O(p)		Unbalanced: O(n)
Iterates through the trees finding the node. For the Unbalanced tree, the 
worst case would be going through the entire string, node by node. For 
Balanced, worst cast would by the longest path to the node, it would not 
need to go through every character in the string. 

Report:			Balanced: O(p*n)	Unbalanced: O(n*n)
Finds sequence of letters, given index ranges. Worst case is having to go
through the entire rope.

iterator operator++:	Balanced: O(p)		Unbalanced: O(p)
Worst case would be the iterator is at the last leaf on the left side and 
needs to move to the right side. For balanced and unbalanced, worst case it 
would be the longest path.

Split:			Balanced: O(p+2^p)	Unbalanced: O(p+2^p)
Worst case would be that it needs to split one of the node before it can split 
the rope, which is the "p+" part. The it recurses after that split node to 
copy over anything after that, the "2^p" part.

Concat:			Balanced: O(2^m)	Unbalanced: O(2^m)
In either case it has to copy the rope passed through and then assign it to the 
left side of the current rope. Therefore, assuming worst case if every string 
case its own node, it would be "2^m" recursing for every character it needs to 
copy.

For the extremely unbalanced version of the string "Hello my name is Simon" I
assumed it was a branching structure to the right with the leaf nodes at each 
of the left branches. Each node containing one character. This way, it would 
be the most similar to a regular string structure. 



TESTING & DEBUGGING STRATEGY: 
Briefly describe the tests in your StudentTests() function.
How did you test the "corner cases" of your implementation?


When testing my program, I started with testing if the entered variables go out of the
largest index of the Rope and making sure that it doesn't get caught in an infinite
loop. Then I tried to look at how it would behave on really large strings, and modified
my code to work with these. For split, if there are more than 1 branch to add to
the new Rope, then I made sure it would know to concatenate the existing right branch
with the new branch to add. 



MISC. COMMENTS TO GRADER:  
(optional, please be concise!)

