package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MatrixTest {
    protected Matrix testMatrix;
    protected Matrix identityMatrix;

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
        identityRow1.add(1.0f);
        identityRow1.add(0.0f);
        identityRow1.add(0.0f);
        identityRow2.add(0.0f);
        identityRow2.add(1.0f);
        identityRow2.add(0.0f);
        identityRow3.add(0.0f);
        identityRow3.add(0.0f);
        identityRow3.add(1.0f);
        identity.add(identityRow1);
        identity.add(identityRow2);
        identity.add(identityRow3);

        testMatrix = new Matrix(typicalCase, 3, "myMatrix", "desc");
        identityMatrix = new Matrix(identity, 3, "myIDMatrix", "desc");

    }

    @Test
    void testConstructor() {
        assertEquals(typicalCase, testMatrix.getRows());
        assertEquals(3, testMatrix.getWidth());
        assertEquals("myMatrix", testMatrix.getMatrixName());
        assertEquals("desc", testMatrix.getMatrixDesc());
    }

    // @Test
    // void testScaleRowZero() {
    // assertNotEquals(zeroRow, testMatrix.getRow(0));
    // testMatrix.scaleRow(0f, 0);
    // assertEquals(zeroRow, testMatrix.getRow(0));
    // }

    @Test
    void testCheckInvertForNonInvertible() {
        testMatrix.checkInvert();
        assertFalse(testMatrix.getInvertible());
    }

    @Test
    void testCheckInvertForInvertible() {
        identityMatrix.checkInvert();
        assertTrue(identityMatrix.getInvertible());
    }

    @Test
    void testComputeRedRefForTest() {
        testMatrix.computeRedRef();
        assertEquals(typicalCaseRedRef, testMatrix.getRedRefRows());
    }

    @Test
    void testComputeRedRefForIdentity() {
        identityMatrix.computeRedRef();
        assertEquals(identity, identityMatrix.getRedRefRows());
    }

    @Test
    void testChangeMatrixName() {
        assertNotEquals("asdf", testMatrix.getMatrixName());
        testMatrix.changeMatrixName("asdf");
        assertEquals("asdf", testMatrix.getMatrixName());
    }

    @Test
    void testChangeMatrixNameTwice() {
        assertNotEquals(testMatrix.getMatrixName(), "asdf");
        testMatrix.changeMatrixName("asdf");
        assertEquals(testMatrix.getMatrixName(), "asdf");
        assertNotEquals(testMatrix.getMatrixName(), "yourmom");
        testMatrix.changeMatrixName("yourmom");
        assertEquals(testMatrix.getMatrixName(), "yourmom");
    }

    @Test
    void testChangeMatrixDesc() {
        assertNotEquals("asdf", testMatrix.getMatrixDesc());
        testMatrix.changeMatrixDesc("asdf");
        assertEquals("asdf", testMatrix.getMatrixDesc());
    }

    @Test
    void testChangeMatrixDescTwice() {
        assertNotEquals(testMatrix.getMatrixDesc(), "asdf");
        testMatrix.changeMatrixDesc("asdf");
        assertEquals(testMatrix.getMatrixDesc(), "asdf");
        assertNotEquals(testMatrix.getMatrixDesc(), "yourmom");
        testMatrix.changeMatrixDesc("yourmom");
        assertEquals(testMatrix.getMatrixDesc(), "yourmom");
    }



    @Test
    void testRowGet() {
        assertEquals(typicalCase, testMatrix.getMatrixRows());
    }

}
