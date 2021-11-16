#include <iostream>
#include <vector>
#include <string>
#include <map>
using namespace std;

typedef map<int, string> phone;


//VECTOR FUNCTIONS
// add a number, name pair to the phonebook
void add(vector<string> &phonebook, int number, string const& name) {
    phonebook[number] = name;
}

// given a phone number, determine who is calling
void identify(const vector<string> & phonebook, int number) {
    if (phonebook[number] == "UNASSIGNED")
        cout << "unknown caller!" << endl;
    else
        cout << phonebook[number] << " is calling!" << endl;
}


//MAP FUNCTIONS
void map_add(phone& phonebook, int number, const string& name){
    phonebook[number] = name;
}

void map_id(const phone &phonebook, int number){
    phone::const_iterator itr = phonebook.find(number);
    
    if(itr == phonebook.end()){
        cout << "unknown caller!" <<  endl;
    }
    else{
        cout << itr->second << " is calling!" << endl;
    }
}



int main() {
    // create the phonebook; initially all numbers are unassigned
    vector<string> phonebook(10000, "UNASSIGNED");
    
    // add several names to the phonebook
    add(phonebook, 1111, "fred");
    add(phonebook, 2222, "sally");
    add(phonebook, 3333, "george");
    
    // test the phonebook
    identify(phonebook, 2222);
    identify(phonebook, 4444);
    
    //Map version of the phonebook
    phone book;
    
    map_add(book, 1111, "fred");
    map_add(book, 2222, "enrique");
    map_add(book, 1223221, "mary");
    map_add(book, 6076548177, "jenna");
    
    map_id(book, 2222);
    map_id(book, 1222);
    map_id(book, 1223221);
    map_id(book, 6076548177)
    
    
    
}






/*
 Time complexity to run the program: O(N), add: O(1), Identify: O(1), if you extended it to 7 or 10 digit numbers then it would still be O(N) when initializing the vector as it would have to allocate that much space of UNASSIGNED vector space. Because the vector is random access, it will be able to index at a constant time complexity, so add and identify at O(1) complexity.
 */

/*
 Time compexity for the map version add and id is O(log(n)). For increasing to a 7 or 10 digit number, the complexity is relatively similar to the 4 digit numbers, the main factor that would cause the time complexity on the maps version to increase is the number of numbers in the map, not necessarily the same as all of the possible numbers.
 
 The vector is better when it comes to indexing to find the number, however it becomes inefficient when it comes to large possibilities of numbers, whereas the map only keeps track of the numbers that exist, not all of the possibilites, so while it takes longer to index the map, overall it is faster because it does not have to initialize every possibility.
 */
