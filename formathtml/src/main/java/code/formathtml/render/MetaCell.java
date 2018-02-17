package code.formathtml.render;

public final class MetaCell extends MetaContainer {

    private final int rowspan;
    private final int colspan;
    public MetaCell(MetaContainer _parent) {
        this(_parent, 1, 1);
    }
    public MetaCell(MetaContainer _parent, int _rowspan, int _colspan) {
        super(_parent, MetaLayout.BOX);
        rowspan = _rowspan;
        colspan = _colspan;
    }
    public int getRowspan() {
        return rowspan;
    }
    public int getColspan() {
        return colspan;
    }
}
