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
        try {
            File file_ = new File(_file);
            FileInputStream fis_ = new FileInputStream(file_);
            BufferedInputStream buff_ = new BufferedInputStream(fis_);
            long len_ = file_.length();
            int index_ = CustList.FIRST_INDEX;
            byte[] bytes_ = new byte[(int) len_];
            while (true) {
                int read_ = buff_.read(bytes_, index_, (int) (len_ - index_));
                if (read_ == CustList.INDEX_NOT_FOUND_ELT) {
                    break;
                }
                if (index_ == len_) {
                    break;
                }
                index_ += read_;
            }
            buff_.close();
            fis_.close();
            return bytes_;
        } catch (RuntimeException _0) {
            return null;
        } catch (IOException _0) {
            return null;
        }
    }

    public static boolean writeFile(String _file, byte[] _content) {
        try {
            FileOutputStream fos_ = new FileOutputStream(new File(_file));
            BufferedOutputStream buff_ = new BufferedOutputStream(fos_);
            buff_.write(_content);
            buff_.close();
            fos_.close();
            return true;
        } catch (Exception _0) {
            return false;
        }
    }
}
