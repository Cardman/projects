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

    public void setIcon(Icon _icon) {
        label.setIcon(_icon);
    }

    public FontMetrics getFontMetrics(Font _font) {
        return label.getFontMetrics(_font);
    }

    public Font getFont() {
        return label.getFont();
    }

    public void setForeground(Color _fg) {
        label.setForeground(_fg);
    }

    public void setBackground(Color _bg) {
        label.setBackground(_bg);
    }

    public void setFont(Font _font) {
        label.setFont(_font);
    }

    public void requestFocus() {
        label.requestFocus();
    }

    @Override
    public JComponent getComponent() {
        return label;
    }

    public void setOpaque(boolean b) {
        label.setOpaque(b);
    }

    public void addMouseListener(MouseListener anchorEvent) {
        label.addMouseListener(anchorEvent);
    }

    public void addKeyListener(KeyListener _l) {
        label.addKeyListener(_l);
    }

    public JLabel getLabel() {
        return label;
    }
}
