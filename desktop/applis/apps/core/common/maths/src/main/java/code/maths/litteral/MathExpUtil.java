package code.maths.litteral;

import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.ListableEntries;

public final class MathExpUtil {
    private static final char MINUS = '-';
    private static final char CHAR_WORD_OTHER = '_';

    private MathExpUtil() {
    }

    public static boolean isNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        if (_string.charAt(i_) == MINUS) {
            if (_string.length() == IndexConstants.ONE_ELEMENT) {
                return false;
            }
            i_++;
        }
        int len_ = _string.length();
        while (i_ < len_) {
            if (!isDigit(_string.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }

    public static boolean isPositiveNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWord(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!isWordChar(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(char _ch) {
        return _ch >= '0' && _ch <= '9';
    }

    public static StringList getWordsSeparatorsPrefix(String _str, String _prefixWord) {
        StringList list_ = getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        for (String t : list_) {
            if (i_ % 2 == 0) {
                if (newList_.isEmpty()) {
                    newList_.add(t);
                } else if (!newList_.last().startsWith(_prefixWord)) {
                    newList_.set(newList_.getLastIndex(),StringUtil.concat(newList_.last(),t));
                } else {
                    newList_.add(t);
                }
                i_++;
                continue;
            }
            if (t.startsWith(_prefixWord)) {
                newList_.add(t);
                i_++;
                continue;
            }
            newList_.set(newList_.getLastIndex(),StringUtil.concat(newList_.last(),t));
            i_++;
        }
        return newList_;
    }

    public static String replaceWordsJoin(String _str, ListableEntries<String,String> _map) {
        return StringUtil.join(replaceWords(_str, _map), StringUtil.EMPTY_STRING);
    }

    private static StringList replaceWords(String _str, ListableEntries<String, String> _map) {
        StringList list_ = getWordsSeparators(_str);
        StringList newList_ = new StringList();
        for (String t : list_) {
            if (_map.contains(t)) {
                newList_.add(_map.getVal(t));
            } else {
                newList_.add(t);
            }
        }
        return newList_;
    }

    public static StringList getWordsSeparators(String _str) {
        if (_str.isEmpty()) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        boolean wasWordChar_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str);
                return ret_;
            }
            if (isWordChar(_str.charAt(i_))) {
                str_.append(_str, IndexConstants.FIRST_INDEX, i_);
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(str_.toString());
                break;
            }
            char char_ = _str.charAt(i_);
            if (isWordChar(char_)) {
                if (!wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = true;
                }
            } else {
                if (wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = false;
                }
            }
            str_.append(char_);
            i_++;
        }
        return ret_;
    }

    public static boolean isWordChar(char _char) {
        if (_char == CHAR_WORD_OTHER) {
            return true;
        }
        if (_char < '0') {
            return false;
        }
        if (_char <= '9') {
            return true;
        }
        if (_char < 'A') {
            return false;
        }
        if (_char <= 'Z') {
            return true;
        }
        if (_char < 'a') {
            return false;
        }
        if (_char <= 'z') {
            return true;
        }
        return _char >= 160;
    }
}
