package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import javax.swing.*;

public final class PreparedLabel extends CustComponent {
    private final JLabel label;
    private int width;
    private int height;

    public PreparedLabel(AbstractImageFactory _img,AbstractImage _icon) {
        this(buildIcon(_img,_icon));
    }

    private PreparedLabel(AbstractImage _icon, JLabel _label) {
        label = _label;
        width = Math.max(0, _icon.getWidth());
        height = Math.max(0,_icon.getHeight());
    }

    public PreparedLabel(Icon _icon) {
        label = new JLabel(_icon);
        width = Math.max(0, _icon.getIconWidth());
        height = Math.max(0,_icon.getIconHeight());
    }

    public static PreparedLabel prep(AbstractImageFactory _img) {
        return prep(_img.newImageArgb(1,1));
    }
    public static PreparedLabel prep(AbstractImage _img) {
        return new PreparedLabel(_img,new JLabel());
    }

    public void setIcon(AbstractImageFactory _fact,AbstractImage _icon) {
        label.setIcon(buildIcon(_fact,_icon));
        width = _icon.getWidth();
        height = _icon.getHeight();
    }

    public static Icon buildIcon(AbstractImageFactory _fact,AbstractImage _icon) {
//        if (_icon == null) {
//            return new ImageIcon();
//        }
//        return new ImageIcon(_icon.toBytes());
        return _fact.icon(_icon);
    }

    @Override
    protected JComponent getComponent() {
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
