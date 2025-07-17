
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class RowNEWTest {
    private RowNEW testRow1;

    private RowNEW testRow2;
    private RowNEW testRow3;
    private RowNEW testRow4;

    private ArrayList<Float> testRow1Vals;
    private ArrayList<Float> testRow1ValsScaled;
    private ArrayList<Float> testRow2Vals;
    private ArrayList<Float> testRow3Vals;
    private ArrayList<Float> testRow4Vals;
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

        testRow4Vals = new ArrayList<>();
        testRow4Vals.add(0.0f);
        testRow4Vals.add(0.0f);
        testRow4Vals.add(0.0f);

        testRowSummedVals = new ArrayList<>();
        testRowSummedVals.add(5.0f + 4.0f);
        testRowSummedVals.add(6.0f + 6.0f);
        testRowSummedVals.add(7.0f + 7.0f);
        testRowSummedVals.add(7.0f + 7.0f);
        testRowSummedVals.add(7.0f + 7.0f);

        testRow1 = new RowNEW(testRow1Vals);
        testRow2 = new RowNEW(testRow2Vals);
        testRow3 = new RowNEW(testRow3Vals);
        testRow4 = new RowNEW(testRow4Vals);

    }

    @Test
    void testConstructor() {
        assertFalse(testRow1.getCol() == 5);
        assertNotEquals(testRow1, testRow2Vals);
        testRow1 = new RowNEW(testRow2Vals);
        assertTrue(testRow1.getCol() == 5);
        assertEquals(testRow1, testRow2Vals);
    }

    @Test
    void testSumRow() {
        assertNotEquals(testRowSummedVals, testRow2);
        testRow2.sumRow(testRow3);
        assertEquals(testRowSummedVals, testRow2);
    }

    @Test
    void testZeroRow() {
        assertTrue(testRow4.zeroRow());
    }

    @Test
    void testZeroRowFalse() {
        assertFalse(testRow3.zeroRow());
    }

    @Test
    void testScaleRow() {
        assertNotEquals(testRow1ValsScaled, testRow1);
        testRow1.scaleRow(2.0f);
        assertEquals(testRow1ValsScaled, testRow1);
    }
}
