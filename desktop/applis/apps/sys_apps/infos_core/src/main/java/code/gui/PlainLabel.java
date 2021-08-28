package code.gui;

import javax.swing.*;

public final class PlainLabel extends CustComponent implements AbsPlainLabel {

    private final JLabel label = new JLabel();

    public PlainLabel(String _text) {
        label.putClientProperty("html.disable", true);
        label.setText(_text);
    }

    public void setText(String _simpleNumberFormat) {
        label.setText(_simpleNumberFormat);
    }

    public String getText() {
        return label.getText();
    }

    @Override
    protected JComponent getNatComponent() {
        return label;
    }
}
