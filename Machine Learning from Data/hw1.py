'''
Created by Jared Gridley on 9/4/2021
Homework 1
Machine Learning from Data
'''
import random
import itertools
import matplotlib.colors
import numpy as np
from matplotlib import pyplot as plt


class point:
    x = int
    y = int
    classifier = int

    def getX(self) -> np.array:
        return np.array([1, self.x, self.y])


class seperator:
    w1 = int
    w0 = int
    w2 = int

    def __init__(self):
        self.w0 = random.randint(-10, 10)
        self.w1 = random.randint(-20, 20)
        self.w2 = random.randint(-20, 20)

    def testpt(self, p: point) -> int:
        if 0 < (self.w2 * p.y + self.w1 * p.x + self.w0):
            return +1
        else:
            return -1


def calcClass(w: np.array, p: point) -> int:
    x = np.array([1, p.x, p.y])
    dot = np.dot(w, x)
    if dot > 0:
        return +1
    else:
        return -1


def genData(n: int, s):
    # Generates the random, linearly seperable data with n data points.
    data = []

    line = seperator()

    for i in range(n):
        newpt = point()
        newpt.x = random.randint(-20*s, 20*s)
        newpt.y = random.randint(-20*s, 20*s)
        newpt.classifier = line.testpt(newpt)

        data.append(newpt)

    return (data, line)


def runPerceptron(data, n):
    # Intialze the weight vector
    w = np.array([random.uniform(-1*n, n),
                  random.uniform(-10*n, 10*n), random.uniform(-10*n, 10*n)])

    i = 0
    while True:
        x_star = None

        # Check to see if its all done.
        All_classified = True
        for j in range(len(data)):
            randpt = data[j]
            if calcClass(w, randpt) != randpt.classifier:
                All_classified = False
                x_star = randpt
                break
        if All_classified:
            break

        y_star = randpt.classifier

        new_w = w + np.array([y_star * 1, y_star *
                              x_star.x, y_star * x_star.y])

        w = new_w
        i += 1

    return w, i


def printPlot(data, w, w1):
    colors = ["r", "b"]
    label = ["-1", "+1"]
    x = []
    y = []
    color_i = []
    for d in data:
        x.append(d.x)
        y.append(d.y)
        color_i.append(colors[int(d.classifier > 0)])

    plt.scatter(x, y, color=color_i)

    m = (w[1]/w[2]*-1)
    b = (w[0]/w[2]*-1)
    axes = plt.gca()
    x_vals = np.array(axes.get_xlim())
    y_vals = b + m*x_vals
    plt.plot(x_vals, y_vals, '-', label = "Given Weights")

    m1 = (w1[1] / w1[2] * -1)
    b1 = (w1[0] / w1[2] * -1)
    axes1 = plt.gca()
    x_vals1 = np.array(axes1.get_xlim())
    y_vals1 = b1 + m1 * x_vals1
    plt.plot(x_vals1, y_vals1, '--', label = "PLA Weights")

    plt.xlabel("x1 value")
    plt.ylabel("x2 value")
    plt.title("Plot of 100 randomly generated points")
    plt.legend()
    plt.show()


if __name__ == "__main__":
    num = 100
    d = genData(num, 2)
    print([d[1].w0, d[1].w1, d[1].w2])
    r = runPerceptron(d[0], num)
    print(r)
    printPlot(d[0], [d[1].w0, d[1].w1, d[1].w2], r[0])
