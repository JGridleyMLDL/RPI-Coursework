// Created by Jared Gridley on 01/03/2021

// Implementation of Dutch National Flag problem (sorting)
method dutch(arr: array?<char>) returns (k: int)
    requires arr != null && arr.Length > 0
    ensures 0 <= k <= arr.Length
    modifies arr
    {
    var i := 0;
    var j := 0;
    while (i < arr.Length)
        invariant j <= i
        decreases (arr.Length - i)
        {
        if (arr[i] == 'r'){
            if ( i != j) {
                var temp := arr[i];
                arr[i] := arr[j];
                arr[j] := temp;
                }
            j := j + 1;
        }
        i := i + 1;
        }
    k := j+1;
    }

