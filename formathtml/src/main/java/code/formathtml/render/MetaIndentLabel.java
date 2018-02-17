package code.formathtml.render;


public final class MetaIndentLabel extends MetaLabel {

    private final int times;
    public MetaIndentLabel(MetaContainer _parent, int _times) {
        super(_parent);
        times = _times;
    }

    public int getTimes() {
        return times;
    }
}
