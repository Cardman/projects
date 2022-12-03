package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.images.BaseSixtyFourUtil;
import code.sml.*;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;

public final class MetaAnimatedImage extends MetaImage {

    private final CustList<int[][]> images;

    private final int delay;

    public MetaAnimatedImage(MetaContainer _parent, StringList _images, String _title,int _delay, Element _anchor, RendKeyWordsGroup _rend) {
        super(_parent, _title, _anchor,_rend);
        delay = _delay;
        images = new CustList<int[][]>(new CollCapacity(_images.size()));
        for (String s: _images) {
            images.add(BaseSixtyFourUtil.getImageByString(s));
        }
    }
    public CustList<int[][]> getImages() {
        return images;
    }

    public int getDelay() {
        return delay;
    }
}
