package ui.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;
import ui.Main;
import ui.MatrixGui;

public class ViewSelectMenu {

    // // FOR TESTING START
    // protected RowList identity;
    // protected Row identityRow1;
    // protected Row identityRow2;
    // protected Row identityRow3;
    // protected Matrix identityMatrix;
    // // FOR TESTING END

    private MatrixList matrices;
    private static MatrixGui mainGui;

    // EFFECTS: builds an empty item used to build View Select Menu
    public ViewSelectMenu(MatrixList matrices) {
        this.matrices = matrices;
        mainGui = Main.getMatrixGui();

        // // FOR TESTING START
        // identity = new RowList(3);
        // identityRow1 = new Row();
        // identityRow2 = new Row();
        // identityRow3 = new Row();
        // identityRow1.add(1.0f);
        // identityRow1.add(0.0f);
        // identityRow1.add(0.0f);
        // identityRow2.add(0.0f);
        // identityRow2.add(1.0f);
        // identityRow2.add(0.0f);
        // identityRow3.add(0.0f);
        // identityRow3.add(0.0f);
        // identityRow3.add(1.0f);
        // identity.add(identityRow1);
        // identity.add(identityRow2);
        // identity.add(identityRow3);
        // identityMatrix = new Matrix(identity, 3, "myIDMatrix", "desc");
        // // FOR TESTING END
    }

    // MODIFIES: panel
    // EFFECTS: adds the list of matrices to the drop down panel.
    public void viewSelectToolAdd(JPanel panel) {
        JComboBox<String> comboOfMatrices = new JComboBox<String>();

        comboOfMatrices.addItem("- Select and View a Matrix -");

        addMatricesToComboBox(comboOfMatrices, matrices);

        comboOfMatrices.addActionListener(new comboHandler());

    }

    private class comboHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            String selectedName = (String) combo.getSelectedItem();
            int indexOfName = combo.getSelectedIndex();
            Matrix shownMatrix = matrices.getMatrices().get(indexOfName - 1); // COMNT IN
            // Matrix shownMatrix = identityMatrix; // STUB
            System.out.println(selectedName);
            System.out.println(indexOfName);
            // shows the selected matrix on the main panel.
            mainGui.showMatrix(shownMatrix); // COMMENT IN

        }
    }

    // MODIFIES: comboOfMatrices
    // EFFECTS: adds the matrix list into the combo
    private void addMatricesToComboBox(JComboBox<String> comboOfMatrices, MatrixList matrices) {
        for (Matrix m : matrices.getMatrices()) {
            String val = m.getMatrixName();
            comboOfMatrices.addItem(val);
        }
    }

}
