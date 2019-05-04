package code.util;

final class WordsSeparators {

    private StringList words;
    private StringList separators;
    private boolean firstSep;
    private boolean lastSep;

    public StringList getWords() {
        return words;
    }

    public void setWords(StringList _words) {
        words = _words;
    }

    public StringList getSeparators() {
        return separators;
    }

    public void setSeparators(StringList _separators) {
        separators = _separators;
    }

    public boolean isFirstSep() {
        return firstSep;
    }

    public void setFirstSep(boolean _firstSep) {
        firstSep = _firstSep;
    }

    public boolean isLastSep() {
        return lastSep;
    }

    public void setLastSep(boolean _lastSep) {
        lastSep = _lastSep;
    }
}
