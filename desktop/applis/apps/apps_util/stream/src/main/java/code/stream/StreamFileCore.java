package code.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class StreamFileCore {
    private StreamFileCore() {
    }
    public static FileInputStream tryCreateFileInputStream(File _file) {
        try {
            return new FileInputStream(_file);
        } catch (IOException e) {
            return null;
        }
    }
    public static FileOutputStream tryCreateFileOutputStream(String _file) {
        try {
            return new FileOutputStream(_file);
        } catch (IOException e) {
            return null;
        }
    }
}
