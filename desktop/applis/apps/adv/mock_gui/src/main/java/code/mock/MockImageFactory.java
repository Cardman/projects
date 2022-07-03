package code.mock;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.images.BaseSixtyFourUtil;
import code.util.core.StringUtil;

public final class MockImageFactory implements AbstractImageFactory {
    @Override
    public AbstractImage newImageArgb(int _i, int _j) {
        return new MockImage(new int[_i][_j]);
    }

    @Override
    public AbstractImage newImageRgb(int _i, int _j) {
        return new MockImage(new int[_i][_j]);
    }

    @Override
    public AbstractImage newImageFromBytes(byte[] _bs) {
        if (_bs == null) {
            return null;
        }
        String decode_ = StringUtil.decode(_bs);
        if (decode_ == null) {
            return null;
        }
        int[][] imageByString_ = BaseSixtyFourUtil.getImageByString(decode_);
        if (imageByString_.length == 0) {
            return null;
        }
        return new MockImage(imageByString_);
    }
}
