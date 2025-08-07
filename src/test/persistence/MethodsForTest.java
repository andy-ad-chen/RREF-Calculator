package persistence;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import model.*;

public class MethodsForTest {

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
        typicalCasePrep();

        otherCasePrep();
        identityPrep();

        redrefPrep();

    }

    private void redrefPrep() {
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
    }

    private void identityPrep() {
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
    }

    private void otherCasePrep() {
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

    }

    private void typicalCasePrep() {
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

    }

    protected void checkMatrix(String name, String desc, int width, RowList rows, Matrix matrix) {
        assertEquals(name, matrix.getMatrixName());
        assertEquals(desc, matrix.getMatrixDesc());
        assertEquals(width, matrix.getWidth());
        assertEquals(rows.size(), matrix.getMatrixRows().size());
    }

}
