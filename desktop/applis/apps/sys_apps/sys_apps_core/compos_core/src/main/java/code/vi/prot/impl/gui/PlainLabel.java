package code.vi.prot.impl.gui;

import code.gui.AbsPlainLabel;

import javax.swing.*;

public final class PlainLabel extends CustComponent implements AbsPlainLabel {

    private final JLabel label = new JLabel();

    public PlainLabel(String _text) {
        disabledRichText(true);
        label.setText(_text);
    }

    public void setText(String _simpleNumberFormat) {
        label.setText(_simpleNumberFormat);
    }

    public String getText() {
        return label.getText();
    }

    @Override
    public JComponent getNatComponent() {
        return label;
    }
}
