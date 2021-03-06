package code.maths.litteralcom;

import code.util.StringList;
import code.util.core.IndexConstants;

public abstract class AbstractWordSplit {
    public StringList loop(String _str) {
        if (_str.isEmpty()) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        StringBuilder strBuild_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str);
                return ret_;
            }
            if (isWordChar(_str.charAt(i_))) {
                strBuild_.append(_str, IndexConstants.FIRST_INDEX, i_);
                break;
            }
            i_++;
        }
        return loop(_str,ret_,i_,strBuild_);
    }
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
