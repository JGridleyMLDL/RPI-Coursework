//Created by Jared Gridley on 01/03/2021

function Factorial(n: int): int
  requires n >= 0
{
  if n == 0 then 1 else n * Factorial(n-1)
}

method LoopyFactorial(n: int) returns (u: int)
  requires n >= 0
  ensures u == Factorial(n)
  {
    u := 1;
    var r := 0;

    // Factorial Calculation with Loops
    while (r < n)
      invariant Factorial(r) == u
    {
      var v := u;
      var s := 1;

      // Incrementing u to the next numerical factorial (e.g. 4! -> 5!)
      while (s<=r)
        invariant s*v == u
      {
        u:=u+v;
        s:=s+1;
      }
      r:=r+1;
      assert Factorial(r) == u;
    }
  }
