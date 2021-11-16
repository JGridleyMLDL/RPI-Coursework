import sys


def Explore(Graph, node, visited, ccs, ccnum, pre_numbering, post_numbering, clock):
    visited.add(node)
    # Pre-visit
    pre_numbering[node] = clock
    clock += 1
    ccs[ccnum].add(node)

    # Recurisvely Exploring New nodes
    for out_node in Graph[node]:
        if out_node not in visited:
            clock = Explore(Graph, out_node, visited, ccs, ccnum,
                            pre_numbering, post_numbering, clock)

    # Post-visit
    post_numbering[node] = clock
    clock += 1

    return clock


def DFS(Graph, sorted_keys=[]):
    visited = set()         # Visited Vertices
    # List of sets for connected componets
    ccs = list()
    ccnum = -1
    pre_numbering = dict()  # Mapping node keys to pre num
    post_numbering = dict()  # node keys to post num

    clock = 0
    if(len(sorted_keys) == 0):
        sorted_keys = Graph.keys()

    for node in sorted_keys:
        if node not in visited:
            ccs.append(set())
            ccnum += 1
            # Explore Operations start here - calling recursive function
            clock = Explore(Graph, node, visited, ccs, ccnum,
                            pre_numbering, post_numbering, clock)

    return (ccs, pre_numbering, post_numbering)


def Start(filename):
    graph = dict()
    reverse_graph = dict()

    # Creating the graph and reverse graph
    with open(filename, 'r') as f:
        lines = f.readlines()
        for l in lines:
            ls = l.split()
            u = ls[0]
            v = ls[1]
            if u not in graph.keys():
                graph[u] = set()
            graph[u].add(v)
            if v not in reverse_graph.keys():
                reverse_graph[v] = set()
            reverse_graph[v].add(u)

    # Running DFS on the graph
    DFS_graph = DFS(graph)
    # Sort all the nodes from highest post num to lowest
    sorted_post = sorted(DFS_graph[2], key=DFS_graph[2].get)
    # DFS on graph reverse
    DFS_reverse_graph = DFS(reverse_graph, sorted_post)

    # Printing out the SCCs
    for i in range(len(DFS_reverse_graph[0])):
        # CHECK THE LAST PART may be sorted_post[i]??
        print(i, len(DFS_reverse_graph[0][i]), DFS_graph[0][i])

    # Determining DAGs in the SCCs
    scc = DFS_reverse_graph[0]
    for component in scc:
        for u in component:
            for v in graph[u]:
                if v not in component:
                    print(u, v)
    return 0


if __name__ == "__main__":
    # Input for the graph functions
    sys.setrecursionlimit(2000)

    if len(sys.argv) != 2:
        print(
            "Incorrent number of arguements (Usage): python3 lab6.py [filename]")
    else:
        Start(sys.argv[1])

    print("Let's start Testing")
