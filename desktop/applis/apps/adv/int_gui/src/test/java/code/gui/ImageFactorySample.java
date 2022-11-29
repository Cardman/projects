package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public class ImageFactorySample implements AbstractImageFactory {
    @Override
    public AbstractImage newImageArgb(int _w, int _h) {
        return null;
    }

    @Override
    public AbstractImage newImageRgb(int _w, int _h, AbsPreparedLabel _component) {
        return null;
    }

    @Override
    public AbstractImage newImageRgb(int _w, int _h) {
        return null;
    }

    @Override
    public AbstractImage newImageFromBytes(byte[] _bytes) {
        return null;
    }
    public AbstractImage setIcon(AbsPreparedLabel _c, AbstractImage _i) {
        return _i;
    }
}
