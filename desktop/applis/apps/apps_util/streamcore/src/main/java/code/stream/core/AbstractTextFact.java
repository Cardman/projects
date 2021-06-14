package code.stream.core;

import code.util.ints.UniformingString;

public interface AbstractTextFact {
    String contentsOfFile(String _nomFichier, UniformingString _apply, long _est);
    boolean write(String _nomFichier, String _text, boolean _append);
}
