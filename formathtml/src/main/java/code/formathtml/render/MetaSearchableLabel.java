package code.formathtml.render;

import code.sml.Element;

public abstract class MetaSearchableLabel extends MetaLabel {

    public MetaSearchableLabel(MetaContainer _parent, String _text, String _title,
            Element _anchor, int _partGroup, int _rowGroup) {
        super(_parent, _text, _title, _anchor, _partGroup, _rowGroup);
    }

    public MetaSearchableLabel(MetaContainer _parent, String _text, String _title,
            int _partGroup, int _rowGroup) {
        super(_parent, _text, _title, null, _partGroup, _rowGroup);
    }
}
