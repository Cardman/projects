package code.stream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import code.stream.core.StreamCoreUtil;
import code.util.core.IndexConstants;

public final class StreamBinaryFile {

    private StreamBinaryFile() {
    }

    public static byte[] loadFile(String _file) {
        if (_file == null) {
            return null;
        }
        File file_ = new File(_file);
        BufferedInputStream buff_ = tryCreateBufferedReader(StreamFileCore.tryCreateFileInputStream(file_));
        return loadFile(file_,buff_);
    }

    private static BufferedInputStream tryCreateBufferedReader(FileInputStream _file) {
        if (_file == null) {
            return null;
        }
        return new BufferedInputStream(_file);
    }
    private static byte[] loadFile(File _file,BufferedInputStream _buff) {
        if (_buff == null) {
            return null;
        }
        try {
            long len_ = _file.length();
            int index_ = IndexConstants.FIRST_INDEX;
            byte[] bytes_ = new byte[(int) len_];
            while (true) {
                int read_ = _buff.read(bytes_, index_, (int) (len_ - index_));
                if (read_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                    break;
                }
                if (index_ == len_) {
                    break;
                }
                index_ += read_;
            }
            StreamCoreUtil.close(_buff);
            return bytes_;
        } catch (IOException e) {
            StreamCoreUtil.close(_buff);
            return null;
        }
    }

    public static boolean writeFile(String _file, byte[] _content) {
        if (_file == null) {
            return false;
        }
        return write(_content, StreamFileCore.tryCreateFileOutputStream(_file));
    }

    private static boolean write(byte[] _content, FileOutputStream _fos) {
        if (_fos == null) {
            return false;
        }
        boolean w_ = write(_content, new BufferedOutputStream(_fos));
        return w_ && StreamCoreUtil.close(_fos);
    }

    private static boolean write(byte[] _content, BufferedOutputStream _buff) {
        try {
            _buff.write(_content);
            return StreamCoreUtil.close(_buff);
        } catch (IOException e) {
            return false;
        }

    }
}
