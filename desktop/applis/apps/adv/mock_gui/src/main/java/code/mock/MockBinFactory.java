package code.mock;

import code.stream.BytesInfo;
import code.stream.core.AbstractBinFactory;
import code.stream.core.AbstractBinStreamIn;
import code.threads.FileStruct;

public final class MockBinFactory implements AbstractBinFactory {
    private final MockFileSet mockBinFact;
    private final MockAbsRand rand;
    public MockBinFactory(MockFileSet _mockBinFact, MockAbsRand _rd) {
        this.mockBinFact = _mockBinFact;
        this.rand = _rd;
    }

    public static byte[] join(BytesInfo _bytes, byte[] _encode) {
        byte[] bytesArr_ = _bytes.getBytes();
        byte[] joined_ = new byte[bytesArr_.length+ _encode.length];
        int len_ = joined_.length;
        for (int i = 0; i < len_; i++) {
            if (i < bytesArr_.length) {
                joined_[i] = bytesArr_[i];
            } else {
                joined_[i] = _encode[i- bytesArr_.length];
            }
        }
        return joined_;
    }

    @Override
    public AbstractBinStreamIn buildIn(String _filePath) {
        return new MockBinStreamInImpl(MockBinFact.load(_filePath,mockBinFact), 2);
    }

    @Override
    public int writeFile(String _file, byte[] _content, boolean _append) {
        writeOrAppend(_file, _content, _append, mockBinFact);
        return MockThreadFactory.edit(rand);
    }

    public static void writeOrAppend(String _file, byte[] _content, boolean _append, MockFileSet _mockBinFact) {
        if (_append) {
            BytesInfo bytes_ = MockBinFact.load(_file, _mockBinFact);
            if (!bytes_.isNul()) {
                _mockBinFact.getFiles().put(_file,new FileStruct(join(bytes_, _content), _mockBinFact.getMockMillis().millis()));
            } else {
                _mockBinFact.getFiles().put(_file, new FileStruct(_content, _mockBinFact.getMockMillis().millis()));
            }
        } else {
            _mockBinFact.getFiles().put(_file, new FileStruct(_content, _mockBinFact.getMockMillis().millis()));
        }
    }
}
