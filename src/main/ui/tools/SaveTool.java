package ui.tools;

import ui.Main;
import ui.MatrixGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * 
 * This class is inspired by code provided by the UBC Department of Computer
 * Science, in the course material of CPSC 210.
 * Found in lecture lab, CPSC 210 2025S2, SimplyDrawingPlayer,
 * DeleteTool.java class.
 * 
 */

// Tool to saves matrices from file 
public class SaveTool extends Tool {
    private static MatrixGui mainGui;

    // MODIFIES: parent
    // EFFECTS: adds a new LoadTool button
    public SaveTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: parent
    // EFFECTS: adds a new button saying "Save and Write to File"
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save and Write to File");
        button = customizeButton(button);
        addToParent(parent);
    }

    // MODIFIES: parent
    // EFFECTS: adds a handler for this button
    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolHandler());

    }

    // EFFECTS: a handler for this button
    private class SaveToolHandler implements ActionListener {
        // MODIFIES: this, mainGui
        // EFFECTS: loads from file
        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println("clicked the save tool"); //USED FOR DEBUGGING
            mainGui = Main.getMatrixGui();
            mainGui.saveMatrixList();
        }
    }
}