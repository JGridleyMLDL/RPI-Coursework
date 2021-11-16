def star(n):
    print('**')
    if n == 0:
        return
    for i in range(n):
        star(i)


print(star())

# 1 -> 2, 2 -> 4, 3 -> 8

'''
It will print the star 2^(n+1) times. This is because each time it will call star(i) with an increasing amount of
'''
