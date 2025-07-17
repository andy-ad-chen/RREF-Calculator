
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RowTest {
    private Row testRow1;
    private Row testRow1Scaled;
    private Row testRow2;
    private Row testRow3;
    private Row testRow4;
    private Row testRowSummed;

    private ArrayList<Float> testRow2Vals;

    @BeforeEach
    void runBefore() {
        testRow1 = new Row();
        testRow1.add(1.0f);
        testRow1.add(2.0f);
        testRow1.add(3.0f);

        testRow1Scaled = new Row();
        testRow1Scaled.add(2.0f);
        testRow1Scaled.add(4.0f);
        testRow1Scaled.add(6.0f);

        testRow2 = new Row();
        testRow2.add(5.0f);
        testRow2.add(6.0f);
        testRow2.add(7.0f);
        testRow2.add(7.0f);
        testRow2.add(7.0f);

        testRow2Vals = new ArrayList<>();
        testRow2Vals.add(5.0f);
        testRow2Vals.add(6.0f);
        testRow2Vals.add(7.0f);
        testRow2Vals.add(7.0f);
        testRow2Vals.add(7.0f);

        testRow3 = new Row();
        testRow3.add(4.0f);
        testRow3.add(6.0f);
        testRow3.add(7.0f);
        testRow3.add(7.0f);
        testRow3.add(7.0f);

        testRow4 = new Row();
        testRow4.add(0.0f);
        testRow4.add(0.0f);
        testRow4.add(0.0f);

        testRowSummed = new Row();
        testRowSummed.add(5.0f + 4.0f);
        testRowSummed.add(6.0f + 6.0f);
        testRowSummed.add(7.0f + 7.0f);
        testRowSummed.add(7.0f + 7.0f);
        testRowSummed.add(7.0f + 7.0f);

    }

    @Test
    void testConstructor() {
        assertFalse(testRow1.size() == 5);
        assertNotEquals(testRow1, testRow2);
        assertTrue(testRow1.size() == 3);
    }


    @Test
    void testConstructorCopyCollection() {
        assertFalse(testRow1.size() == 5);
        assertNotEquals(testRow1, testRow2);
        testRow1 = new Row(testRow2Vals);
        assertTrue(testRow1.size() == 5);
        assertEquals(testRow1, testRow2);
    }



    @Test
    void testConstructorCopyRow() {
        assertFalse(testRow1.size() == 5);
        assertNotEquals(testRow1, testRow2);
        testRow1 = new Row(testRow2);
        assertTrue(testRow1.size() == 5);
        assertEquals(testRow1, testRow2);
    }


    @Test
    void testSumRow() {
        assertNotEquals(testRowSummed, testRow2);
        testRow2.sumRow(testRow3);
        assertEquals(testRowSummed, testRow2);
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
        assertNotEquals(testRow1Scaled, testRow1);
        testRow1.scaleRow(2.0f);
        assertEquals(testRow1Scaled, testRow1);
    }
}
