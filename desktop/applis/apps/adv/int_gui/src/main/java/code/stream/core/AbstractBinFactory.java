package code.stream.core;

public interface AbstractBinFactory {
    AbstractBinStreamIn buildIn(String _filePath);
    boolean writeFile(String _file, byte[] _content);
}
