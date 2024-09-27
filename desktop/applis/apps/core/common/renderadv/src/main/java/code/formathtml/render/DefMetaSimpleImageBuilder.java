package code.formathtml.render;

import code.images.BaseSixtyFourUtil;

public final class DefMetaSimpleImageBuilder implements AbsMetaSimpleImageBuilder {
    private final String base;
    public DefMetaSimpleImageBuilder(String _b) {
        base= _b;
    }
    @Override
    public int[][] build(String _image) {
        return BaseSixtyFourUtil.getImageByString(_image,base);
    }
}
