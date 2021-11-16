package hw5;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;



public class MarvelPathsTest {

    MarvelPaths SmallGraph = new MarvelPaths();
    MarvelPaths LargeGraph = new MarvelPaths();

    @Test
    public void ConstructionTest() {
        new MarvelPaths();
    }

    @Test
    public void TestSmallFile() {
        SmallGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw5/data/Marvel10.csv");
        SmallGraph.findPath("FROST, CARMILLA", "M'SHULLA");
        assertEquals(SmallGraph.findPath("FROST, CARMILLA", "M'SHULLA"), "path from FROST, CARMILLA to M'SHULLA:\nFROST, CARMILLA to M'SHULLA via AA2 35\n");
    }

    @Test
    public void TestNoNode1(){
        SmallGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw5/data/Marvel10.csv");
        SmallGraph.findPath("PETERS, SHANA TOC", "SEERESS");
        assertEquals(SmallGraph.findPath("PETERS, SHANA TOC", "SEERESS"), "unknown character PETERS, SHANA TOC\n" );
    }

    @Test
    public void TestNoNode2(){
        SmallGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw5/data/Marvel10.csv");
        SmallGraph.findPath("M'SHULLA", "SEERESS");
        assertEquals(SmallGraph.findPath("M'SHULLA", "SEERESS"), "unknown character SEERESS\n" );
    }

    @Test
    public void TestLargeGraph(){
        LargeGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw5/data/marvel.csv");
        LargeGraph.findPath("FROST, CARMILLA", "SEERESS");
        String result = "path from FROST, CARMILLA to SEERESS:\n" +
                "FROST, CARMILLA to HAWK via AA2 21\n" +
                "HAWK to BANNER, BETTY ROSS T via H2 128\n" +
                "BANNER, BETTY ROSS T to RAVAGE/PROF. GEOFFRE via RH2 3\n" +
                "RAVAGE/PROF. GEOFFRE to SEERESS via M/CP 117/4\n";
        assertEquals(LargeGraph.findPath("FROST, CARMILLA", "SEERESS"), result);
    }

    @Test
    public void TestReflexive(){
        LargeGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw5/data/marvel.csv");
        LargeGraph.findPath("SEERESS", "SEERESS");
    }

    @Test
    public void TestLexicographic(){
        LargeGraph.createNewGraph("/Users/jaredgridley/Dropbox/CSCI2800-PSOFT/Homework/hw5/data/marvel.csv");
        LargeGraph.findPath("PETERS, SHANA TOC", "SEERESS");
        String Result = "path from PETERS, SHANA TOC to SEERESS:\n" +
                "PETERS, SHANA TOC to KNIGHT, MISTY via M/CP 80/3\n" +
                "KNIGHT, MISTY to ALEXANDER, CALEB via N 17\n" +
                "ALEXANDER, CALEB to HULK/DR. ROBERT BRUC via N@ 1/3\n" +
                "HULK/DR. ROBERT BRUC to RAVAGE/PROF. GEOFFRE via RH2 2\n" +
                "RAVAGE/PROF. GEOFFRE to SEERESS via M/CP 117/4\n";
        assertEquals(LargeGraph.findPath("PETERS, SHANA TOC", "SEERESS"), Result);
    }

}



