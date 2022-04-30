package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.SuffixedNumber;
import code.util.CharList;
import code.util.Ints;
import code.util.core.StringUtil;

public final class ElResolverCommon {
    private ElResolverCommon() {
    }

    static int addNamed(String _string, int _begin, int _end, Ints _namedArgs, AnalyzedPageEl _page) {
        int bk_ = StringExpUtil.getBackPrintChar(_string, _begin);
        for (char c: CharList.wrapCharArray(',','(','{','[')) {
            if (StringExpUtil.nextCharIs(_string, bk_, _string.length(), c)) {
                int n_ = nextNamedDbDot(_string, _begin, _end, _page);
                int pr_ = _begin;
                if (n_ > -1) {
                    _namedArgs.add(n_);
                    pr_ = StringExpUtil.nextPrintChar(n_ + 1, _string.length(), _string);
                }
                return pr_;
            }
        }
        return _begin;
    }
    static int nextNamedDbDot(String _string, int _begin, int _end, AnalyzedPageEl _page) {
        int s_ = _string.indexOf(':',_end);
        String sub_;
        if (s_ < 0) {
            sub_ = "";
        } else {
            sub_ = _string.substring(_begin,s_);
        }
        if (!AnaInherits.isOkQualFields(sub_,_page)) {
            return -1;
        }
        return s_;
    }
    static int processPredefinedMethod(String _string, int _i, String _name) {
        int afterSuper_ = _i + _name.length();
        int index_ = _string.indexOf(ElResolver.PAR_LEFT,afterSuper_);
        if (index_ < 0) {
            return -afterSuper_;
        }
        if (!_string.substring(afterSuper_, index_).trim().isEmpty()) {
            return -afterSuper_;
        }
        return index_;
    }

    static boolean isTernary(String _string, int _len, int _i) {
        boolean ternary_ = false;
        if (StringExpUtil.nextCharIs(_string, _i + 1, _len, ElResolver.DOT_VAR)) {
            int n_ = StringExpUtil.nextPrintChar(_i + 2, _len, _string);
            if (isDigitOrDot(_string,n_)) {
                ternary_ = true;
            }
        } else {
            if (!StringExpUtil.nextCharIs(_string, _i + 1, _len, ElResolver.BEGIN_TERNARY)
                    &&!StringExpUtil.nextCharIs(_string, _i + 1, _len, ElResolver.ARR_LEFT)) {
                ternary_ = true;
            }
        }
        return ternary_;
    }
    static boolean isDigitOrDot(String _string, int _n) {
        return _n > -1 && (StringExpUtil.isDigit(_string.charAt(_n)) || _string.charAt(_n) == ElResolver.DOT_VAR);
    }

    static NumberInfosOutput processNb(KeyWords _key, int _start, int _max, String _string, boolean _seenDot) {
        //_string.charAt(_start) is digit
        NumberInfosOutput output_ = new NumberInfosOutput();
        NumberInfos nbInfos_ = new NumberInfos();
        output_.setInfos(nbInfos_);
        StringBuilder intPart_ = new StringBuilder();
        StringBuilder decPart_ = new StringBuilder();
        StringBuilder expPart_ = new StringBuilder();
        nbInfos_.setIntPart(intPart_);
        nbInfos_.setDecimalPart(decPart_);
        nbInfos_.setExponentialPart(expPart_);
        int start_ = _start;
        char startChar_ = _string.charAt(start_);
        int base_ = 10;
        String hexPre_ = _key.getKeyWordNbHex();
        String hexEnd_ = _key.getKeyWordNbHexEnd();
        String binPre_ = _key.getKeyWordNbBin();
        String decExp_ = _key.getKeyWordNbExpDec();
        String binExp_ = _key.getKeyWordNbExpBin();
        if (_seenDot) {
            nbInfos_.setSuffix(ElResolver.DOUBLE);
            decPart_.append(startChar_);
            if (startChar_ == '.') {
                nbInfos_.setError(true);
            }
            if (StringUtil.isWhitespace(startChar_)) {
                nbInfos_.setError(true);
            }
        } else {
            nbInfos_.setSuffix(ElResolver.INTEGER);
            if (startChar_ == '0') {
                String sub_ = _string.substring(start_ + 1);
                if (start_ + 1 < _max) {
                    SuffixedNumber suff_ = _key.getNbKeyWord(sub_, 0);
                    if (suff_ != null) {
                        char ch_ = suff_.getValue();
                        nbInfos_.setSuffix(ch_);
                        intPart_.append(startChar_);
                        output_.setNextIndex(start_ + 1 + suff_.getKey().length());
                        return output_;
                    }
                    if (sub_.startsWith(hexPre_)) {
                        base_ = 16;
                        start_+=hexPre_.length();
                        start_++;
                        if (start_ >= _max) {
                            output_.setNextIndex(_max);
                            nbInfos_.setError(true);
                            return output_;
                        }
                        startChar_ = _string.charAt(start_);
                    } else {
                        if (sub_.startsWith(binPre_)) {
                            base_ = 2;
                            start_+=binPre_.length();
                            start_++;
                            if (start_ >= _max) {
                                output_.setNextIndex(_max);
                                nbInfos_.setError(true);
                                return output_;
                            }
                            startChar_ = _string.charAt(start_);
                        } else {
                            if (StringExpUtil.isDigit(_string.charAt(start_ + 1))){
                                base_ = 8;
                                start_++;
                                startChar_ = _string.charAt(start_);
                            }
                        }
                    }
                }
            }
            intPart_.append(startChar_);
        }
        nbInfos_.setBase(base_);
        int j_ = start_ + 1;
        boolean dot_ = false;
        boolean exp_ = false;
        int iExp_ = j_;
        while (j_ < _max) {
            char current_ = _string.charAt(j_);
            if (!StringExpUtil.isTypeLeafChar(current_)) {
                int n_ = StringExpUtil.nextPrintChar(j_ + 1, _max, _string);
                if (current_ == ElResolver.DOT_VAR) {
                    if (_seenDot) {
                        nbInfos_.setError(true);
                        decPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (nbInfos_.isError()) {
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (n_ == -1) {
                        if (base_ == 10) {
                            nbInfos_.setSuffix(ElResolver.DOUBLE);
                            output_.setNextIndex(j_ + 1);
                            return output_;
                        }
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (isWhite(j_ + 1, _max, _string) && isDigitOrDot(_string, n_)) {
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    if (_string.charAt(n_) == ElResolver.DOT_VAR) {
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                    dot_ = true;
                }
                if (isWhite(j_, _max, _string)) {
                    if (nbInfos_.isError()) {
                        append(_seenDot, intPart_, decPart_, current_);
                        j_++;
                        continue;
                    }
                    if (isDigitOrDot(_string,n_)) {
                        nbInfos_.setError(true);
                        intPart_.append(current_);
                        j_++;
                        continue;
                    }
                }
                break;
            }
            String sub_ = _string.substring(j_);
            int off_ = getExpLength(base_, decExp_, binExp_);
            if (isExp(base_, decExp_, binExp_, sub_)) {
                if (nbInfos_.isError()) {
                    append(_seenDot, intPart_, decPart_, current_);
                    j_++;
                    continue;
                }
                if (isWhite(j_ + off_,_max,_string)) {
                    nbInfos_.setError(true);
                    append(_seenDot, intPart_, decPart_, current_);
                    j_++;
                    continue;
                }
                nbInfos_.setSuffix(ElResolver.DOUBLE);
                iExp_ = j_ + off_ - 1;
                j_ += off_ - 1;
                exp_ = true;
                break;
            }
            int dig_ = digitPart(current_, base_, _key);
            if (dig_ < 0) {
                j_ = incrSep(j_,base_,sub_,hexEnd_);
                boolean ok_ = processSuffix(_key, _string, output_, nbInfos_, j_);
                if (ok_) {
                    return output_;
                }
                nbInfos_.setError(true);
                append(_seenDot, intPart_, decPart_, current_);
                j_++;
                continue;
            }
            //current_ is digit or expected letter
            append(_seenDot, intPart_, decPart_, (char) dig_);
            j_++;
        }
        if (dot_) {
            nbInfos_.setSuffix(ElResolver.DOUBLE);
            String sub_ = _string.substring(j_ + 1);
            int off_ = getExpLength(base_, decExp_, binExp_);
            if (isExp(base_, decExp_, binExp_, sub_)) {
                j_+=off_;
                //_string.charAt(j_) == EXP
                processExp(_key, j_, _max, _string, output_);
                return output_;
            }
            j_++;
            boolean added_ = false;
            while (j_ < _max) {
                char curChar_ = _string.charAt(j_);
                int dig_ = digitPart(curChar_, base_, _key);
                if (dig_ < 0) {
                    break;
                }
                decPart_.append((char) dig_);
                added_ = true;
                j_++;
            }
            if (!added_) {
                processDotSuffix(_key,base_,sub_,hexEnd_, _string, output_, nbInfos_,j_, _max);
                return output_;
            }
            if (j_ >= _max) {
                output_.setNextIndex(j_);
                return output_;
            }
            sub_ = _string.substring(j_);
            if (isExp(base_, decExp_, binExp_, sub_)) {
                j_+=off_-1;
                processExp(_key, j_, _max, _string, output_);
                return output_;
            }
            processDotSuffix(_key,base_,sub_,hexEnd_, _string, output_, nbInfos_, j_, _max);
            return output_;
        }
        if (exp_) {
            //_string.charAt(iExp_) == EXP
            processExp(_key, iExp_, _max, _string, output_);
            return output_;
        }
        output_.setNextIndex(j_);
        return output_;
    }

    private static int incrSep(int _j, int _base, String _sub, String _hexPre) {
        int j_ = _j;
        if (_base != 10 && _sub.startsWith(_hexPre)) {
            j_ += _hexPre.length();
        }
        return j_;
    }

    private static boolean isWhite(int _current, int _max, String _str) {
        return _current < _max && StringUtil.isWhitespace(_str.charAt(_current));
    }

    private static void append(boolean _seenDot, StringBuilder _intPart, StringBuilder _decPart, char _current) {
        if (_seenDot) {
            _decPart.append(_current);
        } else {
            _intPart.append(_current);
        }
    }

    private static boolean isExp(int _base, String _decExp, String _binExp, String _sub) {
        boolean exp_;
        if (_base == 10) {
            exp_ = _sub.startsWith(_decExp);
        } else {
            exp_ = _sub.startsWith(_binExp);
        }
        return exp_;
    }

    private static int getExpLength(int _base, String _decExp, String _binExp) {
        int off_;
        if (_base == 10) {
            off_ = _decExp.length();
        } else {
            off_ = _binExp.length();
        }
        return off_;
    }

    private static void processDotSuffix(KeyWords _key, int _base, String _sub, String _hexPre, String _string,
                                         NumberInfosOutput _output, NumberInfos _nbInfos, int _j, int _max) {
        int j_ = incrSep(_j,_base,_sub,_hexPre);
        SuffixedNumber suff_ = _key.getNbKeyWord(_string, j_);
        if (suff_ != null) {
            j_ +=suff_.getKey().length();
            char su_ = suff_.getValue();
            _nbInfos.setSuffix(su_);
        }
        j_ = nextIndex(_key,j_, _max, _string, _output, _nbInfos.getDecimalPart());
        _output.setNextIndex(j_);
    }

    private static boolean processSuffix(KeyWords _key, String _string, NumberInfosOutput _output, NumberInfos _nbInfos, int _j) {
        SuffixedNumber suff_ = _key.getNbKeyWord(_string, _j);
        if (suff_ == null) {
            return false;
        }
        char ch_ = suff_.getValue();
        _nbInfos.setSuffix(ch_);
        int n_ = _j + suff_.getKey().length();
        boolean ok_ = processedCorrectOrContinue(_string, n_);
        if (ok_) {
            _output.setNextIndex(n_);
        }
        return ok_;
    }
    private static int digitPart(char _char, int _base, KeyWords _key) {
        if (_char == ElResolver.NB_INTERN_SP) {
            return _char;
        }
        return digit(_char,_base,_key);
    }

    private static int digit(char _char, int _base, KeyWords _key) {
        if (_base == 10) {
            if (StringExpUtil.isDigit(_char)) {
                return _char;
            }
            return -1;
        }
        if (_base == 16) {
            if (StringExpUtil.isDigit(_char)) {
                return _char;
            }
            int min_ = NumParsers.toMinCaseLetter(_char);
            String keyWordNbDig_ = _key.getKeyWordNbDig();
            int ch_ = keyWordNbDig_.indexOf(min_);
            if (ch_ >= 0) {
                return ch_ + 'A';
            }
            return -1;
        }
        if (_base == 2) {
            if (_char == '0' || _char == '1') {
                return _char;
            }
            return -1;
        }
        if (_char >= ElResolver.MIN_ENCODE_DIGIT && _char <= '7') {
            return _char;
        }
        return -1;
    }

    private static void processExp(KeyWords _key, int _start, int _max, String _string, NumberInfosOutput _output) {
        if (_start + 1 >= _max) {
            _output.getInfos().setError(true);
            _output.setNextIndex(_max);
            return;
        }
        StringBuilder exp_ = _output.getInfos().getExponentialPart();
        int j_ = _start;
        j_++;
        if (_string.charAt(j_) == ElResolver.MINUS_CHAR || _string.charAt(j_) == ElResolver.PLUS_CHAR) {
            if (j_ + 1 >= _max) {
                _output.getInfos().setError(true);
                _output.setNextIndex(j_);
                return;
            }
            if (!StringExpUtil.isDigit(_string.charAt(j_+1))) {
                _output.getInfos().setError(true);
                _output.setNextIndex(j_);
                return;
            }
            exp_.append(_string.charAt(j_));
            j_++;
        } else {
            if (!StringExpUtil.isDigit(_string.charAt(j_))) {
                _output.getInfos().setError(true);
                if (!StringExpUtil.isTypeLeafChar(_string.charAt(j_))) {
                    int n_ = StringExpUtil.nextPrintChar(j_+1, _max, _string);
                    if (n_ < 0) {
                        _output.setNextIndex(j_);
                        return;
                    }
                    if (!StringExpUtil.isTypeLeafChar(_string.charAt(n_))) {
                        _output.setNextIndex(j_);
                        return;
                    }
                    if (!StringUtil.isWhitespace(_string.charAt(j_))) {
                        _output.setNextIndex(j_);
                        return;
                    }
                    while (j_ < n_) {
                        exp_.append(_string.charAt(j_));
                        j_++;
                    }
                }
            }
        }
        while (j_ < _max) {
            int dig_ = digitPart(_string.charAt(j_), 10, _key);
            if (dig_ < 0) {
                break;
            }
            exp_.append((char) dig_);
            j_++;
        }
        int n_ = StringExpUtil.nextPrintChar(j_, _max, _string);
        if (n_ > -1 && _string.charAt(n_) == ElResolver.DOT_VAR) {
            _output.getInfos().setError(true);
            _output.setNextIndex(n_);
            return;
        }
        if (j_ < _max && StringDataLetterUtil.isLetter(_string.charAt(j_))) {
            SuffixedNumber keyWord_ = _key.getNbKeyWord(_string, j_);
            if (keyWord_ != null) {
                char suf_ = keyWord_.getValue();
                _output.getInfos().setSuffix(suf_);
                j_+=keyWord_.getKey().length();
            }
        }
        j_ = nextIndex(_key,j_, _max, _string, _output, exp_);
        _output.setNextIndex(j_);
    }

    private static int nextIndex(KeyWords _key, int _j, int _max, String _string, NumberInfosOutput _output, StringBuilder _str) {
        int j_ = _j;
        if (j_ < _max) {
            if (unexpectedWordChars(_key, _max, _string, j_)) {
                _output.getInfos().setError(true);
                _str.append(_string.charAt(j_));
                j_++;
                while (j_ < _max) {
                    if (!unexpectedWordChars(_key, _max, _string, j_)) {
                        break;
                    }
                    _str.append(_string.charAt(j_));
                    j_++;
                }
            }
        }
        return j_;
    }

    private static boolean unexpectedWordChars(KeyWords _key, int _max, String _string, int _j) {
        return hasSpaceByWordChar(_key,_max, _string, _j)|| StringExpUtil.isDollarWordChar(_string.charAt(_j));
    }

    private static boolean hasSpaceByWordChar(KeyWords _key, int _max, String _string, int _j) {
        boolean space_ = false;
        if (StringUtil.isWhitespace(_string.charAt(_j)) ) {
            int n_ = StringExpUtil.nextPrintChar(_j, _max, _string);
            if (n_ > -1 && StringExpUtil.isDollarWordChar(_string.charAt(n_))) {
                if (!StringExpUtil.startsWithKeyWord(_string,n_,_key.getKeyWordInstanceof())) {
                    space_ = true;
                }
            }
        }
        return space_;
    }

    private static boolean processedCorrectOrContinue(String _string, int _j) {
        String nextPartAf_ = _string.substring(_j).trim();
        return !isNotCorrectNbEnd(nextPartAf_);
    }

    private static boolean isNotCorrectNbEnd(String _next) {
        if (_next.isEmpty()) {
            return false;
        }
        char n_ = _next.charAt(0);
        return n_ == ElResolver.DOT_VAR;
    }

    static int incrInstanceOf(String _string, int _len, int _next) {
        int next_ = incrType(_next,_string);
        if (next_ < _len && _string.charAt(next_) == ElResolver.LOWER_CHAR) {
            int nbOpened_ = 1;
            next_++;
            while (next_ < _len) {
                char curLoc_ = _string.charAt(next_);
                if (curLoc_ == ElResolver.LOWER_CHAR) {
                    nbOpened_++;
                    next_++;
                    continue;
                }
                if (nbOpened_ == 0 && !StringUtil.isWhitespace(curLoc_)) {
                    break;
                }
                if (curLoc_ == ElResolver.GREATER_CHAR) {
                    nbOpened_--;
                    next_++;
                    if (nbOpened_ == 0) {
                        String substring_ = _string.substring(next_);
                        if (substring_.trim().startsWith(".")) {
                            next_ = incrType(next_,_string);
                        }
                    }
                    continue;
                }
                next_++;
            }
        }
        if (next_ < _len) {
            char curLoc_ = _string.charAt(next_);
            if (curLoc_ == ElResolver.ARR_LEFT) {
                while (next_ < _len) {
                    curLoc_ = _string.charAt(next_);
                    if (StringUtil.isWhitespace(curLoc_)) {
                        next_++;
                        continue;
                    }
                    if (curLoc_ == ElResolver.ARR_LEFT) {
                        next_++;
                        continue;
                    }
                    if (curLoc_ == ElResolver.ARR_RIGHT) {
                        next_++;
                        continue;
                    }
                    break;
                }
            }
        }
        return next_;
    }

    private static int incrType(int _i, String _string) {
        int next_ = _i;
        int len_ = _string.length();
        while (next_ < len_) {
            char curLoc_ = _string.charAt(next_);
            if (StringExpUtil.isTypeLeafChar(curLoc_)) {
                next_++;
                continue;
            }
            if (curLoc_ == ElResolver.DOT_VAR) {
                next_++;
                continue;
            }
            if (StringUtil.isWhitespace(curLoc_)) {
                next_++;
                continue;
            }
            break;
        }
        return next_;
    }

    static int getWord(String _string, int _len, int _i) {
        int j_ = _i;
        while (j_ < _len) {
            char locChar_ = _string.charAt(j_);
            if (!StringExpUtil.isTypeLeafChar(locChar_)) {
                break;
            }
            j_++;
        }
        return j_;
    }

    static void tryAddStringParts(StackOperators _parsBrackets, int _i, StackDelimiters _stack) {
        int indexLast_ = _stack.getIndexesNew().indexOf(_parsBrackets.lastKey());
        if (indexLast_ > -1) {
            _stack.getIndexesNewEnd().add(_i);
            _stack.getStringsNewEnd().add(_stack.getStringsNew().get(indexLast_));
        }
        int indexLastSwitch_ = _stack.getIndexesSwitch().indexOf(_parsBrackets.lastKey());
        if (indexLastSwitch_ > -1) {
            _stack.getIndexesSwitchEnd().add(_i);
            _stack.getStringsSwitchEnd().add(_stack.getStringsSwitch().get(indexLastSwitch_));
        }
    }

    static void tryAddAnnotationsParts(StackOperators _parsBrackets, StackDelimiters _stack) {
        int indexLast_ = _stack.getIndexesNew().indexOf(_parsBrackets.lastKey());
        if (indexLast_ > -1) {
            _stack.getAnnotationsEnd().add(_stack.getAnnotations().get(indexLast_));
        }
        int indexLastSw_ = _stack.getIndexesSwitch().indexOf(_parsBrackets.lastKey());
        if (indexLastSw_ > -1) {
            _stack.getRetSwitchList().add(_stack.getRetSwitch().get(indexLastSw_));
            _stack.getAnnotationsEndSw().add(_stack.getAnnotationsSw().get(indexLastSw_));
            _stack.getAnnotationsEndSwPar().add(_stack.getAnnotationsSwPar().get(indexLastSw_));
        }
    }
}
