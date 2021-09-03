package code.gui.images;

public final class MetaRect {
    private final MetaPoint point;
    private final int width;
    private final int height;

    public MetaRect(int _x, int _y, int _width, int _height) {
        this.point = new MetaPoint(_x, _y);
        this.width = _width;
        this.height = _height;
    }

    public MetaPoint move() {
        return new MetaPoint(point.getXcoord()+width, point.getYcoord()+height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MetaPoint getPoint() {
        return point;
    }
}
