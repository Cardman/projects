package code.stream.core;

public interface AbstractZipFactory {
    AbstractZipStreamIn buildIn(byte[] _bytes);
    AbstractZipStreamOut buildOut();
}
