package model;

import java.util.ArrayList;

// Represents a row in the matrix; this is a list of float values. Their length is restricted to the number of columns of the matrix.
public class Row {
    public ArrayList<Float> rowVals;
    public int columnNum;


    // REQUIRES: cols = rowVals.size()
    // EFFECTS: Builds an empty row of same length as column number
    public Row(int cols, ArrayList<Float> vals) {
        columnNum = 0;
        rowVals = new ArrayList<>();
        //stub
    }

    // REQUIRES: two rows are of same length
    // MODIFIES: this
    // EFFECTS: sums the values of the row passed as an argument and this.rowVals
    public void sumRow(Row secondRow) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: scales the values of this row
    public void scaleRow(float scale, Row scaledRow) {
        // stub
    }


}
