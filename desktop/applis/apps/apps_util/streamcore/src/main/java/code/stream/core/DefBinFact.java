package code.stream.core;

import code.util.core.IndexConstants;

//very ugly implementation
public final class DefBinFact implements AbstractBinFact {

    private final AbstractBinFactory textFactory;

    public DefBinFact(AbstractBinFactory _zipFactory) {
        this.textFactory = _zipFactory;
    }

    @Override
    public byte[] loadFile(String _nomFichier) {
        AbstractBinStreamIn reader_ = textFactory.buildIn(_nomFichier);
        while (true) {
            int read_ = reader_.read();
            if (read_ < IndexConstants.INDEX_NOT_FOUND_ELT) {
                return null;
            }
            if (read_ <= 0) {
                break;
            }
        }
        reader_.close();
        return reader_.getBytes();
    }

    @Override
    public boolean writeFile(String _nomFichier, byte[] _text) {
        return textFactory.writeFile(_nomFichier, _text);
    }
}
