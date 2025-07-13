
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class RowTest {
    private Row testRow1;
    
    private Row testRow2;
    private Row testRow3;
    

    private ArrayList<Float> testRow1Vals;
    private ArrayList<Float> testRow1ValsScaled;
    private ArrayList<Float> testRow2Vals;
    private ArrayList<Float> testRow3Vals;
    private ArrayList<Float> testRowSummedVals;

    @BeforeEach
    void runBefore() {
        testRow1Vals = new ArrayList<>();
        testRow1Vals.add(1.0f);
        testRow1Vals.add(2.0f);
        testRow1Vals.add(3.0f);

        testRow1ValsScaled = new ArrayList<>();
        testRow1ValsScaled.add(2.0f);
        testRow1ValsScaled.add(4.0f);
        testRow1ValsScaled.add(6.0f);

        testRow2Vals = new ArrayList<>();
        testRow2Vals.add(5.0f);
        testRow2Vals.add(6.0f);
        testRow2Vals.add(7.0f);
        testRow2Vals.add(7.0f);
        testRow2Vals.add(7.0f);

        testRow3Vals = new ArrayList<>();
        testRow3Vals.add(4.0f);
        testRow3Vals.add(6.0f);
        testRow3Vals.add(7.0f);
        testRow3Vals.add(7.0f);
        testRow3Vals.add(7.0f);

        testRowSummedVals = new ArrayList<>();
        testRowSummedVals.add(9.0f);
        testRowSummedVals.add(12.0f);
        testRowSummedVals.add(14.0f);
        testRowSummedVals.add(14.0f);
        testRowSummedVals.add(14.0f);

        testRow1 = new Row(3, testRow1Vals);
    

        testRow2 = new Row(5, testRow2Vals);
        testRow3 = new Row(5, testRow3Vals);


    }

    @Test
    void testConstructor() {
        assertFalse(testRow1.getCol() == 5);
        assertFalse(testRow1.getRow() == testRow2Vals);
        testRow1 = new Row(5, testRow2Vals);
        assertTrue(testRow1.getCol() == 5);
        assertTrue(testRow1.getRow() == testRow2Vals);
    }

    @Test
    void testSumRow() {
        ArrayList<Float> a = testRow2.getRow();
        assertFalse(a == testRowSummedVals);
        testRow2.sumRow(testRow3);
        assertTrue(a == testRowSummedVals);
    }


    @Test
    void testScaleRow() {
        ArrayList<Float> a = testRow1.getRow();
        assertFalse(a == testRow1ValsScaled);
        testRow2.scaleRow(2.0f);
        assertTrue(a == testRow1ValsScaled);
    }
}
