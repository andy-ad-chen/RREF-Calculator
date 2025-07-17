package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class MatrixListTest {

    private Matrix baseMatrix1;
    private Matrix baseMatrix2;
    private ArrayList<Float> oneByOneList = new ArrayList<>();
    private Row oneByOneRow;
    private ArrayList<Row> baseVals = new ArrayList<>();

    private MatrixList testList;

    @BeforeEach
    void runBefore() {
        oneByOneList.add(1.0f);
        oneByOneRow = new Row(1, oneByOneList);
        baseVals.add(oneByOneRow);
        baseMatrix1 = new Matrix(baseVals, 1, "name1", "desc1");
        baseMatrix2 = new Matrix(baseVals, 1, "name2", "desc2");

        testList = new MatrixList();
    }

    @Test
    void testConstructor() {
        assertEquals(testList.getMatrices(), new ArrayList<Matrix>());
    }

    @Test
    void testAddOne() {
        ArrayList<Matrix> matrices = testList.getMatrices();
        assertFalse(matrices.contains(baseMatrix1));
        testList.addMatrix(baseMatrix1);
        assertTrue(matrices.contains(baseMatrix1));
    }

    @Test
    void testAddTwo() {
        ArrayList<Matrix> matrices = testList.getMatrices();
        assertFalse(matrices.contains(baseMatrix1));
        testList.addMatrix(baseMatrix1);
        assertTrue(matrices.contains(baseMatrix1));
        assertFalse(matrices.contains(baseMatrix2));
        testList.addMatrix(baseMatrix2);
        assertTrue(matrices.contains(baseMatrix2));
    }

    @Test
    void testAddDupe() {
        ArrayList<Matrix> matrices = testList.getMatrices();
        assertFalse(matrices.contains(baseMatrix1));
        testList.addMatrix(baseMatrix1);
        assertTrue(matrices.contains(baseMatrix1));
        testList.addMatrix(baseMatrix1);
        assertTrue(matrices.contains(baseMatrix1));
    }

    @Test
    void testRemoveOne() {
        ArrayList<Matrix> matrices = testList.getMatrices();
        testList.addMatrix(baseMatrix1);
        assertTrue(matrices.contains(baseMatrix1));
        testList.removeMatrix(0);
        assertFalse(matrices.contains(baseMatrix1));
    }

    @Test
    void testRemoveTwo() {
        ArrayList<Matrix> matrices = testList.getMatrices();
        testList.addMatrix(baseMatrix1);
        testList.addMatrix(baseMatrix1);
        assertTrue(matrices.contains(baseMatrix1));
        testList.removeMatrix(0);
        assertTrue(matrices.contains(baseMatrix1));
        testList.removeMatrix(0);
        assertFalse(matrices.contains(baseMatrix1));
    }


    @Test
    void testChangeNameOne() {
        testList.addMatrix(baseMatrix1);
        assertEquals("name1", testList.getMatrixName(0));
        testList.changeMatrixName("deez", 0);
        assertNotEquals("name1", testList.getMatrixName(0));
        assertEquals("deez", testList.getMatrixName(0));
    }

    @Test
    void testChangeNameTwice() {
        testList.addMatrix(baseMatrix1);
        assertEquals("name1", testList.getMatrixName(0));
        testList.changeMatrixName("deez", 0);
        assertNotEquals("name1", testList.getMatrixName(0));
        assertEquals("deez", testList.getMatrixName(0));
        testList.changeMatrixName("nuts", 0);
        assertNotEquals("deez", testList.getMatrixName(0));
        assertEquals("nuts", testList.getMatrixName(0));
    }

    @Test
    void testChangeDescOne() {
        testList.addMatrix(baseMatrix1);
        assertEquals("desc1", testList.getMatrixDesc(0));
        testList.changeMatrixDesc("deez", 0);
        assertNotEquals("desc1", testList.getMatrixDesc(0));
        assertEquals("deez", testList.getMatrixDesc(0));
    }

    @Test
    void testChangeDescTwice() {
        testList.addMatrix(baseMatrix1);
        assertEquals("desc1", testList.getMatrixDesc(0));
        testList.changeMatrixDesc("deez", 0);
        assertNotEquals("desc1", testList.getMatrixDesc(0));
        assertEquals("deez", testList.getMatrixDesc(0));
        testList.changeMatrixDesc("nuts", 0);
        assertNotEquals("deez", testList.getMatrixDesc(0));
        assertEquals("nuts", testList.getMatrixDesc(0));
    }

}
