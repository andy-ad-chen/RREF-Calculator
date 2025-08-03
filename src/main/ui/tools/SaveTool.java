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

public class SaveTool extends Tool {
    private static MatrixGui mainGui;

    public SaveTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save and Write to File");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolHandler());

    }

    private class SaveToolHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked the save tool");
            mainGui = Main.getMatrixGui();
            mainGui.saveMatrixList();
        }
    }
}