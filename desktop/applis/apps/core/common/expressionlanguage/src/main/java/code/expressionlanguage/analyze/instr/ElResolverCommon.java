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

    static NumberInfosOutput processNb(KeyWords _key, int _start, String _string, boolean _seenDot) {
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
        int init_ = tryStart(_seenDot, _key, _string, _start, output_);
        if (init_ <= _start) {
            return output_;
        }
        int j_ = init_;
        int len_ = _string.length();
        while (j_ < len_) {
            int nextIncr_ = tryIncr(_seenDot,_key,_string,j_,output_);
            if (nextIncr_ <= j_) {
                return output_;
            }
            j_ = nextIncr_;
        }
        afterLoop(_key,_string,false, false, output_, j_);
        return output_;
    }
    private static int tryStart(boolean _seenDot,KeyWords _key, String _string, int _start,NumberInfosOutput _output) {
        NumberInfos nbInfos_ = _output.getInfos();
        StringBuilder intPart_ = nbInfos_.getIntPart();
        StringBuilder decPart_ = nbInfos_.getDecimalPart();
        int start_ = _start;
        char startChar_ = _string.charAt(start_);
        int base_ = 10;
        String hexPre_ = _key.getKeyWordNbHex();
        String binPre_ = _key.getKeyWordNbBin();
        if (_seenDot) {
            nbInfos_.setSuffix(ElResolver.DOUBLE);
            decPart_.append(startChar_);
            if (startChar_ == '.') {
                nbInfos_.setError(true);
            }
            if (StringUtil.isWhitespace(startChar_)) {
                nbInfos_.setError(true);
            }
            nbInfos_.setBase(base_);
            return start_ + 1;
        }
        nbInfos_.setSuffix(ElResolver.INTEGER);
        int len_ = _string.length();
        if (startChar_ != '0' || start_ + 1 >= len_) {
            intPart_.append(startChar_);
            nbInfos_.setBase(base_);
            return start_ + 1;
        }
        SuffixedNumber suff_ = _key.getNbKeyWord(_string, start_ + 1);
        if (suff_ != null) {
            char ch_ = suff_.getValue();
            nbInfos_.setSuffix(ch_);
            intPart_.append(startChar_);
            _output.setNextIndex(start_ + 1 + suff_.getKey().length());
            return _start;
        }
        if (_string.startsWith(hexPre_, start_ + 1)) {
            base_ = 16;
            start_ += hexPre_.length();
            start_++;
            if (start_ >= len_) {
                _output.setNextIndex(len_);
                nbInfos_.setError(true);
                return _start;
            }
            startChar_ = _string.charAt(start_);
            intPart_.append(startChar_);
            nbInfos_.setBase(base_);
            return start_ + 1;
        }
        if (_string.startsWith(binPre_, start_ + 1)) {
            base_ = 2;
            start_ += binPre_.length();
            start_++;
            if (start_ >= len_) {
                _output.setNextIndex(len_);
                nbInfos_.setError(true);
                return _start;
            }
            startChar_ = _string.charAt(start_);
            intPart_.append(startChar_);
            nbInfos_.setBase(base_);
            return start_ + 1;
        }
        if (StringExpUtil.isDigit(_string.charAt(start_ + 1))) {
            base_ = 8;
            start_++;
            startChar_ = _string.charAt(start_);
        }
        intPart_.append(startChar_);
        nbInfos_.setBase(base_);
        return start_ + 1;
    }
    private static int tryIncr(boolean _seenDot,KeyWords _key, String _string, int _j,NumberInfosOutput _output) {
        NumberInfos nbInfos_ = _output.getInfos();
        StringBuilder intPart_ = nbInfos_.getIntPart();
        StringBuilder decPart_ = nbInfos_.getDecimalPart();
        String decExp_ = _key.getKeyWordNbExpDec();
        String binExp_ = _key.getKeyWordNbExpBin();
        char current_ = _string.charAt(_j);
        if (current_ == ElResolver.DOT_VAR) {
            return dotCase(_seenDot, _key, _string, _j, _output);
        }
        if (!StringExpUtil.isTypeLeafChar(current_)) {
            return errOrExit(_seenDot, _key, _string, _j, _output, false);
        }
        int off_ = offExp(nbInfos_.getBase(), decExp_, binExp_, _string, _j);
        if (off_ > 0) {
            return expCase(_seenDot, _key, _string, _j, _output, off_);
        }
        int dig_ = digitPart(current_, nbInfos_.getBase(), _key);
        if (dig_ >= 0) {//current_ is digit or expected letter
            append(_seenDot, intPart_, decPart_, (char) dig_);
            return _j + 1;
        }
        String hexEnd_ = _key.getKeyWordNbHexEnd();
        int jAft_ = incrSep(_j, nbInfos_.getBase(), _string, hexEnd_);
        boolean ok_ = processSuffix(_key, _string, _output, nbInfos_, jAft_);
        if (ok_) {
            return _j;
        }
        nbInfos_.setError(true);
        append(_seenDot, intPart_, decPart_, current_);
        return jAft_ + 1;
    }

    private static int dotCase(boolean _seenDot, KeyWords _key, String _string, int _j, NumberInfosOutput _output) {
        char current_ = _string.charAt(_j);
        NumberInfos nbInfos_ = _output.getInfos();
        StringBuilder intPart_ = nbInfos_.getIntPart();
        StringBuilder decPart_ = nbInfos_.getDecimalPart();
        int len_ = _string.length();
        int n_ = StringExpUtil.nextPrintChar(_j + 1, len_, _string);
        if (_seenDot) {
            nbInfos_.setError(true);
            decPart_.append(current_);
            return _j + 1;
        }
        if (nbInfos_.isError()) {
            intPart_.append(current_);
            return _j + 1;
        }
        if (n_ == -1 && nbInfos_.getBase() == 10) {
            nbInfos_.setSuffix(ElResolver.DOUBLE);
            _output.setNextIndex(_j + 1);
            return _j;
        }
        if (n_ == -1 || isWhite(_j + 1, _string) && isDigitOrDot(_string, n_) || _string.charAt(n_) == ElResolver.DOT_VAR) {
            nbInfos_.setError(true);
            intPart_.append(current_);
            return _j + 1;
        }
        return errOrExit(false, _key, _string, _j, _output, true);
    }

    private static int errOrExit(boolean _seenDot, KeyWords _key, String _string, int _j, NumberInfosOutput _output, boolean _dot) {
        int aft_ = incrDotErr(_seenDot, _output, _string, _j);
        if (aft_ > _j) {
            return aft_;
        }
        afterLoop(_key, _string, _dot,false, _output, _j);
        return _j;
    }

    private static int expCase(boolean _seenDot, KeyWords _key, String _string, int _j, NumberInfosOutput _output, int _off) {
        char current_ = _string.charAt(_j);
        NumberInfos nbInfos_ = _output.getInfos();
        StringBuilder intPart_ = nbInfos_.getIntPart();
        StringBuilder decPart_ = nbInfos_.getDecimalPart();
        if (nbInfos_.isError()) {
            append(_seenDot, intPart_, decPart_, current_);
            return _j + 1;
        }
        if (isWhite(_j + _off, _string)) {
            nbInfos_.setError(true);
            append(_seenDot, intPart_, decPart_, current_);
            return _j + 1;
        }
        nbInfos_.setSuffix(ElResolver.DOUBLE);
        afterLoop(_key, _string,false, true, _output, _j + _off - 1);
        return _j;
    }

    private static int incrDotErr(boolean _seenDot,NumberInfosOutput _output, String _string, int _j) {
        int len_ = _string.length();
        int n_ = StringExpUtil.nextPrintChar(_j + 1, len_, _string);
        char current_ = _string.charAt(_j);
        NumberInfos nbInfos_ = _output.getInfos();
        StringBuilder intPart_ = nbInfos_.getIntPart();
        StringBuilder decPart_ = nbInfos_.getDecimalPart();
        if (isWhite(_j, _string)) {
            if (nbInfos_.isError()) {
                append(_seenDot, intPart_, decPart_, current_);
                return _j + 1;
            }
            if (isDigitOrDot(_string,n_)) {
                nbInfos_.setError(true);
                intPart_.append(current_);
                return _j + 1;
            }
        }
        return _j;
    }
    private static void afterLoop(KeyWords _key, String _string, boolean _dot, boolean _exp, NumberInfosOutput _output, int _j) {
        if (_dot) {
            afterDot(_key,_string, _output, _j);
            return;
        }
        if (_exp) {
            //_string.charAt(iExp_) == EXP
            processExp(_key, _j, _string, _output);
            return;
        }
        _output.setNextIndex(_j);
    }
    private static void afterDot(KeyWords _key, String _string, NumberInfosOutput _output, int _j) {
        int j_ = _j;
        String decExp_ = _key.getKeyWordNbExpDec();
        String binExp_ = _key.getKeyWordNbExpBin();
        int len_ = _string.length();
        NumberInfos nbInfos_ = _output.getInfos();
        StringBuilder decPart_ = nbInfos_.getDecimalPart();
        nbInfos_.setSuffix(ElResolver.DOUBLE);
        int base_ = nbInfos_.getBase();
        int offFirst_ = offExp(base_, decExp_, binExp_, _string, j_ + 1);
        if (offFirst_ > 0) {
            j_+=offFirst_;
            //_string.charAt(j_) == EXP
            processExp(_key, j_, _string, _output);
            return;
        }
        j_++;
        int afterDecPart_ = appendDec(_key, _string, j_, base_, decPart_);
        if (afterDecPart_ <= j_) {
            suffix(_key, _string, _output, incrSep(j_, base_, _string, _key.getKeyWordNbHexEnd()));
            return;
        }
        j_ = afterDecPart_;
        if (j_ >= len_) {
            _output.setNextIndex(j_);
            return;
        }
        int off_ = offExp(base_, decExp_, binExp_, _string, j_);
        if (off_ > 0) {
            j_+=off_-1;
            processExp(_key, j_, _string, _output);
            return;
        }
        suffix(_key, _string, _output, incrSep(j_, base_, _string, _key.getKeyWordNbHexEnd()));
    }
    private static int appendDec(KeyWords _key, String _string, int _j, int _base, StringBuilder _decPart) {
        int len_ = _string.length();
        int j_ = _j;
        while (j_ < len_) {
            char curChar_ = _string.charAt(j_);
            int dig_ = digitPart(curChar_, _base, _key);
            if (dig_ < 0) {
                break;
            }
            _decPart.append((char) dig_);
            j_++;
        }
        return j_;
    }

    private static int incrSep(int _j, int _base, String _string, String _hexEnd) {
        int j_ = _j;
        if (_base != 10 && _string.startsWith(_hexEnd,_j)) {
            j_ += _hexEnd.length();
        }
        return j_;
    }

    private static boolean isWhite(int _current, String _str) {
        int len_ = _str.length();
        return _current < len_ && StringUtil.isWhitespace(_str.charAt(_current));
    }

    private static void append(boolean _seenDot, StringBuilder _intPart, StringBuilder _decPart, char _current) {
        if (_seenDot) {
            _decPart.append(_current);
        } else {
            _intPart.append(_current);
        }
    }

    private static int offExp(int _base, String _decExp, String _binExp, String _string, int _from) {
        int off_ = getExpLength(_base, _decExp, _binExp);
        if (isExp(_base,_decExp,_binExp,_string,_from)) {
            return off_;
        }
        return 0;
    }
    private static boolean isExp(int _base, String _decExp, String _binExp, String _string, int _from) {
        boolean exp_;
        if (_base == 10) {
            exp_ = _string.startsWith(_decExp,_from);
        } else {
            exp_ = _string.startsWith(_binExp,_from);
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

    private static void suffix(KeyWords _key, String _string, NumberInfosOutput _output, int _j) {
        int j_ = _j;
        j_ += incrSuff(_key, _string, _output, _j);
        _output.setNextIndex(nextIndex(_key, j_, _string, _output, _output.getInfos().getDecimalPart()));
    }
    private static int incrSuff(KeyWords _key, String _string, NumberInfosOutput _output, int _j) {
        SuffixedNumber suff_ = _key.getNbKeyWord(_string, _j);
        if (suff_ != null) {
            char su_ = suff_.getValue();
            _output.getInfos().setSuffix(su_);
            return suff_.getKey().length();
        }
        return 0;
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

    private static void processExp(KeyWords _key, int _start, String _string, NumberInfosOutput _output) {
        int len_ = _string.length();
        int j_ = _start;
        j_++;
        if (j_ >= len_) {
            _output.getInfos().setError(true);
            _output.setNextIndex(len_);
            return;
        }
        StringBuilder exp_ = _output.getInfos().getExponentialPart();
        if (_string.charAt(j_) == ElResolver.MINUS_CHAR || _string.charAt(j_) == ElResolver.PLUS_CHAR) {
            if (j_ + 1 >= len_ || !StringExpUtil.isDigit(_string.charAt(j_ + 1))) {
                _output.getInfos().setError(true);
                _output.setNextIndex(j_);
                return;
            }
            exp_.append(_string.charAt(j_));
            j_++;
            expDigitsAndSuffix(_key,_string,exp_,j_,_output);
            return;
        }
        if (!StringExpUtil.isDigit(_string.charAt(j_))) {
            unsignedExpErr(_key, _string, _output, exp_, j_);
            return;
        }
        expDigitsAndSuffix(_key,_string,exp_,j_,_output);
    }

    private static void unsignedExpErr(KeyWords _key, String _string, NumberInfosOutput _output, StringBuilder _exp, int _j) {
        int len_ = _string.length();

        int j_ = _j;
        _output.getInfos().setError(true);
        if (!StringExpUtil.isTypeLeafChar(_string.charAt(j_))) {
            int n_ = StringExpUtil.nextPrintChar(j_ +1, len_, _string);
            if (n_ < 0 || !StringExpUtil.isTypeLeafChar(_string.charAt(n_)) || !StringUtil.isWhitespace(_string.charAt(j_))) {
                _output.setNextIndex(j_);
                return;
            }
            while (j_ < n_) {
                _exp.append(_string.charAt(j_));
                j_++;
            }
        }
        expDigitsAndSuffix(_key, _string, _exp, j_, _output);
    }

    private static void expDigitsAndSuffix(KeyWords _key, String _string, StringBuilder _exp, int _j, NumberInfosOutput _output) {
        int len_ = _string.length();
        int j_ = appendDigitToExp(_key,_string,_exp,_j);
        int n_ = StringExpUtil.nextPrintChar(j_, len_, _string);
        if (n_ > -1 && _string.charAt(n_) == ElResolver.DOT_VAR) {
            _output.getInfos().setError(true);
            _output.setNextIndex(n_);
            return;
        }
        afterExp(_key, _string, _output, _exp, j_);
    }
    private static int appendDigitToExp(KeyWords _key, String _string, StringBuilder _exp, int _j){
        int len_ = _string.length();
        int j_ = _j;
        while (j_ < len_) {
            int dig_ = digitPart(_string.charAt(j_), 10, _key);
            if (dig_ < 0) {
                break;
            }
            _exp.append((char) dig_);
            j_++;
        }
        return j_;
    }
    private static void afterExp(KeyWords _key, String _string, NumberInfosOutput _output, StringBuilder _exp, int _j) {
        int len_ = _string.length();
        int j_ = _j;
        if (j_ < len_ && StringDataLetterUtil.isLetter(_string.charAt(j_))) {
            j_ += incrSuff(_key, _string, _output, j_);
        }
        _output.setNextIndex(nextIndex(_key, j_, _string, _output, _exp));
    }

    private static int nextIndex(KeyWords _key, int _j, String _string, NumberInfosOutput _output, StringBuilder _str) {
        int len_ = _string.length();
        int j_ = _j;
        if (j_ < len_) {
            char first_ = _string.charAt(j_);
            if (unexpectedWordChars(_key, _string, j_, first_)) {
                _output.getInfos().setError(true);
                _str.append(first_);
                j_++;
                while (j_ < len_) {
                    char cur_ = _string.charAt(j_);
                    if (!unexpectedWordChars(_key, _string, j_, cur_)) {
                        break;
                    }
                    _str.append(cur_);
                    j_++;
                }
            }
        }
        return j_;
    }

    private static boolean unexpectedWordChars(KeyWords _key, String _string, int _j, char _cur) {
        return hasSpaceByWordChar(_key, _string, _j, _cur)|| StringExpUtil.isDollarWordChar(_cur);
    }

    private static boolean hasSpaceByWordChar(KeyWords _key, String _string, int _j, char _ch) {
        if (StringUtil.isWhitespace(_ch) ) {
            int len_ = _string.length();
            int n_ = StringExpUtil.nextPrintChar(_j, len_, _string);
            return n_ > -1 && StringExpUtil.isDollarWordChar(_string.charAt(n_)) && !StringExpUtil.startsWithKeyWord(_string, n_, _key.getKeyWordInstanceof());
        }
        return false;
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
        if (next_ >= _len || _string.charAt(next_) != ElResolver.LOWER_CHAR) {
            return fromArrLeft(_string,_len,next_);
        }
        int nbOpened_ = 1;
        next_++;
        while (next_ < _len) {
            char curLoc_ = _string.charAt(next_);
            if (curLoc_ != ElResolver.LOWER_CHAR&&nbOpened_ == 0 && !StringUtil.isWhitespace(curLoc_)) {
                break;
            }
            if (curLoc_ == ElResolver.LOWER_CHAR) {
                nbOpened_++;
                next_++;
            } else  if (curLoc_ == ElResolver.GREATER_CHAR) {
                nbOpened_--;
                next_++;
                next_ = incrAfterGtInstanceOf(_string,nbOpened_,next_);
            } else {
                next_++;
            }
        }
        return fromArrLeft(_string,_len,next_);
    }
    private static int incrAfterGtInstanceOf(String _string, int _nbOpened, int _next) {
        int next_ = _next;
        if (_nbOpened == 0) {
            String substring_ = _string.substring(next_);
            if (substring_.trim().startsWith(".")) {
                next_ = incrType(next_,_string);
            }
        }
        return next_;
    }
    private static int fromArrLeft(String _string, int _len, int _next) {
        int next_ = _next;
        if (next_ < _len) {
            char curLoc_ = _string.charAt(next_);
            if (curLoc_ == ElResolver.ARR_LEFT) {
                while (next_ < _len) {
                    curLoc_ = _string.charAt(next_);
                    if (!StringUtil.isWhitespace(curLoc_) && curLoc_ != ElResolver.ARR_LEFT && curLoc_ != ElResolver.ARR_RIGHT) {
                        break;
                    }
                    next_++;
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
            if (!StringExpUtil.isTypeLeafChar(curLoc_) && curLoc_ != ElResolver.DOT_VAR && !StringUtil.isWhitespace(curLoc_)) {
                break;
            }
            next_++;
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
