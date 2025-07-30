package ui.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Matrix;
import model.MatrixList;
import ui.Main;
import ui.MatrixGui;

public class ViewSelectMenu {
    private MatrixList matrices;
    private static MatrixGui mainGui;

    // EFFECTS: builds an empty item used to build View Select Menu
    public ViewSelectMenu(MatrixList matrices) {
        this.matrices = matrices;
    }

    // MODIFIES: panel
    // EFFECTS: adds the list of matrices to the drop down panel.
    public void viewSelectToolAdd(JPanel panel) {
        JComboBox<String> comboOfMatrices = new JComboBox<String>();

        comboOfMatrices.addItem("- Select and View a Matrix -");
        // addMatricesToComboBox(matrices);
        // TODO: IMPLEMENT THESE
        comboOfMatrices.addItem("Sample2");
        comboOfMatrices.addItem("Sample3");

        panel.add(comboOfMatrices, BorderLayout.CENTER);

        comboOfMatrices.addActionListener(new comboHandler());

    }

    private class comboHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            String selectedName = (String) combo.getSelectedItem();
            int indexOfName = combo.getSelectedIndex();
            // Matrix shownMatrix = matrices.getMatrices().get(indexOfName - 1); // COMMENT IN
            System.out.println(selectedName);
            System.out.println(indexOfName);
            mainGui = Main.getMatrixGui();
            // shows the selected matrix on the main panel.
            mainGui.showMatrix(); // STUB

        }
    }

}
