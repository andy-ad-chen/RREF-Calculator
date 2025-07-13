package model;

import java.util.ArrayList;

// Contains an ArrayList of matrices.
public class MatrixList {
    public ArrayList<Matrix> matrices;

    // EFFECTS: Builds an empty matrix list
    public MatrixList() {
        matrices = new ArrayList<>();
    }

    // EFFECTS: returns the list of matrices
    public ArrayList<Matrix> getMatrices() {
        return new ArrayList<>();
        // stub
    }

    // EFFECTS: returns the list of matrices' names
    public ArrayList<String> getMatricesNames() {
        return new ArrayList<>();
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a matrix to this list of matrices
    public void addMatrix() {
        // stub
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // MODIFIES: this
    // EFFECTS: removes the correct index of the matrix of this list of matrices
    public void removeMatrix() {
        // stub
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // MODIFIES: this
    // EFFECTS: change matrix name of specified index
    public void changeMatrixName() {
        // stub
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // MODIFIES: this
    // EFFECTS: change matrix description of specified index
    public void changeMatrixDesc() {
        // stub
    }

}
