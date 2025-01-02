package aiki.map.tree.util;

public final class Dims {

    private static final String SEPARATOR = ",";

    private int width;

    private int height;

    public Dims() {
    }

    public Dims(int _width, int _height) {
        width = _width;
        height = _height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int _width) {
        width = _width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int _height) {
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
