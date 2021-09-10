package code.vi.sys.impl;

import code.stream.core.AbstractBinStreamIn;
import code.util.core.StringUtil;

import java.io.*;

public final class DefBinStreamIn implements AbstractBinStreamIn {
    private final InputStream reader;
    public DefBinStreamIn(String _fileName) {
        reader = tryCreateFileInputStream(_fileName);
    }

    public static InputStream tryCreateFileInputStream(String _file) {
        try {
            return new FileInputStream(StringUtil.nullToEmpty(_file));
        } catch (IOException e) {
            return null;
        }
    }
    @Override
    public boolean close() {
        return StreamCoreUtil.close(reader);
    }

    @Override
    public int read(byte[] _array, int _off, int _len) {
        try {
            return reader.read(_array, _off, _len);
        } catch (Exception e) {
            return -2;
        }
    }
}
