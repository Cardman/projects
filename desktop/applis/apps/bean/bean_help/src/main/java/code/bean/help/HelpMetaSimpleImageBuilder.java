package code.bean.help;

import code.formathtml.render.AbsMetaSimpleImageBuilder;
import code.images.BaseSixtyFourUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HelpMetaSimpleImageBuilder implements AbsMetaSimpleImageBuilder {
    private final StringMap<String> imgs;

    public HelpMetaSimpleImageBuilder(StringMap<String> _i) {
        this.imgs = _i;
    }

    @Override
    public int[][] build(String _image) {
        return BaseSixtyFourUtil.getImageByString(StringUtil.nullToEmpty(imgs.getVal(_image)));
    }
}
