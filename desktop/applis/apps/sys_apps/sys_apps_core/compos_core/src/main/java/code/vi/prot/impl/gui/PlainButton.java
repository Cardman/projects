package code.vi.prot.impl.gui;

import code.gui.AbsButton;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.DefImageFactory;

import javax.swing.*;
import java.awt.*;

public final class PlainButton extends AbsComButton implements AbsButton {
    private final JButton button;
    public PlainButton() {
        button = new JButton();
    }
    public PlainButton(String _b) {
        button = new JButton(_b);
    }

    public PlainButton(AbstractImage _imageIcon) {
        button = new JButton(DefImageFactory.icon((DefImage) _imageIcon));
        setLineBorder(GuiConstants.BLACK, 1);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    @Override
    protected AbstractButton button() {
        return button;
    }
}
