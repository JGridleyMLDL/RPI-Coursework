# Author: Jared Gridley
# Created on: 02/14/2021 16:47:01

import random

# Regular (recursive) implementation of modexp


def modexp(x, y, N):
    if y == 0:
        return 1
    z = modexp(x, y//2, N)
    if y % 2 == 0:
        return pow(z, 2) % N
    else:
        return x*pow(z, 2) % N


# Iterative Version of modexp:
# Multiplies z by x, y-1 times and takes the mod of that number (so the num doesn't get too big)
def modexpI(x, y, N):
    z = x
    for i in range(y-1):
        z *= x
        z = z % N

    return z


def BasicPrimality(N, k):
    # Returns yes(True) or no (false)
    a = random.sample(range(1, N), k)
    failed = 0
    for i in range(k):
        # If not equivilent to r=1 for all k, then return false
        if modexp(a[i], N-1, N) % N != 1:
            failed += 1

    print("Basic: N=" + str(N) + ", k=" +
          str(k))
    print("Fraction: " + str((k-failed)/k))
    if(failed == 0):
        return True
    else:
        return False


# Recursive Version of Exponentiation
def MillerRabin(N, k):

    a = random.sample(range(1, N), k)
    # Need to find u, t, s.t. N-1 = u * 2^t
    u = N-1
    t = 0
    while(u % 2 == 0):
        u = u/2
        t += 1

    passe = []
    # Check the numbers for primality
    for i in range(k):
        z = modexp(a[i], u, N) % N
        if z == 1 % N:
            passe.append(a[i])
        else:
            found_1 = False
            for j in range(t):
                z_next = modexp(z, 2, N) % N
                if z_next == 1 and z == N-1:
                    passe.append(a[i])
                    break
                else:
                    z = z_next
    print("Miller Rabin: N=" + str(N) + ", k=" +
          str(k))
    print("Fraction: " + str(len(passe)/k))
    if len(passe) == k:
        return True
    else:
        return False

# Uses the iterative version of the exponentiation


def BasicPrimalityI(N, k):
    # Returns yes(True) or no (false)
    a = random.sample(range(1, N), k)
    failed = 0
    for i in range(k):
        # If not equivilent to r=1 for all k, then return false
        if modexpI(a[i], N-1, N) % N != 1:
            failed += 1

    print("Basic: N=" + str(N) + ", k=" +
          str(k))
    print("Fraction: " + str((k-failed)/k))
    if(failed == 0):
        return True
    else:
        return False

# Uses the iterative version of the expentiation


def MillerRabinI(N, k):

    a = random.sample(range(1, N), k)
    # Need to find u, t, s.t. N-1 = u * 2^t
    u = N-1
    t = 0
    while(u % 2 == 0):
        u = u//2
        t += 1

    passe = []
    # Check the numbers for primality
    for i in range(k):
        z = modexpI(a[i], u, N) % N
        if z == 1 % N:
            passe.append(a[i])
        else:
            found_1 = False
            for j in range(t):
                z_next = modexpI(z, 2, N) % N
                if z_next == 1 and z == N-1:
                    passe.append(a[i])
                    break
                else:
                    z = z_next
    print("Miller Rabin: N=" + str(N) + ", k=" +
          str(k))
    print("Fraction: " + str(len(passe)/k))
    if len(passe) == k:
        return True
    else:
        return False


if __name__ == "__main__":
    Carmichael = [
        561,
        6601,
        67902031,
        8956911601,
        438253965870337,
        999987515379253441]
    '''
    # Python int too large to convert to C ssize_t
    BigCarmichael = [
        365376903642671522645639268043801,
        1334733877147062382486934807105197899496002201113849920496510541601,
        2887148238050771212671429597130393991977609459279722700926516024197432303799152733116328983144639225941977803110929349655578418949441740933805615113979999421542416933972905423711002751042080134966731755152859226962916775325475044445856101949404200039904432116776619949629539250452698719329070373564032273701278453899126120309244841494728976885406024976768122077071687938121709811322297802059565867
    ]
    '''

    print("Testing with Carmichael Numbers", end="\n\n")
    for num in Carmichael:
        for i in [10, 20, 50, 100]:
            print("Is Prime? " +
                  str(BasicPrimality(num, i)))
            print("Is Prime? " + str(MillerRabin(num, i)))
    '''
    print("Testing with Iterative Modular Exponentiation")
    for num in BigCarmichael:
        for i in [10, 20, 50, 100]:
            print("Is Prime? " +
                  str(BasicPrimalityI(num, i)))
            print("Is Prime? " + str(MillerRabinI(num, i)))
    '''
