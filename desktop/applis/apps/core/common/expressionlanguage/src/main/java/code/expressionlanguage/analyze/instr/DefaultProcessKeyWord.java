package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.Ints;
import code.util.core.StringUtil;

public final class DefaultProcessKeyWord {
    private static final char ARR_LEFT = '[';
    private static final char ANN_ARR_LEFT = '{';
    private static final char ANN_ARR_RIGHT = '}';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';

    private DefaultProcessKeyWord() {
    }

    public static void processInternKeyWord(AnalyzedPageEl _page, String _exp, int _fr, Delimiters _d, ResultAfterInstKeyWord _out) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSwitch_ = keyWords_.getKeyWordSwitch();
        if (StringExpUtil.startsWithKeyWord(_exp,_fr, keyWordSwitch_)) {
            int j_ = _fr+keyWordSwitch_.length();
            String afterSwitch_ = _exp.substring(j_);
            if (afterSwitch_.trim().startsWith("[")) {
                int k_ = afterSwitch_.indexOf(ARR_LEFT) + 1;
                k_ = incr(k_,_page.getCurrentAnnotDelSwitch());
                int len_ = afterSwitch_.length();
                if (k_ >= len_) {
                    _d.setBadOffset(len_);
                    return;
                }
                int next_ = DefaultProcessKeyWord.skipWhiteSpace(_exp,j_+k_+1);
                if (_exp.startsWith("(",next_)) {
                    _d.getStack().getCallings().add(next_);
                    _out.setNextIndex(next_);
                    return;
                }
                _out.setNextIndex(j_+k_);
                return;
            }
            int next_ = DefaultProcessKeyWord.skipWhiteSpace(_exp,j_);
            if (_exp.startsWith("(",next_)) {
                _d.getStack().getCallings().add(next_);
                _out.setNextIndex(next_);
                return;
            }
            _out.setNextIndex(j_);
        }
    }

    public static void processKeyWordNew(String _exp, int _fr, Delimiters _d, ResultAfterInstKeyWord _out, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        int j_ = _fr + keyWordNew_.length();
        int af_ = extractPrefix(_exp, _d, _out, j_);
        if (af_ < 0) {
            return;
        }
        j_ = af_;
        _d.getDelAnnotNew().add(j_);
        j_ = incr(j_, _page.getCurrentAnnotDelNew());
        _d.getDelAnnotNew().add(j_);
        if (StringExpUtil.startsWithKeyWord(_exp,j_, keyWordInterfaces_)) {
            int k_ = _exp.indexOf(PAR_LEFT, j_);
            if (k_ < 0) {
                _d.setBadOffset(j_);
                return;
            }
            k_ = _exp.indexOf(PAR_RIGHT, k_);
            if (k_ < 0) {
                _d.setBadOffset(j_);
                return;
            }
            j_ = k_+1;
        }
        extractType(_exp, _d, _out, j_);
    }

    private static int incr(int _j, Ints _dels) {
        int k2_ = _j;
        if (_dels.indexOf(_j) >= 0) {
            k2_ = _dels.get(_dels.indexOf(_j)+1);
        }
        return k2_;
    }

    public static int extractPrefix(String _exp, Delimiters _d, ResultAfterInstKeyWord _out, int _fr) {
        boolean foundLeftPar_ = false;
        boolean foundLeft_ = false;
        int len_ = _exp.length();
        StackDelimiters stack_ = _d.getStack();
        int j_ = _fr;
        while (j_ < len_) {
            char curLoc_ = _exp.charAt(j_);
            if (!StringUtil.isWhitespace(curLoc_)) {
                if (curLoc_ == ANN_ARR_LEFT) {
                    foundLeft_ = true;
                    j_++;
                }
                if (curLoc_ == PAR_LEFT || curLoc_ == ARR_LEFT) {
                    foundLeftPar_ = true;
                    if (curLoc_ == PAR_LEFT ) {
                        stack_.getCallings().add(j_);
                    }
                    j_++;
                }
                break;
            }
            j_++;
        }
        if (foundLeftPar_) {
            int i_ = j_-1;
            stack_.getIndexesNew().add(i_);
            stack_.getStringsNew().add("");
            _out.setNextIndex(i_);
            return -1;
        }
        boolean found_ = false;
        while (j_ < len_) {
            char curLoc_ = _exp.charAt(j_);
            if (!StringUtil.isWhitespace(curLoc_)) {
                if (curLoc_ == ANN_ARR_RIGHT) {
                    j_++;
                    found_ = true;
                }
                break;
            }
            j_++;
        }
        if (foundLeft_ && !found_) {
            _d.setBadOffset(j_);
            return -1;
        }
        return skipWhiteSpace(_exp, j_);
    }

    public static int skipWhiteSpace(String _exp, int _fr) {
        int j_ = _fr;
        int len_ = _exp.length();
        while (j_ < len_) {
            char curLoc_ = _exp.charAt(j_);
            if (!StringUtil.isWhitespace(curLoc_)) {
                break;
            }
            j_++;
        }
        return j_;
    }

    public static void extractType(String _exp, Delimiters _d, ResultAfterInstKeyWord _out, int _fr) {
        StackDelimiters stack_ = _d.getStack();
        int j_ = extractType(_exp,stack_, _fr);
        int len_ = _exp.length();
        if (j_ >= len_) {
            _d.setBadOffset(len_ - 1);
            return;
        }
        stack_.getIndexesNew().add(j_);
        stack_.getStringsNew().add(_exp.substring(_fr, j_));
        _out.setNextIndex(j_);
    }
    public static int extractType(String _exp, StackDelimiters _d, int _fr) {
        int j_ = _fr;
        int count_ = 0;
        int len_ = _exp.length();
        while (j_ < len_) {
            char curLoc_ = _exp.charAt(j_);
            if (curLoc_ == StringExpUtil.LT) {
                count_++;
                j_++;
                continue;
            }
            if (curLoc_ == StringExpUtil.GT) {
                count_--;
                j_++;
                continue;
            }
            if (curLoc_ == PAR_LEFT) {
                _d.getCallings().add(j_);
                break;
            }
            if (curLoc_ == ANN_ARR_LEFT) {
                break;
            }
            if (curLoc_ == ARR_LEFT && count_ == 0) {
                break;
            }
            j_++;
        }
        return j_;
    }
}
