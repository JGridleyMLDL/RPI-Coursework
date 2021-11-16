package hw4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Edge class.
 */

public class EdgeTest {
    private final double JUNIT_DOUBLE_DELTA = 0.00001;

    // Creating a basic cases to test on.
    private Edge AB1 = new Edge("A", "B", "1");
    private Edge CD12 = new Edge("C", "D");
    private Edge ACDC1999 = new Edge("AC", "DC", "1999");
    private Edge JA7 = new Edge("J", "A", "-7");
    private Edge JESmall = new Edge("13", "19", "0.001002302");
    private Edge Repeat1 = new Edge("T", "U", "0");
    private Edge Repeat2 = new Edge("3", "0", "String098765#$%^");


    @Test
    public void TestConstructorWithoutWeight() {
        new Edge("X", "Y");
        new Edge("1", "2");
        new Edge("This is a really long string", "This is also a really long string");
        new Edge("643816842186153863169631563", "951478536952147869212365852545865");
        new Edge("95147453696521475869521", "StringStringStringStringString");
        new Edge("!@#$%^&*()", ")(*&^%$%^&^%$#@!");
    }


    @Test
    public void TestConstructorWithWeight() {
        new Edge("T", "U", "0");
        new Edge("V", "X", "0.00000000000009");
        new Edge("A", "B", "12.000");
        new Edge("8", "6", "-1");
        new Edge("7", "5", "-1584692467");
        new Edge("3", "0", "StringWeight");
        new Edge("3", "0", "String098765#$%^");
    }

    @Test
    public void TestGettingSource() {
        assertEquals("A", AB1.getSource());
        assertEquals("AC", ACDC1999.getSource());
        assertEquals("13", JESmall.getSource());
    }

    @Test
    public void TestGettingDest() {
        assertEquals("B", AB1.getDest());
        assertEquals("DC", ACDC1999.getDest());
        assertEquals("19", JESmall.getDest());
    }

    @Test
    public void TestGettingWeight() {
        assertEquals("0", Repeat1.getWeight());
        assertEquals("1999", ACDC1999.getWeight());
        assertEquals("String098765#$%^", Repeat2.getWeight());
        assertEquals("-7", JA7.getWeight());
        assertEquals("", CD12.getWeight());
        assertEquals("String098765#$%^", Repeat2.getWeight());
    }

    @Test
    public void TestUpdateWeight() {
        assertEquals("0", Repeat1.getWeight());
        Repeat1.updateWeight("-100");
        assertEquals("-100", Repeat1.getWeight());
        Repeat1.updateWeight("0.0001251467001");
        assertEquals("0.0001251467001", Repeat1.getWeight());
        Repeat1.updateWeight("0987%^&*(TGHN");
        assertEquals("0987%^&*(TGHN", Repeat1.getWeight());

        assertEquals("", CD12.getWeight());
        CD12.updateWeight("13");
        assertEquals("13", CD12.getWeight());
        CD12.updateWeight("13");                //When Weight is already there
        assertEquals("13", CD12.getWeight());
    }

}