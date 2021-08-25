package code.gui;

import code.gui.events.AbsMouseListener;
import code.gui.events.MouseListenerCore;
import code.gui.events.WrMouseListener;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import javax.swing.*;
import java.awt.*;

public final class ImgButton extends CustComponent implements AbsEnabled {

    private final JButton label;

    public ImgButton(AbstractImageFactory _fact, AbstractImage _imageIcon) {
        label = new JButton(PreparedLabel.buildIcon(_fact,_imageIcon));
        setLineBorder(Color.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void addMouseList(AbsMouseListener _l) {
        label.addMouseListener(new WrMouseListener(new MouseListenerCore(_l, this)));
    }

    @Override
    public boolean isEnabledLabel() {
        return true;
    }

    @Override
    protected JComponent getNatComponent() {
        return label;
    }
}
