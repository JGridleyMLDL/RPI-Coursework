//
//  Query.cpp
//  HW9
//
//  Created by Jared Gridley on 4/25/20.
//  Copyright © 2020 Jared Gridley. All rights reserved.
//

#include <stdio.h>
#include "Query.hpp"


Query::Query(){         //Empty Constuctor, initiazes value to empty
    present = false;
    data = "";
}

Query::Query(string d){ //Data Constructor, sets values to present
    data = d;
    present = true;
}

string Query::getData(){    //Acessor for data string
    return data;
}

bool Query::getPresent(){          //Acessor for present status
    return present;
}
