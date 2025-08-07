package ui.tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import ui.Main;
import ui.MatrixGui;

/*
 * 
 * This class is inspired by code provided by the UBC Department of Computer
 * Science, in the course material of CPSC 210.
 * Found in lecture lab, CPSC 210 2025S2, SimplyDrawingPlayer,
 * DeleteTool.java class.
 * 
 */

// Tool to remove current matrix from file 
public class RemoveMatrixTool extends Tool {
    private static MatrixGui mainGui;

    // MODIFIES: parent
    // EFFECTS: adds a new delete button
    public RemoveMatrixTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: parent
    // EFFECTS: adds a new button saying "Delete This Matrix"
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Delete This Matrix");
        button = customizeButton(button);
        button.setOpaque(true);
        button.setForeground(Color.GRAY);
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: adds action listener to remove matrix
    @Override
    protected void addListener() {
        button.addActionListener(new RemoveMatrixToolHandler());
    }

    /*
     * Bellow methods exist to reduce coupling
     */

    // MODIFIES: this
    // EFFECTS: changes colour to RED
    public void setActive() {
        button.setForeground(Color.RED);
    }

    // MODIFIES: this
    // EFFECTS: changes colour to GRAY
    public void setInactive() {
        button.setForeground(Color.GRAY);
    }

    // EFFECTS: a handler for this button
    private class RemoveMatrixToolHandler implements ActionListener {
        // MODIFIES: this, mainGui
        // EFFECTS: removes seleted matrix
        @Override
        public void actionPerformed(ActionEvent e) {

            // System.out.println("clicked the delete tool"); //USED FOR DEBUGGING
            mainGui = Main.getMatrixGui();
            mainGui.removeActiveIndex();
            mainGui.refreshComboBox();
        }
    }

}
