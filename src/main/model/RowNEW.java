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
    // EFFECTS: sums the values of the row passed as an argument and super
    public void sumRow(RowNEW secondRow) {
        for (int i = 0; i < this.columnNum; i++) {
            float firstRowVal = super.get(i);
            float secondRowVal = secondRow.get(i);
            float a = firstRowVal + secondRowVal;
            super.set(i, a);
        }
    }

    // MODIFIES: this
    // EFFECTS: scales the values of this row
    public void scaleRow(float scale) {
        for (int i = 0; i < this.columnNum; i++) {
            float val = scale * super.get(i);
            super.set(i, val);
        }
    }

    // EFFECTS: produce true if Row is all 0
    public boolean zeroRow() {
        Boolean zeroRow;
        zeroRow = true;
        for (int val = 0; val < super.size(); val++) {
            if (val != 0.0f || val != -0.0f) {
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
