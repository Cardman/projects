package code.mock;

import code.stream.BytesInfo;
import code.stream.core.AbstractBinStreamIn;
import code.util.core.NumberUtil;

public final class MockBinStreamInImpl implements AbstractBinStreamIn {
    private final BytesInfo reader;
    private final int range;
    private int index;
    private byte[] out = new byte[0];

    public MockBinStreamInImpl(byte[] _read, int _r) {
        this(new BytesInfo(_read,false),_r);
    }

    public MockBinStreamInImpl(BytesInfo _read, int _r) {
        this.reader = _read;
        range = _r;
    }
    @Override
    public int close() {
        out = new byte[0];
        return 1;
    }


    @Override
    public int read() {
        if (reader.isNul()){
            return -2;
        }
        int read_ = NumberUtil.max(0, NumberUtil.min(range,reader.getBytes().length-index));
        byte[] bk_ = new byte[out.length+read_];
        int bkLen_ = out.length;
        for (int i = 0; i < bkLen_; i++){
            set(bk_,i,out,i);
        }
        for (int i = 0; i < read_; i++){
            set(bk_,i+bkLen_,reader.getBytes(),i+index);
        }
        out = bk_;
        index += read_;
        return read_;
    }
    public static void set(byte[] _dest,int _idest,byte[] _orig, int _iorigin){
        _dest[_idest] = _orig[_iorigin];
    }

    @Override
    public byte[] getBytes() {
        return out;
    }
}
