package code.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class PreparedLabel extends CustComponent {
    private JLabel label;

    public PreparedLabel() {
        label = new JLabel();
    }

    PreparedLabel(Icon _icon) {
        label = new JLabel(_icon);
    }

    public void setEmptyIcon() {
        label.setIcon(new ImageIcon());
    }

    public void setIcon(BufferedImage _icon) {
        label.setIcon(new ImageIcon(_icon));
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
    protected JComponent getComponent() {
        return label;
    }

    public void setOpaque(boolean b) {
        label.setOpaque(b);
    }

    JLabel getLabel() {
        return label;
    }
}
