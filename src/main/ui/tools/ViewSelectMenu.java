package ui.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;
import ui.Main;
import ui.MatrixGui;

public class ViewSelectMenu {

    // FOR TESTING START
    protected RowList identity;
    protected Row identityRow1;
    protected Row identityRow2;
    protected Row identityRow3;
    protected Matrix identityMatrix;
    // FOR TESTING END

    private MatrixList matrices;
    private static MatrixGui mainGui;
    private Matrix shownMatrix;

    private JComboBox<String> comboOfMatrices;
    private JButton confirmButton;

    // EFFECTS: builds an empty item used to build View Select Menu
    public ViewSelectMenu(MatrixList matrices) {
        this.matrices = matrices;
        mainGui = Main.getMatrixGui();

        // FOR TESTING START
        identity = new RowList(3);
        identityRow1 = new Row();
        identityRow2 = new Row();
        identityRow3 = new Row();
        identityRow1.add(1.0f);
        identityRow1.add(0.0f);
        identityRow1.add(0.0f);
        identityRow2.add(0.0f);
        identityRow2.add(1.0f);
        identityRow2.add(0.0f);
        identityRow3.add(0.0f);
        identityRow3.add(0.0f);
        identityRow3.add(1.0f);
        identity.add(identityRow1);
        identity.add(identityRow2);
        identity.add(identityRow3);
        identityMatrix = new Matrix(identity, 3, "myIDMatrix", "desc");
        // FOR TESTING END

        shownMatrix = identityMatrix;
    }

    // MODIFIES: panel
    // EFFECTS: adds the list of matrices to the drop down panel.
    public void viewSelectToolAdd(JPanel panel) {
        comboOfMatrices = new JComboBox<String>();
        confirmButton = new JButton("View and Edit");
        confirmButton.setForeground(Color.GRAY);
        JPanel container = new JPanel(new BorderLayout());

        // comboOfMatrices.addItem("- asdfs and View a Matrix -");
        addMatricesToComboBox(this.matrices);
        comboOfMatrices.addActionListener(new comboHandler());

        confirmButton.addActionListener(new confirmHandler());

        container.add(comboOfMatrices, BorderLayout.CENTER);
        container.add(confirmButton, BorderLayout.LINE_END);

        panel.add(container);

    }

    private class confirmHandler implements ActionListener {
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

    // RECALL originally intended to handle everything without the button
    // realized this was quite stupid or at least hard for me.
    private class comboHandler implements ActionListener {
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
            // showMatrix(shownMatrix); // DO NOT comment in; use button

        }
    }

    public void showMatrix() {
        int height = shownMatrix.getRows().size();
        int width = shownMatrix.getWidth();
        RowList rowsMatrix = shownMatrix.getMatrixRows();
        RowList redRefMatrix = shownMatrix.getRedRefRows();

        mainGui.resetCenter();

        JPanel container = new JPanel(new FlowLayout());
        container.add(getMatrixPanel(rowsMatrix, width, height, "Unsolved"));
        container.add(getMatrixPanel(redRefMatrix, width, height, "RREF"));

        mainGui.add(container, BorderLayout.CENTER);

        mainGui.revalidate();
        mainGui.repaint();

    }

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

        // for (Matrix matrix : matrices.getMatrices()) {
        // System.out.println(matrix.getMatrixName());
        // }

    }
}