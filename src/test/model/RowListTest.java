package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class RowListTest {

    protected RowList typicalCase;
    protected Row typicalCaseRow1;
    protected Row typicalCaseRow2;
    protected Row typicalCaseRow3;

    protected RowList typicalCaseRedRef;
    protected Row typicalCaseRow1RedRef;
    protected Row typicalCaseRow2RedRef;
    protected Row typicalCaseRow3RedRef;

    protected RowList zero;
    protected Row zeroRow;

    protected RowList oneByOne;
    protected Row oneByOneRow;

    protected RowList identity;
    protected Row identityRow1;
    protected Row identityRow2;
    protected Row identityRow3;

    @BeforeEach
    void runBefore() {
        typicalCase = new RowList(3);
        typicalCaseRow1 = new Row();
        typicalCaseRow2 = new Row();
        typicalCaseRow3 = new Row();
        typicalCaseRow1.add(1.0f);
        typicalCaseRow1.add(2.0f);
        typicalCaseRow1.add(0.0f);
        typicalCaseRow2.add(0.0f);
        typicalCaseRow2.add(2.0f);
        typicalCaseRow2.add(4.0f);
        typicalCaseRow3.add(0.0f);
        typicalCaseRow3.add(0.0f);
        typicalCaseRow3.add(0.0f);
        typicalCase.add(typicalCaseRow1);
        typicalCase.add(typicalCaseRow2);
        typicalCase.add(typicalCaseRow3);

        typicalCaseRedRef = new RowList(3);
        typicalCaseRow1RedRef = new Row();
        typicalCaseRow2RedRef = new Row();
        typicalCaseRow3RedRef = new Row();
        typicalCaseRow1RedRef.add(1.0f);
        typicalCaseRow1RedRef.add(0.0f);
        typicalCaseRow1RedRef.add(-4.0f);
        typicalCaseRow2RedRef.add(0.0f);
        typicalCaseRow2RedRef.add(1.0f);
        typicalCaseRow2RedRef.add(2.0f);
        typicalCaseRow3RedRef.add(0.0f);
        typicalCaseRow3RedRef.add(0.0f);
        typicalCaseRow3RedRef.add(0.0f);
        typicalCaseRedRef.add(typicalCaseRow1RedRef);
        typicalCaseRedRef.add(typicalCaseRow2RedRef);
        typicalCaseRedRef.add(typicalCaseRow3RedRef);

        zero = new RowList(3);
        zeroRow = new Row();
        zeroRow.add(0f);
        zeroRow.add(0f);
        zeroRow.add(0f);
        zero.add(zeroRow);
        zero.add(zeroRow);
        zero.add(zeroRow);

        oneByOne = new RowList(1);
        oneByOneRow = new Row();
        oneByOneRow.add(1.0f);

        identity = new RowList(3);
        identityRow1 = new Row();
        identityRow2 = new Row();
        identityRow3 = new Row();
        identity.add(identityRow1);
        identity.add(identityRow2);
        identity.add(identityRow3);

    }

    @Test
    void testConstructor() {
        assertFalse(typicalCase.size() == 0);
        RowList typicalCase = new RowList();
        assertTrue(typicalCase.size() == 0);
    }

    @Test
    void testConstructorSize() {
        assertTrue(typicalCase.getWidth() == 3);
        RowList typicalCase = new RowList(5);
        assertFalse(typicalCase.getWidth() == 3);
        assertTrue(typicalCase.getWidth() == 5);
    }

    @Test
    void testConstructorCopy() {
        assertNotEquals(typicalCase, typicalCaseRedRef);
        RowList typicalCase = new RowList(typicalCaseRedRef);
        assertEquals(typicalCase, typicalCaseRedRef);
    }

    @Test
    void testScaleRowZero() {
        Row row = typicalCase.get(0);
        Row scaled = new Row(row);
        scaled.scaleRow(0f);
        assertNotEquals(typicalCase.get(0), scaled);
        typicalCase.scaleRow(0f, 0);
        assertEquals(typicalCase.get(0), scaled);
    }

    @Test
    void testScaleRowOne() {
        Row row = typicalCase.get(0);
        Row scaled = new Row(row);
        assertEquals(typicalCase.get(0), scaled);
        typicalCase.scaleRow(1f, 0);
        assertEquals(typicalCase.get(0), scaled);
    }

    @Test
    void testScaleRowTwo() {
        Row row = typicalCase.get(0);
        Row scaled = new Row(row);
        scaled.scaleRow(2.0f);
        assertNotEquals(typicalCase.get(0), scaled);
        typicalCase.scaleRow(2.0f, 0);
        assertEquals(typicalCase.get(0), scaled);
    }

    @Test
    void testSumRowZero() {
        Row row = typicalCase.get(0);
        Row summed = new Row(row);
        summed.sumRow(zeroRow);
        assertEquals(typicalCase.get(0), summed);
        typicalCase.scaleRow(0.0f, 1);
        typicalCase.sumRow(0, 1);
        assertEquals(typicalCase.get(0), summed);

    }


    @Test
    void testSumRowSelf() {
        Row row = typicalCase.get(0);
        Row summed = new Row(row);
        summed.sumRow(row);
        assertNotEquals(typicalCase.get(0), summed);
        typicalCase.sumRow(0, 0);
        assertEquals(typicalCase.get(0), summed);
    }

    @Test
    void testSumRowElse() {
        Row row1 = typicalCase.get(0);
        Row row2 = typicalCase.get(1);
        Row sum1 = new Row(row1);
        Row sum2 = new Row(row2);
        sum1.sumRow(sum2);
        Row summed = sum1;
        assertNotEquals(typicalCase.get(0), summed);
        typicalCase.sumRow(0, 1);
        assertEquals(typicalCase.get(0), summed);
    }

    //TODO: testSwapRowSelf, testSwapRowElse

    //TODO: use sumRow and scaleRow to easily test subtractRowSelf

}
