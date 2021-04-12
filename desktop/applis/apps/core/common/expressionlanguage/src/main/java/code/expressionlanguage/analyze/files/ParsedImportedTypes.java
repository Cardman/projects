package code.expressionlanguage.analyze.files;

import code.expressionlanguage.common.StringExpUtil;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ParsedImportedTypes {
    private static final char BEGIN_BLOCK = '[';
    private static final char END_BLOCK = ']';
    private static final char END_IMPORTS = ';';

    private final StringList importedTypes = new StringList();
    private final Ints offsetsImports = new Ints();
    private final int offset;
    private final String nextPart;

    public ParsedImportedTypes(int _nextIndex,int _offset, String _part) {
        int nextIndex_ = _nextIndex;
        if (_part.isEmpty()) {
            offset = _nextIndex;
            nextPart = _part;
            return;
        }
        if (_part.charAt(0) != BEGIN_BLOCK) {
            offset = _nextIndex;
            nextPart = _part;
            return;
        }

        nextIndex_++;
        int indexImport_ = 0;
        int len_ = _part.length();
        StringBuilder str_ = new StringBuilder();
        int current_ = 1;
        while (current_ < len_) {
            char currentChar_ = _part.charAt(current_);
            if (currentChar_ == END_BLOCK) {

                nextIndex_++;
                current_++;
                break;
            }
            if (currentChar_ == END_IMPORTS) {
                importedTypes.add(str_.toString());
                offsetsImports.add(indexImport_+_offset);
                str_.delete(0, str_.length());
            } else {
                if (!StringUtil.isWhitespace(currentChar_) && str_.length() == 0) {
                    indexImport_ = nextIndex_;
                }
                str_.append(currentChar_);
            }

            nextIndex_++;
            current_++;
        }
        int offsetAfter_ = StringExpUtil.getOffset(_part.substring(current_));
        offset = nextIndex_ + offsetAfter_;
        nextIndex_ = current_ + offsetAfter_;
        nextPart = _part.substring(nextIndex_);
    }

    public StringList getImportedTypes() {
        return importedTypes;
    }
    public Ints getOffsetsImports() {
        return offsetsImports;
    }

    public int getOffset() {
        return offset;
    }

    public String getNextPart() {
        return nextPart;
    }
}
