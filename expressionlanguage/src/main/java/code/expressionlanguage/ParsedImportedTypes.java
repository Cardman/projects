package code.expressionlanguage;

import code.sml.RowCol;
import code.util.Numbers;
import code.util.StringList;

public final class ParsedImportedTypes {
    private static final char BEGIN_BLOCK = '{';
    private static final char END_BLOCK = '}';
    private static final char END_IMPORTS = ';';

    private final StringList importedTypes = new StringList();
    private final Numbers<Integer> offsetsImports = new Numbers<Integer>();
    private int nextIndex;
    private boolean ok;
    private boolean foundBrace;
    public ParsedImportedTypes(int _nextIndex, String _fullFile, int _tabWidth, RowCol _current, EnablingSpaces _enabledSpaces) {
        nextIndex = _nextIndex;
        if (_fullFile.charAt(_nextIndex) != BEGIN_BLOCK) {
            ok = true;
            return;
        }
        foundBrace = true;
        nextIndex = FileResolver.incrementRowCol(nextIndex, _fullFile, _tabWidth, _current, _enabledSpaces);
        int indexImport_ = 0;
        int len_ = _fullFile.length();
        StringBuilder str_ = new StringBuilder();
        while (nextIndex < len_) {
            char currentChar_ = _fullFile.charAt(nextIndex);
            if (currentChar_ == END_BLOCK) {
                nextIndex = FileResolver.incrementRowCol(nextIndex, _fullFile, _tabWidth, _current, _enabledSpaces);
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
            nextIndex = FileResolver.incrementRowCol(nextIndex, _fullFile, _tabWidth, _current, _enabledSpaces);
        }
        nextIndex = FileResolver.skipWhitespace(nextIndex, _fullFile, _tabWidth, _current, _enabledSpaces);
        if (nextIndex < 0) {
            //ERROR
            return;
        }
        if (nextIndex > len_) {
            //ERROR
            return;
        }
        ok = true;
    }
    public boolean isFoundBrace() {
        return foundBrace;
    }
    public boolean isOk() {
        return ok;
    }
    public StringList getImportedTypes() {
        return importedTypes;
    }
    public Numbers<Integer> getOffsetsImports() {
        return offsetsImports;
    }
    public int getNextIndex() {
        return nextIndex;
    }
    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

}
