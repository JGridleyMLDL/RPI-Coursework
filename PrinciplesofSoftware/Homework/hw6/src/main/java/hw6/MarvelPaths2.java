package hw6;

import hw4.EdgeCompare;
import hw4.Graph;
import hw4.Edge;
import hw5.MarvelParser;

import java.io.IOException;
import java.util.*;

public class MarvelPaths2 <T extends Comparable<T>> {
    private Graph<T> Gr;
    private HashMap<T, String> dist;
    private HashMap<T, T> prev;
    PriorityQueue<Edge<T>> PQ;
    ArrayList<Edge> adj_list;

    public void createNewGraph(String filename){
        Gr = new Graph<T>();

        Map<String, Set<String>> charsInBooks = new HashMap<String,Set<String>>();
        Set<String> chars = new HashSet<String>();

        try {
            MarvelParser.readData(filename, charsInBooks, chars);
            MarvelParser.CreateMarvelGraph(Gr, charsInBooks, chars);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
    public void dijkstra(T s, T t){
        //Initializations
        dist = new HashMap<T, String>();
        prev = new HashMap<T, T>();
        HashSet<T> visited = new HashSet<T>();
        PQ = new PriorityQueue<Edge>(1, new EdgeCompare());


        Iterator<T> itr = Gr.listNodes();
        while(itr.hasNext()){
            T n = itr.next();
            dist.put(n, String.POSITIVE_INFINITY);
            prev.put(n, null);
        }

        PQ.add(new Edge(s, s, 0));
        dist.put(s, 0.0);

        while(PQ.size()!=0){
            //Remove U with the min distance
            Edge<T, I> path = PQ.poll();
            T U = path.getDest();
            adj_list = Gr.listChildren( U);

            visited.add(U);
            if(U.equals(t)){
                return;
            }

            HashMap<T, Integer> dij_dist = Gr.dij_adj(U);
            for(Edge<T, I> e : adj_list){
                T V = e.getDest();
                if(!visited.contains(V)){
                    int W = 1/dij_dist.get(e.getDest());
                    String new_dist = dist.get(U) + W;

                    if(new_dist < dist.get(V)){
                        dist.put(V, new_dist);
                        prev.put(V, U);
                    }
                    if(V.equals(t)){
                        return;
                    }
                    PQ.add(new Edge(s, V, dist.get(V)));
                }
            }
        }

    }
    */
    public Edge<T> dijkstra(T s, T t){
        prev = new HashMap<T, T>();
        PQ = new PriorityQueue<Edge<T>>( new EdgeCompare<T>());
        HashSet<T> visited = new HashSet<T>();

        PQ.add(new Edge<T>(s, s, "0.0"));

        while(PQ.size()!= 0){
            Edge<T> minPath = PQ.poll();
            T minDest = minPath.getDest();

            if(minDest.equals(t)){
                return minPath;
            }
            if(visited.contains(minDest)){
                continue;
            }

            ArrayList<Edge<T>> adj = Gr.listChildren(minDest);
            HashMap<T, Double> dij_dist = Gr.dij_adj(minDest);
            for(Edge<T> e : adj){
                T child = e.getDest();
                if(!visited.contains(child)){
                    String path_weight = minPath.getWeight();

                    Double new_weight =  Double.valueOf(path_weight) + 1/dij_dist.get(child);
                    Edge<T> new_path = new Edge<T>(s, child, String.valueOf(new_weight));
                    PQ.add(new_path);
                    prev.put(child, minDest);

                }
                visited.add(minDest);
            }

        }
        return new Edge<T>(s, s, "0.0");

    }

    public String findPath(T node1, T node2){
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

        Edge<T> path_total = dijkstra(node1, node2);

        String output = "path from " + node1+ " to "+ node2 + ":\n";

        if(path_total.getSource().equals(path_total.getDest())){
            output+="no path found\n";
            return output;
        }

        String path = "";
        T target = node2;
        while(!prev.get(target).equals(node1)){
            T src = prev.get(target);
            path = String.valueOf(src) + " to " + String.valueOf(target) + " with weight " + String.format("%.3f", 1/Gr.DijkstraValue(src, target)) + "\n" + path;
            target = src;
        }
        output += path;
        output += String.format("total cost: %.3f", Double.valueOf(path_total.getWeight()));

        System.out.println(output);

        return output;
    }



}