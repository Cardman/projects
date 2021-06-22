package code.stream.core;

public interface AbstractZipStreamIn {
    boolean hasNextEntry();
    boolean closeEntry();
    void close();
    int read(byte[] _array,int _off,int _len);

    String getName();

    boolean isDirectory();

    long getTime();

    long getSize();
}
