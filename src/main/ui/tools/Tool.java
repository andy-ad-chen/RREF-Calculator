package ui.tools;

import ui.MatrixGui;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class Tool {

    protected JButton button;
    protected MatrixGui editor;

    // EFFECTS: constructs a Tool associated with the given editor
    // with its activation button inside the given parent
    public Tool(MatrixGui gui, JComponent parent) {
        this.editor = gui;
        createButton(parent);
        addToParent(parent);

        addListener();
    }

    // MODIFIES: this
    // EFFECTS: customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS: adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

}
