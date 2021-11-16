package hw4;

/**
 * Edge represents a data structure that contains the source node, destination nodes
 * and weight. It is a part of the graph class.
 *
 * Examples of Edges include "("A", "B", "12")" and "("#", "%", "179")"
 */
public class Edge {
    private final String source;
    private final String dest;
    private String label;

    // Abstraction Function:
    //   An Edge e is NaN is composed of a Source (String), Destination (String)
    //   and potentially an Edge Label (String).
    //
    //
    // Representation invariant for every RatNum r:
    //   Source_Node != null && dest != null
    //   In other words,
    //     * source node must exist with string value
    //     * destination node must exist with string value.



    /**
     * @param s The source node for the edge
     * @param d The destination node for the edge
     * @param w The label for the edge
     * @requires s != NULL && s != "" && d != null && d != ""
     * @effects Constructs a new Edge with given values, or 0 for weight
     *      if not specified.
     */
    public Edge(String s, String d, String w) {
       source = s;
       dest = d;
       label = w;
       checkRep();
    }


    /**
     * @param s The source node for the edge
     * @param d The destination node for the edge
     * @requires s != NULL && s != "" && d != null && d != ""
     * @effects Constructs a new Edge with given values, leaves weight as null.
     */
    public Edge(String s, String d) {
        source = s;
        dest = d;
        checkRep();
    }


    /**
     * @returns The source node string.
     */
    public String getSource(){
        return this.source;
    }


    /**
     * @returns The destination node as its string value.
     */
    public String getDest(){
        return this.dest;
    }


    /**
     * @returns The double value of the weight if it is not set to null.
     *          If it is set to null, then returns positive infinity.
     */
    public String getWeight(){
        if(label == null) {
            return "";
        }else{
            return this.label;
        }
    }


    /**
     * @param w The new weight of the edge.
     * @requires w != null
     * @modifies The current weight value will be replaced by the new value.
     * @effects The edge object will now have teh updated weight from the parameter.
     */
    public void updateWeight(String w) {
        label = w;
        checkRep();
    }


    /**
     * Checks that the representation invariant holds (if any).
     * @throws RuntimeException if any of the conditions are not met.
     */
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (source == null) {
            throw new RuntimeException("Source Node == null");
        }
        if (dest == null) {
            throw new RuntimeException("Destination Node == null");
        }
    }
}
