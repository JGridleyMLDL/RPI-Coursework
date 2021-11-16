package hw4;

import java.util.Comparator;

public class EdgeCompare<T extends Comparable<T>> implements Comparator<Edge<T>> {
    @Override
    public int compare(Edge<T> a, Edge<T> b) {
        Double aw = Double.valueOf(a.getWeight());
        Double bw = Double.valueOf(b.getWeight());
        int weight = aw.compareTo(bw);
        if( weight != 0){
            return weight;
        }else{
            Comparable ad = a.getDest();
            Comparable bd = b.getDest();
            int dest = ad.compareTo(bd);
            return dest;
        }
    }
}
