package code.formathtml.render;

public final class MetaNumberedLabel extends MetaLabel {

    private final int number;
    private final MetaNumberBase base;

    public MetaNumberedLabel(MetaContainer _parent, int _number, MetaNumberBase _base) {
        super(_parent);
        number = _number;
        base = _base;
    }

    public int getNumber() {
        return number;
    }

    public MetaNumberBase getBase() {
        return base;
    }
}
