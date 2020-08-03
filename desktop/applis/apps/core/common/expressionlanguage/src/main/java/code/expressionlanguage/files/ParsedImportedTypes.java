package code.expressionlanguage.files;

import code.expressionlanguage.common.StringExpUtil;
import code.util.Ints;
import code.util.StringList;

public final class ParsedImportedTypes {
    private static final char BEGIN_BLOCK = '[';
    private static final char END_BLOCK = ']';
    private static final char END_IMPORTS = ';';

    private final StringList importedTypes = new StringList();
    private final Ints offsetsImports = new Ints();
    private int offset;
    private int nextIndex;
    private boolean skip;

    public ParsedImportedTypes(int _nextIndex, String _part) {
        nextIndex = _nextIndex;
        if (_part.isEmpty()) {
            skip = true;
            return;
        }
        if (_part.charAt(0) != BEGIN_BLOCK) {
            skip = true;
            return;
        }

        nextIndex = nextIndex + 1;
        int indexImport_ = 0;
        int len_ = _part.length();
        StringBuilder str_ = new StringBuilder();
        int current_ = 0;
        current_++;
        while (current_ < len_) {
            char currentChar_ = _part.charAt(current_);
            if (currentChar_ == END_BLOCK) {

                nextIndex = nextIndex + 1;
                current_++;
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

            nextIndex = nextIndex + 1;
            current_++;
        }
        int offsetAfter_ = StringExpUtil.getOffset(_part.substring(current_));
        offset = nextIndex+ offsetAfter_;
        nextIndex = current_ + offsetAfter_;
    }

    public boolean isSkip() {
        return skip;
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

    public int getNextIndex() {
        return nextIndex;
    }

}
