package code.stream.core;

import java.util.zip.ZipEntry;

public interface AbstractZipStreamOut {
    ZipEntry buildEntry(String _name, long _time);
    boolean write(byte[] _array);
    boolean closeEntry();
    void close();
    boolean putNextEntry(ZipEntry _entry);
    byte[] byteArray();
}
