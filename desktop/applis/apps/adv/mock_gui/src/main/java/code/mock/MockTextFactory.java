package code.mock;

import code.stream.core.AbstractTextFactory;
import code.stream.core.AbstractTextStreamIn;
import code.util.StringMap;

public final class MockTextFactory implements AbstractTextFactory {
    private final StringMap<String> mockBinFact;

    public MockTextFactory(StringMap<String> _mockBinFact) {
        this.mockBinFact = _mockBinFact;
    }
    @Override
    public AbstractTextStreamIn buildIn(String _filePath) {
        return new MockTextStreamIn(mockBinFact.getVal(_filePath));
    }

    @Override
    public boolean write(String _nomFichier, String _text, boolean _append) {
        mockBinFact.put(_nomFichier,_text);
        return false;
    }
}
