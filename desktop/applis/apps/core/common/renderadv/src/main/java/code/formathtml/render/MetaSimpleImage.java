package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.sml.*;

public final class MetaSimpleImage extends MetaImage {

    private final int[][] image;

    public MetaSimpleImage(MetaContainer _parent, int[][] _image, String _title, Element _anchor, RendKeyWordsGroup _rend) {
        super(_parent,_title, _anchor,_rend);
        image = _image;
    }

    public int[][] getImage() {
        return image;
    }
}
