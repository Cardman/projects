package code.stream.core;

public interface AbstractTextFactory {
    AbstractTextStreamIn buildIn(String _filePath);
    boolean write(String _nomFichier, String _text, boolean _append);
}
