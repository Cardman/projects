package code.mock;

import code.stream.core.AbstractBinFactory;
import code.stream.core.AbstractBinStreamIn;
import code.threads.FileStruct;

public final class MockBinFactory implements AbstractBinFactory {
    private final MockFileSet mockBinFact;

    public MockBinFactory(MockFileSet _mockBinFact) {
        this.mockBinFact = _mockBinFact;
    }

    @Override
    public AbstractBinStreamIn buildIn(String _filePath) {
        return new MockBinStreamInImpl(MockBinFact.load(_filePath,mockBinFact).getContent(), 2);
    }

    @Override
    public boolean writeFile(String _file, byte[] _content) {
        mockBinFact.getFiles().put(_file,new FileStruct(_content,mockBinFact.getMockMillis().millis()));
        return true;
    }
}
