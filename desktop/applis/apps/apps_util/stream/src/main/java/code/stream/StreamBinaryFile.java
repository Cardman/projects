package code.stream;

import code.stream.core.*;
import code.util.core.*;

public final class StreamBinaryFile {

    private StreamBinaryFile() {
    }

    public static byte[] ext(byte[] _arr, int _read) {
        if (_read <= 0) {
            return new byte[0];
        }
        int n_ = next(_arr, _read);
        int sub_ = NumberUtil.min(_arr.length,_read) -1 + n_;
        byte[] ut_ = new byte[sub_];
        for (int i = 0; i < sub_; i++) {
            set(ut_,i,_arr[i]);
        }
        return ut_;
    }
    public static int next(byte[] _arr, int _read) {
        if (_read < 0) {
            return -1;
        }
        if (_read == 0) {
            return 1;
        }
        if (_arr[_read - 1] == '\n') {
            return 0;
        }
        return 1;
    }
    private static void set(byte[] _a, int _i, byte _b) {
        _a[_i] = _b;
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
