package hw4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the GraphWrapper class.
 * <p>
 */

public class GraphWrapperTest {
    //Using similar basic constructions to test graph because all of the corner
    //cases are the same (since the wrapper is based on the graph)


    // Creating some Edges to use with the Graph
    private Edge AB = new Edge("A", "B", "14");
    private Edge AC = new Edge("A", "C", "7");
    private Edge CD = new Edge("C", "D", "L");
    private Edge AA = new Edge("A", "A", "tomorrow");
    private Edge EB = new Edge("E", "B");
    private Edge BD = new Edge("B", "D");
    private Edge BD5 = new Edge("B", "D", "5");
    private Edge CF = new Edge("C", "F", "0.5");
    private Edge FA = new Edge("F", "A", "7");
    private Edge AF = new Edge("A", "F", "7");
    private Edge Num1 = new Edge("2", "3", "ABC");
    private Edge Sym1 = new Edge(":", "@", "$");
    private Edge Sym2 = new Edge("%", ")", "9");
    private Edge Sym3 = new Edge("*", "@", "7=!");
    private Edge Sym4 = new Edge("#", "Tired", "Always");

    //Edge Lists to be used with graphs
    ArrayList<Edge> Empty_list = new ArrayList<Edge>();
    ArrayList<Edge> OneEdge_list = new ArrayList<Edge>(){{add(AB);}};
    ArrayList<Edge> Small_list = new ArrayList<Edge>(){
        {add(AB); add(AC); add(CD); } };
    ArrayList<Edge> Medium_list = new ArrayList<Edge>(){
        {add(AB); add(AC); add(CD); add(AA); add(EB); add(BD); } };
    ArrayList<Edge> Large_list = new ArrayList<Edge>(){
        {add(AB); add(AC); add(CD); add(AA); add(BD); add(BD5); add(CF); add(FA); add(AF); } };
    ArrayList<Edge> Small_SYMBOL_list = new ArrayList<Edge>(){
        {add(Sym1); add(Sym2); add(Sym3);} };
    ArrayList<Edge> Mixed_list = new ArrayList<Edge>(){
        {add(AB); add(AC); add(AA); add(EB); add(Sym3); add(Sym4); add(Num1);} };
    ArrayList<Edge> SmallSYM_Result = new ArrayList<Edge>(){
        {add(AB); add(AC); add(CD); add(Sym1); add(Sym2); add(Sym3);} };


    // Vertex lists to be used with graphs
    ArrayList<String> Empty_Vlist = new ArrayList<String>();
    ArrayList<String> Simple_Children = new ArrayList<>(Arrays.asList("B(14)", "C(7)", "A(tomorrow)"));
    ArrayList<String> TwoElement_Vlist = new ArrayList<>(Arrays.asList("A","B"));
    ArrayList<String> Small_Vlist = new ArrayList<>(Arrays.asList("A", "B", "C","D"));
    ArrayList<String> Small_Vlist_Empty = new ArrayList<>(Arrays.asList("", "A", "B", "C","D"));
    ArrayList<String> Simple_Children_NoLabel = new ArrayList<>(Arrays.asList("B()", "C()", "A()"));

    GraphWrapper Empty1 = new GraphWrapper();
    GraphWrapper Empty2 = new GraphWrapper();
    GraphWrapper Empty3 = new GraphWrapper();

    @Test
    public void TestConstructor() {
        new GraphWrapper();
    }

    @Test
    public void TestAddNode_Regular() {
        Empty1.addNode("A");
        Empty1.addNode("B");
        int i = 0;
        Iterator<String> With2 = Empty1.listNodes();
        while(With2.hasNext()){assertEquals(With2.next(), TwoElement_Vlist.get(i)); i++;}
        Empty1.addNode("C");
        Empty1.addNode("D");
        i = 0;
        Iterator<String> AfterSmall = Empty1.listNodes();
        while(AfterSmall.hasNext()){assertEquals(AfterSmall.next(), Small_Vlist.get(i)); i++;}
    }

    @Test
    public void TestAddNode_Duplicates() {
        Empty2.addNode("A");
        Empty2.addNode("B");
        Empty2.addNode("B");
        int i = 0;
        Iterator<String> With2 = Empty2.listNodes();
        while(With2.hasNext()){assertEquals(With2.next(), TwoElement_Vlist.get(i)); i++;}
        Empty2.addNode("B");
        Empty2.addNode("C");
        Empty2.addNode("A");
        Empty2.addNode("D");
        i = 0;
        Iterator<String> AfterSmall = Empty2.listNodes();
        while(AfterSmall.hasNext()){assertEquals(AfterSmall.next(), Small_Vlist.get(i)); i++;}
    }

    @Test
    public void TestAddNode_EmptyDuplicates() {
        Empty2.addNode("A");
        Empty2.addNode("");
        Empty2.addNode("B");
        Empty2.addNode("");
        Empty2.addNode("C");
        Empty2.addNode("A");
        Empty2.addNode("D");
        int i = 0;
        Iterator<String> AfterSmall = Empty2.listNodes();
        while(AfterSmall.hasNext()){assertEquals(AfterSmall.next(), Small_Vlist_Empty.get(i)); i++;}
    }

    @Test
    public void TestAddEdge() {
        Empty1.addEdge("A", "B", "14");
        Empty1.addEdge("A", "C", "7");
        Empty1.addEdge("C", "D", "L");
        Empty1.addEdge("A", "A", "tomorrow");
        // Check the Node List for small
        int i = 0;
        Iterator<String> AfterSmall = Empty1.listNodes();
        while(AfterSmall.hasNext()){assertEquals(AfterSmall.next(), Small_Vlist.get(i)); i++;}
    }

    @Test
    public void TestAddEdge_Empty(){
        Empty1.addEdge("", "A", "T-T");
        Empty1.addEdge("A", "B", "14");
        Empty1.addEdge("A", "C", "7");
        Empty1.addEdge("C", "D", "L");
        // Check the Node List for small
        int i = 0;
        Iterator<String> AfterSmall = Empty1.listNodes();
        while(AfterSmall.hasNext()){assertEquals(AfterSmall.next(), Small_Vlist_Empty.get(i)); i++;}
    }

    @Test
    public void TestListChildren() {
        Empty1.addEdge("", "A", "T-T");
        Empty1.addEdge("A", "B", "14");
        Empty1.addEdge("A", "C", "7");
        Empty1.addEdge("C", "D", "L");
        Empty1.addEdge("A", "A", "tomorrow");

        int i = 0;
        Iterator<String> AfterSmall = Empty1.listChildren("A");
        while(AfterSmall.hasNext()){assertEquals(AfterSmall.next(), Simple_Children.get(i)); i++;}
    }

    @Test
    public void TestListChildren_None() {
        Empty1.addEdge("", "A", "T-T");
        Empty1.addEdge("A", "B", "14");
        Empty1.addEdge("A", "C", "7");
        Empty1.addEdge("C", "D", "L");
        Empty1.addEdge("A", "A", "tomorrow");

        int i = 0;
        Iterator<String> AfterSmall = Empty1.listChildren("D");
        while(AfterSmall.hasNext()){assertEquals(AfterSmall.next(), Empty_Vlist.get(i)); i++;}
    }
}