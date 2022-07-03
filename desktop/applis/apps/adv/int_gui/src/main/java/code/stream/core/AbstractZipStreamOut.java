package code.stream.core;


public interface AbstractZipStreamOut {
    boolean closeEntry();
    void close();
    byte[] byteArray();

    boolean putNextEntry(String _key, long _lastModifTime, byte[] _content);
    boolean putNextEmptyEntry(String _key, long _lastModifTime);
}
