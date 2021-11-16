package hw4;

import java.util.*;

public class GraphWrapper <T extends Comparable<T>> {
    private Graph graph_object;

    /**
     * @effects Constructs a new Empty graph_wrapper with empth Graph object.
     */
    public GraphWrapper() {
        graph_object = new Graph();
    }

    /**
     * @param nodeData The string values of the node that will be added.
     * @requires new_data != null
     * @modifies The node_list within the graph object, if node is not a duplicate
     * @effects The graph object will contain more nodes than before the method was called.
     */
    public void addNode(String nodeData) {
        ArrayList<String> new_node = new ArrayList<String>(){{add(nodeData);}};

        graph_object.addNodes(new_node);
    }


    /**
     * @param parentNode string of the source node for the edge.
     * @param childNode string of the destination node for the edge
     * @param edgeLabel string of the label/weight for the edge (can be null)
     * @requires parentNode != null && childNode != null
     * @modifies The edge_list within the graph object
     * @effects The graph object will contain more edges than before the method was called.
     * @effects The graph object may also contain more nodes, if the edge contains a node not in graph.
     */
    public void addEdge(String parentNode, String childNode, String edgeLabel){
        Edge<T> new_edge = new Edge(parentNode, childNode, edgeLabel);
        ArrayList<Edge<T>> new_edges = new ArrayList<Edge<T>>(){{add(new_edge);}};

        graph_object.addEdges(new_edges);
    }

    /**
     * @effects creates a new sorted arrayList of the nodes (by calling the corresponding graph method)
     * @returns An iterator pointing to the first element of the sorted list of vertices
     */
    public Iterator<String> listNodes(){
        return graph_object.listNodes();
    }

    /**
     * @param parentNode the node for which we are looking at the children of.
     * @effects creates a new sorted arrayList of the nodes and weights (by calling the corresponding graph method)
     * @returns An iterator pointing to the first element of the sorted list of vertices
     */
    public Iterator<String> listChildren(String parentNode){
        List<String > children = new Vector<>();

        //Go through and find the children
        ArrayList<Edge> edge_itr = graph_object.listChildren(parentNode);
        for(Edge<T> e : edge_itr){
            T dest = e.getDest();
            String weight = e.getWeight();
            Edge ed = graph_object.getEdge(parentNode, dest, weight);

            String new_child = ed.getDest() + "(" + ed.getWeight() + ")";
            children.add(new_child);

        }
        ArrayList<String> all_Children = new ArrayList<>(children);
        Collections.sort(all_Children);
        Iterator<String> itr = all_Children.iterator();

        return itr;
    }
}