import math
from random import randint
import time


class memoize(dict):
    def __init__(self, func):
        self.func = func

    def __call__(self, *args):
        return self[args]

    def __missing__(self, key):
        result = self[key] = self.func(*key)
        return result


def Multiply(x, y):
    '''
    This is a function that will multiple 2 numbers à la française
    Input:  Two n-bit integers x and y (y >= 0)
    Output: Product of x and y
    '''
    counter = 0
    answer = 0

    # Replaces recursive call
    while(y != 0):
        # byte shift if odd (also handles 0 cases)
        if (y % 2 == 1):
            answer += x << counter

        counter += 1
        y = y//2
    return answer


@memoize
def MultiplyDC(x, y):

    # Bit Strings
    n = max(x.bit_length(), y.bit_length())

    # Base Case
    if(n <= 1):
        return x*y

    # Creating the shift amount for each splitting below
    n_split = n//2

    # Base Case
    if(n <= 1):
        return x*y

    # Splitting the binary strings in half
    xl = x >> n_split
    xr = x - (xl << n_split)
    yl = y >> n_split
    yr = y - (yl << n_split)

    p1 = MultiplyDC(xl, yl)
    p2 = MultiplyDC(xr, yr)
    p3 = MultiplyDC(xl+xr, yl + yr)

    return p1 * pow(2, 2*n_split) + ((p3 - p1 - p2) * pow(2, n_split)) + p2


# Testing
if __name__ == "__main__":

    for n in [[100, 999], [1000, 9999], [10000, 99999]]:
        num1 = randint(n[0], n[1])
        num2 = randint(n[0], n[1])

        for i in range(3):
            num1 = randint(n[0], n[1])
            num2 = randint(n[0], n[1])

            print("Test" + str(i))

            t = time.time()
            Multiply(num1, num2)
            prod100_1time = time.time() - t

            t = time.time()
            prod100_2 = MultiplyDC(num1, num2)
            prod100_2time = time.time() - t

            print(prod100_1time, prod100_2time)
