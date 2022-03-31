package code.formathtml.render;

import code.formathtml.errors.RendKeyWords;
import code.sml.Element;
import code.util.core.NumberUtil;

public final class MetaAnchorLabel extends MetaSearchableLabel {

    private final long nb;
    public MetaAnchorLabel(MetaContainer _parent, String _text, String _title,
            Element _anchor, int _partGroup, int _rowGroup,  RendKeyWords _rend) {
        super(_parent, _text, _title, _anchor, _partGroup, _rowGroup);
        nb = NumberUtil.parseLongZero(_anchor.getAttribute(_rend.getAttrNa()));
    }

    public long getNb() {
        return nb;
    }
}
