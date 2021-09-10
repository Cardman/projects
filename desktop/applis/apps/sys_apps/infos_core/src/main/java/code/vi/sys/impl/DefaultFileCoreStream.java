package code.vi.sys.impl;

import code.stream.AbstractFile;
import code.stream.AbstractFileCoreStream;
import code.stream.AbstractListRoot;

public final class DefaultFileCoreStream implements AbstractFileCoreStream {

    @Override
    public AbstractListRoot newFileList() {
        return new DefListRoot();
    }

    @Override
    public AbstractFile newFile(String _fileName) {
        return new DefaultFile(_fileName);
    }
}
