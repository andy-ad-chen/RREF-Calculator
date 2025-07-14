package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class MatrixListTest {

    private Matrix baseMatrix1;
    private Matrix baseMatrix2;
    private Matrix baseMatrix3;
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
        baseMatrix3 = new Matrix(baseVals, 1, "name3", "desc3");

        testList = new MatrixList();
    }

    @Test
    void testConstructor() {
        assertTrue(testList.getMatrices() == new ArrayList<Matrix>());
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
        testList.removeMatrix(baseMatrix1, 0);
        assertFalse(matrices.contains(baseMatrix1));
    }

    @Test
    void testRemoveTwo() {
        ArrayList<Matrix> matrices = testList.getMatrices();
        testList.addMatrix(baseMatrix1);
        testList.addMatrix(baseMatrix1);
        assertTrue(matrices.contains(baseMatrix1));
        testList.removeMatrix(baseMatrix1, 0);
        assertTrue(matrices.contains(baseMatrix1));
        testList.removeMatrix(baseMatrix1, 0);
        assertFalse(matrices.contains(baseMatrix1));
    }

    @Test
    void testChangeNameOne() {
        assertTrue(testList.getMatrixName(0) == "name1");
        testList.changeMatrixName("deez", 0);
        assertFalse(testList.getMatrixName(0) == "name1");
        assertTrue(testList.getMatrixName(0) == "deez");
    }

    @Test
    void testChangeNameTwice() {
        assertTrue(testList.getMatrixName(0) == "name1");
        testList.changeMatrixName("deez", 0);
        assertFalse(testList.getMatrixName(0) == "name1");
        assertTrue(testList.getMatrixName(0) == "deez");
        testList.changeMatrixName("nuts", 0);
        assertFalse(testList.getMatrixName(0) == "deez");
        assertTrue(testList.getMatrixName(0) == "nuts");
    }

    @Test
    void testChangeDescOne() {
        assertTrue(testList.getMatrixName(0) == "desc1");
        testList.changeMatrixName("deez", 0);
        assertFalse(testList.getMatrixName(0) == "desc1");
        assertTrue(testList.getMatrixName(0) == "deez");
    }

    @Test
    void testChangeDescTwice() {
        assertTrue(testList.getMatrixDesc(0) == "desc1");
        testList.changeMatrixDesc("deez", 0);
        assertFalse(testList.getMatrixDesc(0) == "desc1");
        assertTrue(testList.getMatrixDesc(0) == "deez");
        testList.changeMatrixDesc("nuts", 0);
        assertFalse(testList.getMatrixDesc(0) == "deez");
        assertTrue(testList.getMatrixDesc(0) == "nuts");
    }

}
