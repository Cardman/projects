package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.ParsedAnnotations;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.core.StringUtil;

public final class DefaultProcessKeyWord implements AbstractProcessKeyWord {
    private static final char ARR_LEFT = '[';
    private static final char ARR_RIGHT = ']';
    private static final char ANN_ARR_LEFT = '{';
    private static final char ANN_ARR_RIGHT = '}';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private final AnalyzedPageEl page;

    public DefaultProcessKeyWord(AnalyzedPageEl _page) {
        page = _page;
    }

    @Override
    public void processInternKeyWord(String _exp, int _fr, Delimiters _d, ResultAfterInstKeyWord _out) {
        KeyWords keyWords_ = page.getKeyWords();
        String keyWordSwitch_ = keyWords_.getKeyWordSwitch();
        if (StringExpUtil.startsWithKeyWord(_exp,_fr, keyWordSwitch_)) {
            int j_ = _fr+keyWordSwitch_.length();
            String afterSwitch_ = _exp.substring(j_);
            if (afterSwitch_.trim().startsWith("[")) {
                int k_ = afterSwitch_.indexOf(ARR_LEFT) + 1;
                int len_ = afterSwitch_.length();
                int count_ = 1;
                while (k_ < len_) {
                    char ch_ = afterSwitch_.charAt(k_);
                    if (ch_ == ARR_LEFT) {
                        count_++;
                    }
                    if (count_ == 1 && ch_ == ':') {
                        int l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,k_+1);
                        if (afterSwitch_.startsWith("@",l_)) {
                            ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(l_),l_);
                            parse_.parse(page.getCurrentParts());
                            l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,parse_.getIndex());
                        }
                        if (afterSwitch_.startsWith(":",l_)) {
                            int m_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,l_+1);
                            if (afterSwitch_.startsWith("@",m_)) {
                                ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(m_),m_);
                                parse_.parse(page.getCurrentParts());
                                m_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,parse_.getIndex());
                            }
                            l_ = m_;
                        }
                        if (afterSwitch_.startsWith("]",l_)) {
                            k_ = l_;
                            break;
                        }
                    }
                    if (ch_ == ARR_RIGHT) {
                        count_--;
                        if (count_ == 0) {
                            break;
                        }
                    }
                    k_++;
                }
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
        if (_exp.startsWith("@",j_)) {
            ParsedAnnotations parse_ = new ParsedAnnotations(_exp.substring(j_),j_);
            parse_.parse(_page.getCurrentParts());
            j_ = DefaultProcessKeyWord.skipWhiteSpace(_exp,parse_.getIndex());
        }
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
