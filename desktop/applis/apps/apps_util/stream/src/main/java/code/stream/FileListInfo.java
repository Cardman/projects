package code.stream;

public final class FileListInfo {
    private final AbstractFile[] names;
    private final boolean nul;

    public FileListInfo(AbstractFile[] _names) {
        this.nul = _names == null;
        if (nul) {
            this.names = new AbstractFile[0];
        } else {
            this.names = _names;
        }
    }

    public AbstractFile[] getNames() {
        return names;
    }

    public boolean isNul() {
        return nul;
    }
}
