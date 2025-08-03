package ui.tools;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Main;
import ui.MatrixGui;
import ui.MatrixInserter;


/*
 * 
 * This class is inspired by code provided by the UBC Department of Computer
 * Science, in the course material of CPSC 210.
 * Found in lecture lab, CPSC 210 2025S2, SimplyDrawingPlayer,
 * DeleteTool.java class.
 * 
 */

public class AddMatrixTool extends Tool {
    private static MatrixGui mainGui;

    private JButton next;

    private JTextField descField;
    private JTextField nameField;
    private JTextField widthField;
    private JTextField heightField;

    private int width;
    private int height;
    private String name;
    private String desc;

    // // FOR TESTING START
    // protected RowList identity;
    // protected Row identityRow1;
    // protected Row identityRow2;
    // protected Row identityRow3;
    // protected Matrix identityMatrix;
    // // FOR TESTING END

    public AddMatrixTool(MatrixGui gui, JComponent parent) {
        super(gui, parent);
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

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add a new Matrix");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AddMatrixToolHandler());
    }

    // EFFECTS: handler for add matrix button
    private class AddMatrixToolHandler implements ActionListener {

        // MODIFIES: mainGui
        // EFFECTS: calls addMatrixToList(); displays pane for adding matrix and adds a
        // matrix
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked the add matrix tool");
            mainGui = Main.getMatrixGui();
            addMatrixToList();
            mainGui.refreshComboBox();
        }
    }

    // MODIFIES: matrices
    // EFFECTS: allows user to specify and add a matrix
    public void addMatrixToList() {
        // matrices.getMatrices().add(identityMatrix); // STUB

        next = new JButton("Verify values and Continue");
        JPanel wrapper = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JPanel widthChooser = new JPanel(new BorderLayout());
        JPanel heightChooser = new JPanel(new BorderLayout());
        JPanel nameChooser = new JPanel(new BorderLayout());
        JPanel descChooser = new JPanel(new BorderLayout());
        initFields();
        displayLabels(widthChooser, heightChooser, nameChooser, descChooser);

        // init all the choosers
        widthChooserInit(widthChooser);
        heightChooserInit(heightChooser);
        nameChooserInit(nameChooser);
        descChooserInit(descChooser);

        // Action listener for Continue Button
        actionListenerOnNext();

        // adds everything to panel
        addToPanel(panel, widthChooser, heightChooser, nameChooser, descChooser);

        wrapper.add(panel, BorderLayout.CENTER);
        wrapper.add(next, BorderLayout.SOUTH);

        mainGui.resetCenter();
        mainGui.getContentPane().add(wrapper, BorderLayout.CENTER);
        mainGui.revalidate();
        mainGui.repaint();
    }

    // MODIFIES: panel
    // EFFECTS: adds everything to panel
    private void addToPanel(JPanel panel, JPanel widthChooser, JPanel heightChooser, JPanel nameChooser,
            JPanel descChooser) {
        panel.add(nameChooser);
        panel.add(widthChooser);
        panel.add(descChooser);
        panel.add(heightChooser);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // MODIFES: this
    // EFFECTS: adds an actionlisteer for the "next" button, with a value checker
    private void actionListenerOnNext() {
        next.addActionListener(e -> {
            if (valuesOk()) {
                MatrixInserter adder = new MatrixInserter();
                adder.adder(width, height, name, desc);

                System.out.println("starting matrix value inserter");

                // JOptionPane.showMessageDialog(null,
                // "all OK now");
            } else {
                JOptionPane.showMessageDialog(null,
                        "These values are not valid: please ensure that "
                                + "matrices are at least 1 x 1 and are less than"
                                + " 15 x 15. For larger matrices, please use the console"
                                + " version of this app. Please also ensure that"
                                + " names are unique.");
            }
        });
    }

    // MODIFIES: JPanel parameter
    // EFFECTS: initializes a text field for desc
    private void descChooserInit(JPanel descChooser) {
        descField = new JTextField(20);
        descField.setText("Description");
        descChooser.add(descField, BorderLayout.CENTER);
    }

    // MODIFIES: JPanel parameter
    // EFFECTS: initializes a text field for name
    private void nameChooserInit(JPanel nameChooser) {
        nameField = new JTextField(20);
        nameField.setText("Name");
        nameChooser.add(nameField, BorderLayout.CENTER);
    }

    // MODIFIES: JPanel parameter
    // EFFECTS: initializes a text field for height
    private void heightChooserInit(JPanel heightChooser) {
        heightField = new JTextField(20);
        heightField.setText("1");
        heightChooser.add(heightField, BorderLayout.CENTER);
    }

    // MODIFIES: JPanel parameter
    // EFFECTS: initializes a text field for width
    private void widthChooserInit(JPanel widthChooser) {
        widthField = new JTextField(20);
        widthField.setText("1");
        widthChooser.add(widthField, BorderLayout.CENTER);
    }

    // MODIFEIS: this
    // EFFECTS: initializes fields
    private void initFields() {
        width = 0;
        height = 0;
        name = null;
        desc = null;
    }

    // MODIFIES: widthChooser, heightChooser, nameChooser, descChooser
    // EFFECTS: Display labels
    private void displayLabels(JPanel widthChooser, JPanel heightChooser, JPanel nameChooser, JPanel descChooser) {
        widthChooser.add(new Label("Choose a number of Columns"), BorderLayout.NORTH);
        heightChooser.add(new Label("Choose a number of Rows"), BorderLayout.NORTH);
        nameChooser.add(new Label("Choose a name"), BorderLayout.NORTH);
        descChooser.add(new Label("Write a description"), BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: checks the matrix for valid values, returns true if OK.
    // TODO: prevent same names from passing.
    private boolean valuesOk() {
        try {
            String input = widthField.getText();
            width = Integer.parseInt(input);
        } catch (Exception f) {
            System.out.println("did not except non-number");
        }
        try {
            String input = heightField.getText();
            height = Integer.parseInt(input);
        } catch (Exception f) {
            System.out.println("did not except non-number");
        }

        name = nameField.getText();
        desc = descField.getText();

        if (name != null && desc != null && width > 0 && width < 15 && height > 0 && height < 15) {
            return true;
        } else {
            return false;
        }
    }

}
