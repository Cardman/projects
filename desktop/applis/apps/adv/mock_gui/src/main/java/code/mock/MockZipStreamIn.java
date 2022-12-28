package code.mock;

import code.stream.BytesInfo;
import code.stream.core.AbstractZipStreamIn;
import code.stream.core.ComZipStreamIn;
import code.stream.core.ContentTime;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class MockZipStreamIn extends ComZipStreamIn implements AbstractZipStreamIn {
    private final StringMap<ContentTime> content;
    private int index;
    private int indexCh;
    private byte[] out = new byte[0];
    private byte[] reader = new byte[0];
    public MockZipStreamIn(BytesInfo _bytes) {
        content = new MockZipFact().zippedBinaryFiles(_bytes);
    }
    @Override
    public boolean hasNextEntry() {
        if (index < content.size()) {
            setName(content.getKey(index));
            ContentTime value_ = content.getValue(index);
            setTime(value_.getLastModifTime());
            byte[] content_ = value_.getContent();
            reader = content_;
            if (content_ != null) {
                setSize(content_.length);
                setDirectory(false);
            } else {
                setDirectory(true);
            }
            index++;
            indexCh = 0;
            out = new byte[0];
            return true;
        }
        return false;
    }

    @Override
    public boolean closeEntry() {
        return true;
    }

    @Override
    public void close() {
        closeEntry();
    }

    @Override
    public int read(byte[] _array, int _off, int _len) {
        int read_ = NumberUtil.max(0, NumberUtil.min(2,reader.length-indexCh));
        byte[] bk_ = new byte[out.length+read_];
        int bkLen_ = out.length;
        for (int i = 0; i < bkLen_; i++){
            MockBinStreamInImpl.set(bk_,i,out,i);
        }
        for (int i = 0; i < read_; i++){
            MockBinStreamInImpl.set(bk_,i+bkLen_,reader,i+indexCh);
        }
        out = bk_;
        indexCh += read_;
        return read_;
    }

    @Override
    public byte[] getReadBytes() {
        return out;
    }

}
