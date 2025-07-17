package model;

// Represents a matrix as having a name, description, invertibility, and the list of rows making up the matrix.
public class Matrix {
    private String name; // give each matrix a name
    private String desc; // give each matrix a description
    private Boolean invertible; // give each matrix an invertibility
    private RowList matrixRows; // list of matrix rows
    private int columnNum; // number of columns OR number of elements in each row
    private RedRefRowList redrefRows; // list of matrix rows in redref

    // REQUIRES name and desc are both non-zero length strings,
    // matrixRows.size() = cols > 0,
    // rows is not empty.
    // EFFECTS: builds a matrix
    public Matrix(RowList rows, int cols, String name, String desc) {
        matrixRows = rows;
        redrefRows = new RedRefRowList(rows);
        columnNum = cols;
        this.name = name;
        this.desc = desc;
        invertible = false;
    }

    // EFFECTS: gets col number of matrix
    public int getWidth() {
        return this.columnNum;
    }

    // EFFECTS: gets list of rows of matrix
    public RowList getRows() {
        return this.matrixRows;
    }

    // MODIFIES: this
    // EFFECTS: computes the redref (Reduced Row Echelon Form) of a matrix as being
    // a list of rows.
    public void computeRedRef() {
        this.redrefRows.computeRedRef(columnNum, redrefRows.size());
    }

    // MODIFIES: this
    // EFFECTS: checks if the matrix is invertible
    public void checkInvert() {
        redrefRows.computeRedRef(columnNum, redrefRows.size());
        this.invertible = redrefRows.checkInvert();
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
    public RowList getMatrixRows() {
        return this.matrixRows;
    }

    // EFFECTS: gets list of rows of a matrix's rref
    public RowList getRedRefRows() {
        return this.redrefRows;
    }

    // EFFECTS: gets invertibility of matrix
    public Boolean getInvertible() {
        return this.invertible;
    }
}
