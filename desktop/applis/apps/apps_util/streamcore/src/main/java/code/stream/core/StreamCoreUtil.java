package code.stream.core;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

public final class StreamCoreUtil {

    private StreamCoreUtil() {
    }
    public static boolean close(Closeable _close) {
        if (_close == null) {
            return true;
        }
        try {
            _close.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static int read(BufferedReader _br) {
        try {
            return _br.read();
        } catch (IOException e) {
            return -2;
        }
    }
}
