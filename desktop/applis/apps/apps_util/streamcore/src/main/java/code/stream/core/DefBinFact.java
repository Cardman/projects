package code.stream.core;

import code.util.core.IndexConstants;

//very ugly implementation
public final class DefBinFact implements AbstractBinFact {

    private final AbstractBinFactory textFactory;

    public DefBinFact(AbstractBinFactory _zipFactory) {
        this.textFactory = _zipFactory;
    }

    @Override
    public byte[] loadFile(String _nomFichier, long _est) {
        AbstractBinStreamIn reader_ = textFactory.buildIn(_nomFichier);
        int index_ = IndexConstants.FIRST_INDEX;
        byte[] bytes_ = new byte[(int) _est];
        while (true) {
            int read_ = reader_.read(bytes_, index_, (int) (_est - index_));
            if (read_ < IndexConstants.INDEX_NOT_FOUND_ELT) {
                return null;
            }
            if (read_ == IndexConstants.INDEX_NOT_FOUND_ELT || index_ == _est) {
                break;
            }
            index_ += read_;
        }
        reader_.close();
        return bytes_;
    }

    @Override
    public boolean writeFile(String _nomFichier, byte[] _text) {
        return textFactory.writeFile(_nomFichier, _text);
    }
}
