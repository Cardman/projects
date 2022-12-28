package code.stream.core;

import code.stream.BytesInfo;

public interface AbstractBinFact {
    BytesInfo loadFile(String _nomFichier);

    boolean writeFile(String _nomFichier, byte[] _text);
}
