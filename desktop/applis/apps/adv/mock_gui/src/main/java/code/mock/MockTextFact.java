package code.mock;

import code.stream.BytesInfo;
import code.stream.core.AbstractTextFact;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class MockTextFact implements AbstractTextFact {

    private final MockBinFact mockBinFact;

    public MockTextFact(MockBinFact _bin) {
        this.mockBinFact = _bin;
    }

    @Override
    public String contentsOfFile(String _s, UniformingString _uni, long _est) {
        BytesInfo bytes_ = mockBinFact.loadFile(_s);
        if (bytes_.isNul()) {
            return null;
        }
        String d_ = StringUtil.decode(bytes_.getBytes());
        if (d_ == null) {
            return null;
        }
        return _uni.apply(d_);
    }

    @Override
    public boolean write(String _file, String _cont, boolean _append) {
        if (_append) {
            BytesInfo bytes_ = mockBinFact.loadFile(_file);
            if (!bytes_.isNul()) {
                byte[] encode_ = StringUtil.encode(_cont);
                byte[] joined_ = new byte[bytes_.getBytes().length+encode_.length];
                int len_ = joined_.length;
                for (int i = 0; i < len_; i++) {
                    if (i < bytes_.getBytes().length) {
                        joined_[i] = bytes_.getBytes()[i];
                    } else {
                        joined_[i] = encode_[i-bytes_.getBytes().length];
                    }
                }
                return mockBinFact.writeFile(_file,joined_);
            }
        }
        return mockBinFact.writeFile(_file,StringUtil.encode(_cont));
    }
}
