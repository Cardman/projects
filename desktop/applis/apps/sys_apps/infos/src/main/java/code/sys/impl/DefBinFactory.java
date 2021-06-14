package code.sys.impl;

import code.stream.core.AbstractBinFactory;
import code.stream.core.AbstractBinStreamIn;
import code.util.core.StringUtil;

import java.io.FileOutputStream;
import java.io.OutputStream;

public final class DefBinFactory implements AbstractBinFactory {
    @Override
    public AbstractBinStreamIn buildIn(String _filePath) {
        return new DefBinStreamIn(_filePath);
    }

    @Override
    public boolean writeFile(String _file, byte[] _content) {
        return write(_content, tryCreateFileOutputStream(_file));
    }

    public static OutputStream tryCreateFileOutputStream(String _file) {
        try {
            return new FileOutputStream(StringUtil.nullToEmpty(_file));
        } catch (Exception e) {
            return null;
        }
    }
    private static boolean write(byte[] _content, OutputStream _fos) {
        OutputStream w_ = write2(_content, _fos);
        return StreamCoreUtil.close(w_);
    }

    private static OutputStream write2(byte[] _content, OutputStream _buff) {
        try {
            _buff.write(_content);
            return _buff;
        } catch (Exception e) {
            return _buff;
        }

    }
}
