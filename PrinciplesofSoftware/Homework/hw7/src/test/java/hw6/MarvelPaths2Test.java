package hw6;


import hw5.MarvelPaths;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.IOException;

public class MarvelPaths2Test<T>{
    MarvelPaths2 SmallGraph = new MarvelPaths2();
    MarvelPaths2 MedGraph = new MarvelPaths2();

    @Test
    public void TestSmallGraph() throws IOException {
        SmallGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw7/data/Marvel10.csv");
        SmallGraph.findPath("FROST, CARMILLA", "M'SHULLA");
        assertEquals(SmallGraph.findPath("FROST, CARMILLA", "M'SHULLA"), "path from FROST, CARMILLA to M'SHULLA\n"+
        "FROST, CARMILLA to M'SHULLA with weight 1.000\n"+
        "total cost: 1.000");

    }

    @Test
    public void TestNoNode1() throws IOException{
        SmallGraph.createNewGraph("data/Marvel10.csv");
        SmallGraph.findPath("SEERESS", "FROST, CARMILLA");
        assertEquals(SmallGraph.findPath("SEERESS", "FROST, CARMILLA"), "unknown character SEERESS\n" );
    }

    @Test
    public void TestNoNode2() throws IOException{
        SmallGraph.createNewGraph("data/Marvel10.csv");
        SmallGraph.findPath("M'SHULLA", "SEERESS");
        assertEquals(SmallGraph.findPath("M'SHULLA", "SEERESS"), "unknown character SEERESS\n" );
    }

    @Test
    public void TestBothInvalid() throws IOException{
        SmallGraph.createNewGraph("data/Marvel50.csv");
        SmallGraph.findPath("M'SHULLA", "SEERESS");
        assertEquals(SmallGraph.findPath("INVALID1", "INVALID2"), "unknown character INVALID1\nunknown character INVALID2\n" );
    }

    @Test
    public void TestReflexive() throws IOException{
        MedGraph.createNewGraph("data/marvel.csv");
        MedGraph.findPath("SEERESS", "SEERESS");
        assertEquals(MedGraph.findPath("SEERESS", "SEERESS"), "path from SEERESS to SEERESS:\n" );
    }

    @Test
    public void TestLargeGraph() throws IOException{
        MedGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw7/data/marvel.csv");
        MedGraph.findPath("PETERS, SHANA TOC", "SEERESS");
        assertEquals("path from PETERS, SHANA TOC to SEERESS\n"+
                "PETERS, SHANA TOC to KNIGHT, MISTY with weight 1.000\n"+
        "KNIGHT, MISTY to CAGE, LUKE/CARL LUCA with weight 0.017\n"+
        "CAGE, LUKE/CARL LUCA to HULK/DR. ROBERT BRUC with weight 0.032\n"+
        "HULK/DR. ROBERT BRUC to RAVAGE/PROF. GEOFFRE with weight 0.500\n"+
        "RAVAGE/PROF. GEOFFRE to SEERESS with weight 1.000\n"+
        "total cost: 2.549", MedGraph.findPath("PETERS, SHANA TOC", "SEERESS"));
    }
}