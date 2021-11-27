package code.vi.sys.impl;

import code.stream.core.AbstractTextStreamIn;
import code.util.core.StringUtil;
import code.vi.prot.impl.StreamCoreUtil;

import java.io.*;

public final class DefTextStreamIn implements AbstractTextStreamIn {
    private final Reader reader;
    public DefTextStreamIn(String _fileName) {
        reader = tryCreateBufferedReader(tryCreateFileInputStream(_fileName));
    }
    private static Reader tryCreateBufferedReader(InputStream _file) {
        try {
            return new InputStreamReader(_file, StreamCoreUtil.utf());
        } catch (Exception e)  {
            return null;
        }
    }
    public static InputStream tryCreateFileInputStream(String _file) {
        try {
            return new FileInputStream(StringUtil.nullToEmpty(_file));
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public boolean close() {
        return StreamCoreUtil.close(reader);
    }

    @Override
    public int read() {
        try {
            return reader.read();
        } catch (Exception e) {
            return -2;
        }
    }
}
