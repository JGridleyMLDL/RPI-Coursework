import matplotlib.pyplot as plt
import matplotlib
import pandas as pd
import time


def fib1(n):
    if n == 0:
        return 0
    if n == 1:
        return 1

    return fib1(n-1) + fib1(n-2)


def fib2(n):
    if n == 0:
        return 0

    f = []
    f.append(0)
    f.append(1)

    for i in range(2, n+1):
        next = f[-1] + f[-2]
        f.pop(0)
        f.append(next)

    return f[-1]

def mat_mult2x2(mat1, mat2):
    top = []
    bottom = []

    #Top left element
    top.append((mat1[0][0]*mat2[0][0]) + (mat1[0][1]*mat2[1][0]))
    #Top right element
    top.append((mat1[0][0]*mat2[0][1]) + (mat1[0][1]*mat2[1][1]))
    #Bottom left element
    bottom.append((mat1[1][0]*mat2[0][1]) + (mat1[1][1]*mat2[1][1]))
    #Bottom right element
    bottom.append((mat1[1][0]*mat2[0][1]) + (mat1[1][1]*mat2[1][1]))

    return [top,bottom]


def fib3(n):
    m = [[0,1],[1,1]]
    new_m = m
    #Calculating [-M-]^n
    for i in range(n):
        new_m = mat_mult2x2(new_m, m)
    #Multiplying by the (F_n; F_n+1) vector
    result_vec = [new_m[0][1], new_m[1][1]]

    return result_vec[0]





if __name__ == "__main__":
    #Initializing all the variables
    rows = ["Fib1(n)", "Fib2(n)", "Fib3(n)"]
    n_values = [1, 5, 10, 15, 20, 25, 30, 35, 40, 41, 42, 43]
    exponents = [10,12,14,16, 18, 19]

    runtimes_fib1 = []
    fib1_results = []

    runtimes_fib2 = []
    fib2_results = []

    r_check = []
    quad_Tcheck = []

    runtimes_fib3 = []
    fib3_results = []

    #Running Fib1, Fib2, and Fib3 on the n_values
    for i in range(len(n_values)):
        #Fib1
        start_fib1 = time.time()
        a = fib1(n_values[i])
        runtimes_fib1.append(time.time() - start_fib1)
        fib1_results.append(a)

        #Fib2
        start_fib2 = time.time()
        b = fib2(n_values[i])
        runtimes_fib2.append(time.time() - start_fib2)
        fib2_results.append(b)

        #Fib3
        start_fib3 = time.time()
        c = fib3(n_values[i])
        runtimes_fib3.append(time.time() - start_fib3)
        fib3_results.append(c)

    #Running the exponents on Fib2 to check that it is quadratic
    for q in range(len(exponents)):
        r = pow(2, exponents[q])
        r_check.append(r)
        start_t = time.time()
        t = fib2(r)
        quad_Tcheck.append(time.time() - start_t)

    #Printing the results to be put into tabular and graph format
    print(runtimes_fib1)
    print(runtimes_fib2)
    print(runtimes_fib3)
    print(quad_Tcheck)
