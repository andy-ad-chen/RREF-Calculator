package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Robot;
import java.awt.print.PrinterException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Matrix;
import model.Row;
import model.RowList;

public class MatrixInserter {
    private static MatrixGui mainGui;
    private String name;
    private String desc;
    private int width;
    private int height;

    private DefaultTableModel model;
    private JTable table;
    private JPanel wrapper;
    private JButton next;

    private Matrix workingMatrix;

    public MatrixInserter() {
        mainGui = Main.getMatrixGui();
        next = new JButton("Verify values and Continue");
        next.addActionListener(e -> {
            if (valuesOk()) {
                readValuesIntoMatrix();
                mainGui.addCompletedMatrix(workingMatrix);
                mainGui.refreshComboBox();
                mainGui.resetCenter();

            } else {
                JOptionPane.showMessageDialog(null,
                        "These values are not valid; please ensure that " +
                                "all items in matrix are numbers.");
            }
        });
    }

    public void adder(int width, int height, String name, String desc) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.desc = desc;

        mainGui.resetCenter();
        String[] columns = new String[width];

        model = new DefaultTableModel(columns, height);
        table = new JTable(model);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setSelectionBackground(Color.BLUE);
        table.setSelectionForeground(Color.WHITE);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);

        wrapper = new JPanel(new BorderLayout());
        wrapper.add(table, BorderLayout.CENTER);

        wrapper.add(next, BorderLayout.SOUTH);

        mainGui.getContentPane().add(wrapper, BorderLayout.CENTER);
        mainGui.revalidate();
        mainGui.repaint();

    }

    private boolean valuesOk() {
        try {
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
            for (int i = 0; i < height; i++) {
                for (int k = 0; k < width; k++) {
                    Object input = table.getValueAt(i, k);
                    System.out.println(input.toString());
                    Float.parseFloat(input.toString());
                }
            }
        } catch (Exception e) {
            return false ;
        }
        return true;

        // return false; // STUB
    }

    // MODIFIES: this
    // EFFECTS: reads values and puts them in matrix
    private void readValuesIntoMatrix() {
        Matrix matrix;
        RowList rowList = new RowList();
        for (int i = 0; i < height; i++) {
            Row row = new Row();
            for (int k = 0; k < width; k++) {
                Object input = table.getValueAt(i, k);
                System.out.println(input.toString());
                Float f = Float.parseFloat(input.toString());
                row.add(f);
            }
            rowList.add(row);
        }

        matrix = new Matrix(rowList, width, name, desc);
        matrix.computeRedRef();
        workingMatrix = matrix;
    }

}
