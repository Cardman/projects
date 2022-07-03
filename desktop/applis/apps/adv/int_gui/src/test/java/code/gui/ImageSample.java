package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

public class ImageSample implements AbstractImage {
    @Override
    public AbsPreparedLabel newAbsPreparedLabel() {
        return null;
    }

    @Override
    public void translate(int _x, int _y) {

    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getRGB(int _i, int _j) {
        return 0;
    }

    @Override
    public void setRGB(int _i, int _j, int _rgb) {

    }

    @Override
    public void drawImage(AbstractImage _image, int _i, int _j) {

    }

    @Override
    public void setColorBg(AbsCustComponent _color) {

    }

    @Override
    public void setColorFg(AbsCustComponent _color) {

    }

    @Override
    public void setColor(int _color) {

    }

    @Override
    public void fillRect(int _a, int _b, int _width, int _height) {

    }

    @Override
    public void drawString(String _str, int _x, int _y) {

    }

    @Override
    public void drawRect(int _a, int _b, int _c, int _d) {

    }

    @Override
    public void fillPolygon(int[] _x, int[] _y, int _nb) {

    }

    @Override
    public void fillOval(int _x, int _y, int _a, int _b) {

    }

    @Override
    public void drawLine(int _x, int _y, int _a, int _b) {

    }

    @Override
    public int getColorValue() {
        return 0;
    }

    @Override
    public MetaFont getMetaFont() {
        return null;
    }

    @Override
    public void setFont(String _name, int _style, int _size) {

    }

    @Override
    public void setFont(MetaFont _font) {

    }

    @Override
    public void setFont(AbsCustComponent _font) {

    }

    @Override
    public void setFont(AbsMetaLabelComInt _font) {

    }

    @Override
    public void drawPolygon(int[] _w, int[] _y, int _n) {

    }

    @Override
    public void drawOval(int _x, int _y, int _width, int _height) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public byte[] writeImg(String _format) {
        return new byte[0];
    }
}
