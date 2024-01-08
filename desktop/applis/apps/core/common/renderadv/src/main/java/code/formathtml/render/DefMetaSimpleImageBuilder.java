package code.formathtml.render;

import code.images.BaseSixtyFourUtil;

public final class DefMetaSimpleImageBuilder implements AbsMetaSimpleImageBuilder {
    @Override
    public int[][] build(String _image) {
        return BaseSixtyFourUtil.getImageByString(_image);
    }
}
