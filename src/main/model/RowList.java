package model;

import java.util.ArrayList;

public class RowList extends ArrayList<Row> {
    private int columnNum;

    // EFFECTS: constructs an empty RowList
    public RowList() {
        super();
        columnNum = 0;
    }

    // EFFECTS: constructs a RowList with Column Number
    public RowList(int num) {
        super();
        columnNum = num;
    }

    // EFFECTS: constructs a deep clone of RowList
    public RowList(RowList rowList) {
        super();
        columnNum = rowList.getWidth();
        for (Row row : rowList) {
            Row newRow = new Row(row);
            super.add(newRow);
        }
    }

    // REQUIRES: 0 <= index < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: scales a row by a float value.
    public void scaleRow(float scale, int index) {
        super.get(index).scaleRow(scale);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: sum the values in the first row by the second row
    public void sumRow(int firstIndex, int secondIndex) {
        Row a = super.get(firstIndex);
        Row b = super.get(secondIndex);
        a.sumRow(b);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // REQUIRES: at least 2 rows
    // MODIFIES: this
    // EFFECTS: swaps two rows in the matrix, first and second index
    public void swapRow(int firstIndex, int secondIndex) {
        Row a = super.get(firstIndex);
        Row copyFirst = new Row(a);

        Row b = super.get(secondIndex);
        Row copySecond = new Row(b);
        // create a deepy copy of the second row to ensure same-row shenanigans don't
        // bug our code

        super.set(firstIndex, copySecond);
        super.set(secondIndex, copyFirst);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row times some
    // factor
    public void subtractRow(int firstIndex, int secondIndex, float factor) {
        Row secondRow = super.get(secondIndex);
        Row copyRow = new Row(secondRow);
        float negFactor = -factor;
        copyRow.scaleRow(negFactor);
        super.get(firstIndex).sumRow(copyRow);

    }

    // EFFECTS: gets a RowList's column number
    public int getWidth() {
        return this.columnNum;
    }

}
