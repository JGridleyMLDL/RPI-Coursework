""" 
    This is the skeleton to demonstrate how to put Lab 9 together. 
    It provides an example to show the use of doctest. Note the function,
    addone(x) presented below has an additional 4 lines after 
    the normal function description. The lines beginning with '>>>'
    indicate examples of how the function can be called, and 
    the lines immediately after represent the expected return
    from the call. So, for example, 'addone(1)' should return '2'
    and 'addone(0) should return 1. These lines provide examples
    for a potential user of the lab9 module, but more importantly
    for this lab, they work with the doctest module to allow us to
    do automated testing. 
    
    Look at the file 'test_driver.py' for an example of how to use
    this testing information. Then come back here and change 
    the answer for one or both of the addone examples to 
    an incorrect value and run the testing again to see how a failing
    test is reported.
    
    ^^
    What he said.
"""

#This is for the Sorted list version
def closest1(L1):
    '''
    This finds the values in a list that are the closest together USING SORTING
    
    >>> closest1([ 15.1, -12.1, 5.4, 11.8, 17.4, 4.3, 6.9 ])
    (4.3, 5.4)
    
    >>> closest1([ 5.4, -12.1, 5.4, 11.8, 17.4, 4.3, 6.9 ])
    (5.4, 5.4)
    '''
    min_difference = [0, 0, 100]
    L2 = sorted(L1)
    if len(L1) < 2:
        min_difference = (None, None)
    else:
        for num in range(len(L1) - 1):
            diff = abs(L2[num] - L2[num+1])
            if diff < min_difference[-1]:
                min_difference = [L2[num], L2[num+1], round(diff, 3)]
                
    return (min_difference[0], min_difference[1])



#This is to do it without sorting the list first. 
def closest2(L1):
    '''
    This finds the values in a list that are the closest together WITHOUT SORTING.
    
    >>> closest2([ 15.1, -12.1, 5.4, 11.8, 17.4, 4.3, 6.9 ])
    (5.4, 4.3)
    
    >>> closest2([ 5.4, -12.1, 5.4, 11.8, 17.4, 4.3, 6.9 ])
    (5.4, 5.4)
    '''
    min_difference = [0, 0, 100]
    if len(L1) < 2:
        min_difference = (None, None)
    else:
        for i in range(len(L1)-1):
            j = i+1    
            while j < (len(L1)-1):
                diff = abs(L1[i] - L1[j])
                if diff < min_difference[-1]:
                    min_difference = [L1[i], L1[j], diff]
                j += 1    
    
    return (min_difference[0], min_difference[1])


    