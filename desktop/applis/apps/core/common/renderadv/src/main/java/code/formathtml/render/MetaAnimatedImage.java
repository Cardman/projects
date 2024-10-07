package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.sml.*;
import code.util.CollCapacity;
import code.util.CustList;

public final class MetaAnimatedImage extends MetaImage {

    private final CustList<int[][]> images;

    private final int delay;

    public MetaAnimatedImage(MetaContainer _parent, CustList<int[][]> _images, String _title, int _delay, Element _anchor, RendKeyWordsGroup _rend) {
        super(_parent, _title, _anchor,_rend);
        delay = _delay;
        images = new CustList<int[][]>(new CollCapacity(_images.size()));
        for (int[][] s: _images) {
            images.add(s);
        }
    }
    public CustList<int[][]> getImages() {
        return images;
    }

    public int getDelay() {
        return delay;
    }
}
