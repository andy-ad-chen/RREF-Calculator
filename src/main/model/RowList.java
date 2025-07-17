package model;

import java.util.ArrayList;

public class RowList extends ArrayList<RowLEGACY> {

    public RowList() {
        super();
    }

    // REQUIRES: 0 <= rowIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: scales a row by a float value.
    public void scaleRow() {
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: sum the values in the first row by the second row
    public void sumRow() {
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // REQUIRES: at least 2 rows
    // MODIFIES: this
    // EFFECTS: swaps two rows in the matrix, first and second index
    public void swapRow() {
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row times some
    // factor
    public void subtractRow() {
    }

    // EFFECTS: clones a RowList and copies all objects
    public void deepClone() {

    }



}
