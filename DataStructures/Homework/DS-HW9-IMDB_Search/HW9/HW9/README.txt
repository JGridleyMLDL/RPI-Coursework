HOMEWORK 9: IMDB SEARCH


NAME:  Jared Gridley

COLLABORATORS AND OTHER RESOURCES:
List the names of everyone you talked to about this assignment
(classmates, TAs, ALAC tutors, upperclassmen, students/instructor via
LMS, etc.), and all of the resources (books, online reference
material, etc.) you consulted in completing this assignment.

cplusplus.com
Stackoverflow.com
geeksforgeeks.com


Remember: Your implementation for this assignment must be done on your
own, as described in "Academic Integrity for Homework" handout.



ESTIMATE OF # OF HOURS SPENT ON THIS ASSIGNMENT:  14


HASH FUNCTION DESCRIPTION
My hash function utilizes the permute filters function provided. It uses 
the function to create a vector of vectors and then parses through that to
find all of the combinations of data. From there I convert all of the
combinations into a string which is stored in a query class. I then add
up all of the characters in order and % that by the size of the table,
this becomes my index. If two values hash to the same spot, I apply linear
probing to move it to the next available index.






MISC. COMMENTS TO GRADER:  
(optional, please be concise!)


