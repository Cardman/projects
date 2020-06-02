package code.formathtml.render;

public final class MetaImageMap extends MetaContainer {

    private final int width;
    public MetaImageMap(MetaContainer _parent, int _width) {
        super(_parent, MetaLayout.GRID);
        width = _width;
    }
    public int getWidth() {
        return width;
    }
}
