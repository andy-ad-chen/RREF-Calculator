package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Represents a matrix as having a name, description, invertibility, and the list of rows making up the matrix.
public class Matrix {
    private String name; // give each matrix a name
    private String desc; // give each matrix a description
    private Boolean invertible; // give each matrix an invertibility
    private ArrayList<Row> matrixRows; // list of matrix rows
    private int columnNum; // number of columns OR number of elements in each row
    private ArrayList<Row> redrefRows; // list of matrix rows in redref

    // REQUIRES name and desc are both non-zero length strings,
    // matrixRows.size() = cols > 0,
    // rows is not empty.
    // EFFECTS: builds a matrix
    public Matrix(ArrayList<Row> rows, int cols, String name, String desc) {
        matrixRows = rows;
        redrefRows = new ArrayList<>();
        columnNum = cols;
        this.name = name;
        this.desc = desc;
        invertible = false;
    }

    // REQUIRES: 0 <= rowIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: scales a row by a float value.
    public void scaleRow(float scale, int rowIndex) {
        Row a = this.matrixRows.get(rowIndex);
        a.scaleRow(scale);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: sum the values in the first row by the second row
    public void sumRow(int firstIndex, int secondIndex) {
        Row a = this.matrixRows.get(firstIndex);
        Row b = this.matrixRows.get(secondIndex);
        a.sumRow(b);
    }

    // THESE two above functions are not useful in computing RREF; only the swap row
    // in RREF
    // and the subtract Row with factor is needed.

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: swaps 2 rows in the RREF matrix
    public void swapRowRedRef(int firstIndex, int secondIndex) {
        Row firstRow = this.redrefRows.get(firstIndex);
        Row secondRow = this.redrefRows.get(secondIndex);
        Row firstRowClone = new Row(firstRow.getCol(), new ArrayList<Float>(firstRow.getFloatArray()));
        Row secondRowClone = new Row(secondRow.getCol(), new ArrayList<Float>(secondRow.getFloatArray()));
        this.redrefRows.set(firstIndex, secondRowClone);
        this.redrefRows.set(secondIndex, firstRowClone);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row
    public void subtractRow(int firstIndex, int secondIndex, float factor) {

        Row a = this.matrixRows.get(secondIndex);
        Row b = new Row(a.getCol(), new ArrayList<Float>(a.getFloatArray()));
        // create a deepy copy to ensure same-row shenanigans don't bug our codew
        b.scaleRow(factor);
        this.matrixRows.get(firstIndex).sumRow(b);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row
    public void subtractRowRedRef(int firstIndex, int secondIndex, float factor) {

        Row a = this.redrefRows.get(secondIndex);
        Row b = new Row(a.getCol(), new ArrayList<Float>(a.getFloatArray()));
        // create a deepy copy to ensure same-row shenanigans don't bug our codew
        b.scaleRow(factor);
        this.redrefRows.get(firstIndex).sumRow(b);
    }

    // EFFECTS: gets col number of matrix
    public int getCols() {
        return this.columnNum;
    }

    // EFFECTS: gets list of rows of matrix
    public ArrayList<Row> getRows() {
        return this.matrixRows;
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // EFFECTS: gets a row of matrix
    public Row getRow(int index) {
        return matrixRows.get(0);
    }

    // MODIFIES: this
    // EFFECTS: computes the redref (Reduced Row Echelon Form) of a matrix as being
    // a list of rows.


/*     public void computeRedRef() {
        this.redrefRows = deepClone();
        int rowNumber = redrefRows.size();
        for (int k = 0; k < rowNumber; k++) {
            for (int i = k; i < rowNumber; i++) {
                Row row = redrefRows.get(i);
                if (row.getFloatArray().get(i) != 0) {
                    swapRowRedRef(i, k);
                    scaleRow(1 / row.getFloatArray().get(k), k);
                    annihilator(k, rowNumber);
                    break;
                }
            }
        }
    }

 */

    public void computeRedRef() {
        this.redrefRows = deepClone();
        int widthNumber = columnNum;
        int heightNumber = redrefRows.size();
        for (int k = 0; k < widthNumber; k++) {
            for (int i = 0; i < heightNumber; i++) {
                Row row = redrefRows.get(i);
                if (row.getFloatArray().get(k) != 0) {
                   // swapRowRedRef(i, k);
                    scaleRow(1 / row.getFloatArray().get(k), k);
                    annihilator(k, heightNumber);
                    break;
                }
            }
        }
    }





    // EFFECTS: subtracts i-th row times index(j) for all j s.t. i< j <
    // redrefRows.size()

    public void annihilator(int index, int length) {
        for (int j = 0; j < length; j++) {
            if (j == index) {
                continue;
            } else {
                subtractRowRedRef(j, index, -redrefRows.get(j).getFloatArray().get(index));
            }
        }
    }

    // EFFECTS: clones an ArrayList<Row> object... deeply
    public ArrayList<Row> deepClone() {
        ArrayList<Row> returnVal = new ArrayList<>();
        for (Row i : this.matrixRows) {
            Row tempRow = new Row(i.getCol(), i.getFloatArray());
            returnVal.add(tempRow);
        }
        return returnVal;
    }

    // MODIFIES: this
    // EFFECTS: checks if the matrix is invertible
    public void checkInvert() {
        computeRedRef();
        if (redrefRows.size() == this.columnNum) {
            this.invertible = !hasZeroRows();
            if (this.columnNum == 1) {
                if (redrefRows.get(0).getFloatArray().get(0) == 1) {
                    this.invertible = true;
                }
            }
        }
    }

    // EFFECTS: produce true if the matrix has zero rows.
    public Boolean hasZeroRows() {
        Boolean zeroRow;
        zeroRow = true;
        for (Row i : this.redrefRows) {
            if (i.zeroRow() == false) {
                zeroRow = false;
            }
        }
        return zeroRow;
    }

    // MODIFIES: this
    // EFFECTS: change name of matrix
    public void changeMatrixName(String name) {
        this.name = name;
    }

    // EFFECTS: gets name of matrix
    public String getMatrixName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: change description of matrix
    public void changeMatrixDesc(String desc) {
        this.desc = desc;
    }

    // EFFECTS: gets desc of matrix
    public String getMatrixDesc() {
        return this.desc;
    }

    // EFFECTS: gets list of rows of a matrix
    public ArrayList<Row> getMatrixRows() {
        return this.matrixRows;
    }

    // EFFECTS: gets list of rows of a matrix's rref
    public ArrayList<Row> getRedRefRows() {
        return this.redrefRows;
    }

    // EFFECTS: gets invertibility of matrix
    public Boolean getInvertible() {
        return this.invertible;
    }

    // EFFECTS: gets size of matrix
    public int getSize() {
        return matrixRows.size();
    }

}
