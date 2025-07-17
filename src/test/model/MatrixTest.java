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

    private ArrayList<RowLEGACY> typicalTestVals = new ArrayList<>();
    private ArrayList<RowLEGACY> baseVals = new ArrayList<>();
    private ArrayList<RowLEGACY> identityVals = new ArrayList<>();
    private ArrayList<RowLEGACY> zeroVals = new ArrayList<>();
    private ArrayList<RowLEGACY> redRefVals = new ArrayList<>();

    private RowLEGACY typicalCaseRow1;
    private RowLEGACY typicalCaseRow2;
    private RowLEGACY typicalCaseRow3;
    private RowLEGACY typicalCaseRow1RedRef;
    private RowLEGACY typicalCaseRow2RedRef;
    private RowLEGACY typicalCaseRow3RedRef;
    private RowLEGACY typicalCaseScaled;
    private RowLEGACY typicalCaseSummed;
    private RowLEGACY zeroRow;

    private RowLEGACY oneByOneRow;

    private RowLEGACY identity1;
    private RowLEGACY identity2;
    private RowLEGACY identity3;

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
        listFloatTypical1RedRef.add(0.0f);
        listFloatTypical1RedRef.add(-4.0f);

        listFloatTypical2RedRef.add(0.0f);
        listFloatTypical2RedRef.add(1.0f);
        listFloatTypical2RedRef.add(2.0f);

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

        typicalCaseRow1 = new RowLEGACY(3, listFloatTypical1);
        typicalCaseRow2 = new RowLEGACY(3, listFloatTypical2);
        typicalCaseRow3 = new RowLEGACY(3, listFloatTypical3);

        typicalCaseRow1RedRef = new RowLEGACY(3, listFloatTypical1RedRef);
        typicalCaseRow2RedRef = new RowLEGACY(3, listFloatTypical2RedRef);
        typicalCaseRow3RedRef = new RowLEGACY(3, listFloatTypical3RedRef);

        identity1 = new RowLEGACY(3, listIdentity1);
        identity2 = new RowLEGACY(3, listIdentity2);
        identity3 = new RowLEGACY(3, listIdentity3);

        typicalCaseScaled = new RowLEGACY(3, listFloatTypicalScaled);

        typicalCaseSummed = new RowLEGACY(3, listFloatTypicalSumFirstSecondRow);

        zeroRow = new RowLEGACY(3, zeroList);

        oneByOneRow = new RowLEGACY(1, oneByOneList);

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
        assertNotEquals(zeroRow.getFloatArray(), testMatrix.getRow(0).getFloatArray());
        testMatrix.scaleRow(0f, 0);
        assertEquals(zeroRow.getFloatArray(), testMatrix.getRow(0).getFloatArray());
    }

    // @Test
    // void testScaleRowZero() {
    // assertNotEquals(zeroRow, testMatrix.getRow(0));
    // testMatrix.scaleRow(0f, 0);
    // assertEquals(zeroRow, testMatrix.getRow(0));
    // }

    @Test
    void testScaleRowByOne() {
        assertEquals(typicalCaseRow1.getFloatArray(), testMatrix.getRow(0).getFloatArray());
        testMatrix.scaleRow(1f, 0);
        assertEquals(typicalCaseRow1.getFloatArray(), testMatrix.getRow(0).getFloatArray());
    }

    @Test
    void testScaleRowByTwo() {
        assertNotEquals(typicalCaseScaled.getFloatArray(), testMatrix.getRow(0).getFloatArray());
        testMatrix.scaleRow(2f, 0);
        assertEquals(typicalCaseScaled.getFloatArray(), testMatrix.getRow(0).getFloatArray());
    }

    @Test
    void testSumRowOneTwo() {
        assertNotEquals(typicalCaseSummed.getFloatArray(), testMatrix.getRow(0).getFloatArray());
        testMatrix.sumRow(0, 1);
        assertEquals(typicalCaseSummed.getFloatArray(), testMatrix.getRow(0).getFloatArray());
    }

    @Test
    void testSumRowOneOne() {
        assertNotEquals(typicalCaseScaled.getFloatArray(), testMatrix.getRow(0).getFloatArray());
        testMatrix.sumRow(0, 0);
        assertEquals(typicalCaseScaled.getFloatArray(), testMatrix.getRow(0).getFloatArray());
    }

    @Test
    void testSubtractRowOneOne() {
        assertNotEquals(zeroRow.getFloatArray(), testMatrix.getRow(0).getFloatArray());
        testMatrix.subtractRow(0, 0, -1.0f);
        assertEquals(zeroRow.getFloatArray(), testMatrix.getRow(0).getFloatArray());
    }

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
    void testCheckInvertForBase() {
        baseMatrix.checkInvert();
        assertTrue(baseMatrix.getInvertible());
    }

    @Test
    void testCheckInvertForZero() {
        assertFalse(zeroMatrix.getInvertible());
    }

    // TODO
    @Test
    void testRowSorterZeroMatrix() {
        Matrix cloned = new Matrix(zeroMatrix.deepClone(), zeroMatrix.getCols(), "test", "test");
        zeroMatrix.computeRedRef();
        zeroMatrix.rowSorter();
        for (int i = 0; i < zeroMatrix.getCols(); i++) {
            assertEquals(cloned.getRows().get(i).getFloatArray(),
                    zeroMatrix.getRows().get(i).getFloatArray());
        }
    }


    // TODO
    @Test
    void testRowSorterIdentityMatrix() {
        Matrix cloned = new Matrix(identityMatrix.deepClone(), identityMatrix.getCols(), "test", "test");
        identityMatrix.computeRedRef();
        identityMatrix.rowSorter();
        for (int i = 0; i < identityMatrix.getCols(); i++) {
            assertEquals(cloned.getRows().get(i).getFloatArray(),
                    identityMatrix.getRedRefRows().get(i).getFloatArray());
        }
    }



    // TODO
    @Test
    void testRowSorterSwappedIdentityMatrix() {
        Matrix cloned = new Matrix(identityMatrix.deepClone(), identityMatrix.getCols(), "test", "test");
        identityMatrix.computeRedRef();
        identityMatrix.swapRowRedRef(0, 1);
        identityMatrix.rowSorter();
        for (int i = 0; i < identityMatrix.getCols(); i++) {
            assertEquals(cloned.getRows().get(i).getFloatArray(),
                    identityMatrix.getRedRefRows().get(i).getFloatArray());
        }
    }



    @Test
    void testCheckDeepClone() {
        for (int i = 0; i < testMatrix.getCols(); i++) {
            assertEquals(testMatrix.getRows().get(i).getFloatArray(),
                    testMatrix.deepClone().get(i).getFloatArray());
        }
    }

    // @Test
    // void testComputeRedRefForTest() {
    // assertEquals(redRefVals, testMatrix.getMatrixRows());
    // }

    @Test
    void testComputeRedRefForTest() {
        testMatrix.computeRedRef();
        for (int i = 0; i < testMatrix.getCols(); i++) {
            assertEquals(redRefVals.get(i).getFloatArray(),
                    testMatrix.getRedRefRows().get(i).getFloatArray());
        }
    }

    @Test
    void testComputeRedRefForIdentity() {
        identityMatrix.computeRedRef();
        for (int i = 0; i < identityMatrix.getCols(); i++) {
            assertEquals(identityMatrix.getMatrixRows().get(i).getFloatArray(),
                    identityMatrix.getRedRefRows().get(i).getFloatArray());
        }
    }

    @Test
    void testComputeRedRefForZero() {
        zeroMatrix.computeRedRef();
        for (int i = 0; i < zeroMatrix.getSize(); i++) {
            assertEquals(zeroMatrix.getMatrixRows().get(i).getFloatArray(),
                    zeroMatrix.getRedRefRows().get(i).getFloatArray());
        }
    }

    @Test
    void testComputeRedRefForBase() {
        baseMatrix.computeRedRef();
        for (int i = 0; i < baseMatrix.getSize(); i++) {
            assertEquals(baseMatrix.getMatrixRows().get(i).getFloatArray(),
                    baseMatrix.getRedRefRows().get(i).getFloatArray());
        }
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

}
