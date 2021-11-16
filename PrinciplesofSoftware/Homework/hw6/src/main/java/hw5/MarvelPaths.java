package hw5;
import hw4.Edge;
import hw4.Graph;

import java.util.*;
import java.io.*;

public class MarvelPaths <T extends Comparable<T>> {
    private Graph<T> Marvel;


    public void createNewGraph(String filename){
        Marvel = new Graph<T>();

        Map<String, Set<String>> charsInBooks = new HashMap<String,Set<String>>();
        Set<String> chars = new HashSet<String>();

        try {
            MarvelParser.readData(filename, charsInBooks, chars);
            MarvelParser.CreateMarvelGraph(Marvel, charsInBooks, chars);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Vector<T> BFS(T node1, T node2){
        if(node1.equals(node2)){
            return new Vector<T>();
        }

        //BFS Algorithm
        Vector<T> Q = new Vector<>();
        Map<T, Vector<T>> Paths = new HashMap<T, Vector<T>>();

        Q.add(node1);
        Paths.put(node1, new Vector<T>(1){{add(node1);}});

        while(!Q.isEmpty()){
            T n = Q.remove(0);
            if(n.equals(node2)){
                return Paths.get(n);
            }
            // Need to sort the edges lexicographically first
            ArrayList<Edge<T>> children = Marvel.listChildren(n);
            for(Edge<T> c : children){
                if(!Paths.containsKey(c.getDest()) ){
                    Vector<T> current_path = Paths.get(n);
                    Vector<T> new_path = new Vector<T>();
                    for(T s : current_path){
                        new_path.add(s);
                    }
                    new_path.add(c.getDest());
                    Paths.put(c.getDest(), new_path);
                    Q.add(c.getDest());
                }
            }
        }
        return new Vector<T>();
    }


    public String findPath(T node1, T node2){
        if(!Marvel.hasNode(node1) && !Marvel.hasNode(node2)){
            return "unknown character "+ node1+ "\nunknown character "+ node2 + "\n";
        }
        if(!Marvel.hasNode(node1) ){
            return "unknown character "+ node1+ "\n";
        }
        if(!Marvel.hasNode(node2) ){
            return "unknown character "+ node2 + "\n";
        }

        Vector<T> Path = BFS(node1, node2);
        String output = "path from " + node1+ " to "+ node2 + ":\n";
        int steps = 0;
        for(int i = 1; i< Path.size(); i++){
            T s = Path.get(i-1);
            T d = Path.get(i);
            Edge<T> e = Marvel.findEdge(s, d);
            String next_line = s + " to "+ d+" via "+ e.getWeight() + "\n";
            output+=next_line;
            steps+=1;
        }
        if(steps==0 && !node1.equals(node2)){
            output+="no path found\n";
        }
        //System.out.println(output);
        return output;
    }

}
