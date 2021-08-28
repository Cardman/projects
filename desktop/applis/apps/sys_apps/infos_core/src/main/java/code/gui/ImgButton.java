package code.gui;

import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.sys.impl.DefImage;
import code.sys.impl.DefImageFactory;

import javax.swing.*;
import java.awt.*;

public final class ImgButton extends CustComponent implements AbsImgButton {

    private final JButton label;

    public ImgButton(AbstractImage _imageIcon) {
        label = new JButton(DefImageFactory.icon((DefImage) _imageIcon));
        setLineBorder(Color.BLACK.getRGB(), 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void addMouseList(AbsActionListener _l) {
        label.addActionListener(new WrActionListener(_l));
    }

    @Override
    protected JComponent getNatComponent() {
        return label;
    }
}
