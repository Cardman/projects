package code.stream.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public final class StreamCoreUtil {

    private StreamCoreUtil() {
    }
    public static boolean close(Closeable _close) {
        try {
            _close.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static int read(Reader _br) {
        try {
            return _br.read();
        } catch (IOException e) {
            return -2;
        }
    }
}
