package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.images.BaseSixtyFourUtil;
import code.sml.*;

public final class MetaSimpleImage extends MetaImage {

    private int[][] image;

    public MetaSimpleImage(MetaContainer _parent, String _image, String _title, Element _anchor, RendKeyWordsGroup _rend) {
        super(_parent,_title, _anchor,_rend);
        image = BaseSixtyFourUtil.getImageByString(_image);
    }

    public int[][] getImage() {
        return image;
    }
}
