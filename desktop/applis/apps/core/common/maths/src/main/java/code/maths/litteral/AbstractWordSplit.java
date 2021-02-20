package code.maths.litteral;

import code.util.StringList;

public abstract class AbstractWordSplit {
    public StringList loop(String _str, StringList _ret, int _i, StringBuilder _strBuild) {
        int i_ = _i;
        StringBuilder strBuild_ = _strBuild;
        boolean wasWordChar_ = false;
        while (true) {
            if (i_ >= _str.length()) {
                _ret.add(strBuild_.toString());
                break;
            }
            char char_ = _str.charAt(i_);
            if (isWordChar(char_)) {
                if (!wasWordChar_) {
                    _ret.add(strBuild_.toString());
                    strBuild_ = new StringBuilder();
                    wasWordChar_ = true;
                }
            } else {
                if (wasWordChar_) {
                    _ret.add(strBuild_.toString());
                    strBuild_ = new StringBuilder();
                    wasWordChar_ = false;
                }
            }
            strBuild_.append(char_);
            i_++;
        }
        return _ret;
    }

    protected abstract boolean isWordChar(char _char);
}
