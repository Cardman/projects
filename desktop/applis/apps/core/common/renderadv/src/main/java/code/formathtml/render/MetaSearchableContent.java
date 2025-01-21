package code.formathtml.render;

public final class MetaSearchableContent {
    private final String text;

    private final int partGroup;

    private final int rowGroup;

    public MetaSearchableContent(String _t, int _p, int _r) {
        this.text = _t;
        this.partGroup = _p;
        this.rowGroup = _r;
    }

    public String getText() {
        return text;
    }

    public int getPartGroup() {
        return partGroup;
    }

    public int getRowGroup() {
        return rowGroup;
    }
}
