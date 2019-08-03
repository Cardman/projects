package code.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class PlainButton extends CustComponent {
    private JButton button;
    public PlainButton() {
        button = new JButton();
    }
    public PlainButton(String _b) {
        button = new JButton(_b);
    }

    public boolean isDefaultButton() {
        return button.isDefaultButton();
    }

    public boolean isDefaultCapable() {
        return button.isDefaultCapable();
    }

    public String getText() {
        return button.getText();
    }

    public void setText(String text) {
        button.setText(text);
    }

    public void addActionListener(ActionListener l) {
        button.addActionListener(l);
    }

    public void setEnabled(boolean b) {
        button.setEnabled(b);
    }

    @Override
    public JComponent getComponent() {
        return button;
    }
}
