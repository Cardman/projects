package code.minirts;

import code.gui.AbsMetaLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.minirts.rts.Facade;
import code.maths.geo.Rect;


import java.awt.Dimension;

public final class Selecting extends AbsMetaLabel {
    private final Facade facade;

    public Selecting(Facade _facade, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        facade = _facade;
        setSize(new Dimension(2048, 2048));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        Rect r_ = facade.getSelection();
        _g.setColor(GuiConstants.BLUE);
        _g.drawRect((int)r_.getLeft().ll(),(int) r_.getTop().ll(), (int)r_.getWidth().ll(), (int)r_.getHeight().ll());
    }
}
