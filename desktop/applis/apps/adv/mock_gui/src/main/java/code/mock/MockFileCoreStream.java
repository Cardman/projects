package code.mock;

import code.stream.AbstractFile;
import code.stream.AbstractFileCoreStream;
import code.stream.AbstractListRoot;

public final class MockFileCoreStream implements AbstractFileCoreStream {
    private final MockFileSet fileSet;
    public MockFileCoreStream(MockFileSet _mfs) {
        fileSet = _mfs;
    }
    @Override
    public AbstractListRoot newFileList() {
        return new MockListRoot(fileSet.getRoots());
    }

    @Override
    public AbstractFile newFile(String _s) {
        return new MockFile(fileSet, _s);
    }
}
