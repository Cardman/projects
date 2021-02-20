package code.maths.litteral;

final class MathStringState {
    private static final char ESCAPE_META_CHAR = '\\';
    private static final char DELIMITER_STRING_SEP = ';';
    private static final char DELIMITER_STRING_END = '}';
    private boolean escapedMeta;
    private boolean constString;
    private int index;
    private int parsBrackets;

    boolean exit(StringBuilder _elt,Delimiters _d,char _curChar,String _string, ErrorStatus _error){
        int len_ = _string.length();
        if (!escapedMeta) {
            if (_curChar == ESCAPE_META_CHAR) {
                if (index + 1 >= len_) {
                    _error.setIndex(index + 1);
                    _error.setError(true);
                    _error.setString(_string);
                    return true;
                }
                escapedMeta = true;
                index++;
                return false;
            }
            if (_curChar == DELIMITER_STRING_END) {
                _d.getDelStringsChars().add(index);
                _d.getStringInfo().last().add(_elt.toString());
                _elt.delete(0, _elt.length());
                constString = false;
                index++;
                return false;
            }
            if (_curChar == DELIMITER_STRING_SEP) {
                _d.getStringInfo().last().add(_elt.toString());
                _elt.delete(0, _elt.length());
            } else {
                _elt.append(_curChar);
            }
            index++;
            return false;
        }
        if (seps(_curChar)) {
            _elt.append(_curChar);
            escapedMeta = false;
            index++;
            return false;
        }
        _error.setIndex(index);
        _error.setError(true);
        _error.setString(_string);
        return true;
    }

    private static boolean seps(char _curChar) {
        return _curChar == DELIMITER_STRING_END || _curChar == DELIMITER_STRING_SEP || _curChar == ESCAPE_META_CHAR;
    }

    boolean isConstString() {
        return constString;
    }

    void setConstString() {
        this.constString = true;
    }

    int getIndex() {
        return index;
    }

    void setIndex(int _index) {
        this.index = _index;
    }

    int getParsBrackets() {
        return parsBrackets;
    }

    void setParsBrackets(int _parsBrackets) {
        this.parsBrackets = _parsBrackets;
    }
}
