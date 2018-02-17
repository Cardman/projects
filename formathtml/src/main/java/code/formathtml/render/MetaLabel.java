package code.formathtml.render;

import code.sml.Element;

public abstract class MetaLabel extends MetaLeaf {

    private final String text;

    private final Element anchor;

    private final int partGroup;

    private final int rowGroup;

    protected MetaLabel(MetaContainer _parent) {
        this(_parent, -1, -1);
    }

    protected MetaLabel(MetaContainer _parent, int _partGroup, int _rowGroup) {
        this(_parent, EMPTY_STRING, EMPTY_STRING, null, _partGroup, _rowGroup);
    }
    protected MetaLabel(MetaContainer _parent, String _text, String _title, Element _anchor, int _partGroup, int _rowGroup) {
        super(_parent, _title);
        text = _text;
        anchor = _anchor;
        partGroup = _partGroup;
        rowGroup = _rowGroup;
    }

    public String getText() {
        return text;
    }

    public Element getAnchor() {
        return anchor;
    }

    public int getPartGroup() {
        return partGroup;
    }

    public int getRowGroup() {
        return rowGroup;
    }
}
