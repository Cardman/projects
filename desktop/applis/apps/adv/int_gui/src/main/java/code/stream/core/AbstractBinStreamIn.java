package code.stream.core;


public interface AbstractBinStreamIn {
    int close();
    int read();
    byte[] getBytes();
}
