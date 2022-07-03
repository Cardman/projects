package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public class ImageFactorySample implements AbstractImageFactory {
    @Override
    public AbstractImage newImageArgb(int _w, int _h) {
        return new ImageSample();
    }

    @Override
    public AbstractImage newImageRgb(int _w, int _h) {
        return new ImageSample();
    }

    @Override
    public AbstractImage newImageFromBytes(byte[] _bytes) {
        return new ImageSample();
    }
}
