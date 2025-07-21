package ui;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;

import java.util.ArrayList;
import java.util.Scanner;
    /*
     * 
     * This class is inspired by code provided by the UBC Department of Computer
     * Science, in the course material of CPSC 210.
     * Found in edX, CPSC 210 2025S2, Personal Project, Phase 1.
     * Teller application, TellerApp class.
     * 
     */

public class MatrixReducerApp {

    private MatrixList matrices = new MatrixList();
    private Scanner input;


    // EFFECTS: runs the Matrix Reducer App
    public MatrixReducerApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;
        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Bye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        input = new Scanner(System.in);
        // input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("===============================================");
        System.out.println("Select from:");
        System.out.println("a: add matrix:   ");
        System.out.println("v: view list of matrices:   ");
        System.out.println("s: select and detail view a matrix:   ");
        System.out.println("r: remove a matrix:   ");
        System.out.println("c: change a matrix name:   ");
        System.out.println("d: change a matrix description:   ");
        System.out.println("q: quit:   ");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addingMatrix();
        } else if (command.equals("v")) {
            viewList();
        } else if (command.equals("s")) {
            selectMatrixIndex();
        } else if (command.equals("r")) {
            removeMatrixIndex();
        } else if (command.equals("c")) {
            changeMatrixName();
        } else if (command.equals("d")) {
            changeMatrixDesc();
        }

        else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows a user to specify a matrix
    private void addingMatrix() {
        System.out.println("===============================================");
        System.out.print("Enter the number of rows of your matrix:   ");
        int height = input.nextInt();
        System.out.print("Enter the number of columns of your matrix:   ");
        int width = input.nextInt();
        input.nextLine();
        System.out.println("Enter the name of your matrix:   ");
        String name = input.nextLine();
        System.out.println("Enter the description of your matrix:   ");
        String desc = input.nextLine();
        specifyMatrix(height, width, name, desc);
    }

    // MODIFIES: this
    // EFFECTS: allows user to continue to specify a matrix
    void specifyMatrix(int height, int width, String name, String desc) {
        RowList listOfRows = new RowList(width);
        for (int j = 0; j < height; j++) {
            ArrayList<Float> listOfVals = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                System.out.print("Enter the next entry of your matrix, in the " + (j + 1) + " -th row:  ");
                float val = input.nextFloat();
                listOfVals.add(val);
            }
            Row row = new Row(listOfVals);
            listOfRows.add(row);
        }
        Matrix matrix = new Matrix(listOfRows, width, name, desc);
        matrices.addMatrix(matrix);
    }

    // EFFECTS: shows users a list of matrice's names alongside their number
    void viewList() {
        for (int i = 0; i < matrices.getMatrices().size(); i++) {
            System.out.println("#" + (i + 1) + " - " + matrices.getMatrices().get(i).getMatrixName());
        }
    }

    // MODIFIES: this
    // EFFECTS: allows users to inspect a matrix
    void selectMatrixIndex() {
        System.out.println("Matrix number?:  ");
        System.out.println("===============================================");
        int val = input.nextInt();
        Matrix matrix = matrices.getMatrices().get(val - 1);
        System.out.println("Matrix is called:  " + matrix.getMatrixName());
        System.out.println(matrix.getMatrixDesc());
        System.out.println("===============================================");
        for (int i = 0; i < matrix.getMatrixRows().size(); i++) {
            System.out.println(matrix.getMatrixRows().get(i));
        }
        System.out.println("===============================================");
        System.out.println("Matrix in Reduced Row Echelon Form is:");
        System.out.println("===============================================");
        matrix.computeRedRef();
        for (int i = 0; i < matrix.getRedRefRows().size(); i++) {
            System.out.println(matrix.getRedRefRows().get(i));
        }
        matrix.checkInvert();
        System.out.println("Invertible?: " + matrix.getInvertible());
        System.out.println("===============================================");
    }

    // MODIFIES: this
    // EFFECTS: allows users to remove a matrix
    void removeMatrixIndex() {
        System.out.println("Remove matrix number?:  ");
        System.out.println("===============================================");
        int val = input.nextInt();
        matrices.removeMatrix(val - 1);
        System.out.println("Done!");
    }

    // MODIFIES: this
    // EFFECTS: allows users to change name of matrix
    void changeMatrixName() {
        System.out.println("change name matrix number?:  ");
        System.out.println("===============================================");
        int val = input.nextInt();
        System.out.println("change name to?:  ");
        System.out.println("===============================================");
        input.nextLine();
        String name = input.nextLine();
        matrices.changeMatrixName(name, val - 1);
        System.out.println("Done!");
    }

    // MODIFIES: this
    // EFFECTS: allows users to change desc of matrix
    void changeMatrixDesc() {
        System.out.println("change description matrix number?:  ");
        System.out.println("===============================================");
        int val = input.nextInt();
        System.out.println("change description to?:  ");
        System.out.println("===============================================");
        input.nextLine();
        String desc = input.nextLine();
        matrices.changeMatrixDesc(desc, val - 1);
        System.out.println("Done!");
    }
}
