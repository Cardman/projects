package code.sys.impl;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import javax.swing.*;
import java.awt.image.BufferedImage;

public final class DefImageFactory implements AbstractImageFactory {
    @Override
    public AbstractImage newImageArgb(int _w, int _h) {
        return new DefImage(BufferedImage.TYPE_INT_ARGB,_w,_h);
    }

    @Override
    public AbstractImage newImageRgb(int _w, int _h) {
        return new DefImage(BufferedImage.TYPE_INT_RGB,_w,_h);
    }

    @Override
    public Icon icon(AbstractImage _img) {
        return icon((DefImage) _img);
    }

    public static ImageIcon icon(DefImage _img) {
        try {
            return new ImageIcon(_img.data());
        } catch (Exception e) {
            return new ImageIcon();
        }
    }
}
