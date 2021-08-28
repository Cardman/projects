package code.gui.images;

public final class MetaDimension {
    private final int width;
    private final int height;
    public MetaDimension(MetaDimension _d) {
        this(_d.width, _d.height);
    }
    public MetaDimension(int _width, int _height) {
        width = _width;
        height = _height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
