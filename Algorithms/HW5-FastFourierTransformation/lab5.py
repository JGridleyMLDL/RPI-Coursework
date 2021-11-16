'''
Created by Jared Gridley on 8 March 2021

Lab 5: Multiplying Large Polynomials
'''

import random
import time
import cmath
import math


def Alg1(d):
    '''
    Basic Quadratic time algorithm for Polynomial Mult
    Input d: Max degree for polynomials A/B
    Output (result poly coefficients, time for algorithm)
    '''
    start = time.time()
    A = [random.randrange(0, 10, 1) for i in range(d)]
    B = [random.randrange(0, 10, 1) for i in range(d)]

    if d <= 100:
        print("Coefficients of A (Alg1):\n" + str(A))
        print("Coefficients of B (Alg1)\n" + str(B))

    C = [0] * ((2 * d)-1)

    for j in range(d):
        for l in range(d):
            k = j + l
            C[k] = C[k] + (A[j] * B[l])
    # print(A, B, C)
    final_time = time.time() - start

    return (C, final_time)


def FFT(D, w):
    '''
    Fast Fourier Transform for Polynomial Mult
    Input:  n (power of 2, >= 2d-1)
    Output: M_n(w)a
    '''
    if cmath.isclose(w, 1):
        return D

    n = len(D)
    s = FFT(D[::2], pow(w, 2))              # Call with Evens
    s_prime = FFT(D[1::2], pow(w, 2))        # Call with Odds

    r = [0] * n
    for j in range(n//2):
        r[j] = s[j] + (pow(w, j)*s_prime[j])
        r[j+(n//2)] = s[j] - (pow(w, j)*s_prime[j])

    return r


def FFT_helper(d):
    '''
    Helper function for FFT Polynomial Mult
    Input:  n (power of 2, >= 2d-1)
    Output: (result coefficients, algorithm time)
    '''
    # Calculating n
    c = math.ceil(math.log((2*d)-1, 2))
    n = pow(2, c+1)  # Ask about what upper should be

    # Calculating w
    angle = 2. * cmath.pi/n  # Primitive root's angle
    w = cmath.cos(angle) + cmath.sin(angle) * 1j  # Primitive nth root of unity

    # Generating lists of coefficients
    A = [random.randrange(0, 10, 1) for i in range(d)]
    B = [random.randrange(0, 10, 1) for i in range(d)]

    # Printing specifically for lab turn in
    if d <= 100:
        print("Coefficients of A (FFT):\n" + str(A))
        print("Coefficients of B (FFT):\n" + str(B))

    A = A + ([0] * (n-d))
    B = B + ([0] * (n-d))

    start = time.time()
    # Evaluation
    fft_A = FFT(A, w)
    fft_B = FFT(B, w)
    fft_C = [0] * len(fft_A)

    # Sanity check
    # if len(fft_A) != len(fft_B):
    #    print("FAILED")
    #    return -1

    # Multiplication (Element-wise)
    for i in range(len(fft_A)):
        fft_C[i] = fft_A[i] * fft_B[i]

    # Interpolation (Check for more efficient way)
    C = [x for x in FFT(fft_C, pow(w, -1))]
    end = time.time() - start

    result = []
    for y in C:
        if round(y.imag) == 0 and round(y.real) != 0:
            result.append(int(round(y.real) * (1/n)))

    return (result, end)


if __name__ == "__main__":
    print("Let's start Testing")
    #  Precalculting the Values
    Alg1_100 = Alg1(100)
    FFT_100 = FFT_helper(100)
    Alg1_1000 = Alg1(1000)
    FFT_1000 = FFT_helper(1000)
    Alg1_10000 = Alg1(10000)
    FFT_10000 = FFT_helper(10000)

    # Printing the results for d = 100
    print("Results for d = 100")
    print("Alg1: {}".format(Alg1_100[0]))
    print("FFT: {}".format(FFT_100[0]))

    # Printing in formatted way
    print("Alg1 Runtimes: 100: {}, 1000: {}, 10000: {}".format(
        round(Alg1_100[1], 5), round(Alg1_1000[1], 5), round(Alg1_10000[1], 5)))
    print("FFT  Runtimes: 100: {}, 1000: {}, 10000: {}".format(
        round(FFT_100[1], 5), round(FFT_1000[1], 5), round(FFT_10000[1], 5)))
