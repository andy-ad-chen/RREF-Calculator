package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.AddMatrixTool;
import ui.tools.LoadTool;
import ui.tools.SaveTool;
import ui.tools.Tool;
import ui.tools.ViewSelectMenu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixGui extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 330;
    private static final String JSON_STORE = "./data/matrices.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private MatrixList matrices;


    private ViewSelectMenu viewSelect;
    private SaveTool saveTool;
    private LoadTool loadTool;
    private AddMatrixTool addTool;

    // EFFECTS: runs the Matrix Reducer App
    public MatrixGui() {
        super("Matrix Reducer App");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeFields();
        initializeGraphics();
        // initializeInteraction();
        // runApp();
    }

    // MODIFIES: this
    // EFFECTS: sets activeTool, currentDrawing to null, and instantiates drawings
    // and tools with ArrayList
    // this method is called by the DrawingEditor constructor
    private void initializeFields() {
        matrices = new MatrixList();
        // activeTool = null;
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this MatrixGui will operate, and
    // populates the tools to be used
    // to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: a helper method which declares and instantiates all tools
    public void createTools() {

        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0, 1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        // adds a view select tool
        viewSelect = new ViewSelectMenu(this.matrices);
        viewSelect.viewSelectToolAdd(toolArea);

        // adds Save Tool
        saveTool = new SaveTool(this, toolArea);

        // adds Load Tool
        loadTool = new LoadTool(this, toolArea);

        // adds add a matrix tool
        addTool = new AddMatrixTool(this, toolArea);

        pack();

        System.out.println("tools created / recreated");

    }

    public void refreshComboBox() {
        viewSelect.addMatricesToComboBox(this.matrices);
    }

    public void addMatrix() {
        addTool.addMatrixToList(this.matrices);
    }

    // public void showMatrix(Matrix matrix) {
    //     int height = matrix.getRows().size();
    //     int width = matrix.getWidth();
    //     RowList rowsMatrix = matrix.getMatrixRows();
    //     RowList redRefMatrix = matrix.getRedRefRows();

    //     JPanel container = new JPanel(new FlowLayout());
    //     container.add(getMatrixPanel(rowsMatrix, width, height, "Unsolved"));
    //     container.add(getMatrixPanel(redRefMatrix, width, height, "RREF"));

    //     add(container, BorderLayout.CENTER);

    //     pack();
    //     revalidate();
    //     repaint();
    // }

    // private JPanel getMatrixPanel(RowList rowList, int width, int height, String msg) {
    //     JPanel matrixSpace = new JPanel(new GridLayout(height, width, 2, 2));

    //     for (Row r : rowList) {
    //         for (float f : r) {
    //             String val = String.valueOf(f);
    //             JLabel label = new JLabel(val);
    //             label.setHorizontalAlignment(SwingConstants.CENTER);
    //             matrixSpace.add(label);
    //         }
    //     }
    //     matrixSpace.setBorder(new LineBorder(Color.BLACK, 2));
    //     matrixSpace.setPreferredSize(new Dimension(width * 30, height * 30));
    //     JPanel container = new JPanel();
    //     container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    //     container.add(matrixSpace);
    //     container.add(new JLabel(msg + " Matrix"));
    //     return container;
    // }

    // // EFFECTS: TESTING METHOD ONLY
    // public void showMatrix() {
    // // TODO: creates a JPanel of a matrix then adds to JFrame.
    // // JPanel is aligned to the same area so repeated calls
    // // of this method lead to replacing the matrix.

    // matrixArea.setBorder(new LineBorder(Color.BLACK, 2));

    // JPanel container = new JPanel();
    // container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    // matrixArea.setPreferredSize(new Dimension(100, 100));

    // container.add(matrixArea);
    // container.add(new JLabel("this is a LABEL RAGGHH"));

    // JPanel outerContainer = new JPanel(new FlowLayout());
    // outerContainer.add(container);

    // outerContainer.add(new JLabel("a thing"));

    // add(outerContainer, BorderLayout.CENTER);
    // // STUB

    // pack();
    // revalidate();
    // repaint();

    // }

    /*
     * 
     * This above method successfully creates a "stub"
     * button whenever I click and select a matrix!
     * 
     */

    // EFFECTS: saves the MatrixList to file

    public void saveMatrixList() {
        try {
            jsonWriter.open();
            jsonWriter.write(matrices);
            jsonWriter.close();
            System.out.println("Saved matrices " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads matrices from file
    public void loadMatrixList() {
        try {
            this.matrices = jsonReader.read();
            System.out.println("Loaded matrices from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        // System.out.println(matrices.getMatrices().get(0).getMatrixDesc()); working
        // fine
    }

}
