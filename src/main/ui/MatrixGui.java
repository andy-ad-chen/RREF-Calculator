package ui;

import javax.swing.*;

import model.Event;
import model.EventLog;
import model.Matrix;
import model.MatrixList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.AddMatrixTool;
import ui.tools.LoadTool;
import ui.tools.RemoveMatrixTool;
import ui.tools.SaveTool;
import ui.tools.ViewSelectMenu;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 
 * This class is inspired by code provided by the UBC Department of Computer
 * Science, in the course material of CPSC 210.
 * Found in lecture lab, CPSC 210 2025S2, SimplyDrawingPlayer,
 * DrawingEditor.java class.
 * 
 */

// GUI of matrix app
public class MatrixGui extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 330;
    private static final String JSON_STORE = "./data/matrices.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private MatrixList matrices;

    private ViewSelectMenu viewSelect;
    // private SaveTool saveTool;
    // private LoadTool loadTool;
    // private AddMatrixTool addTool;
    private RemoveMatrixTool deleteTool;
    private int activeIndex;

    private JPanel toolArea;

    // EFFECTS: runs the Matrix Reducer App GUI, initializes fields and starts
    // graphics
    public MatrixGui() {
        super("Matrix Reducer App");
        setVisible(true);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: creates a new MatrixList in this
    private void initializeFields() {
        matrices = new MatrixList();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this MatrixGui will operate, and
    // populates the tools to be used
    // to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // changed
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // changed
        addWindowListener();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void addWindowListener() {
        this.addWindowListener(new CloseWindowHandler());
    }

    // NOTE: Next method uses WindowAdapter since I tried WindowListener (as I used
    // ActionListener in all my buttons) and found that I had to implement many
    // different window events' actions

    /*
     * 
     * These below methods were previously necessarily implemented in this private
     * class as this private class
     * implemented the interface WindowListener
     * 
     * 
     * @Override
     * public void windowIconified(WindowEvent e) {
     * }
     * 
     * @Override
     * public void windowDeiconified(WindowEvent e) {
     * }
     * 
     * @Override
     * public void windowActivated(WindowEvent e) {
     * }
     * 
     * @Override
     * public void windowDeactivated(WindowEvent e) {
     * }
     * 
     * @Override
     * public void windowClosed(WindowEvent e) {
     * }
     * 
     * @Override
     * public void windowOpened(WindowEvent e) {
     * }
     * 
     */

    // EFFECTS: a handler for window close
    // private class CloseWindowHandler implements WindowListener {
    private class CloseWindowHandler extends WindowAdapter {
        // MODIFIES: this
        // EFFECTS: Closes window and prints log to console;
        @Override
        public void windowClosing(WindowEvent e) {
            printLog(EventLog.getInstance());
            dispose();
            System.exit(0);
        }
    }

    // EFFECTS: prints log
    private void printLog(EventLog el) {
        for (Event e : el) {
            System.out.println(e.toString() + "\n");
        }
    }

    public void resetCenter() {
        Container contentPane = getContentPane();
        Component center = ((BorderLayout) contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER);

        if (center != null) {
            contentPane.remove(center);
        }
    }

    // MODIFIES: this
    // EFFECTS: a helper method which declares and instantiates all tools
    public void createTools() {

        toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0, 1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        // adds a view select tool
        viewSelect = new ViewSelectMenu(this.matrices);
        // adds the matrices to this menu
        viewSelect.viewSelectToolAdd(toolArea);
        // adds Save Tool
        new SaveTool(this, toolArea);
        // adds Load Tool
        new LoadTool(this, toolArea);
        // adds "add a matrix" tool
        new AddMatrixTool(this, toolArea);
        // adds a delete tool and stores it to field
        deleteTool = new RemoveMatrixTool(this, toolArea);
        pack();
    }

    // REQUIRES: -1 <= i <= matrices.size()
    // MODIFIES: this
    // EFFECTS: sets the active index to i, setting to -1 when "- select matrix -"
    // is
    // selected
    public void setActiveIndex(int i) {
        activeIndex = i;
    }

    // EFFECTS: get the active index i
    public int getActiveIndex() {
        return activeIndex;
    }

    /*
     * The next four methods are included to reduce coupling between many classes
     * and RemoveMatrixTool.java
     */

    // MODIFIES: this
    // EFFECTS: remove the current acitve matrix
    public void removeActiveIndex() {
        if (activeIndex >= 0) {
            matrices.removeMatrix(activeIndex);
        }
    }

    // MODIFIES: this.deleteTool
    // EFFECTS: sets deleteTool to be red
    public void setDeleteActive() {
        deleteTool.setActive();
    }

    // MODIFIES: this.deleteTool
    // EFFECTS: sets deleteTool to be grayed out
    public void setDeleteInactive() {
        deleteTool.setInactive();
    }

    // MODIFIES: this.viewSelect
    // EFFECTS: redraws the comboBox to show updated matrices list
    public void refreshComboBox() {
        viewSelect.addMatricesToComboBox(this.matrices);
    }

    // EFFECTS: gets matrices
    public MatrixList getMatrices() {
        return this.matrices;
    }

    // MODIFIES: this
    // EFFECTS: adds a matrix to this
    public void addCompletedMatrix(Matrix matrix) {
        matrices.addMatrix(matrix);

    }

    // EFFECTS: saves the matrices to file
    public void saveMatrixList() {
        try {
            jsonWriter.open();
            jsonWriter.write(matrices);
            jsonWriter.close();
            // System.out.println("Saved matrices " + " to " + JSON_STORE); //USED FOR
            // DEBUGGING
        } catch (FileNotFoundException e) {
            // System.out.println("Unable to write to file: " + JSON_STORE); //USED FOR
            // DEBUGGING
        }
    }

    // MODIFIES: this
    // EFFECTS: loads matrices from file
    public void loadMatrixList() {
        try {
            this.matrices = jsonReader.read();
            // System.out.println("Loaded matrices from" + JSON_STORE); //USED FOR DEBUGGING
        } catch (IOException e) {
            // System.out.println("Unable to read from file: " + JSON_STORE); //USED FOR DEBUGGING
        }
    }

}
