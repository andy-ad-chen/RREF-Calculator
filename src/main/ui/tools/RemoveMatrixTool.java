package ui.tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import ui.Main;
import ui.MatrixGui;

public class RemoveMatrixTool extends Tool {
    private static MatrixGui mainGui;

    public RemoveMatrixTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new RemoveMatrixToolHandler());

    }

        @Override
    protected void createButton(JComponent parent) {
        // TODO Auto-generated method stub
        button = new JButton("Delete This Matrix");
        button = customizeButton(button);
        button.setOpaque(true);
        button.setForeground(Color.RED);
        addToParent(parent);
    }

    private class RemoveMatrixToolHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("clicked the delete tool");
            mainGui = Main.getMatrixGui();
            mainGui.loadMatrixList();
            mainGui.refreshComboBox();

        }
    }

}
