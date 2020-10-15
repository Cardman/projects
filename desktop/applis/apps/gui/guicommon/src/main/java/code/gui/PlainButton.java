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

    public void setText(String _text) {
        button.setText(_text);
    }

    public void addActionListener(ActionListener _l) {
        button.addActionListener(_l);
    }

    public boolean isEnabled() {
        return button.isEnabled();
    }
    public void setEnabled(boolean _b) {
        button.setEnabled(_b);
    }

    @Override
    protected JComponent getComponent() {
        return button;
    }
}
