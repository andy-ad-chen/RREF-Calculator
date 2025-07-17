package model;

import java.util.ArrayList;

public class RedRefRowList extends RowList {

    // EFFECTS: constructs a RedRefRowList
    public RedRefRowList() {
        super();
    }

    // EFFECTS: clones a RedRefRowList from RowList
    public RedRefRowList(RowList rowList) {
        super(rowList);
    }

    // MODIFIES: this
    // EFFECTS: computes the redref (Reduced Row Echelon Form) of a RowList
    public void computeRedRef(int widthNumber, int heightNumber) {
        ArrayList<Integer> alreadyPassed = new ArrayList<>();
        for (int k = 0; k < widthNumber; k++) {
            rowCycleFinder(heightNumber, k, alreadyPassed);
        }
        rowSorter();
    }

    // MODIFIES: this & alreadyPassed
    // EFFECTS: searches column for a new row with a nonzero entry without a pivot;
    // computes redref from that.
    private void rowCycleFinder(int heightNumber, int k, ArrayList<Integer> alreadyPassed) {
        for (int i = 0; i < heightNumber; i++) {
            Row row = new Row(super.get(i));
            if (row.get(k) != 0.0f) {
                if (!alreadyPassed.contains(i)) {
                    scaleRow((1 / row.get(k)), i);
                    annihilator(i, heightNumber, k);
                    alreadyPassed.add(i);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sends all rows that are full of 0 to the bottom, and sorts rows with
    // earliest "1" value to top of matrix. Does this to the RREF matrix
    private void rowSorter() {
        ArrayList<Integer> alreadyPassed = new ArrayList<>();
        for (int k = 0; k < super.getWidth(); k++) {
            for (int i = 0; i < super.size(); i++) {
                if (super.get(i).get(k) == 1.0f) {
                    if (!alreadyPassed.contains(i)) {
                        swapRow(i, alreadyPassed.size());
                        alreadyPassed.add(alreadyPassed.size());
                    }
                }
            }
        }
    }

    // EFFECTS: subtracts i-th row times index(j) for all j s.t. i< j <
    // redrefRows.size()
    private void annihilator(int i, int heightNumber, int k) {
        for (int j = 0; j < heightNumber; j++) {
            if (!(j == i)) {
                subtractRow(j, i, super.get(j).get(k));
            }
        }
    }

    // EFFECTS: checks if the RowList (matrix) is invertible
    public boolean checkInvert() {
        if (super.size() == super.getWidth()) {
            return !hasZeroRows();
        }
        return false;
    }

    // EFFECTS: determines if a RowList has zero rows
    public boolean hasZeroRows() {
        Boolean zeroRow;
        zeroRow = false;
        for (int i = 0; i < super.size(); i++) {
            if (super.get(i).zeroRow() == true) {
                zeroRow = true;
            }
        }
        return zeroRow;
    }
}
