package code.gui.images;

import code.gui.AbsCustComponent;
import code.gui.AbsMetaLabel;
import code.gui.AbsPreparedLabel;

import java.awt.Color;

public interface AbstractImage {
    AbsPreparedLabel newAbsPreparedLabel();
    void translate(int _x, int _y);
    byte[] toBytes();
    int getHeight();

    int getWidth();

    int getRGB(int _i, int _j);

    void setRGB(int _i, int _j, int _rgb);

    void drawImage(AbstractImage _image, int _i, int _j);

    void setColor(Color _color);

    void fillRect(int _a, int _b, int _width, int _height);

    void drawString(String _str, int _x, int _y);

    void drawRect(int _a, int _b, int _c, int _d);

    void fillPolygon(int[] _x, int[] _y, int _nb);

    void fillOval(int _x, int _y, int _a, int _b);

    void drawLine(int _x, int _y, int _a, int _b);

    Color getColor();

    MetaFont getMetaFont();
    void setFont(String _name, int _style, int _size);
    void setFont(MetaFont _font);
    void setFont(AbsCustComponent _font);
    void setFont(AbsMetaLabel _font);

    void drawPolygon(int[] _w, int[] _y, int _n);

    void drawOval(int _x, int _y, int _width, int _height);

    void dispose();
}
