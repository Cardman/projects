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

    public static boolean isWav(byte[] _bytes) {
        if (_bytes.length < 12) {
            return false;
        }
        return _bytes[0] == 'R' && _bytes[1] == 'I' && _bytes[2] == 'F' && _bytes[3] == 'F'
                &&_bytes[8] == 'W' && _bytes[9] == 'A' && _bytes[10] == 'V' && _bytes[11] == 'E';
    }
    public static boolean isMp3(byte[] _bytes) {
        if (isMpFirst3(_bytes)) {
            return true;
        }
        return _bytes.length >= 3 && _bytes[0] == 'I' && _bytes[1] == 'D' && _bytes[2] == '3';
    }
    public static boolean isMpFirst3(byte[] _bytes) {
        if (_bytes.length < 2) {
            return false;
        }
        if (_bytes[0] == (byte)255 && _bytes[1] == (byte)251) {
            return true;
        }
        if (_bytes[0] == (byte)255 && _bytes[1] == (byte)243) {
            return true;
        }
        return _bytes[0] == (byte) 255 && _bytes[1] == (byte) 242;
    }

    public static boolean isBinary(BytesInfo _bytes) {
        if (_bytes.isNul()) {
            return false;
        }
        for (byte b: _bytes.getBytes()) {
            if (b < ' ' && b != '\n' && b != '\t' && b != '\r') {
                return true;
            }
        }
        return false;
    }

    public AbstractFile[] getNames() {
        return names;
    }

    public boolean isNul() {
        return nul;
    }
}
