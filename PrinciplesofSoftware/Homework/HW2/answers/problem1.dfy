// Created by Jared Gridley on 01/02/2021

// Function to be used in conditions
function prod(m: int, n: int): int
    requires n >= 0
{
    n*m
}

// Lemma
lemma double_and_halve(m:int, n:int)
  requires n >= 0
  ensures prod(m, 2 * n) == prod(m + m, n);
{
  if (n != 0)
  {
    double_and_halve(m, n - 1);
  }
}

//Helper Method for Even product case - Adding and dividing
method evens(w: int, v: int) returns (x: int, y:int)
    requires v > 1 && v % 2 == 0
    ensures 0 < y < v && w*v==x*y
    {
    //Adding x
    x := w + w;

    // Dividing y by 2
    if(2 * v/2 == v){
      y := v/2;
    }else{
      assert 3<2;
    }

    return x,y;
    }


//Helper Function for odd product case - adding and subtracting
method odds(w: int, v: int, res: int) returns (result: int, y:int)
    requires v > 0 && v % 2 == 1
    ensures 0 <= y < v && w*v+res == w*y+result
    {
    //Adding x to result
    result := res + w;

    // Y calculation (-1)
    y := v-1;
    }

// Implementation of Product Method
method product(m: int, n: int) returns (result: int)
    requires n >= 0
    ensures result == m*n
{
    var y := n;
    var x := m;
    result := 0;
    while y!=0
        invariant result + x*y == n*m;
        decreases y;
        {
        if y%2 == 0{
            x,y:=evens(x,y);
        }
        else{
            result, y := odds(x, y, result);
        }
        }
}

