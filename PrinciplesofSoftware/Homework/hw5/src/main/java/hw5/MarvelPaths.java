package hw5;

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
            ArrayList<String> children = Marvel.listChildren(n);
            for(String c : children){
                if(!Paths.containsKey(c) ){
                    Object current_path = Paths.get(n).clone();
                    Vector<String> new_path = (Vector<String>) current_path;
                    new_path.add(c);
                    Paths.put(c, new_path);
                    Q.add(c);
                }
            }
        }
        return new Vector<String>();
    }


    public String findPath(String node1, String node2){
        if(!Marvel.hasNode(node1) ){
            return "unknown character "+ node1 + "\n";
        }
        if(!Marvel.hasNode(node2) ){
            return "unknown character "+ node2 + "\n";
        }

        Vector<String> Path = BFS(node1, node2);
        String output = "path from " + node1+ " to "+ node2+":\n";
        int steps = 0;
        for(int i = 1; i< Path.size(); i++){
            String s = Path.get(i-1);
            String d = Path.get(i);
            Edge e = Marvel.getEdge(s, d);
            String next_line = s + " to "+ d+" via "+ e.getWeight()+"\n";
            output+=next_line;
            steps+=1;
        }
        if(steps==0 && !node1.equals(node2)){
            output+="no path founrd\n";
        }
        System.out.println(output);
        return output;
    }

}
