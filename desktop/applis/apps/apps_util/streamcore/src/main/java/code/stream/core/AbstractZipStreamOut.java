package code.stream.core;


public interface AbstractZipStreamOut {
    boolean write(byte[] _array);
    boolean closeEntry();
    void close();
    byte[] byteArray();

    boolean putNextEntry(String _key, long _lastModifTime, long _size);
}
