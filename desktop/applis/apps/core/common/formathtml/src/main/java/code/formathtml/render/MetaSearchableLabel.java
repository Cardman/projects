package code.formathtml.render;

import code.sml.Element;

public abstract class MetaSearchableLabel extends MetaLabel {

    protected MetaSearchableLabel(MetaContainer _parent, String _text, String _title,
            Element _anchor, int _partGroup, int _rowGroup) {
        super(_parent, _text, _title, _anchor, _partGroup, _rowGroup);
    }

}
