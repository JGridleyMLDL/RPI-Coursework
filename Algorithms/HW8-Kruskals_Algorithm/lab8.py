import sys
import time


class UnionFindDS:
    size = 0                                  # Num Elements in this union find
    num_trees = 0                             # Num of Components in this union find
    max_rank = 0
    uf = dict()                                  # map[node] = (parent, rank)

    def __init__(self):
        size = 0
        num_trees = 0
        max_rank = 0

    def makeset(self, x):           # Check input for x, might be a problem
        # Creates a new component for x
        # Modifies size trackers (increment)
        self.size += 1
        self.num_trees += 1
        self.uf[x] = [x, 0]

    def find(self, x):
        # Returns the root node of the component containing x
        parent = x
        while(self.uf[parent][0] != parent):
            parent = self.uf[parent][0]
        return parent

    def union(self, x, y):
        # Merges the roots of x and y
        # Modifies root rank for new component
        r_x = self.find(x)
        r_y = self.find(y)

        if r_x == r_y:
            return
        if self.uf[r_x][1] > self.uf[r_x][1]:
            self.uf[r_y][0] = r_x
            self.uf[r_x][1] += 1
            if(self.uf[r_y][1] > self.max_rank):
                self.max_rank = self.uf[r_y][1]
        else:
            self.uf[r_x][0] = r_y
            self.uf[r_y][1] += 1
            if(self.uf[r_y][1] > self.max_rank):
                self.max_rank = self.uf[r_y][1]

        self.num_trees -= 1

    ##############################
    # Path Compression Functions #
    ##############################
    def find_compression(self, x):
        if(self.uf[x][0] != x):
            self.uf[x][0] = self.find_compression(self.uf[x][0])

        return self.uf[x][0]

    def union_compression(self, x, y):
        # Merges the roots of x and y
        # Modifies root rank for new component
        r_x = self.find_compression(x)
        r_y = self.find_compression(y)

        if r_x == r_y:
            return
        if self.uf[r_x][1] > self.uf[r_x][1]:
            self.uf[r_y][0] = r_x
            self.uf[r_x][1] += 1
            if(self.uf[r_y][1] > self.max_rank):
                self.max_rank = self.uf[r_y][1]
        else:
            self.uf[r_x][0] = r_y
            self.uf[r_y][1] += 1
            if(self.uf[r_y][1] > self.max_rank):
                self.max_rank = self.uf[r_y][1]

        self.num_trees -= 1

    def compute_height(self):
        max_height = 0
        for node in self.uf.keys():
            height = 0
            parent = node
            while self.uf[parent][0] != parent:
                parent = self.uf[parent][0]
                height += 1
            if height > max_height:
                max_height = height

        return max_height


def kruskals(G):
    # Input: connected undirected graph G = (V, E) with E = (u, v, weight)
    # Output: A minimum spanning tree defined by edges in X
    start = time.time()
    union_ds = UnionFindDS()

    for v in G[0]:
        union_ds.makeset(v)

    total_weight = 0
    X = set()
    sorted_edges = sorted(G[1], key=lambda x: x[2])
    for e in sorted_edges:
        u = e[0]
        v = e[1]
        if union_ds.find(u) != union_ds.find(v):
            X.add((e[0], e[1]))
            total_weight += e[2]
            union_ds.union(u, v)

    # (Weight, max rank, height, time)
    return (total_weight, union_ds.max_rank - 1, union_ds.compute_height(), time.time()-start)


def kruskals_Compression(G):
    # Input: connected undirected graph G = (V, E) with E = (u, v, weight)
    # Output: A minimum spanning tree defined by edges in X, with path compression
    start = time.time()
    union_ds = UnionFindDS()

    for v in G[0]:
        union_ds.makeset(v)

    total_weight = 0
    X = set()
    sorted_edges = sorted(G[1], key=lambda x: x[2])
    for e in sorted_edges:
        u = e[0]
        v = e[1]
        if union_ds.find_compression(u) != union_ds.find_compression(v):
            X.add((e[0], e[1]))
            total_weight += e[2]
            union_ds.union_compression(u, v)

    # (Weight, max rank, height, time)
    return (total_weight, union_ds.max_rank - 1, union_ds.compute_height(), time.time()-start)


if __name__ == "__main__":
    # Read in file from command line
    print("Testing")

    if len(sys.argv) != 2:
        print("Incorrect arguments: please give a filename")
        exit(1)

    f = open(sys.argv[1], 'r')
    lines = f.readlines()

    V = set()
    E = []
    for row in lines:
        r = row.split()
        V.add(int(r[0]))
        V.add(int(r[1]))
        E.append((int(r[0]), int(r[1]), int(r[2])))

    G = (V, E)
    print(kruskals(G))
