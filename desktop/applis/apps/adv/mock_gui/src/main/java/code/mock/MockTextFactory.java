package code.mock;

import code.stream.core.AbstractTextFactory;
import code.stream.core.AbstractTextStreamIn;
import code.util.StringMap;

public final class MockTextFactory implements AbstractTextFactory {
    private final StringMap<String> mockBinFact;
    private final MockAbsRand rand;

    public MockTextFactory(StringMap<String> _mockBinFact, MockAbsRand _rd) {
        this.mockBinFact = _mockBinFact;
        this.rand = _rd;
    }
    @Override
    public AbstractTextStreamIn buildIn(String _filePath) {
        return new MockTextStreamIn(mockBinFact.getVal(_filePath));
    }

    @Override
    public int write(String _nomFichier, String _text, boolean _append) {
        mockBinFact.put(_nomFichier,_text);
        return MockThreadFactory.edit(rand);
    }
}
