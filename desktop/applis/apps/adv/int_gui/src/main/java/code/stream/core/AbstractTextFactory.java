package code.stream.core;

public interface AbstractTextFactory {
    AbstractTextStreamIn buildIn(String _filePath);
    int write(String _nomFichier, String _text, boolean _append);
}
