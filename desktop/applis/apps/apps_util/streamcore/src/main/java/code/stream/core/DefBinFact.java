package code.stream.core;

import code.stream.BytesInfo;
import code.util.core.IndexConstants;

//very ugly implementation
public final class DefBinFact implements AbstractBinFact {

    private final AbstractBinFactory textFactory;

    public DefBinFact(AbstractBinFactory _zipFactory) {
        this.textFactory = _zipFactory;
    }

    @Override
    public BytesInfo loadFile(String _nomFichier) {
        AbstractBinStreamIn reader_ = textFactory.buildIn(_nomFichier);
        while (true) {
            int read_ = reader_.read();
            if (read_ < IndexConstants.INDEX_NOT_FOUND_ELT) {
                return new BytesInfo(new byte[0],true);
            }
            if (read_ <= 0) {
                break;
            }
        }
        byte[] bytes_ = reader_.getBytes();
        reader_.close();
        return new BytesInfo(bytes_,false);
    }

    @Override
    public boolean writeFile(String _nomFichier, byte[] _text) {
        return textFactory.writeFile(_nomFichier, _text);
    }
}
