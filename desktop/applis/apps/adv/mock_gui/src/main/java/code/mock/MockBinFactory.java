package code.mock;

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

    @Override
    public AbstractBinStreamIn buildIn(String _filePath) {
        return new MockBinStreamInImpl(MockBinFact.load(_filePath,mockBinFact), 2);
    }

    @Override
    public int writeFile(String _file, byte[] _content) {
        mockBinFact.getFiles().put(_file,new FileStruct(_content,mockBinFact.getMockMillis().millis()));
        return MockThreadFactory.edit(rand);
    }
}
