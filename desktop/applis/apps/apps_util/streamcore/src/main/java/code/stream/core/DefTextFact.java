package code.stream.core;

import code.util.ints.UniformingString;

//very ugly implementation
public final class DefTextFact implements AbstractTextFact {

    private final AbstractTextFactory textFactory;

    public DefTextFact(AbstractTextFactory _zipFactory) {
        this.textFactory = _zipFactory;
    }

    @Override
    public String contentsOfFile(String _nomFichier, UniformingString _apply, long _est) {
        AbstractTextStreamIn reader_ = textFactory.buildIn(_nomFichier);
        StringBuilder strBuilder_ = new StringBuilder((int) _est);
        while (true) {

            int char_ = reader_.read();
            if (char_ == -2) {
                reader_.close();
                return null;
            }
            if (char_ < 0) {
                break;
            }
            strBuilder_.append((char) char_);
        }
        reader_.close();
        return _apply.apply(strBuilder_.toString());
    }

    @Override
    public boolean write(String _nomFichier, String _text, boolean _append) {
        return textFactory.write(_nomFichier, _text, _append);
    }
}
