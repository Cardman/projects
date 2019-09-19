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

    PreparedLabel(BufferedImage _icon) {
        label = new JLabel(new ImageIcon(_icon));
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

    @Override
    protected JComponent getComponent() {
        return label;
    }

    JLabel getLabel() {
        return label;
    }
}
