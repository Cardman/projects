package code.vi.sys.impl.gui;

import code.gui.AbsImgButton;
import code.gui.GuiConstants;
import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.vi.sys.impl.DefImage;
import code.vi.sys.impl.DefImageFactory;
import code.vi.sys.impl.gui.events.WrActionListener;

import javax.swing.*;
import java.awt.*;

public final class ImgButton extends CustComponent implements AbsImgButton {

    private final JButton label;

    public ImgButton(AbstractImage _imageIcon) {
        label = new JButton(DefImageFactory.icon((DefImage) _imageIcon));
        setLineBorder(GuiConstants.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void addMouseList(AbsActionListener _l) {
        label.addActionListener(new WrActionListener(_l));
    }

    @Override
    public JComponent getNatComponent() {
        return label;
    }
}
