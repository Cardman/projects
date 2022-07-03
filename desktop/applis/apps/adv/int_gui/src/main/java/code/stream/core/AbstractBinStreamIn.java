package code.stream.core;


public interface AbstractBinStreamIn {
    boolean close();
    int read();
    byte[] getBytes();
}
