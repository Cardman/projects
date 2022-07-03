package code.stream;

public interface AbstractBinStream {
    byte[] loadFile(String _file);
    boolean writeFile(String _file, byte[] _content);
}
