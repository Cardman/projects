package code.mock;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.images.*;
import code.stream.FileListInfo;
import code.util.core.StringUtil;

public final class MockImageFactory implements AbstractImageFactory {
    @Override
    public AbstractImage newImageArgb(int _i, int _j) {
        return new MockImage(new int[_j][_i]);
    }

    @Override
    public AbstractImage newImageRgb(int _i, int _j) {
        return new MockImage(new int[_j][_i]);
    }

    @Override
    public AbstractImage newImageFromBytes(byte[] _bs) {
        if (_bs == null) {
            return null;
        }
        for (byte[] p: new byte[][]{new byte[]{},new byte[]{(byte)0x89,(byte)0x50,(byte)0x4E,(byte)0x47,(byte)0x0D,(byte)0x0A,(byte)0x1A,(byte)0x0A}}) {
            if (FileListInfo.startsWith(_bs,p)) {
                byte[] bytes_ = FileListInfo.extractWithPrefixes(_bs, p).getBytes();
                String decode_ = StringUtil.decode(bytes_);
                if (decode_ != null) {
                    int[][] imageByString_ = ImageCsv.getImageByString(decode_);
                    if (imageByString_.length != 0) {
                        return new MockImage(imageByString_);
                    }
                }
            }
        }
        return null;
//        String decode_ = StringUtil.decode(_bs);
//        if (decode_ == null) {
//            return null;
//        }
//        int[][] imageByString_ = BaseSixtyFourUtil.getImageByString(decode_);
//        if (imageByString_.length == 0) {
//            return null;
//        }
//        return new MockImage(imageByString_);
    }

    @Override
    public byte[] decodeToImage(int[][] _bytes) {
        return new MockImage(_bytes).toBytes();
    }
}
