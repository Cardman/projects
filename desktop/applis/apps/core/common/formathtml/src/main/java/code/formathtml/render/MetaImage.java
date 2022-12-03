package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.sml.*;
import code.util.core.NumberUtil;


public abstract class MetaImage extends MetaLeaf {

    private final Element anchor;

    private final long nb;

    protected MetaImage(MetaContainer _parent, String _title, Element _anchor, RendKeyWordsGroup _rend) {
        super(_parent, _title);
        anchor = _anchor;
        if (_anchor != null) {
            nb = NumberUtil.parseLongZero(_anchor.getAttribute(_rend.getKeyWordsAttrs().getAttrNa()));
        } else {
            nb = -1;
        }
    }
    public Element getAnchor() {
        return anchor;
    }

    public long getNb() {
        return nb;
    }
}
