package model;

import java.util.ArrayList;

// Represents a matrix as having a name, description, invertibility, and the list of rows making up the matrix.
public class Matrix {
    public String name; // give each matrix a name
    public String desc; // give each matrix a description
    public Boolean invertible; // give each matrix an invertibility
    public ArrayList<Row> matrixRows; // list of matrix rows
    public int columnNum; // number of columns OR number of elements in each row
    public ArrayList<Row> redrefRows; // list of matrix rows in redref

    // REQUIRES name and desc are both non-zero length strings
    // EFFECTS: builds an empty matrix
    public Matrix() {
        matrixRows = new ArrayList<>();
        redrefRows = new ArrayList<>();
        columnNum = 0;
        name = "name of matrix";
        desc = "this is the description of some matrix";
        invertible = false;
    }

    // MODIFIES: this
    // EFFECTS: scales a row by a float value.
    public void scaleRow(float scale, int rowIndex) {
        // stub
    }

    // REQUIRES: 0 <= firstIndex < columnNum - 1
    // REQUIRES: 0 <= secondIndex < columnNum - 1
    // MODIFIES: this
    // EFFECTS: swaps 2 rows
    public void swapRow(int firstIndex, int secondIndex) {
        // stub
    }

    // REQUIRES: 0 <= firstIndex < columnNum - 1
    // REQUIRES: 0 <= secondIndex < columnNum - 1
    // MODIFIES: this
    // EFFECTS: sum the values in the first row by the second row
    public void sumRow(int firstIndex, int secondIndex) {
        // stub
    }

    // REQUIRES: 0 <= firstIndex < columnNum - 1
    // REQUIRES: 0 <= secondIndex < columnNum - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row
    public void subtractRow(int firstIndex, int secondIndex) {
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

    // MODIFIES: this
    // EFFECTS: change name of matrix
    public void changeMatrixName() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: change description of matrix
    public void changeMatrixDesc() {
        // stub
    }
}
