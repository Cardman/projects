package code.stream.core;

public interface AbstractBinFactory {
    AbstractBinStreamIn buildIn(String _filePath);
    int writeFile(String _file, byte[] _content, boolean _append);
}
