package ui;

import javax.swing.*;

import model.Matrix;
import model.MatrixList;
import ui.tools.LoadTool;
import ui.tools.SaveTool;
import ui.tools.Tool;
import ui.tools.ViewSelectMenu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MatrixGui extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private MatrixList matrices = new MatrixList();

    private List<Tool> tools;
    // private Tool activeTool;

    // EFFECTS: runs the Matrix Reducer App
    public MatrixGui() {
        super("Matrix Reducer App");
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
        // activeTool = null;
        tools = new ArrayList<Tool>();
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
    private void createTools() {

        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0, 1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        // adds a view select tool
        ViewSelectMenu viewSelect = new ViewSelectMenu(matrices);
        viewSelect.viewSelectToolAdd(toolArea);

        // adds Save Tool
        SaveTool saveTool = new SaveTool(this, toolArea);
        tools.add(saveTool);

        // adds Load Tool
        LoadTool loadTool = new LoadTool(this, toolArea);
        tools.add(loadTool);

    }

    public void showMatrix(Matrix matrix) {
        // TODO: creates a JPanel of a matrix then adds to JFrame.
        // JPanel is aligned to the same area so repeated calls
        // of this method lead to replacing the matrix.
        

        JPanel matrixArea = new JPanel();
        matrixArea

    }

}
