package code.stream;

import code.stream.core.*;

public final class StreamBinaryFile {

    private StreamBinaryFile() {
    }
    public static byte[] wrap(int _b) {
        if (_b < 0 || _b == '\n') {
            return new byte[0];
        }
        return new byte[]{(byte) _b};
    }

    public static BytesInfo readBytes(AbstractBinStreamIn _abs) {
        while (true) {
            int read_ = _abs.read();
            if (read_ < 0) {
                return null;
            }
            if (read_ == 0) {
                break;
            }
        }
        return new BytesInfo(_abs.getBytes(),false);
    }

    public static BytesInfo loadFile(String _file, TechStreams _str) {
        return _str.getBinFact().loadFile(_file);
    }

    public static boolean writeFile(String _file, byte[] _content, TechStreams _str) {
        return _str.getBinFact().writeFile(_file,_content, false);
    }

}
