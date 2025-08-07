package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Contains an ArrayList of matrices.
public class MatrixList implements Writable {
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
    // EFFECTS: adds a matrix to this list of matrices,
    // LOGS THIS EVENT!!!
    public void addMatrix(Matrix m) {
        matrices.add(m);
        EventLog.getInstance().logEvent(new Event("Added matrix to working list: " + m.getMatrixName()));
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

    // EFFECTS: logs a view event
    // LOGS THIS EVENT!!!
    public void logView() {
        EventLog.getInstance().logEvent(new Event("List of matrices to view has been updated"));
    }

    // EFFECTS: retursn JSONObject of list of matrix
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List of Matrices", matricesToJson());
        return json;
    }

    // EFFECTS: returns matrices in this matrix list as a JSON array
    private JSONArray matricesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Matrix m : matrices) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}
