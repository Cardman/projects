package code.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public final class PreparedLabel extends CustComponent {
    private JLabel label;

    public PreparedLabel() {
        label = new JLabel();
    }

    public PreparedLabel(Icon _icon) {
        label = new JLabel(_icon);
    }

    public void setIcon(ImageIcon _icon) {
        label.setIcon(_icon);
    }

    public FontMetrics getFontMetrics(Font _font) {
        return label.getFontMetrics(_font);
    }

    public void setForeground(Color _fg) {
        label.setForeground(_fg);
    }

    public void setBackground(Color _bg) {
        label.setBackground(_bg);
    }


    @Override
    public JComponent getComponent() {
        return label;
    }

    public void setOpaque(boolean b) {
        label.setOpaque(b);
    }

    JLabel getLabel() {
        return label;
    }
}
