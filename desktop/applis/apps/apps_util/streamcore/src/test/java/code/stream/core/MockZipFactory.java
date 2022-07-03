package code.stream.core;

import code.mock.MockZipStreamIn;
import code.mock.MockZipStreamOut;

public final class MockZipFactory implements AbstractZipFactory{
    @Override
    public AbstractZipStreamIn buildIn(byte[] _bytes) {
        return new MockZipStreamIn(_bytes);
    }

    @Override
    public AbstractZipStreamOut buildOut() {
        return new MockZipStreamOut();
    }
}
