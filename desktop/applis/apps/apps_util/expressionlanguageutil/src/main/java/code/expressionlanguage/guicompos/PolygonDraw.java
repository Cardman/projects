package code.expressionlanguage.guicompos;

import code.gui.images.AbstractImage;

public final class PolygonDraw implements PolygonAction {
    @Override
    public void act(AbstractImage _img, int[] _xs, int[] _ys, int _len) {
        _img.drawPolygon(_xs,_ys,_len);
    }
}
