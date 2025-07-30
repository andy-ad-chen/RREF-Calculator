package ui.tools;

import ui.Main;
import ui.MatrixGui;

import javax.swing.*;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTool extends Tool {
    private static MatrixGui mainGui;

    public SaveTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
            System.out.println("clicked the save tool");
            mainGui = Main.getMatrixGui();

            mainGui.saveMatrixList();
        }
    }
}