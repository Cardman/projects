package code.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class PlainLabel extends CustComponent {

    private JLabel label = new JLabel();

    public PlainLabel(String _text) {
        label.putClientProperty("html.disable", true);
        label.setText(_text);
    }

    public PlainLabel(String _titre, int _align) {
        this(_titre);
        label.setHorizontalAlignment(_align);
    }

    public void setText(String _simpleNumberFormat) {
        label.setText(_simpleNumberFormat);
    }

    public String getText() {
        return label.getText();
    }

    @Override
    public JComponent getComponent() {
        return label;
    }
}
