package code.vi.sys.impl;

import code.stream.core.AbstractTextFactory;
import code.stream.core.AbstractTextStreamIn;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public final class DefTextFactory implements AbstractTextFactory {
    @Override
    public AbstractTextStreamIn buildIn(String _filePath) {
        return new DefTextStreamIn(_filePath);
    }

    @Override
    public boolean write(String _nomFichier, String _text, boolean _append) {
        return writeInner(_nomFichier, _text, _append);
    }

    private static boolean writeInner(String _nomFichier, String _text, boolean _append) {
        try {
            OutputStreamWriter res_ = writeLoc(tryCreateWriter(_nomFichier, _append), _text);
            return StreamCoreUtil.close(res_);
        } catch (Exception e) {
            return false;
        }
    }

    private static OutputStreamWriter writeLoc(OutputStreamWriter _bw, String _text) {
        try {
            _bw.write(_text);
            return _bw;
        } catch (Exception e) {
            return _bw;
        }
    }
    private static OutputStreamWriter tryCreateWriter(String _nomFichier, boolean _append) {
        return tryCreateOutWriter(tryCreateFileWriter(_nomFichier, _append));
    }
    private static FileOutputStream tryCreateFileWriter(String _nomFichier, boolean _append) {
        try {
            return new FileOutputStream(_nomFichier,_append);
        } catch (Exception e) {
            return null;
        }
    }
    private static OutputStreamWriter tryCreateOutWriter(FileOutputStream _out) {
        try {
            return new OutputStreamWriter(_out, StreamCoreUtil.utf());
        } catch (Exception e) {
            return null;
        }
    }
}
