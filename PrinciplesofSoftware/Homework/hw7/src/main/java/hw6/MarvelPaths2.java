package hw6;

import hw4.EdgeCompare;
import hw4.Graph;
import hw4.Edge;
import hw5.MarvelParser;

import java.io.IOException;
import java.util.*;

public class MarvelPaths2 <T extends Comparable<String>> {
    private Graph Gr;
    PriorityQueue<Edge> PQ;
    Edge total;



    /**
     * Constructor 1 for MarvelPaths2 - default, creates empty graph
     */
    public MarvelPaths2(){
        Gr = new Graph();
    }

    /**
     * Constructor 2 for MarvelPaths2 - used when only want to access dijkstra's algo
     * @param g - already made graph
     * @modifies Gr - sets it equal to G (graph passed in)
     */
    public MarvelPaths2(Graph g){
        Gr = g;
    }

    /**
     * Uses the readData and CreateMarvel graph functions to put data into graph object
     * @param filename - file we are created the marvel graph from
     * @effects uses funcitons in the Marvel parser to add data to the graph object
     * @throws IOException if the file cannot be found
     */
    public void createNewGraph(String filename) throws IOException{
        Gr = new Graph();

        Map<String, Set<String>> charsInBooks = new HashMap<String,Set<String>>();
        Set<String> chars = new HashSet<String>();

        MarvelParser.readData(filename, charsInBooks, chars);
        MarvelParser.CreateMarvelGraph(Gr, charsInBooks, chars);

    }

    /**
     * Dijkstra's algorithm for our node graph
     * @param s - source node
     * @param t - target node
     * @effects PQ - uses the priority queue to traverse through minimal path
     * @effects creates new edges to push and pull with the PQ and eventually return for the min path
     * @return edge object that contains the total distance and path taken
     */
    public Edge dijkstra(String s, String t){
        PQ = new PriorityQueue<Edge>( new EdgeCompare());
        HashSet<String> visited = new HashSet<String>();
        ArrayList<String> start_path = new ArrayList<String>(){{add(s);}};

        PQ.add(new Edge(s, s, "0.0", start_path));

        while(PQ.size()!= 0){
            Edge minPath = PQ.poll();
            String minDest = minPath.getDest();

            if(minDest.equals(t)){
                return minPath;
            }
            if(!visited.contains(minDest)){
                 ArrayList<Edge> adj = Gr.listChildren(minDest);
                 HashMap<String, Double> dij_dist = Gr.dij_adj(minDest);

                 for(Edge e : adj){
                     String child = e.getDest();

                     if(!visited.contains(child)){
                         String path_weight = minPath.getWeight();

                         Double new_weight =  Double.valueOf(path_weight) + 1/dij_dist.get(child);
                         ArrayList<String> info = minPath.getInfo();
                         ArrayList<String> path_info = new ArrayList<String>(){{addAll(info); add(child);}};
                         Edge new_path = new Edge(s, child, String.valueOf(new_weight), path_info);
                         PQ.add(new_path);
                     }
                     visited.add(minDest);
                 }
            }

        }
        return new Edge(s, s, "0.0");
    }

    /**
     * Creates a string of all of the lines for the marvel path
     * @return the output string in the marvel path format
     */
    public String pathReconstruction(){
        ArrayList<String> path = total.getInfo();
        String prev = path.get(0);
        StringBuilder Output = new StringBuilder("path from " + total.getSource() + " to " + total.getDest() + "\n");

        for(int i =1; i<path.size(); i++ ){
            String curr = path.get(i);
            double weight = 1/Gr.DijkstraValue(prev, curr);
            Output.append(prev).append(" to ").append(curr).append(String.format(" with weight %.3f\n", weight));
            prev = curr;

        }
        Output.append(String.format("total cost: %.3f", Double.valueOf(total.getWeight())));
        return Output.toString();

    }

    /**
     * Goes through and checks the nodes for validity and then finds and retruns the path
     * @param node1 - source node
     * @param node2 - target node
     * @effects calls the dijkstra's algorithm above to find the shortest path
     * @return the output string for the terminal
     */
    public String findPath(String node1, String node2){
        if(!Gr.hasNode(node1) && !Gr.hasNode(node2)){
            return ("unknown character "+ node1+ "\nunknown character "+ node2 + "\n");
        }
        if(!Gr.hasNode(node1) ){
            return ("unknown character "+ node1+ "\n");
        }
        if(!Gr.hasNode(node2) ){
            return ("unknown character "+ node2 + "\n");
        }
        if(node1.equals(node2)){
            return "path from " + node1+ " to "+ node2 + ":\n";
        }

        total = dijkstra(node1, node2);

        String output = pathReconstruction();

        return output;
    }



}