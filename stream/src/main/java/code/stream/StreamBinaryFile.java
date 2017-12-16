package code.stream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import code.util.CustList;
import code.util.StringList;

public final class StreamBinaryFile {

//    private static final String END_PATH = "/$";
//    private static final String EMPTY_STRING = "";

    private StreamBinaryFile() {
    }

    public static void copyFileToFolder(String _fromFile, String _toFolder) {
        StringList list_ = StringList.splitStrings(_fromFile, StreamTextFile.SEPARATEUR);
        String toFolder_ = _toFolder;
        if (toFolder_.endsWith(StreamTextFile.SEPARATEUR)) {
            toFolder_ = toFolder_.substring(CustList.FIRST_INDEX, toFolder_.length() - 1);
        }
        String toFile_ = toFolder_ + StreamTextFile.SEPARATEUR + list_.last();
        copyFile(_fromFile, toFile_);
    }

    public static void copyFile(String _fromFolder, String _fileName, String _toFolder) {
        String from_ = _fromFolder + StreamTextFile.SEPARATEUR + _fileName;
        String to_ = _toFolder + StreamTextFile.SEPARATEUR + _fileName;
        copyFile(from_, to_);
    }
    public static void copyFile(String _fromFile, String _toFile) {
        FileOutputStream out_ = null;
        FileInputStream in_ = null;
        try {
            FileChannel src_ = null;
            FileChannel dest_ = null;
            in_ = new FileInputStream(_fromFile);
            src_ = in_.getChannel();
            out_ = new FileOutputStream(_toFile);
            dest_ = out_.getChannel();
            dest_.transferFrom(src_, CustList.FIRST_INDEX, src_.size());
        } catch (Throwable _0) {
        } finally {
            if (in_ != null) {
                try {
                    in_.close();
                } catch (IOException _0) {
                }
            }
            if (out_ != null) {
                try {
                    out_.close();
                } catch (IOException _0) {
                }
            }
        }
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

    public static void writeFile(String _file, byte[] _content) {
        try {
            FileOutputStream fos_ = new FileOutputStream(new File(_file));
            BufferedOutputStream buff_ = new BufferedOutputStream(fos_);
            buff_.write(_content);
            buff_.close();
            fos_.close();
        } catch (RuntimeException _0) {
        } catch (IOException _0) {
        }
    }
}
