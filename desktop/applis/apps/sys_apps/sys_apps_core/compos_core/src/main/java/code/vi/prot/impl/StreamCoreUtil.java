package code.vi.prot.impl;

import java.io.Closeable;
import java.nio.charset.Charset;

public final class StreamCoreUtil {

    private static final String UTF_8 = "UTF-8";

    private StreamCoreUtil() {
    }
    public static int close(Closeable _close) {
        try {
            _close.close();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    public static Charset utf() {
        return Charset.forName(UTF_8);
    }
}
