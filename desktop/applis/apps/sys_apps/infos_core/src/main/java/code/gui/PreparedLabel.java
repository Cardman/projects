package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.sys.impl.DefImage;
import code.sys.impl.DefImageFactory;

import javax.swing.*;

public final class PreparedLabel extends CustComponent implements AbsPreparedLabel {
    private final JLabel label;
    private int width;
    private int height;

    public PreparedLabel(AbstractImage _icon) {
        this(DefImageFactory.icon((DefImage) _icon));
    }

    public PreparedLabel(AbstractImage _icon, JLabel _label) {
        label = _label;
        width = Math.max(0, _icon.getWidth());
        height = Math.max(0,_icon.getHeight());
    }

    public PreparedLabel(Icon _icon) {
        label = new JLabel(_icon);
        width = Math.max(0, _icon.getIconWidth());
        height = Math.max(0,_icon.getIconHeight());
    }

    public void setIcon(AbstractImageFactory _fact,AbstractImage _icon) {
        label.setIcon(DefImageFactory.icon((DefImage) _icon));
        width = _icon.getWidth();
        height = _icon.getHeight();
    }

    @Override
    public JComponent getNatComponent() {
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
