package code.gui;

import javax.swing.*;

public final class CustButtonGroup {
    private ButtonGroup buttonGroup = new ButtonGroup();

    public void add(RadioButton b) {
        buttonGroup.add((AbstractButton) b.getComponent());
    }

    public void remove(RadioButton b) {
        buttonGroup.remove((AbstractButton) b.getComponent());
    }

    public void clearSelection() {
        buttonGroup.clearSelection();
    }

    public int getButtonCount() {
        return buttonGroup.getButtonCount();
    }
}
