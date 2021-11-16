'''
Start to the Date class for Lab 9.  This code will not run in Python
until three methods are added:
    __init__,
    __str__
    same_day_in_year
'''

days_in_month = [ 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ]
month_names = [ '', 'January', 'February', 'March', 'April', 'May', 'June', 'July',\
                    'August','September', 'October', 'November', 'December' ]

class Date(object):
    def __init__ (self, year0 = 1900, month0 = 1, day0 = 1):
        '''
        Initializer that sets default date 1/1/1900
        '''
        self.year = year0
        self.month = month0
        self.day = day0
    def __str__ (self):
        #this is the same at the :02d day = str(self.day).rjust(2, '0')
        # this is the same at the :02d month = str(self.month).rjust(2, '0')
        return'{0}/{1:02d}/{2:02d}'.format(self.year, int(self.month), int(self.day))
    
    def same_day_in_year (self, d2):
        '''
        Returns True if the month and day are the same, year can be different
        '''
        if self.month == d2.month and self.day == d2.day:
            return True
        else:
            return False
        
    def is_leap_year(self):
        '''
        Returns true if it is a leap year.
        '''
        if self.year % 4 == 0:
            if self.year % 100 == 0 and self.year % 400 != 0:
                return False
            else:
                return True
        else:
            return False
        
    def __lt__ (self, d2):
        if self.year < d2.year:
            return True
        elif self.year == d2.year:
            if self.month < d2.month:
                return True
            elif self.month == d2.month:
                if self.day < d2.day:
                    return True
                else:
                    return False
            else:
                return False
        else:
            return False


    
if __name__ == "__main__":
    d1 = Date(1972, 3, 27)
    d2 = Date(1998, 4, 13)
    d3 = Date(1996, 4, 13)
    print("d1: " + str(d1))
    print("d2: " + str(d2))
    print("d3: " + str(d3))
    print("d1.same_day_in_year(d2)", d1.same_day_in_year(d2))
    print("d2.same_day_in_year(d3)", d2.same_day_in_year(d3)) 