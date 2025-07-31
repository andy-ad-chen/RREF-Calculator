package ui.tools;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Main;
import ui.MatrixGui;

public class AddMatrixTool extends Tool {
    private static MatrixGui mainGui;

    private int width;
    private int height;
    private String name;
    private String desc;

    // FOR TESTING START
    protected RowList identity;
    protected Row identityRow1;
    protected Row identityRow2;
    protected Row identityRow3;
    protected Matrix identityMatrix;
    // FOR TESTING END

    public AddMatrixTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);

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
    }

    @Override
    protected void createButton(JComponent parent) {
        // TODO Auto-generated method stub
        button = new JButton("Add a new Matrix");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AddMatrixToolHandler());

    }

    private class AddMatrixToolHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("clicked the add matrix tool");
            mainGui = Main.getMatrixGui();
            mainGui.addMatrix();
            mainGui.refreshComboBox();
        }
    }

    // MODIFIES: matrices
    // EFFECTS: allows user to specify and add a matrix
    public void addMatrixToList(MatrixList matrices) {
        matrices.getMatrices().add(new Matrix(identity, 3, "differnet id matrix", "nuh huh")); // STUB
        matrices.getMatrices().add(identityMatrix); // STUBS

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JPanel widthChooser = new JPanel(new BorderLayout());
        JPanel heightChooser = new JPanel(new BorderLayout());
        JPanel nameChooser = new JPanel(new BorderLayout());
        JPanel descChooser = new JPanel(new BorderLayout());

        // initialize fields
        width = 0;
        height = 0;
        name = null;
        desc = null;

        // display labels
        widthChooser.add(new Label("Choose a number of Columns"), BorderLayout.NORTH);
        heightChooser.add(new Label("Choose a number of Rows"), BorderLayout.NORTH);
        nameChooser.add(new Label("Choose a name"), BorderLayout.NORTH);
        descChooser.add(new Label("Write a description"), BorderLayout.NORTH);

        JTextField nameField = new JTextField(20);
        nameField.setText("Default Name");
        nameChooser.add(nameField, BorderLayout.CENTER);

        JTextField descField = new JTextField(20);
        descField.setText("Default Description");
        descChooser.add(descField, BorderLayout.CENTER);

        // add to panel and refresh screen.
        panel.add(nameChooser);
        panel.add(widthChooser);
        panel.add(descChooser);
        panel.add(heightChooser);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        mainGui.resetCenter();
        mainGui.getContentPane().add(panel, BorderLayout.CENTER);
        mainGui.revalidate();
        mainGui.repaint();
    }

}
