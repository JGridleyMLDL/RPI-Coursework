'''
Lab 4 code to generate RSA encryption component
Created by Jared Gridley
'''
import random
import math


def modexp(x, y, N):
    if y == 0:
        return 1
    z = modexp(x, y//2, N)
    if y % 2 == 0:
        return pow(z, 2) % N
    else:
        return x*pow(z, 2) % N


def GeneratePrime(n):
    trials = 1
    # List of random numbers in the range.
    while True:
        # Random number
        r = random.getrandbits(n)
        # Weed out some corner cases
        if r % 2 != 0 and r != 0 and r != 1 and MillerRabin(r, min(r, 10) == True):
            return (r, trials)

        trials += 1


def MillerRabin(N, k=10):
    if (N % 2 == 0 and N != 2) or N == 0:
        return False

    if N == 2:
        return True

    u = N-1
    t = 0
    while(u % 2 == 0):
        u = u/2
        t += 1

    # create the list of random ints
    l = []
    for j in range(k):
        l.append(random.randint(1, N-1))

    passed = []
    for i in l:
        z = modexp(i, 2, N)
        if z == 1 or z == N-1:
            passed.append(i)
        else:
            for j in range(t+1):
                prev = z
                z = modexp(z, 2, N)
                if z == 1 and prev == N-1:
                    passed.append(i)
                    break

    if len(passed) == k:
        return True
    else:
        return False

# Calculates the public key e from M = (p-1)(q-1) ()


def Euclid(x, y):
    if y > x:
        t = x
        x = y
        y = t

    if y == 0:
        return x
    return Euclid(y, x % y)


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


def RandomRelativePrime(n):
    p = GeneratePrime(n)
    q = GeneratePrime(n)
    print("P: \t" + str(p[0]))
    print("P Trials: \t" + str(p[1]))
    print("Q: \t" + str(q[0]))
    print("Q Trials: \t" + str(q[1]))
    M = MultiplyDC(p[0]-1, q[0]-1)
    print("M:\t" + str(M))

    trials = 1
    for i in range(100):
        # Random number
        e = random.randrange(2, M-1)
        if Euclid(M, e) == 1:
            print("GCD of (M, e): \t" + str(Euclid(M, e)))
            print("e:\t" + str(e))
            print("e Trials: \t" + str(trials))
            return e
        trials += 1

    return 1


if __name__ == "__main__":
    print("n = 256")
    RandomRelativePrime(256)
    print()
    print("n = 512")
    RandomRelativePrime(512)
