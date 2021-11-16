class Ball(object):
    def __init__(self, x, y, dx, dy, radius, color):
        self.x, self.y = int(x), int(y)
        self.dx, self.dy = int(dx), int(dy)
        self.r = int(radius)
        self.color = color
        
    def position(self):
        return (self.x, self.y)
    
    def move(self):
        self.x = self.x + self.dx
        self.y = self.y + self.dy
        
    def bounding_box(self):
        return (self.x-self.r, self.y-self.r, self.x+self.r, self.y+self.r)
    
    def get_color(self):
        return self.color

    def some_inside(self, maxx, maxy):
        return 0 < self.x + self.r and self.x - self.r < maxx and \
              0 < self.y + self.r and \
              self.y - self.r < maxy
    
    
    def check_and_reverse(self, maxx, maxy):
        if self.x-self.r <= 0 or self.x+self.r >= maxx:
            self.dx = self.dx * -1
        if self.y-self.r <= 0 or self.y+self.r >= maxy:
            self.dy = self.dy * -1