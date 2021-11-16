package hw4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Graph Class
 */

public class GraphTest {
    private final double JUNIT_DOUBLE_DELTA = 0.00001;

    // Creating some Edges to use with the Graph
    private Edge AB = new Edge("A", "B", "1");
    private Edge AC = new Edge("A", "C", "76");
    private Edge CD = new Edge("C", "D", "Long");
    private Edge AA = new Edge("A", "A", "Short");
    private Edge BE = new Edge("B", "E");
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
        {add(AB); add(AC); add(CD); add(AA); add(BE); add(BD); } };
    ArrayList<Edge> Large_list = new ArrayList<Edge>(){
        {add(AB); add(AC); add(CD); add(AA); add(BD); add(BD5); add(CF); add(FA); add(AF); } };
    ArrayList<Edge> Small_SYMBOL_list = new ArrayList<Edge>(){
        {add(Sym1); add(Sym2); add(Sym3);} };
    ArrayList<Edge> Mixed_list = new ArrayList<Edge>(){
        {add(AB); add(AC); add(AA); add(BE); add(Sym3); add(Sym4); add(Num1);} };
    ArrayList<Edge> SmallSYM_Result = new ArrayList<Edge>(){
        {add(AB); add(AC); add(CD); add(Sym1); add(Sym2); add(Sym3);} };

    // Vertex lists to be used with graphs
    ArrayList<String> Empty_Vlist = new ArrayList<String>();
    ArrayList<String> OneElement_Vlist = new ArrayList<>(Arrays.asList("A"));
    ArrayList<String> TwoElement_Vlist = new ArrayList<>(Arrays.asList("A","B"));
    ArrayList<String> Small_Vlist = new ArrayList<>(Arrays.asList("A", "B", "C","D"));
    ArrayList<String> Medium_Vlist = new ArrayList<>(Arrays.asList("A", "B", "C","D", "E","F", "G", "H", "I", "J"));
    ArrayList<String> Large_Vlist = new ArrayList<>(Arrays.asList("A","B","C","E","F","G","H","I","J","5","&","$","LAX"));
    ArrayList<String> Symbols_Vlist = new ArrayList<>(Arrays.asList("!","@","#","$","%","^","&","*","(",")","_","-"));
    ArrayList<String> WithEmptyStr_Vlist = new ArrayList<>(Arrays.asList("a","B","","D","E"));
    ArrayList<String> resultant = new ArrayList<String>(
            Arrays.asList("A","B","C","D","E","F","G","H","I","J","5","&","$","LAX","!","@","#","%","^","*","(",")","_","-")
    );
    ArrayList<String> Sorted_Large = new ArrayList<String>(Arrays.asList("$","&","5","A","B","C","D","E","F","G","H","I","J","LAX"));
    ArrayList<String> SmallandLarge_VList = new ArrayList<String>(Arrays.asList("A", "B", "C","D", "F"));


    //Creating some basic cases to test on.
    private Graph emptyGraph = new Graph();
    private Graph SmallGraph = new Graph(Small_Vlist, Small_list);
    private Graph MediumGraph = new Graph(Medium_Vlist, Medium_list);
    private Graph BigandSmall = new Graph(Large_Vlist, Small_list);
    private Graph SmallandBig = new Graph(Small_Vlist, Large_list);
    private Graph MixedGraph = new Graph(Empty_Vlist, Mixed_list);


    @Test
    public void TestEmptyConstructor() {
        new Graph();
    }

    @Test
    public void TestArgumentConstructor() {
        new Graph(OneElement_Vlist, Empty_list);
        new Graph(TwoElement_Vlist, OneEdge_list);
        new Graph(OneElement_Vlist, OneEdge_list);
        new Graph(Large_Vlist, Empty_list);
        new Graph(Empty_Vlist, Large_list);
        new Graph(Large_Vlist, Mixed_list);
        new Graph(Symbols_Vlist, Mixed_list);
        new Graph(WithEmptyStr_Vlist, Small_SYMBOL_list);
    }

    @Test
    public void TestAddandListNodes_Empty() {
        //Empty to with nodes
        emptyGraph.addNodes(OneElement_Vlist);

        Iterator<String> Empty_test = emptyGraph.listNodes();
        int i = 0;
        while(Empty_test.hasNext()){assertEquals(Empty_test.next(), OneElement_Vlist.get(i)); i++; }
    }

    @Test
    public void TestandListNodes_Duplicates() {
        //trying to add Duplicates
        int i = 0;
        Iterator<String> Before_Sym = BigandSmall.listNodes();
        while(Before_Sym.hasNext()){assertEquals(Before_Sym.next(), Sorted_Large.get(i)); i++;}

        BigandSmall.addNodes(Symbols_Vlist);

        i = 0;
        Iterator<String> AfterSym = BigandSmall.listNodes();
        Collections.sort(resultant);
        while(AfterSym.hasNext()){assertEquals(AfterSym.next(), resultant.get(i)); i++;}
    }

    @Test
    public void TestandListNodes_NoChange() {
        //Adding nodes that were created with the edges (Should be no change)
        int i = 0;
        Iterator<String> Before_med = SmallandBig.listNodes();
        while(Before_med.hasNext()){assertEquals(Before_med.next(), SmallandLarge_VList.get(i)); i++;}

        SmallandBig.addNodes(Small_Vlist);

        i = 0;
        Iterator<String> After_med = SmallandBig.listNodes();
        while(After_med.hasNext()){assertEquals(After_med.next(), SmallandLarge_VList.get(i)); i++;}

    }



}