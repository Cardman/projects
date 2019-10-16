package code.expressionlanguage.files;

import code.util.Ints;
import code.util.StringList;

public final class ParsedImportedTypes {
    private static final char BEGIN_BLOCK = '[';
    private static final char END_BLOCK = ']';
    private static final char END_IMPORTS = ';';

    private final StringList importedTypes = new StringList();
    private final Ints offsetsImports = new Ints();
    private int nextIndex;
    private boolean ok;
    public ParsedImportedTypes(int _nextIndex, String _fullFile, EnablingSpaces _enabledSpaces) {
        nextIndex = _nextIndex;
        if (_nextIndex >= _fullFile.length()) {
            return;
        }
        if (_fullFile.charAt(_nextIndex) != BEGIN_BLOCK) {
            ok = true;
            return;
        }
        nextIndex = FileResolver.incrementRowCol(nextIndex, _fullFile, _enabledSpaces);
        int indexImport_ = 0;
        int len_ = _fullFile.length();
        StringBuilder str_ = new StringBuilder();
        while (nextIndex < len_) {
            char currentChar_ = _fullFile.charAt(nextIndex);
            if (currentChar_ == END_BLOCK) {
                nextIndex = FileResolver.incrementRowCol(nextIndex, _fullFile, _enabledSpaces);
                break;
            }
            if (currentChar_ == END_IMPORTS) {
                importedTypes.add(str_.toString());
                offsetsImports.add(indexImport_);
                str_.delete(0, str_.length());
            } else {
                if (!Character.isWhitespace(currentChar_)) {
                    if (str_.length() == 0) {
                        indexImport_ = nextIndex;
                    }
                }
                str_.append(currentChar_);
            }
            nextIndex = FileResolver.incrementRowCol(nextIndex, _fullFile, _enabledSpaces);
        }
        nextIndex = FileResolver.skipWhitespace(nextIndex, _fullFile, _enabledSpaces);
        ok = true;
    }

    public boolean isOk() {
        return ok;
    }
    public StringList getImportedTypes() {
        return importedTypes;
    }
    public Ints getOffsetsImports() {
        return offsetsImports;
    }
    public int getNextIndex() {
        return nextIndex;
    }

}
