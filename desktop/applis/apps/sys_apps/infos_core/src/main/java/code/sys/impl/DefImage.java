package code.sys.impl;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaFont;
import code.sys.impl.gui.CustComponent;
import code.sys.impl.gui.PreparedLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public final class DefImage implements AbstractImage {
    private final BufferedImage image;
    private final Graphics graphics;
    private int colorValue;
    public DefImage(int _type, int _w, int _h) {
        image = new BufferedImage(_w,_h,_type);
        graphics = image.getGraphics();
    }

    public DefImage(BufferedImage _read) {
        image = _read;
        graphics = image.getGraphics();
    }

    @Override
    public AbsPreparedLabel newAbsPreparedLabel() {
        return new PreparedLabel(this,new JLabel());
    }

    @Override
    public void translate(int _x, int _y) {
        graphics.translate(_x, _y);
    }

    @Override
    public byte[] toBytes() {
        return ConverterGraphicBufferedImage.toBytes(( (DataBufferInt) image.getRaster().getDataBuffer() ).getData());
    }

    public Image data() {
        return image;
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getRGB(int _i, int _j) {
        return image.getRGB(_i, _j);
    }

    @Override
    public void setRGB(int _i, int _j, int _rgb) {
        image.setRGB(_i, _j, _rgb);
    }

    @Override
    public void drawImage(AbstractImage _image, int _i, int _j) {
        graphics.drawImage(((DefImage)_image).image,_i,_j,null);
//        ConverterGraphicBufferedImage.drawImage(this,_image,_i,_j);
    }

    @Override
    public int getColorValue() {
        return colorValue;
    }

    public void setColor(Color _color) {
        graphics.setColor(_color);
        colorValue = _color.getRGB();
    }

    @Override
    public void setColorBg(AbsCustComponent _color) {
        setColor(((CustComponent)_color).getBackground());
    }

    @Override
    public void setColorFg(AbsCustComponent _color) {
        setColor(((CustComponent)_color).getForeground());
    }

    @Override
    public void setColor(int _color) {
        setColor(new Color(_color));
    }

    @Override
    public MetaFont getMetaFont() {
        try {
            Font font_ = getFont();
            return new MetaFont(font_.getFamily(),GuiConstants.fontStyle(font_.isBold(),font_.isItalic()),font_.getSize());
        } catch (Exception e) {
            return null;
        }
    }

    public Font getFont() {
        return graphics.getFont();
    }

    @Override
    public void setFont(MetaFont _font) {
        setFont(new Font(_font.getFontFamily(), _font.getFont(), _font.getRealSize()));
    }

    public void setFont(Font _font) {
        graphics.setFont(_font);
    }

    @Override
    public void setFont(String _name, int _style, int _size) {
        graphics.setFont(new Font(_name,_style,_size));
    }

    @Override
    public void setFont(AbsCustComponent _font) {
        graphics.setFont(((CustComponent)_font).getFont());
    }

    @Override
    public void setFont(AbsMetaLabel _font) {
        graphics.setFont(((CustComponent)_font.getPaintableLabel()).getFont());
    }

    @Override
    public void drawPolygon(int[] _w, int[] _y, int _n) {
        graphics.drawPolygon(_w, _y, _n);
    }

    @Override
    public void drawOval(int _x, int _y, int _width, int _height) {
        graphics.drawOval(_x, _y, _width, _height);
    }

    @Override
    public void fillRect(int _a, int _b, int _width, int _height) {
        graphics.fillRect(_a, _b, _width, _height);
    }

    @Override
    public void drawString(String _str, int _x, int _y) {
        graphics.drawString(_str, _x, _y);
    }

    @Override
    public void drawRect(int _a, int _b, int _c, int _d) {
        graphics.drawRect(_a, _b, _c, _d);
    }

    @Override
    public void fillPolygon(int[] _x, int[] _y, int _nb) {
        graphics.fillPolygon(_x, _y, _nb);
    }

    @Override
    public void fillOval(int _x, int _y, int _a, int _b) {
        graphics.fillOval(_x, _y, _a, _b);
    }

    @Override
    public void drawLine(int _x, int _y, int _a, int _b) {
        graphics.drawLine(_x, _y, _a, _b);
    }

    @Override
    public void dispose() {
        graphics.dispose();
    }
}
