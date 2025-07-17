package model;

public class RedRefRowList extends RowList {
    public RedRefRowList() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: computes the redref (Reduced Row Echelon Form) of a RowList
    public void computeRedRef() {
    }

    // MODIFIES: this & alreadyPassed
    // EFFECTS: searches column for a new row with a nonzero entry without a pivot;
    // computes redref from that.
    private void rowCycleFinder() {
    }

    // MODIFIES: this
    // EFFECTS: sends all rows that are full of 0 to the bottom, and sorts rows with
    // earliest "1" value to top of matrix. Does this to the RREF matrix
    private void rowSorter() {
    }

    // MODIFIES: this
    // EFFECTS: subtracts i-th row times index(j) for all j s.t. i< j <
    // redrefRows.size()
    private void annihilator() {
    }


    // EFFECTS: determines if a RowList has zero rows
    public void hasZeroRows() {

    }


}
