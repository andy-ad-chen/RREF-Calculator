package model;

import java.util.ArrayList;

// Contains an ArrayList of matrices.
public class MatrixList {
    private ArrayList<Matrix> matrices;

    // EFFECTS: Builds an empty matrix list
    public MatrixList() {
        matrices = new ArrayList<>();
    }

    // EFFECTS: returns the list of matrices
    public ArrayList<Matrix> getMatrices() {
        return this.matrices;
    }

    // MODIFIES: this
    // EFFECTS: adds a matrix to this list of matrices
    public void addMatrix(Matrix m) {
        matrices.add(m);
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // MODIFIES: this
    // EFFECTS: removes the correct index of the matrix of this list of matrices
    public void removeMatrix(int index) {
        matrices.remove(index);
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // MODIFIES: this
    // EFFECTS: change matrix name of specified index
    public void changeMatrixName(String n, int index) {
        matrices.get(index).changeMatrixName(n);
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // MODIFIES: this
    // EFFECTS: change matrix description of specified index
    public void changeMatrixDesc(String d, int index) {
        matrices.get(index).changeMatrixDesc(d);
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // EFFECTS: get matrix name of specified index
    public String getMatrixName(int index) {
        return matrices.get(index).getMatrixName();
    }

    // REQUIRES: 0 <= index < matrices.size() - 1
    // EFFECTS: get matrix description of specified index
    public String getMatrixDesc(int index) {
        return matrices.get(index).getMatrixDesc();
    }

}





