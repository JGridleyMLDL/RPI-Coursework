package hw4;

import java.util.Comparator;

public class EdgeCompare implements Comparator<Edge> {
    @Override
    public int compare(Edge a, Edge b) {
        Double aw = Double.valueOf(a.getWeight());
        Double bw = Double.valueOf(b.getWeight());
        int weight = aw.compareTo(bw);
        if( weight != 0){
            return weight;
        }else{
            String ad = String.valueOf(a.getDest());
            String bd = String.valueOf(b.getDest());

            return ad.compareTo(bd);
        }
    }
}
