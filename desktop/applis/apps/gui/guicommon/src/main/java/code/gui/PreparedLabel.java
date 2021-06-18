package code.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class PreparedLabel extends CustComponent {
    private final JLabel label;
    private int width;
    private int height;
    private BufferedImage buffer;

    public PreparedLabel() {
        label = new JLabel();
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
    }

    PreparedLabel(BufferedImage _icon) {
        this(buildIcon(_icon));
        buffer = _icon;
    }

    public PreparedLabel(Icon _icon) {
        label = new JLabel(_icon);
        width = _icon.getIconWidth();
        height = _icon.getIconHeight();
    }

    public void setEmptyIcon() {
        label.setIcon(buildIcon(null));
        width = 0;
        height = 0;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public Icon getIcon() {
        return label.getIcon();
    }
    public void setIcon(BufferedImage _icon) {
        ImageIcon icon_ = buildIcon(_icon);
        label.setIcon(icon_);
        width = icon_.getIconWidth();
        height = icon_.getIconHeight();
    }

    public static ImageIcon buildIcon(BufferedImage _icon) {
        if (_icon == null) {
            return new ImageIcon();
        }
        return new ImageIcon(_icon);
    }

    @Override
    protected JComponent getComponent() {
        return label;
    }

    JLabel getLabel() {
        return label;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
