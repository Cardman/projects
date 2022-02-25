package code.stream.core;

public interface AbstractBinFact {
    byte[] loadFile(String _nomFichier);

    boolean writeFile(String _nomFichier, byte[] _text);
}
