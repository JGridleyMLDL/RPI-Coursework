package hw4;
import java.util.*;

/**
 * Graph represents a collection of nodes and edges. It can be manipulated by
 * adding and removing nodes and edges. It can be analyzed by the functions
 * documented below.
 *
 * Examples of Graphs include "((A,B,C,D), ((A,B,1),(B,C,2)))" and "((1,2,3,4), ((2,4,A),(1,3,A)))"
 */
public class Graph <T extends Comparable<T>> {
    private Set<T> nodes;
    private HashMap<T, HashSet<Edge<T>>> edges;
    private HashMap<T, HashMap<T, Double>> dijkstra_weight;


    // Abstraction Function:
    // Graph, g, represents the graph that is made up of Vertices and Edges.
    // If there are no vertices, then the graph represents an empty graph.
    //
    // Representation Invariant for every Graph g:
    // for each node in Nodes:
    //      No other node in Node_list has same E value.
    // for each edge in edges:
    //      Cannot have duplicate edges
    //
    // In other words:
    // * Edges cannot have have duplicates in their
    // * The nodes cannot have duplicate values



    /**
     * @effects Constructs a new Empty graph with empty node_list and edges.
     */
    public Graph() {
        nodes = new HashSet<T>();
        edges = new HashMap<T, HashSet<Edge<T>>>();
        dijkstra_weight = new HashMap<T, HashMap<T, Double>>();

        checkRep();
    }


    /**
     * @param new_nodes The ArrayList of new node(s) to be added
     * @requires new_nodes be of type ArrayList<E>
     * @requires new_nodes.size() >= 1 (if 0, then list does not change)
     * @modifies node_list will increase in size
     * @effects The graph will contain more nodes than before the method was called.
     */
    public void addNodes(ArrayList<T> new_nodes){
        if(new_nodes.size() >= 1) {
            for(int i =0; i <new_nodes.size(); i++){
                if(nodes.add(new_nodes.get(i))){
                    edges.put(new_nodes.get(i), new HashSet<Edge<T>>());
                    dijkstra_weight.put(new_nodes.get(i), new HashMap<T, Double>());
                }
            }
        }
        checkRep();
    }

    /**
     * @param new_node The new node E to be added
     * @requires new_node be of type E
     * @requires new_node not equal to the empty E
     * @modifies node_list will increase in size
     * @effects The graph will contain more nodes than before the method was called.
     * @effects The edges will have a new start E if, node not already present.
     */
    public void addNode(T new_node){
        if(nodes.add(new_node)){
            edges.put(new_node, new HashSet<Edge<T>>());
            dijkstra_weight.put(new_node, new HashMap<T, Double>());
        }
        checkRep();
    }


    /**
     * @param new_edges Arraylist of all of the new edges to add
     * @requires new_edges be of type ArrayList<Edge>
     * @requires new_edges.size() >= 1 (if 0, then list does not change)
     * @modifies edge_list will increase in size, numEdges will increase in value
     * @modifies node_list may increase in size and numVertices may also increase in size
     * @effects The graph will contain more edges (and potentially more nodes).
     */

    public void addEdges(ArrayList<Edge<T>> new_edges) {
        for(int i = 0; i< new_edges.size(); i++){
            this.addEdge(new_edges.get(i));
        }
        checkRep();
    }

    /**
     * @param new_edge Edge to add to Hashmap of Edges
     * @requires new_edges be of type Edge
     * @modifies Edgelist at that hashmap value
     * @modifies node_list may increase in size
     * @effects The graph will contain more edges (and potentially more nodes).
     */

    public void addEdge(Edge<T> new_edge) {
        this.addNode(new_edge.getSource());
        edges.get(new_edge.getSource()).add(new_edge);

        if(!new_edge.getSource().equals(new_edge.getDest())) {

            HashMap<T, Double> h = this.dijkstra_weight.get(new_edge.getSource());

            if(!h.containsKey(new_edge.getDest())){
                h.put(new_edge.getDest(), 1.0);
            }else{
                h.put(new_edge.getDest(), h.get(new_edge.getDest())+1);
            }
        }
        checkRep();
    }


    /**
     * @effects creates a new sorted arrayList of the nodes
     * @returns An iterator pointing to the first element of the sorted list of vertices
     */
    public Iterator<T> listNodes(){
        ArrayList<T> current_nodes = new ArrayList<T>(nodes);
        Collections.sort(current_nodes);

        return current_nodes.iterator();

    }


    /**
     * @param parent the parent node we are looking at the children of
     * @requires parent is is the list of nodes in the graph
     * @returns An iterator pointing to the first element of a list of children edges
     */
    public ArrayList<Edge<T>> listChildren(T parent){
        if(!nodes.contains(parent)){return new ArrayList<Edge<T>>();}

        ArrayList<Edge<T>> child = new ArrayList<Edge<T>>(edges.get(parent).size());
        HashSet<Edge<T>> children = edges.get(parent);
        for(Edge<T> e : children){
            child.add(e);
        }

        return child;
    }

    /**
     * @param src the source node we are looking at
     * @param dest the desination node we are getting the dijkstra value for
     * @requires parent is is the list of nodes in the graph
     * @returns An iterator pointing to the first element of a list of children edges
     */
    public Double DijkstraValue(T src, T dest){
        return dijkstra_weight.get(src).get(dest);
    }

    /**
     * @param h the parent node we are looking at the children of for dijkistra values
     * @requires parent is is the list of nodes in the graph
     * @returns An iterator pointing to the first element of a list of children edges
     */
    public HashMap<T, Double> dij_adj(T h){
        HashMap<T, Double> adj = dijkstra_weight.get(h);
        return adj;
    }

    /**
     * @returns A specified edge in the graph
     */
    public Edge<T> getEdge(T source, T dest, String weight){
        HashSet<Edge<T>> p = edges.get(source);

        HashSet<String> books = new HashSet<String>();
        for(Edge<T> e : p){
            if(e.getSource().equals(source) && e.getDest().equals(dest) && e.getWeight().equals(weight)){
                books.add( e.getWeight());
            }
        }

        ArrayList<String> book = new ArrayList<String>(books);
        Collections.sort(book);


        return new Edge<T>(source, dest, book.get(0));
    }

    public Edge<T> findEdge(T source, T dest){
        HashSet<Edge<T>> p = edges.get(source);

        HashSet<String> books = new HashSet<String>();
        for(Edge<T> e : p){
            if(e.getSource().equals(source) && e.getDest().equals(dest)){
                books.add( e.getWeight());
            }
        }

        ArrayList<String> book = new ArrayList<String>(books);
        Collections.sort(book);


        return new Edge<T>(source, dest, book.get(0));
    }
    public static <I> void sort(ArrayList<I> A, Comparator<? super I> c){

    }

    /**
     * @param n the node we are checking in the graph
     * @returns A bool for whether the graph contains the node.
     */
    public Boolean hasNode(T n){
        if(nodes.contains(n)){
            return true;
        }
        return false;
    }

    /**
     * @returns number of nodes in the graph
     */
    public int NumNodes(){
        return nodes.size();
    }

    /**
     * Checks that the representation invariant holds (if any).
     * @throws RuntimeException if any of the conditions are not met.
     */
    private void checkRep() throws RuntimeException {
        if(nodes == null){
            throw new RuntimeException("Node_List == Null (incorrectly initialized)");
        }
        if(edges == null){
            throw new RuntimeException("Edge_List == Null (incorrectly initialized)");
        }

    }
}