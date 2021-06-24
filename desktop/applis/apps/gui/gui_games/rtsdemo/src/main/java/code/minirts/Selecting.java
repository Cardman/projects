package code.minirts;

import code.gui.PaintableLabel;
import code.gui.images.AbstractImage;
import code.minirts.rts.Facade;
import code.maths.geo.Rect;

import java.awt.Color;
import java.awt.Dimension;

public class Selecting extends PaintableLabel {
    private final Facade facade;

    public Selecting(Facade _facade) {
        facade = _facade;
        setSize(new Dimension(2048, 2048));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        Rect r_ = facade.getSelection();
        _g.setColor(Color.BLUE);
        _g.drawRect((int)r_.getLeft().ll(),(int) r_.getTop().ll(), (int)r_.getWidth().ll(), (int)r_.getHeight().ll());
    }
}
