import sys


class binary_heap:
    # Busting out the old DS homeworks
    def __init__(self):
        # Initialize binary heap with the source as 0
        self.heap = []
        self.size = 0

    def minChild(self, x):
        # Checks for leaf, then which child is smaller
        # Used in percolateUp and Deletemin function
        if (x*2 + 1) > self.size:
            return 2*x
        else:
            if self.heap[2*x][1] < self.heap[self.size][1]:
                return 2*x
            else:
                return (2*x + 1)

    def percolateUp(self, x):
        # Check if value needs to be moved up and swaps if needed (reverse of down)
        # Needed for insert
        # x is the index
        while x//2 > 0:
            if self.heap[x][1] < self.heap[x//2][1]:
                temp = self.heap[x//2]
                self.heap[x//2] = self.heap[x]
                self.heap[x] = temp
            x = x//2

    def percolateDown(self, x):
        # Goes down the graph, swapping if needed until reaches end of tree (reverse of up)
        # Used for the delete min function
        while x*2 <= self.size:
            min_child = self.minChild(x)
            if(self.heap[x][1] > self.heap[min_child][1]):
                temp = self.heap[min_child]
                self.heap[min_child] = self.heap[x]
                self.heap[x] = self.heap[min_child]
            x = min_child

    def insert(self, x):
        self.heap.append(x)
        self.size += 1
        self.percolateUp(self.size)

    def deleteMin(self):
        if self.size == 0:
            return None
        minval = self.heap[0]
        self.heap[0] = self.heap[self.size-1]
        self.size = self.size - 1
        self.heap.pop()
        self.percolateDown(1)

        return minval

    def decrease_key(self, node):
        # Finding the index of the node in the heap
        if self.size == 0:
            return
        i = 0
        while self.heap[i][0] != node:
            i += 1
        # Deacreasing the size
        self.heap[i] = (self.heap[i][0], self.heap[i][1]-1)

    def getSize(self):
        return self.size


def dijkstra_array(G, l, s):
    '''
    Dijkstra's Algorithm
    Input: Graph G = (V, E), edge lengths {l_e: e in E}, source s in V
    Output: For all u in V, dist(u) is the distance from s to u
    '''
    # Equivilent to the for loop in pseudocode, initializing all [dist, prev] to infty/Null
    dist = [float("inf")] * len(G[0])
    prev = [-1] * len(G[0])
    dist[s] = 0

    H = {s: 0}
    while(len(H) != 0):
        u = min(H, key=lambda k: H[k])
        del H[u]
        for e in G[1]:
            if dist[e[1]] > (dist[e[0]] + l[G[1].index(e)]):
                dist[e[1]] = dist[e[0]] + l[G[1].index(e)]
                prev[e[1]] = e[0]
                H[e[1]] = dist[e[1]]

    return (dist, prev)


def dijkstra_heap(G, l, s):
    '''
    Dijkstra's Algorithm
    Input: Graph G = (V, E), edge lengths {l_e: e in E}, source s in V
    Output: For all u in V, dist(u) is the distance from s to u
    '''
    # Equivilent to the for loop in pseudocode, initializing all [dist, prev] to infty/Null
    dist = [float("inf")] * len(G[0])
    prev = [-1] * len(G[0])
    dist[s] = 0

    H = binary_heap()
    H.insert((s, dist[s]))
    while(H.getSize() != 0):
        u = H.deleteMin()
        for e in G[1]:
            if dist[e[1]] > (dist[e[0]] + l[G[1].index(e)]):
                dist[e[1]] = dist[e[0]] + l[G[1].index(e)]
                prev[e[1]] = e[0]
                H.decrease_key(e[1])

    return (dist, prev)


def formatted_print(d, p, s):
    for i in range(len(d)):
        shortest_path = [i]
        j = i
        while shortest_path[0] != s:
            j = p[j]
            if j == -1:
                break
            shortest_path.insert(0, j)
        print(i, d[i], shortest_path)


if __name__ == "__main__":
    # Opening the file
    f = open(sys.argv[1], 'r')
    lines = f.readlines()

    # Extracting the values from the file
    s = 0
    V = set()
    E = []
    l = []
    for row in lines:
        line = row.split()
        u = int(line[0])
        v = int(line[1])
        l_e = int(line[2])
        V.add(u)

        E.append((u, v))
        l.append(l_e)

    G = (V, E)
    output = dijkstra_array(G, l, s)
    formatted_print(output[0], output[1], 0)
