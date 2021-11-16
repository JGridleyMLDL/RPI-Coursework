def fib1(n):
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


for n in range(2, 100):
    print(fib1(n-1)/fib1(n))



[0.0002040863037109375, 0.0009319782257080078, 0.006556272506713867, 0.0554046630859375, 0.7025370597839355, 2.704249143600464]
[3.0994415283203125e-06, 7.867813110351562e-06, 1.1920928955078125e-05, 1.9073486328125e-05, 2.5272369384765625e-05, 3.2901763916015625e-05, 3.933906555175781e-05, 4.696846008300781e-05, 5.3882598876953125e-05, 5.3882598876953125e-05, 5.602836608886719e-05, 5.888938903808594e-05]
