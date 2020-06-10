package code.stream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import code.util.CustList;

public final class StreamBinaryFile {

    private StreamBinaryFile() {
    }

    public static byte[] loadFile(String _file) {
        if (_file == null) {
            return null;
        }
        try {
            File file_ = new File(_file);
            BufferedInputStream buff_ = new BufferedInputStream(new FileInputStream(_file));
            return loadFile(file_,buff_);
        } catch (IOException _0) {
            return null;
        }
    }
    private static byte[] loadFile(File _file,BufferedInputStream _buff) {
        try {
            long len_ = _file.length();
            int index_ = CustList.FIRST_INDEX;
            byte[] bytes_ = new byte[(int) len_];
            while (true) {
                int read_ = _buff.read(bytes_, index_, (int) (len_ - index_));
                if (read_ == CustList.INDEX_NOT_FOUND_ELT) {
                    break;
                }
                if (index_ == len_) {
                    break;
                }
                index_ += read_;
            }
            _buff.close();
            return bytes_;
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean writeFile(String _file, byte[] _content) {
        if (_file == null) {
            return false;
        }
        try {
            return write(_content, new FileOutputStream(_file));
        } catch (IOException _0) {
            return false;
        }
    }

    private static boolean write(byte[] _content, FileOutputStream _fos) {
        try {
            boolean w_ = write(_content, new BufferedOutputStream(_fos));
            _fos.close();
            return w_;
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean write(byte[] _content, BufferedOutputStream _buff) {
        try {
            _buff.write(_content);
            _buff.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
