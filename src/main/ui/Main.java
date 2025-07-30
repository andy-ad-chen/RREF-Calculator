package ui;

public class Main {
    private static MatrixGui mainGui;


    public static void main(String[] args) {
        mainGui = new MatrixGui();
    }

    public static MatrixGui getMatrixGui() {
        return mainGui; 
    }

    // public static void main(String[] args) {
    //     new MatrixReducerApp();
    // }


    public static void mainConsole(String[] args) {
        new MatrixReducerApp();
    }
}



