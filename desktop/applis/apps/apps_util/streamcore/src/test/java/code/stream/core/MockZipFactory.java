package code.stream.core;

import code.mock.MockZipStreamIn;
import code.mock.MockZipStreamOut;
import code.stream.BytesInfo;

public final class MockZipFactory implements AbstractZipFactory{
    @Override
    public AbstractZipStreamIn buildIn(byte[] _bytes) {
        return new MockZipStreamIn(new BytesInfo(_bytes,false));
    }

    @Override
    public AbstractZipStreamOut buildOut() {
        return new MockZipStreamOut();
    }
}
