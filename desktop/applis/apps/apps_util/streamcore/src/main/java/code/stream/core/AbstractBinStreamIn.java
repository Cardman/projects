package code.stream.core;


public interface AbstractBinStreamIn {
    boolean close();
    int read(byte[] _array, int _off, int _len);
}
