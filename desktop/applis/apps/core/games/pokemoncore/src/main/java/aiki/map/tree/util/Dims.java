package aiki.map.tree.util;

public final class Dims {

    private static final String SEPARATOR = ",";

    private short width;

    private short height;

    public Dims() {
    }

    public Dims(int _width, int _height) {
        width = (short) _width;
        height = (short) _height;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short _width) {
        width = _width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short _height) {
        height = _height;
    }

    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(height);
        str_.append(SEPARATOR);
        str_.append(width);
        return str_.toString();
    }
}
