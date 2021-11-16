package hw5;
import hw4.Edge;
import hw4.Graph;

import java.util.*;
import java.io.*;

public class MarvelPaths {
    private Graph Marvel;


    public void createNewGraph(String filename){
        Marvel = new Graph();

        Map<String, Set<String>> charsInBooks = new HashMap<String,Set<String>>();
        Set<String> chars = new HashSet<String>();

        try {
            MarvelParser.readData(filename, charsInBooks, chars);
            MarvelParser.CreateMarvelGraph(Marvel, charsInBooks, chars);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Vector<String> BFS(String node1, String node2){
        if(node1.equals(node2)){
            return new Vector<String>();
        }

        //BFS Algorithm
        Vector<String> Q = new Vector<>();
        Map<String, Vector<String>> Paths = new HashMap<String, Vector<String>>();

        Q.add(node1);
        Paths.put(node1, new Vector<String>(1){{add(node1);}});

        while(!Q.isEmpty()){
            String n = Q.remove(0);
            if(n.equals(node2)){
                return Paths.get(n);
            }
            // Need to sort the edges lexicographically first
            ArrayList<Edge> children = Marvel.listChildren(n);
            for(Edge c : children){
                if(!Paths.containsKey(c.getDest()) ){
                    Vector<String> current_path = Paths.get(n);
                    Vector<String> new_path = new Vector<String>(current_path);
                    new_path.add(c.getDest());
                    Paths.put(c.getDest(), new_path);
                    Q.add(c.getDest());
                }
            }
        }
        return new Vector<String>();
    }


    public String findPath(String node1, String node2){
        if(!Marvel.hasNode(node1) && !Marvel.hasNode(node2)){
            return "unknown character "+ node1+ "\nunknown character "+ node2 + "\n";
        }
        if(!Marvel.hasNode(node1) ){
            return "unknown character "+ node1+ "\n";
        }
        if(!Marvel.hasNode(node2) ){
            return "unknown character "+ node2 + "\n";
        }

        Vector<String> Path = BFS(node1, node2);
        StringBuilder output = new StringBuilder("path from " + node1 + " to " + node2 + ":\n");
        int steps = 0;
        for(int i = 1; i< Path.size(); i++){
            String s = Path.get(i-1);
            String d = Path.get(i);
            Edge e = Marvel.findEdge(s, d);
            String next_line = s + " to "+ d+" via "+ e.getWeight() + "\n";
            output.append(next_line);
            steps+=1;
        }
        if(steps==0 && !node1.equals(node2)){
            output.append("no path found\n");
        }
        //System.out.println(output);
        return output.toString();
    }

}
