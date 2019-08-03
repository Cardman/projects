package minirts;

import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import minirts.rts.Facade;
import minirts.rts.Rect;

import java.awt.*;

public class Selecting extends PaintableLabel {
    private final Facade facade;

    public Selecting(Facade _facade) {
        facade = _facade;
        setSize(new Dimension(2048, 2048));
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        Graphics g_ = getComponent().getGraphics();
        Rect r_ = facade.getSelection();
        g_.setColor(Color.BLUE);
        g_.drawRect(r_.getLeft(), r_.getTop(), r_.getWidth(), r_.getHeight());
    }
}
