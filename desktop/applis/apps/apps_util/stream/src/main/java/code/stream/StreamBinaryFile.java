package code.stream;
import java.io.*;

import code.stream.core.StreamCoreUtil;
import code.util.core.IndexConstants;

public final class StreamBinaryFile {

    private StreamBinaryFile() {
    }

    public static byte[] loadFile(String _file,AbstractFileCoreStream _fact) {
        AbstractFile file_ = _fact.newFile(_file);
        InputStream buff_ = StreamFileCore.tryCreateFileInputStream(_file);
        return loadFile(file_,buff_);
    }

    private static byte[] loadFile(AbstractFile _file,InputStream _buff) {
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
        return write(_content, StreamFileCore.tryCreateFileOutputStream(_file));
    }

    private static boolean write(byte[] _content, OutputStream _fos) {
        if (_content == null || _fos == null) {
            return false;
        }
        boolean w_ = write2(_content, _fos);
        return w_ && StreamCoreUtil.close(_fos);
    }

    private static boolean write2(byte[] _content, OutputStream _buff) {
        try {
            _buff.write(_content);
            return StreamCoreUtil.close(_buff);
        } catch (IOException e) {
            return false;
        }

    }
}
