package aiki.gui.components.walk;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public interface IntTileRender {
    AbstractImage render(AbstractImageFactory _fact, int[][] _img, int _defWidth, int _defHeight);
    AbstractImage centerImage(AbstractImageFactory _fact, int[][] _img, int _def);
    AbstractImage centerImage(AbstractImageFactory _fact, int[][] _img, int _width, int _height);
}
