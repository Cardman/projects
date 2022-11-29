package code.vi.prot.impl;

import code.gui.AbsPreparedLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

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
    public AbstractImage newImageRgb(int _w, int _h, AbsPreparedLabel _component) {
        AbstractImage buff_ = newImageRgb(_w,_h);
        buff_.setFont(_component);
        return buff_;
    }

    @Override
    public AbstractImage setIcon(AbsPreparedLabel _c, AbstractImage _i) {
        _c.setIcon(this,_i);
        return _i;
    }

    @Override
    public AbstractImage newImageFromBytes(byte[] _bytes) {
        MemoryCacheImageInputStream mem_ = new MemoryCacheImageInputStream(new ByteArrayInputStream(_bytes));
        try {
            BufferedImage read_ = ImageIO.read(mem_);
            StreamCoreUtil.close(mem_);
            return new DefImage(read_);
        } catch (Exception e) {
            StreamCoreUtil.close(mem_);
            return null;
        }
    }

    public static ImageIcon icon(DefImage _img) {
        try {
            return new ImageIcon(_img.data());
        } catch (Exception e) {
            return new ImageIcon();
        }
    }
}
