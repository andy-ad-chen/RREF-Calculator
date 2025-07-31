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
import ui.tools.RemoveMatrixTool;
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

    // // contents in BorderLayout.CENTER
    // private JPanel container;

    private MatrixList matrices;

    private ViewSelectMenu viewSelect;
    private SaveTool saveTool;
    private LoadTool loadTool;
    private AddMatrixTool addTool;
    private RemoveMatrixTool deleteTool;
    private int activeIndex;

    private JPanel toolArea;

    // EFFECTS: runs the Matrix Reducer App
    public MatrixGui() {
        super("Matrix Reducer App");
        setVisible(true);
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

    public void resetCenter() {
        Container contentPane = getContentPane();
        Component center = ((BorderLayout) contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER);

        if (center != null) {
            contentPane.remove(center);
        }
    }

    // public JPanel getCenter() {
    // return this.container;
    // }

    // MODIFIES: this
    // EFFECTS: a helper method which declares and instantiates all tools
    public void createTools() {

        toolArea = new JPanel();
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

        // adds a delete tool
        deleteTool = new RemoveMatrixTool(this, toolArea);

        // // empty centre area.
        // this.container = new JPanel();

        pack();

    }

    public void setActiveIndex(int i) {
        activeIndex = i;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void removeActiveIndex() {
        if (activeIndex >= 0) {
            matrices.removeMatrix(activeIndex);
        }
    }

    public void setDeleteActive() {
        deleteTool.setActive();
    }

    public void setDeleteInactive() {
        deleteTool.setInactive();
    }

    public void refreshComboBox() {
        viewSelect.addMatricesToComboBox(this.matrices);
    }

    public MatrixList getMatrices() {
        return this.matrices;
    }

    public void addCompletedMatrix(Matrix matrix) {
        matrices.addMatrix(matrix);
    }

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
