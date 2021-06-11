package code.stream;

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
