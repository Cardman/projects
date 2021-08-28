package code.sys.impl;

import java.io.Closeable;
import java.nio.charset.Charset;

public final class StreamCoreUtil {

    private static final String UTF_8 = "UTF-8";

    private StreamCoreUtil() {
    }
    public static boolean close(Closeable _close) {
        try {
            _close.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static Charset utf() {
        return Charset.forName(UTF_8);
    }
}
