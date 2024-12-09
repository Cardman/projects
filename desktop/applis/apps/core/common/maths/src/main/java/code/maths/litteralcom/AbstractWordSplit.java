package code.maths.litteralcom;

import code.util.*;
import code.util.core.*;

public abstract class AbstractWordSplit {
    public int previous(String _txt, int _index) {
        int pr_ = _index-1;
        while (lookingForIndex(_txt, pr_)) {
            pr_--;
        }
        return NumberUtil.max(pr_,0);
    }
    public int next(String _txt, int _index) {
        int ne_ = _index+1;
        while (lookingForIndex(_txt, ne_)) {
            ne_++;
        }
        return NumberUtil.min(ne_,_txt.length());
    }

    public boolean lookingForIndex(String _txt, int _i) {
        return valid(_txt, _i) && !breakPart(_txt, _i);
    }

    public boolean breakPart(String _txt, int _index) {
        if (_index == 0) {
            return true;
        }
        return isWordChar(_txt.charAt(_index - 1)) != isWordChar(_txt.charAt(_index));
    }

    public static boolean valid(String _txt, int _index) {
        return _index >= 0 && _index < _txt.length();
    }

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
