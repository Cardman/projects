package code.bean.help;

import code.formathtml.render.AbsMetaSimpleImageBuilder;
import code.util.StringMap;

public final class HelpMetaSimpleImageBuilder implements AbsMetaSimpleImageBuilder {
    private final StringMap<int[][]> imgs;

    public HelpMetaSimpleImageBuilder(StringMap<int[][]> _i) {
        this.imgs = _i;
    }

    @Override
    public int[][] build(String _image) {
        int[][] v_ = imgs.getVal(_image);
        if (v_ == null) {
            return new int[0][];
        }
        return v_;
    }
}
