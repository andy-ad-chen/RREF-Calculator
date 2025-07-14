package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class MatrixTest {
    private Matrix testMatrix;
    private Matrix baseMatrix;
    private Matrix identityMatrix;
    private Matrix zeroMatrix;

    private ArrayList<Float> listFloatTypical1 = new ArrayList<>();
    private ArrayList<Float> listFloatTypical2 = new ArrayList<>();
    private ArrayList<Float> listFloatTypical3 = new ArrayList<>();

    private ArrayList<Float> listFloatTypical1RedRef = new ArrayList<>();
    private ArrayList<Float> listFloatTypical2RedRef = new ArrayList<>();
    private ArrayList<Float> listFloatTypical3RedRef = new ArrayList<>();

    private ArrayList<Float> listFloatTypicalScaled = new ArrayList<>();
    private ArrayList<Float> listFloatTypicalSumFirstSecondRow = new ArrayList<>();
    private ArrayList<Float> zeroList = new ArrayList<>();

    private ArrayList<Float> oneByOneList = new ArrayList<>();

    private ArrayList<Float> listIdentity1 = new ArrayList<>();
    private ArrayList<Float> listIdentity2 = new ArrayList<>();
    private ArrayList<Float> listIdentity3 = new ArrayList<>();

    private ArrayList<Row> typicalTestVals = new ArrayList<>();
    private ArrayList<Row> baseVals = new ArrayList<>();
    private ArrayList<Row> identityVals = new ArrayList<>();
    private ArrayList<Row> zeroVals = new ArrayList<>();
    private ArrayList<Row> redRefVals = new ArrayList<>();

    private Row typicalCaseRow1;
    private Row typicalCaseRow2;
    private Row typicalCaseRow3;
    private Row typicalCaseRow1RedRef;
    private Row typicalCaseRow2RedRef;
    private Row typicalCaseRow3RedRef;
    private Row typicalCaseScaled;
    private Row typicalCaseSummed;
    private Row zeroRow;

    private Row oneByOneRow;

    private Row identity1;
    private Row identity2;
    private Row identity3;

    @BeforeEach
    void runBefore() {
        listFloatTypical1.add(1.0f);
        listFloatTypical1.add(2.0f);
        listFloatTypical1.add(0.0f);

        listFloatTypical2.add(0.0f);
        listFloatTypical2.add(2.0f);
        listFloatTypical2.add(4.0f);

        listFloatTypical3.add(0.0f);
        listFloatTypical3.add(0.0f);
        listFloatTypical3.add(0.0f);

        listFloatTypical1RedRef.add(1.0f);
        listFloatTypical1RedRef.add(2.0f);
        listFloatTypical1RedRef.add(0.0f);

        listFloatTypical2RedRef.add(0.0f);
        listFloatTypical2RedRef.add(2.0f);
        listFloatTypical2RedRef.add(4.0f);

        listFloatTypical3RedRef.add(0.0f);
        listFloatTypical3RedRef.add(0.0f);
        listFloatTypical3RedRef.add(0.0f);

        listFloatTypicalScaled.add(2.0f);
        listFloatTypicalScaled.add(4.0f);
        listFloatTypicalScaled.add(0.0f);

        listIdentity1.add(1.0f);
        listIdentity1.add(0.0f);
        listIdentity1.add(0.0f);
        listIdentity2.add(0.0f);
        listIdentity2.add(1.0f);
        listIdentity2.add(0.0f);
        listIdentity3.add(0.0f);
        listIdentity3.add(0.0f);
        listIdentity3.add(1.0f);

        listFloatTypicalSumFirstSecondRow.add(1.0f);
        listFloatTypicalSumFirstSecondRow.add(4.0f);
        listFloatTypicalSumFirstSecondRow.add(4.0f);

        zeroList.add(0f);
        zeroList.add(0f);
        zeroList.add(0f);

        oneByOneList.add(1.0f);

        typicalCaseRow1 = new Row(3, listFloatTypical1);
        typicalCaseRow2 = new Row(3, listFloatTypical1);
        typicalCaseRow3 = new Row(3, listFloatTypical3);

        typicalCaseRow1RedRef = new Row(3, listFloatTypical1RedRef);
        typicalCaseRow2RedRef = new Row(3, listFloatTypical1RedRef);
        typicalCaseRow3RedRef = new Row(3, listFloatTypical3RedRef);

        identity1 = new Row(3, listIdentity1);
        identity2 = new Row(3, listIdentity1);
        identity3 = new Row(3, listIdentity3);

        typicalCaseScaled = new Row(3, listFloatTypicalScaled);

        typicalCaseSummed = new Row(3, listFloatTypicalSumFirstSecondRow);

        zeroRow = new Row(3, zeroList);

        oneByOneRow = new Row(1, oneByOneList);

        typicalTestVals.add(typicalCaseRow1);
        typicalTestVals.add(typicalCaseRow2);
        typicalTestVals.add(typicalCaseRow3);

        redRefVals.add(typicalCaseRow1RedRef);
        redRefVals.add(typicalCaseRow2RedRef);
        redRefVals.add(typicalCaseRow3RedRef);

        identityVals.add(identity1);
        identityVals.add(identity2);
        identityVals.add(identity3);

        zeroVals.add(zeroRow);
        zeroVals.add(zeroRow);
        zeroVals.add(zeroRow);

        baseVals.add(oneByOneRow);

        testMatrix = new Matrix(typicalTestVals, 3, "myMatrix", "this is a matrix desc");
        baseMatrix = new Matrix(baseVals, 1, "name", "desc");
        identityMatrix = new Matrix(identityVals, 3, "identity", "this is the identity Matrix 3x3");
        zeroMatrix = new Matrix(zeroVals, 3, "zero", "this is the zero Matrix 3x3");
    }

    @Test
    void testConstructor() {

        assertEquals(typicalTestVals, testMatrix.getRows());
        assertEquals(3, testMatrix.getCols());
        assertEquals("myMatrix", testMatrix.getMatrixName());
        assertEquals("this is a matrix desc", testMatrix.getMatrixDesc());
    }

    @Test
    void testConstructorZeroMatrix() {

        assertEquals(baseVals, baseMatrix.getRows());
        assertEquals(1, baseMatrix.getCols());
        assertEquals("name", baseMatrix.getMatrixName());
        assertEquals("desc", baseMatrix.getMatrixDesc());
    }

    @Test
    void testScaleRowZero() {
        assertNotEquals(zeroRow, testMatrix.getRow(0));
        testMatrix.scaleRow(0f, 0);
        assertEquals(zeroRow.getFloatArray(), testMatrix.getRow(0).getFloatArray());
    }

    // @Test
    // void testScaleRowZero() {
    //     assertNotEquals(zeroRow, testMatrix.getRow(0));
    //     testMatrix.scaleRow(0f, 0);
    //     assertEquals(zeroRow, testMatrix.getRow(0));
    // }

    @Test
    void testScaleRowByOne() {
        assertEquals(typicalCaseRow1, testMatrix.getRow(0));
        testMatrix.scaleRow(1f, 0);
        assertEquals(typicalCaseRow1, testMatrix.getRow(0));
    }

    @Test
    void testScaleRowByTwo() {
        assertFalse(typicalCaseScaled == testMatrix.getRow(0));
        testMatrix.scaleRow(2f, 0);
        assertEquals(typicalCaseScaled, testMatrix.getRow(0));
    }

    @Test
    void testSumRowOneTwo() {
        assertFalse(typicalCaseSummed == testMatrix.getRow(0));
        testMatrix.sumRow(0, 1);
        assertTrue(typicalCaseSummed == testMatrix.getRow(0));
    }

    @Test
    void testSumRowOneOne() {
        assertFalse(typicalCaseScaled == testMatrix.getRow(0));
        testMatrix.sumRow(0, 0);
        assertTrue(typicalCaseScaled == testMatrix.getRow(0));
    }

    @Test
    void testCheckInvertForNonInvertible() {
        assertFalse(testMatrix.getInvertible());
    }

    @Test
    void testCheckInvertForInvertible() {
        assertTrue(identityMatrix.getInvertible());
    }

    @Test
    void testCheckInvertForBase() {
        assertTrue(baseMatrix.getInvertible());
    }

    @Test
    void testCheckInvertForZero() {
        assertFalse(zeroMatrix.getInvertible());
    }

    @Test
    void testComputeRedRefForTest() {
        assertEquals(redRefVals, testMatrix.getMatrixRows());
    }

    @Test
    void testComputeRedRefForIdentity() {
        assertEquals(identityMatrix.getRedRefRows(), identityMatrix.getMatrixRows());
    }

    @Test
    void testComputeRedRefForZero() {
        assertEquals(zeroMatrix.getRedRefRows(), zeroMatrix.getMatrixRows());
    }

    @Test
    void testComputeRedRefForBase() {
        assertEquals(baseMatrix.getRedRefRows(), baseMatrix.getMatrixRows());
    }

    @Test
    void testChangeMatrixName() {
        assertFalse(testMatrix.getMatrixName() == "asdf");
        testMatrix.changeMatrixName("asdf");
        assertTrue(testMatrix.getMatrixName() == "asdf");
    }

    @Test
    void testChangeMatrixNameTwice() {
        assertFalse(testMatrix.getMatrixName() == "asdf");
        testMatrix.changeMatrixName("asdf");
        assertTrue(testMatrix.getMatrixName() == "asdf");
        assertFalse(testMatrix.getMatrixName() == "yourmom");
        testMatrix.changeMatrixName("yourmom");
        assertTrue(testMatrix.getMatrixName() == "yourmom");
    }

    @Test
    void testChangeMatrixDesc() {
        assertFalse(testMatrix.getMatrixDesc() == "asdf");
        testMatrix.changeMatrixDesc("asdf");
        assertTrue(testMatrix.getMatrixDesc() == "asdf");
    }

    @Test
    void testChangeMatrixDescTwice() {
        assertFalse(testMatrix.getMatrixDesc() == "asdf");
        testMatrix.changeMatrixDesc("asdf");
        assertTrue(testMatrix.getMatrixDesc() == "asdf");
        assertFalse(testMatrix.getMatrixDesc() == "yourmom");
        testMatrix.changeMatrixDesc("yourmom");
        assertTrue(testMatrix.getMatrixDesc() == "yourmom");
    }

}
