package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.sml.*;
import code.util.core.NumberUtil;

public final class MetaAnchorLabel extends MetaSearchableLabel {

    private final long nb;
    public MetaAnchorLabel(MetaContainer _parent, String _text, String _title,
            Element _anchor, int _partGroup, int _rowGroup,  RendKeyWordsGroup _rend) {
        super(_parent, _text, _title, _anchor, _partGroup, _rowGroup);
        nb = NumberUtil.parseLongZero(_anchor.getAttribute(_rend.getKeyWordsAttrs().getAttrNa()));
    }

    public long getNb() {
        return nb;
    }
}
