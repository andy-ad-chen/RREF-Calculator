package ui.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;
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

// menu to see all matrices
public class ViewSelectMenu {
    private MatrixList matrices;
    private static MatrixGui mainGui;
    private Matrix shownMatrix;

    private JComboBox<String> comboOfMatrices;
    private JButton confirmButton;

    // EFFECTS: builds an empty item used to build View Select Menu
    public ViewSelectMenu(MatrixList matrices) {
        this.matrices = matrices;
        mainGui = Main.getMatrixGui();

    }

    // MODIFIES: panel
    // EFFECTS: adds the list of matrices to the drop down panel.
    public void viewSelectToolAdd(JPanel panel) {
        comboOfMatrices = new JComboBox<String>();
        confirmButton = new JButton("View");
        confirmButton.setForeground(Color.GRAY);
        JPanel container = new JPanel(new BorderLayout());

        addMatricesToComboBox(this.matrices);
        comboOfMatrices.addActionListener(new ComboHandler());

        confirmButton.addActionListener(new ConfirmHandler());

        container.add(comboOfMatrices, BorderLayout.CENTER);
        container.add(confirmButton, BorderLayout.LINE_END);

        panel.add(container);

    }

    // EFFECTS: a handler for the view button.
    private class ConfirmHandler implements ActionListener {
        // MODIFIES: this, mainGui
        // EFFECTS: displays selected matrix
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked the confirm button");
            int indexOfName = comboOfMatrices.getSelectedIndex() - 1;
            if (indexOfName >= 0) {
                // I personally find this very funny
                shownMatrix = mainGui.getMatrices().getMatrices().get(indexOfName);
            }
            if (shownMatrix != null) {
                showMatrix();
            }
        }
    }

    // RECALL: I originally intended to handle everything without a confirm button
    // but this was difficult as many strange events were being caught.

    // EFFECTS: handler for delete key changing colour
    private class ComboHandler implements ActionListener {
        // MODIFIES: mainGui
        // EFFECTS: if the selected index is not the first (IE is a valid matrix),
        // change delete key colour to red.
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            // String selectedName = (String) combo.getSelectedItem();
            int indexOfName = combo.getSelectedIndex() - 1;

            mainGui = Main.getMatrixGui();
            // Make this only run when the selection is not the default.

            if (indexOfName > -1) {
                confirmButton.setForeground(Color.GREEN);
                mainGui.setDeleteActive();
            } else {
                confirmButton.setForeground(Color.GRAY);
                mainGui.setDeleteInactive();
            }

            mainGui.setActiveIndex(indexOfName);

        }
    }

    // MODIFIES: mainGui
    // EFFECTS: shows a matrix
    public void showMatrix() {
        int height = shownMatrix.getRows().size();
        int width = shownMatrix.getWidth();
        RowList rowsMatrix = shownMatrix.getMatrixRows();
        RowList redRefMatrix = shownMatrix.getRedRefRows();
        String desc = shownMatrix.getMatrixDesc();

        mainGui.resetCenter();

        JPanel container = new JPanel(new FlowLayout());
        JTextArea descText = new JTextArea(desc);
        container.add(getMatrixPanel(rowsMatrix, width, height, "Unsolved"));
        container.add(getMatrixPanel(redRefMatrix, width, height, "RREF"));
        container.add(descText);

        // TODO: display the description

        mainGui.add(container, BorderLayout.CENTER);
        mainGui.revalidate();
        mainGui.repaint();

    }

    // EFFECTS: returns a JPanel of the matrix passed in. 
    private JPanel getMatrixPanel(RowList rowList, int width, int height, String msg) {
        JPanel matrixSpace = new JPanel(new GridLayout(height, width, 2, 2));

        for (Row r : rowList) {
            for (float f : r) {
                String val = String.valueOf(f);
                JLabel label = new JLabel(val);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                matrixSpace.add(label);
            }
        }
        matrixSpace.setBorder(new LineBorder(Color.BLACK, 2));
        matrixSpace.setPreferredSize(new Dimension(width * 30, height * 30));
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(matrixSpace);
        container.add(new JLabel(msg + " Matrix"));
        return container;
    }

    // MODIFIES: comboOfMatrices
    // EFFECTS: adds the matrix list into the combo
    public void addMatricesToComboBox(MatrixList matrices) {

        comboOfMatrices.removeAllItems();
        comboOfMatrices.addItem("- Select and View a Matrix -");
        ArrayList<Matrix> listOf = matrices.getMatrices();
        System.out.println("Size of matrix list to append: " + listOf.size());
        for (int i = 0; i < listOf.size(); i++) {
            String val = listOf.get(i).getMatrixName();
            comboOfMatrices.addItem(val);
        }
        comboOfMatrices.revalidate();
        comboOfMatrices.repaint();
    }
}