package code.vi.sys.impl;

import code.stream.core.AbstractBinStreamIn;
import code.stream.core.AbstractInputStreamBuilder;

public final class DefaultInputStreamBuilder implements AbstractInputStreamBuilder {

    @Override
    public AbstractBinStreamIn build(String _fileName) {
        return new DefBinStreamIn(_fileName);
    }
}
