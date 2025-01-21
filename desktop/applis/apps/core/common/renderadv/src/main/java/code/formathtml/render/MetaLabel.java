package code.formathtml.render;

import code.sml.Element;

public abstract class MetaLabel extends MetaLeaf {

    private final Element anchor;

    private final MetaSearchableContent content;

    protected MetaLabel(MetaContainer _parent) {
        this(_parent, -1, -1);
    }

    protected MetaLabel(MetaContainer _parent, int _partGroup, int _rowGroup) {
        this(_parent, EMPTY_STRING, EMPTY_STRING, null, _partGroup, _rowGroup);
    }
    protected MetaLabel(MetaContainer _parent, String _text, String _title, Element _anchor, int _partGroup, int _rowGroup) {
        super(_parent, _title);
        content = new MetaSearchableContent(_text,_partGroup,_rowGroup);
        anchor = _anchor;
    }

    public String getText() {
        return getContent().getText();
    }

    public Element getAnchor() {
        return anchor;
    }

    public int getPartGroup() {
        return getContent().getPartGroup();
    }

    public int getRowGroup() {
        return getContent().getRowGroup();
    }

    public MetaSearchableContent getContent() {
        return content;
    }
}
