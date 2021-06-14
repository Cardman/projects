package code.stream.core;

import java.util.zip.ZipEntry;

public interface AbstractZipStreamIn {
    ZipEntry getNextEntry();
    boolean closeEntry();
    void close();
    int read(byte[] _array,int _off,int _len);
}
