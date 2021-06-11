package code.stream;

import code.util.core.StringUtil;

import java.io.*;

public final class StreamFileCore {
    private StreamFileCore() {
    }

    public static InputStream tryCreateFileInputStream(String _file) {
        try {
            return new FileInputStream(StringUtil.nullToEmpty(_file));
        } catch (IOException e) {
            return null;
        }
    }
    public static OutputStream tryCreateFileOutputStream(String _file) {
        try {
            return new FileOutputStream(StringUtil.nullToEmpty(_file));
        } catch (IOException e) {
            return null;
        }
    }
}
