package model;

import java.util.ArrayList;

public class RowNEW extends ArrayList<Float> {
    private int columnNum;

    // EFFECTS: constructs row with a collection and correct column num
    public RowNEW(ArrayList<Float> collection) {
        super(collection);
        columnNum = collection.size();
    }

    // REQUIRES: two rows are of same length
    // MODIFIES: this
    // EFFECTS: sums the values of the row passed as an argument and this.rowVals
    public void sumRow(RowNEW secondRow) {

    }

    // MODIFIES: this
    // EFFECTS: scales the values of this row
    public void scaleRow(float scale) {

    }

    // EFFECTS: produce true if Row is all 0
    public Boolean zeroRow() {
        Boolean zeroRow;
        zeroRow = true;
        for (float f : this.rowVals) {
            if (f != 0.0f || f != -0.0f) {
                zeroRow = false;
            }
        }
        return zeroRow;
    }

    // EFFECTS: gets columns number
    public int getCol() {
        return this.columnNum;
    }

}
