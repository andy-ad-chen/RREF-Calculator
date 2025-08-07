package ui;

public class Main {
    private static MatrixGui mainGui;

    // EFFECTS: runs GUI when run as main. 
    public static void main(String[] args) {
        mainGui = new MatrixGui();
    }

    // EFFECTS: returns mainGui when called.
    public static MatrixGui getMatrixGui() {
        return mainGui; 
    }

    // EFFECTS: runs console app when run as main
    public static void mainConsole(String[] args) {
        new MatrixReducerApp();
    }
}

