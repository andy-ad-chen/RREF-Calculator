package ui.tools;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import model.Matrix;
import model.MatrixList;
import model.Row;
import model.RowList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Main;
import ui.MatrixGui;

public class AddMatrixTool extends Tool {
    private static MatrixGui mainGui;

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
        matrices.getMatrices().add(new Matrix(identity, 3, "differnet id matrix", "nuh huh"));
        matrices.getMatrices().add(identityMatrix); // STUB 

    }

}
