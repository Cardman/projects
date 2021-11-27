package code.vi.prot.impl;

import code.stream.core.AbstractZipFactory;
import code.stream.core.AbstractZipStreamIn;
import code.stream.core.AbstractZipStreamOut;

public final class DefZipFactory implements AbstractZipFactory {
    @Override
    public AbstractZipStreamIn buildIn(byte[] _bytes) {
        return new DefZipStreamIn(_bytes);
    }

    @Override
    public AbstractZipStreamOut buildOut() {
        return new DefZipStreamOut();
    }
}
