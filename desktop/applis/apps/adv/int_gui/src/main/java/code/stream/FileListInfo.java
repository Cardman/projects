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

    public static boolean isZip(byte[] _bytes) {
        return _bytes != null && _bytes.length > 3
                && _bytes[0] == (byte)0x50&& _bytes[1] == (byte)0x4b
                && _bytes[2] == (byte)0x03&& _bytes[3] == (byte)0x04;
    }
    public AbstractFile[] getNames() {
        return names;
    }

    public boolean isNul() {
        return nul;
    }
}
