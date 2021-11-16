package hw5;
import java.util.*;

/**
 * Graph represents a collection of nodes and edges. It can be manipulated by
 * adding and removing nodes and edges. It can be analyzed by the functions
 * documented below.
 *
 * Examples of Graphs include "((A,B,C,D), ((A,B,1),(B,C,2)))" and "((1,2,3,4), ((2,4,A),(1,3,A)))"
 */
public class Graph {
    private Set<String> nodes;
    private HashMap<String, HashSet<Edge>> edges;


    // Abstraction Function:
    // Graph, g, represents the graph that is made up of Vertices and Edges.
    // If there are no vertices, then the graph represents an empty graph.
    //
    // Representation Invariant for every Graph g:
    // for each node in Nodes:
    //      No other node in Node_list has same string value.
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
        nodes = new HashSet<String>();
        edges = new HashMap<String, HashSet<Edge>>();

        checkRep();
    }


    /**
     * @param nodeList The ArrayList of Nodes to be added.
     * @param edgeList The ArrayList of Edges to be added (Start, End, Weight).
     * @requires nodeList of type ArrayList<String>
     * @requires edgeList of type Arraylist<Edge>
     * @effects Constructs a new graph based off of the parameters given.
     */
    public Graph(ArrayList<String> nodeList, ArrayList<Edge> edgeList) {
        nodes = new HashSet<String>(nodeList);
        edges = new HashMap<String, HashSet<Edge>>();
        this.addEdges(edgeList);
        checkRep();
    }


    /**
     * @param new_nodes The ArrayList of new node(s) to be added
     * @requires new_nodes be of type ArrayList<String>
     * @requires new_nodes.size() >= 1 (if 0, then list does not change)
     * @modifies node_list will increase in size
     * @effects The graph will contain more nodes than before the method was called.
     */
    public void addNodes(ArrayList<String> new_nodes){
        if(new_nodes.size() >= 1) {
            for(int i =0; i <new_nodes.size(); i++){
                if(nodes.add(new_nodes.get(i))){
                    edges.put(new_nodes.get(i), new HashSet<Edge>());
                }
            }
        }
        checkRep();
    }

    /**
     * @param new_node The new node String to be added
     * @requires new_node be of type String
     * @requires new_node not equal to the empty string
     * @modifies node_list will increase in size
     * @effects The graph will contain more nodes than before the method was called.
     * @effects The edges will have a new start string if, node not already present.
     */
    public void addNode(String new_node){
        if(nodes.add(new_node)){
            edges.put(new_node, new HashSet<Edge>());
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

    public void addEdges(ArrayList<Edge> new_edges) {
        for(int i = 0; i< new_edges.size(); i++){
            this.addEdge(new_edges.get(i));
            int finalI = i;
            ArrayList<String> s = new ArrayList<String>(){{add(new_edges.get(finalI).getSource()); add(new_edges.get(finalI).getDest());}};

            // Add Edge to both the source and dest lists'.
            for(int j = 0; j<2; j++) {
                this.addNode(s.get(j));
                edges.get(s.get(i)).add(new_edges.get(i));
            }
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

    public void addEdge(Edge new_edge) {
        this.addNode(new_edge.getSource());
        edges.get(new_edge.getSource()).add(new_edge);

        checkRep();
    }



    /**
     * @effects creates a new sorted arrayList of the nodes
     * @returns An iterator pointing to the first element of the sorted list of vertices
     */
    public Iterator<String> listNodes(){
        ArrayList current_nodes = new ArrayList<String>(nodes);
        Collections.sort(current_nodes);

        Iterator<String> itr = current_nodes.iterator();
        return itr;

    }


    /**
     * @param parent the parent node we are looking at the children of
     * @requires parent is is the list of nodes in the graph
     * @returns An iterator pointing to the first element of a list of children edges
     */
    public ArrayList<String> listChildren(String parent){
        if(!nodes.contains(parent)){return new ArrayList<String>();}

        ArrayList<String> child = new ArrayList<String>(edges.get(parent).size());
        HashSet<Edge> children = edges.get(parent);
        for(Edge e : children){
            child.add(e.getDest());
        }
        Collections.sort(child);

        return child;
    }

    /**
     * @returns A specified edge in the graph
     */
    public Edge getEdge(String source, String dest){
        HashSet<Edge> p = edges.get(source);

        HashSet<String> books = new HashSet<String>();
        HashSet<Edge> paths = new HashSet<Edge>();
        for(Edge e : p){
            if(e.getSource().equals(source) && e.getDest().equals(dest)){
                books.add(e.getWeight());
                paths.add(e);
            }
        }

        ArrayList<String> book = new ArrayList<String>(books);
        Collections.sort(book);


        return new Edge(source, dest, book.get(0));
    }

    public Boolean hasNode(String n){
        if(nodes.contains(n)){
            return true;
        }
        return false;
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
