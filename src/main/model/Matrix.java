package model;

import java.util.ArrayList;


// Represents a matrix as having a name, description, invertibility, and the list of rows making up the matrix.
public class Matrix {
    private String name; // give each matrix a name
    private String desc; // give each matrix a description
    private Boolean invertible; // give each matrix an invertibility
    private ArrayList<Row> matrixRows; // list of matrix rows
    private int columnNum; // number of columns OR number of elements in each row
    private ArrayList<Row> redrefRows; // list of matrix rows in redref

    // REQUIRES name and desc are both non-zero length strings,
    // matrixRows.size() = cols > 0,
    // rows is not empty.
    // EFFECTS: builds a matrix
    public Matrix(ArrayList<Row> rows, int cols, String name, String desc) {
        matrixRows = rows;
        redrefRows = new ArrayList<>();
        columnNum = cols;
        this.name = name;
        this.desc = desc;
        invertible = false;
    }

    // REQUIRES: 0 <= rowIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: scales a row by a float value.
    public void scaleRow(float scale, int rowIndex) {
        Row a = this.matrixRows.get(rowIndex);
        a.scaleRow(scale);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: sum the values in the first row by the second row
    public void sumRow(int firstIndex, int secondIndex) {
        Row a = this.matrixRows.get(firstIndex);
        Row b = this.matrixRows.get(secondIndex);
        a.sumRow(b);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row
    public void subtractRow(int firstIndex, int secondIndex, float factor) {

        Row a = this.matrixRows.get(secondIndex);
        Row b = new Row(a.getCol(), new ArrayList<Float>(a.getFloatArray()));
        // create a deepy copy to ensure same-row shenanigans don't bug our codew
        b.scaleRow(factor);
        this.matrixRows.get(firstIndex).sumRow(b);
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // REQUIRES: 0 <= secondIndex < matrixRows.size() - 1
    // MODIFIES: this
    // EFFECTS: subtracts the values in the first row by the second row
    public void subtractRowRedRef(int firstIndex, int secondIndex, float factor) {

        Row a = this.redrefRows.get(secondIndex);
        Row b = new Row(a.getCol(), new ArrayList<Float>(a.getFloatArray()));
        // create a deepy copy to ensure same-row shenanigans don't bug our codew
        b.scaleRow(factor);
        this.redrefRows.get(firstIndex).sumRow(b);
    }

    // REQUIRES: matrix of at least 2 rows
    // MODIFIES: this
    // EFFECTS: swaps two rows in the matrix, first and second index
    // public void swapRow(int firstIndex, int secondIndex) {

    //     Row a = this.matrixRows.get(firstIndex);
    //     Row copyFirst = new Row(a.getCol(), new ArrayList<Float>(a.getFloatArray()));

    //     Row b = this.matrixRows.get(secondIndex);
    //     Row copySecond = new Row(b.getCol(), new ArrayList<Float>(b.getFloatArray()));
    //     // create a deepy copy of the second row to ensure same-row shenanigans don't
    //     // bug our code

    //     this.matrixRows.set(firstIndex, copySecond);
    //     this.matrixRows.set(secondIndex, copyFirst);
    // }

    // REQUIRES: matrix of at least 2 rows
    // MODIFIES: this
    // EFFECTS: swaps two rows in the matrix, first and second index
    public void swapRowRedRef(int firstIndex, int secondIndex) {

        Row a = this.redrefRows.get(firstIndex);
        Row copyFirst = new Row(a.getCol(), new ArrayList<Float>(a.getFloatArray()));

        Row b = this.redrefRows.get(secondIndex);
        Row copySecond = new Row(b.getCol(), new ArrayList<Float>(b.getFloatArray()));
        // create a deepy copy of the second row to ensure same-row shenanigans don't
        // bug our code

        this.redrefRows.set(firstIndex, copySecond);
        this.redrefRows.set(secondIndex, copyFirst);
    }

    // NOTE THAT THIS METHOD IS REDUNDANT IF WE HAVE a good interface :(

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * NOTES; for better style, I could make ArrayList<Row> a separately defined
     * class and object; each matrix has two sets of these values
     * after all, since one is the RREF and the other is the non-modified one. This
     * would make this class smaller as the row-swapping ops
     * can now be placed in that new class. I should also make ArrayList<Row> an
     * INTERFACE since that ways I could just give two different
     * implementations.
     * 
     * This avoids the copied code that I used to make subtractRow and
     * subtractRowRedRef
     * 
     * 
     * 
     * 
     * 
     * 
     */

    // EFFECTS: gets col number of matrix
    public int getCols() {
        return this.columnNum;
    }

    // EFFECTS: gets list of rows of matrix
    public ArrayList<Row> getRows() {
        return this.matrixRows;
    }

    // REQUIRES: 0 <= firstIndex < matrixRows.size() - 1
    // EFFECTS: gets a row of matrix
    public Row getRow(int index) {
        return matrixRows.get(0);
    }

    /*
     * public void computeRedRef() {
     * this.redrefRows = deepClone();
     * int rowNumber = redrefRows.size();
     * for (int k = 0; k < rowNumber; k++) {
     * for (int i = k; i < rowNumber; i++) {
     * Row row = redrefRows.get(i);
     * if (row.getFloatArray().get(i) != 0) {
     * swapRowRedRef(i, k);
     * scaleRow(1 / row.getFloatArray().get(k), k);
     * annihilator(k, rowNumber);
     * break;
     * }
     * }
     * }
     * }
     * 
     */

    // MODIFIES: this
    // EFFECTS: computes the redref (Reduced Row Echelon Form) of a matrix as being
    // a list of rows.
    public void computeRedRef() {
        ArrayList<Integer> alreadyPassed = new ArrayList<>();
        this.redrefRows = deepClone();
        int widthNumber = columnNum;
        int heightNumber = matrixRows.size();
        for (int k = 0; k < widthNumber; k++) {
            rowCycleFinder(heightNumber, k, alreadyPassed);
        }
        rowSorter();
    }

    // MODIFIES: this & alreadyPassed
    // EFFECTS: searches column for a new row with a nonzero entry without a pivot;
    // computes redref from that.
    public void rowCycleFinder(int heightNumber, int k, ArrayList<Integer> alreadyPassed) {
        for (int i = 0; i < heightNumber; i++) {
            Row row = redrefRows.get(i);
            if (row.getFloatArray().get(k) != 0.0f) {
                if (!alreadyPassed.contains(i)) {
                    scaleRow((1 / row.getFloatArray().get(k)), i);
                    annihilator(i, heightNumber, k);
                    alreadyPassed.add(i);
                }
            }
        }
    }

    // EFFECTS: subtracts i-th row times index(j) for all j s.t. i< j <
    // redrefRows.size()
    public void annihilator(int index, int length, int k) {
        for (int j = 0; j < length; j++) {
            if (!(j == index)) {
                subtractRowRedRef(j, index, -redrefRows.get(j).getFloatArray().get(k));
            }
        }
    }

    // TODO:
    // can also implement for matrixRows not just redRefRows??

    // MODIFIES: this
    // EFFECTS: sends all rows that are full of 0 to the bottom, and sorts rows with
    // earliest "1" value to top of matrix. Does this to the RREF matrix
    public void rowSorter() {
        ArrayList<Integer> alreadyPassed = new ArrayList<>();
            for (int k = 0; k < columnNum; k++) {
                for (int i = 0; i <  redrefRows.size(); i++) {
                    if (redrefRows.get(i).getFloatArray().get(k) == 1.0f) {
                        if (!alreadyPassed.contains(i)) {
                            if ((i < (redrefRows.size() - 1))) {
                                swapRowRedRef(i, alreadyPassed.size());
                                alreadyPassed.add(alreadyPassed.size());
                            }
                        }
                    }
                }
            }
    }


    // EFFECTS: clones an ArrayList<Row> object... deeply
    public ArrayList<Row> deepClone() {
        ArrayList<Row> returnVal = new ArrayList<>();
        for (Row i : this.matrixRows) {
            Row tempRow = new Row(i.getCol(), i.getFloatArray());
            returnVal.add(tempRow);
        }
        return returnVal;
    }

    // MODIFIES: this
    // EFFECTS: checks if the matrix is invertible
    public void checkInvert() {
        computeRedRef();
        if (redrefRows.size() == this.columnNum) {
            this.invertible = !hasZeroRows();
        }
    }

    // EFFECTS: produce true if the matrix has "zero" rows.
    public Boolean hasZeroRows() {
        Boolean zeroRow;
        zeroRow = false;
        for (Row i : this.redrefRows) {
            if (i.zeroRow() == true) {
                zeroRow = true;
            }
        }
        return zeroRow;
    }

    // MODIFIES: this
    // EFFECTS: change name of matrix
    public void changeMatrixName(String name) {
        this.name = name;
    }

    // EFFECTS: gets name of matrix
    public String getMatrixName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: change description of matrix
    public void changeMatrixDesc(String desc) {
        this.desc = desc;
    }

    // EFFECTS: gets desc of matrix
    public String getMatrixDesc() {
        return this.desc;
    }

    // EFFECTS: gets list of rows of a matrix
    public ArrayList<Row> getMatrixRows() {
        return this.matrixRows;
    }

    // EFFECTS: gets list of rows of a matrix's rref
    public ArrayList<Row> getRedRefRows() {
        return this.redrefRows;
    }

    // EFFECTS: gets invertibility of matrix
    public Boolean getInvertible() {
        return this.invertible;
    }

    // EFFECTS: gets size of matrix
    public int getSize() {
        return matrixRows.size();
    }

}
