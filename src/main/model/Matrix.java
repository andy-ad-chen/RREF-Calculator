package model;

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
    // EFFECTS: swaps 2 rows
    public void swapRow(int firstIndex, int secondIndex) {
        // stub
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: sum the values in the first row by the second row
    public void sumRow(int firstIndex, int secondIndex) {
        // stub
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row
    public void subtractRow(int firstIndex, int secondIndex) {
        // stub
    }

    // EFFECTS: gets col number of matrix
    public int getCols() {
        return this.columnNum;
    }

    // EFFECTS: gets list of rows of matrix
    public ArrayList<Row> getRows() {
        return this.matrixRows;
        // stub
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // EFFECTS: gets a row of matrix
    public Row getRow(int index) {
        return matrixRows.get(0);
        // stub
    }

    // MODIFIES: this
    // EFFECTS: computes the redref (Reduced Row Echelon Form) of a matrix as being
    // a list of rows.
    public void computeRedRef() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: checks if the matrix is invertible
    public void checkInvert() {
        // stub
    }

    // EFFECTS: checks if the redref of a matrix is a square
    public boolean checkSquareRedRef() {
        return false;
        // stub
    }
    // COULD BE REDUNANT: code funtionality into the checkInvert

    // MODIFIES: this
    // EFFECTS: change name of matrix
    public void changeMatrixName(String name) {
        // stub
    }

    // EFFECTS: gets name of matrix
    public String getMatrixName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: change description of matrix
    public void changeMatrixDesc(String desc) {
        // stub
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

}
