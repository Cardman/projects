package code.minirts;

import code.gui.AbsMetaLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.minirts.rts.Facade;
import code.maths.geo.Rect;




public final class Selecting extends AbsMetaLabel {
    private final Facade facade;

    public Selecting(Facade _facade, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        facade = _facade;
        setSize(new MetaDimension(2048, 2048));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        Rect r_ = facade.getSelection();
        int w_ = getWidth();
        int h_ = getHeight();
        _g.setColor(GuiConstants.newColor(255,255,255,0));
        _g.fillRect(0,0,w_,h_);
        _g.setColor(GuiConstants.BLUE);
        _g.drawRect((int)r_.getLeft().ll(),(int) r_.getTop().ll(), (int)r_.getWidth().ll(), (int)r_.getHeight().ll());
    }
}
