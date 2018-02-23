package code.formathtml.render;

import code.sml.Element;

public abstract class MetaIndent extends MetaLabel {

    public MetaIndent(MetaContainer _parent) {
        super(_parent);
    }

    public MetaIndent(MetaContainer _parent, int _partGroup, int _rowGroup) {
        super(_parent, _partGroup, _rowGroup);
    }

    public MetaIndent(MetaContainer _parent, String _text, String _title,
            Element _anchor, int _partGroup, int _rowGroup) {
        super(_parent, _text, _title, _anchor, _partGroup, _rowGroup);
    }

}
