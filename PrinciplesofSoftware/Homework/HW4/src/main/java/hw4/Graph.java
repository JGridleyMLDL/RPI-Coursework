package hw4;
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
    private HashMap<String, Vector<Edge>> edges;

    private int numVertices;
    private int numEdges;

    // Abstraction Function:
    // Graph, g, represents the graph that is made up of Vertices and Edges.
    // If there are no vertices, then the graph represents an empty graph.
    //
    // Representation Invariant for every Graph g:
    // for each node in Node_List:
    //      No other node in Node_list has same string value.
    // for each edge in EdgeList:
    //      The source node is in the Node_List
    //      The Edge node is in the Node_list
    //
    // In other words:
    // * All of the Source and Destination nodes for edges in the graph must exist.
    // * The nodes cannot have duplicate values



    /**
     * @effects Constructs a new Empty graph with empty node_list and edges.
     */
    public Graph() {
        nodes = new HashSet<String>();
        edges = new HashMap<String, Vector<Edge>>();

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
        numVertices = nodeList.size();

        nodes = new HashSet<String>(nodeList);
        edges = new HashMap<String, Vector<Edge>>();
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
                addNode(new_nodes.get(i));
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
            edges.put(new_node, new Vector<Edge>());
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
        ArrayList<String> s = new ArrayList<String>(){{add(new_edge.getSource()); add(new_edge.getDest());}};

        // Add Edge to both the source and dest lists'.
        for(int i = 0; i<2; i++) {
            if(!edges.containsKey(s.get(i))){
                this.addNode(s.get(i));
            }
            Vector<Edge> values = edges.get(s.get(i));
            values.add(new_edge);
            edges.replace(s.get(i), values);
        }

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
     * @returns An iterator pointing to the first element of a list of edges
     */
    public Iterator<String> listChildren(String parent){
        Vector<Edge> children = edges.get(parent);
        Vector<String> c = new Vector<String>();

        for(int i = 0; i<children.size(); i++){
            if(children.get(i).getSource() == parent){
                c.add(children.get(i).getDest());
            }else if(children.get(i).getDest() == parent){
                c.add(children.get(i).getSource());
            }
        }

        Iterator<String> itr = c.iterator();
        return itr;

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

        Set<String> keys = edges.keySet();
        String keys_arr[] = new String[keys.size()];
        keys_arr = keys.toArray(keys_arr);

        for(int i = 0; i <keys_arr.length; i++){
            //Check that all the Edge nodes exist
            if(!nodes.contains(keys_arr[i])){
                throw new RuntimeException("Edge has node not in nodes, added incorrectly");
            }

            //Check that all the children in Edges contain the parent
            String parent = keys_arr[i];
            Vector<Edge> children = edges.get(parent);
            for(int j = 0; j<children.size(); j++){
                if(children.get(i).getSource() != parent && children.get(i).getDest() != parent){
                    throw new RuntimeException("Contains child edge that does not contain the parent.");
                }
            }

        }
    }

}
