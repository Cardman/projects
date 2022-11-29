package code.gui.images;

import code.gui.AbsPreparedLabel;

public interface AbstractImageFactory {
    AbstractImage newImageArgb(int _w, int _h);
    AbstractImage newImageRgb(int _w, int _h);
    AbstractImage newImageRgb(int _w, int _h, AbsPreparedLabel _component);
    AbstractImage newImageFromBytes(byte[] _bytes);

    AbstractImage setIcon(AbsPreparedLabel _c, AbstractImage _i);
}
