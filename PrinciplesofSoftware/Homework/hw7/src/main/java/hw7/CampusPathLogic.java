package hw7;

import hw4.Edge;
import hw4.Graph;
import hw6.MarvelPaths2;

import java.io.*;
import java.util.*;

public class CampusPathLogic {
    private final Graph G;
    private final HashMap<Integer, RPIPathNode> buildings;
    private final HashMap<String, Integer> Name_map;

    Edge total_path;

    // Abstraction Function:
    // CampusPathLogics represents the logic operations to find and output the path between two RPI buildings.
    // CampusPathsLogic can be empty, if RPi removed all its buildings from campus.
    //
    // if G g == NULL :
    //      The graph was not initialized correctly and will not perform as expected
    // if size of buildings != size of Name_map:
    //      Something went wrong with the initialization and one of the Nodes may be unidentifiable
    //
    // In other words:
    //  * the buildings Hashmap and Name_map hashmap should be equivilent in size because they are both made up
    //            the same nodes in the graph object, so they should both contain the same number of mappings.
    //  * if the graph is null that it cannot look for paths, and the code, when run, will give NullPointerExceptions

    /**
     * Constructor for Campus paths - Initializes each of the variables
     */
    public CampusPathLogic(){
        G = new Graph();
        buildings = new HashMap<>();
        Name_map = new HashMap<>();
        CheckRep();
    }

    /**
     * NodeParser: adds node data to the proper data structures
     * @param file path to node file
     * @throws Exception if file is not found
     * @modifies Graph object, G, adds all of the new node data
     * @effects buildings Hashmap - adds each node to the proper index
     * @effects Name_map Hashmap - adds each nodes data to allow for fast lookup
     *              between Name and id
     */
    public void NodeParser(String file) throws Exception{
        String line;
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null) {
                String[] b = line.split(splitBy);
                if(b[0].equals("")){
                    b[0] = "Intersection " + b[1];
                }

                RPIPathNode n = new RPIPathNode(b[0], b[1], b[2], b[3]);
                buildings.put(Integer.valueOf(b[1]), n);
                if(!b[0].equals("")) {Name_map.put(b[0], Integer.valueOf(b[1]));}
                G.addNode(b[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * EdgeParser: adds edge object to the graph
     * @param file filename for the edge data csv file
     * @effects Graph object G - adds edges to the graph (undirected)
     * @throws Exception if file cannot be found
     */
    public void EdgeParser(String file) throws Exception{
        String line;
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null) {
                String[] e = line.split(splitBy);
                RPIPathNode n1 = buildings.get(Integer.valueOf(e[0]));
                RPIPathNode n2 = buildings.get(Integer.valueOf(e[1]));
                double pixel_dist = n1.distance(n2);
                G.addEdge(new Edge(e[0], e[1], String.valueOf(pixel_dist)));
                G.addEdge(new Edge(e[1], e[0], String.valueOf(pixel_dist)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ShortestPath - helper function to find shortest path and print the output
     * @param n1 - source node
     * @param n2 - destination/target node
     * @effects total_path - sets it to the Edge object with the correct shortest path
     */
    public void ShortestPath(String n1, String n2){
        MarvelPaths2 RPIPaths = new MarvelPaths2(G);
        total_path = RPIPaths.dijkstra(n1, n2);
        PathReconstruction(n1, n2);
    }

    /**
     * PathReconstruction - gives the output for the path from source to destination
     * @effects - prints out the path using the total_path edge object, buildings Hashmap, and distance function
     */
    public void PathReconstruction(String s, String t){
        ArrayList<String> path = total_path.getInfo();
        int src = Integer.parseInt(total_path.getSource());
        int dest = Integer.parseInt(total_path.getDest());

        if(path==null && s != t) {
            System.out.print("There is no path from " + buildings.get(Integer.parseInt(s)).getName() + " to " + buildings.get(Integer.parseInt(t)).getName() + ".\n");
            return;
        }
        String prev = path.get(0);

        StringBuilder Output = new StringBuilder("Path from " + buildings.get(src).getName() + " to " + buildings.get(dest).getName() + ":\n");
        double total_dist = 0;

        for(int i =1; i<path.size(); i++ ){
            String curr = path.get(i);
            String direction = buildings.get(Integer.valueOf(prev)).getDirection(buildings.get(Integer.valueOf(curr)));
            Output.append("\tWalk ").append(direction).append(" to (").append(buildings.get(Integer.valueOf(curr)).getName()).append(")\n");
            total_dist += buildings.get(Integer.valueOf(prev)).distance(buildings.get(Integer.valueOf(curr)));
            prev = curr;

        }
        Output.append(String.format("Total distance: %.3f pixel units.\n", total_dist));
        System.out.print(Output);
    }

    /**
     * listBuildings - outputs all of the building nodes in the graph (excluding intersections) - for 'b' command
     * @effects prints out all of the buildings and their ids
     */
    public void listBuildings(){
        ArrayList<String> b = new ArrayList<>(Name_map.keySet());

        Collections.sort(b);
        StringBuilder output = new StringBuilder();
        for(String s : b){
            if(!s.contains("Intersection")) {
                output.append(s).append(",").append(Name_map.get(s)).append("\n");
            }
        }

        System.out.print(output);
    }

    /**
     * getID -
     * @param n the name of a node we want the id for
     * @return the ID for the node
     */
    public String getID(String n){
        return String.valueOf(Name_map.get(n));
    }

    /**
     * isValid - checks if a node is valid (if it in the graph)
     * @param n - node we are checking validity for  (can be name or id)
     * @return boolean value indicating if it is a valid node
     */
    public boolean isValid(String n){
        if(n.matches("-?\\d+")){
            Integer id = Integer.valueOf(n);
            if(buildings.get(id).getName().contains("Intersection")){
                return false;
            }
            return true;
        }else{
            return Name_map.containsKey(n) && !n.contains("Intersection");
        }
    }

    /**
     * Check representation invariant function
     * @throws RuntimeException if one of the invariants does not hold.
     */
    private void CheckRep(){
        if(G == null){
            throw new RuntimeException("Graph Incorrectly Initialzed, G == null");
        }
        if(buildings.size() != Name_map.size()){
            throw new RuntimeException("Maps do not match. Nodes may not be acessible.");
        }
    }
}
