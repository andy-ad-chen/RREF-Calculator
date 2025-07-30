package ui.tools;

import ui.Main;
import ui.MatrixGui;

import javax.swing.*;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadTool extends Tool {
    private static MatrixGui mainGui;

    public LoadTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);
        mainGui = Main.getMatrixGui();
    }

    @Override
    protected void createButton(JComponent parent) {
        // TODO Auto-generated method stub
        button = new JButton("Load from File");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new LoadToolHandler());

    }

    private class LoadToolHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("clicked the load tool");
            mainGui.loadMatrixList();
        }
    }
}