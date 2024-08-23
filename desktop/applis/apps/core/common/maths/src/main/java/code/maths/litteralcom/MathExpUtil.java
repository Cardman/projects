package code.maths.litteralcom;

import code.maths.Rate;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.ListableEntries;

public final class MathExpUtil {

    private static final char CHAR_WORD_OTHER = '_';

    private MathExpUtil() {
    }

    public static double toDouble(long _nb){
        return toDouble((double)_nb);
    }
    private static double toDouble(double _nb){
        return _nb;
    }
    public static boolean allOp(char _curChar) {
        return _curChar == MatCommonCst.PAR_RIGHT || supplDels(_curChar) ||unary(_curChar) || normalOp(_curChar);
    }

    private static boolean supplDels(char _curChar) {
        return arrOp(_curChar)||matrix(_curChar);
    }

    private static boolean matrix(char _curChar) {
        return _curChar == MatCommonCst.SEP_ARG_BRA || _curChar == MatCommonCst.BRA_LEFT || _curChar == MatCommonCst.BRA_RIGHT;
    }
    private static boolean arrOp(char _curChar) {
        return _curChar == MatCommonCst.ARR_LEFT || _curChar == MatCommonCst.ARR_RIGHT;
    }
    public static boolean unary(char _curChar) {
        return _curChar == MatCommonCst.PLUS_CHAR || _curChar == MatCommonCst.MINUS_CHAR || _curChar == MatCommonCst.NEG_BOOL_CHAR;
    }
    public static boolean normalOp(char _curChar) {
        return mult(_curChar) || andOr(_curChar) || cmp(_curChar) || call(_curChar);
    }

    private static boolean call(char _curChar) {
        return _curChar == MatCommonCst.PAR_LEFT || _curChar == MatCommonCst.SEP_ARG;
    }

    private static boolean cmp(char _curChar) {
        return cmpStr(_curChar) || _curChar == MatCommonCst.EQ_CHAR;
    }

    public static boolean cmpStr(char _curChar) {
        return _curChar == MatCommonCst.LOWER_CHAR || _curChar == MatCommonCst.GREATER_CHAR;
    }

    public static boolean cmpEq(char _curChar) {
        return _curChar == MatCommonCst.NEG_BOOL_CHAR || _curChar == MatCommonCst.EQ_CHAR;
    }

    private static boolean andOr(char _curChar) {
        return _curChar == MatCommonCst.AND_CHAR || _curChar == MatCommonCst.OR_CHAR;
    }

    private static boolean mult(char _curChar) {
        return _curChar == MatCommonCst.MULT_CHAR || _curChar == MatCommonCst.DIV_CHAR;
    }

    public static boolean isNumber(String _string) {
        return NumberUtil.isNumber(_string);
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

    public static boolean isDollarWord(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!isDollarWordChar(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(char _ch) {
        return NumberUtil.isDigit(_ch);
    }

    public static StringList getWordsSeparatorsPrefix(String _str, String _prefixWord) {
        StringList list_ = getDollarWordSeparators(_str);
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
            } else if (t.startsWith(_prefixWord)) {
                newList_.add(t);
            } else {
                newList_.set(newList_.getLastIndex(), StringUtil.concat(newList_.last(), t));
            }
            i_++;
        }
        return newList_;
    }

    public static String replaceWordsJoin(String _str, ListableEntries<String,String> _map) {
        return StringUtil.join(replaceWords(_str, _map), StringUtil.EMPTY_STRING);
    }

    private static StringList replaceWords(String _str, ListableEntries<String, String> _map) {
        StringList list_ = getDollarWordSeparators(_str);
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
        return new DefaultWordSplit().loop(_str);
    }

    public static StringList getDollarWordSeparators(String _str) {
        return new AdvWordSplit().loop(_str);
    }

    public static boolean isDollarWordChar(char _char) {
        if (_char == '$') {
            return true;
        }
        return isWordChar(_char);
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
        if (_char < NumberUtil.MIN_UPP) {
            return false;
        }
        if (_char <= NumberUtil.MIN_UPP + 25) {
            return true;
        }
        if (_char < NumberUtil.MIN_LOW) {
            return false;
        }
        if (_char <= NumberUtil.MIN_LOW + 25) {
            return true;
        }
        return _char >= 160;
    }

    public static Rate caracgaucheferme(Rate _rateOne, Rate _rateTwo) {
        Rate res_=Rate.zero();
        if(Rate.lowerEq(_rateOne, _rateTwo)){
            res_=Rate.one();
        }
        return res_;
    }

    public static Rate caracgaucheouvert(Rate _rateOne, Rate _rateTwo) {
        Rate res_=Rate.zero();
        if(Rate.strLower(_rateOne, _rateTwo)){
            res_=Rate.one();
        }
        return res_;
    }

    public static Rate caracdroiteferme(Rate _rateOne, Rate _rateTwo) {
        Rate res_=Rate.zero();
        if(Rate.greaterEq(_rateOne, _rateTwo)){
            res_=Rate.one();
        }
        return res_;
    }

    public static Rate caracdroiteouvert(Rate _rateOne, Rate _rateTwo) {
        Rate res_=Rate.zero();
        if(Rate.strGreater(_rateOne, _rateTwo)){
            res_=Rate.one();
        }
        return res_;
    }

    public static Rate caracsemiouvertd(Rate _rateOne, Rate _rateTwo, Rate _rateThree) {
        Rate res_=Rate.zero();
        if(Rate.greaterEq(_rateOne, _rateTwo)&&Rate.strLower(_rateOne, _rateThree)){
            res_=Rate.one();
        }
        return res_;
    }

    public static Rate caracsemiouvertg(Rate _rateOne, Rate _rateTwo, Rate _rateThree) {
        Rate res_=Rate.zero();
        if(Rate.strGreater(_rateOne, _rateTwo)&&Rate.lowerEq(_rateOne, _rateThree)){
            res_=Rate.one();
        }
        return res_;
    }

    public static Rate caracouvert(Rate _rateOne, Rate _rateTwo, Rate _rateThree) {
        Rate res_=Rate.zero();
        if(Rate.strGreater(_rateOne, _rateTwo)&&Rate.strLower(_rateOne, _rateThree)){
            res_=Rate.one();
        }
        return res_;
    }

    public static Rate segment(Rate _rateOne, Rate _rateTwo, Rate _rateThree) {
        Rate res_=Rate.zero();
        if(Rate.greaterEq(_rateOne, _rateTwo)&&Rate.lowerEq(_rateOne, _rateThree)){
            res_=Rate.one();
        }
        return res_;
    }

    public static boolean charIs(String _string, int _len,int _i, char _ch) {
        return _i < _len && _string.charAt(_i) == _ch;
    }
}
