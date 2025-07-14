package model;

import java.util.ArrayList;

// Represents a row in the matrix; this is a list of float values. Their length is restricted to the number of columns of the matrix.
public class Row {
    public ArrayList<Float> rowVals;
    public int columnNum;

    // REQUIRES: cols = rowVals.size() > 0
    // EFFECTS: Builds an empty row of same length as column number
    public Row(int cols, ArrayList<Float> vals) {
        columnNum = cols;
        rowVals = vals;
    }

    // REQUIRES: two rows are of same length
    // MODIFIES: this
    // EFFECTS: sums the values of the row passed as an argument and this.rowVals
    public void sumRow(Row secondRow) {
        for (int i = 0; i < this.columnNum; i++) {
            float firstRowVal = this.rowVals.get(i);
            float secondRowVal = secondRow.getFloatArray().get(i);
            float a = firstRowVal + secondRowVal;
            this.rowVals.set(i, a);
        }
    }

    // MODIFIES: this
    // EFFECTS: scales the values of this row
    public void scaleRow(float scale) {
        for (int i = 0; i < this.columnNum; i ++) {
            float val = scale * this.rowVals.get(i);
            this.rowVals.set(i, val);
        }
    }

    // EFFECTS: gets columns number
    public int getCol() {
        return this.columnNum;
    }

    // EFFECTS: gets array of values making up row
    public ArrayList<Float> getFloatArray() {
        return this.rowVals;
    }

}
