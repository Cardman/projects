package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ElRetrieverAnonymous {
    private ElRetrieverAnonymous() {
    }

    public static void commonCheckQuick(String _string, int _minIndex, AnalyzedPageEl _page, ResultExpression _res) {
        _page.getAnonymousResults().clear();
        String currentPkg_ = _page.getCurrentPkg();
        FileBlock currentFile_ = _page.getCurrentFile();
        stackBegin(_string,_minIndex, currentFile_,currentPkg_,_page);
        _res.setAnonymousResults(new CustList<AnonymousResult>(_page.getAnonymousResults()));
    }

    private static void stackBegin(String _string, int _from, FileBlock _file, String _pkg, AnalyzedPageEl _page) {
        boolean constTextString_ = false;
        boolean constTextChar_ = false;
        boolean constString_ = false;
        boolean constChar_ = false;
        boolean constText_ = false;
        StackDelimiters stack_ = new StackDelimiters();
        StackOperators parsBrackets_ = new StackOperators();
        char prevOp_ = ElResolver.SPACE;
        int from_ = _from;
        int len_ = _string.length();
        while (from_ < len_) {
            char curChar_ = _string.charAt(from_);
            if (constTextString_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_STRING
                        && StringExpUtil.nextCharIs(_string,from_+1,len_, ElResolver.DELIMITER_STRING)
                        &&StringExpUtil.nextCharIs(_string,from_+2,len_, ElResolver.DELIMITER_STRING)) {
                    constTextString_ = false;
                    from_ += 3;
                    continue;
                }
                from_++;
                continue;
            }
            if (constString_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_STRING) {
                    constString_ = false;
                }
                from_++;
                continue;
            }
            if (constTextChar_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_CHAR
                        &&StringExpUtil.nextCharIs(_string,from_+1,len_, ElResolver.DELIMITER_CHAR)
                        &&StringExpUtil.nextCharIs(_string,from_+2,len_, ElResolver.DELIMITER_CHAR)) {
                    constTextChar_ = false;
                    from_ += 3;
                    continue;
                }
                from_++;
                continue;
            }
            if (constChar_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_CHAR) {
                    constChar_ = false;
                }
                from_++;
                continue;
            }
            if (constText_) {
                if (curChar_ == ElResolver.DELIMITER_TEXT) {
                    if (from_ + 1 >= len_ ||_string.charAt(from_ + 1) != ElResolver.DELIMITER_TEXT) {
                        constText_ = false;
                        from_++;
                        continue;
                    }
                    from_++;
                }
                from_++;
                continue;
            }
            if (curChar_ == ElResolver.DELIMITER_STRING) {
                if (ElResolverCommon.isRepeated(_string, from_, len_, ElResolver.DELIMITER_STRING)) {
                    constTextString_ = true;
                    from_+=3;
                    continue;
                }
                constString_ = true;
                from_++;
                continue;
            }
            if (curChar_ == ElResolver.DELIMITER_CHAR) {
                if (ElResolverCommon.isRepeated(_string, from_, len_, ElResolver.DELIMITER_CHAR)) {
                    constTextChar_ = true;
                    from_+=3;
                    continue;
                }
                constChar_ = true;
                from_++;
                continue;
            }
            if (curChar_ == ElResolver.DELIMITER_TEXT) {
                constText_ = true;
                from_++;
                continue;
            }
            if (_page.getCurrentBlock() instanceof FieldBlock
                    && parsBrackets_.isEmpty()
                    && StringExpUtil.isTypeLeafChar(curChar_)) {
                int bk_ = StringExpUtil.getBackPrintChar(_string, from_);
                if (bk_ < 0 || StringExpUtil.nextCharIs(_string, bk_, len_, ',')) {
                    int j_ = ElResolverCommon.getWord(_string, len_, from_);
                    int n_ = StringExpUtil.nextPrintChar(j_, len_, _string);
                    if (n_ < 0
                            || StringExpUtil.nextCharIs(_string, n_, len_, '=') && !StringExpUtil.nextCharIs(_string, n_ + 1, len_, '=')
                            || StringExpUtil.nextCharIs(_string, n_, len_, ',')) {
                        from_ = j_;
                        continue;
                    }
                }
            }
            int next_ = processAfterInstuctionKeyWordQuick(_string, from_,stack_, _page);
            if (next_ > from_) {
                from_ = next_;
                continue;
            }
            if (StringExpUtil.isTypeLeafChar(curChar_)) {
                next_ = processWordsQuickBegin(_string,from_,prevOp_,curChar_,stack_, _page, _pkg, _file);
                if (next_ < 0) {
                    break;
                }
                from_ = next_;
                continue;
            }
            if (prevOp_ != '.' && curChar_ == ElResolver.DOT_VAR) {
                int n_ = StringExpUtil.nextPrintChar(from_ + 1, len_, _string);
                if (!StringExpUtil.nextCharIs(_string,n_,len_, ElResolver.DOT_VAR)) {
                    if (ElResolverCommon.isDigitOrDot(_string,n_)) {
                        KeyWords keyWords_ = _page.getKeyWords();
                        NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, from_ + 1, len_, _string, true);
                        from_ = res_.getNextIndex();
                        continue;
                    }
                }
            }
            next_ = processOperatorsQuickBegin(parsBrackets_,stack_,from_,curChar_, _string, _page, _pkg, _file);
            if (next_ < 0) {
                break;
            }
            if (!StringUtil.isWhitespace(curChar_)) {
                prevOp_ = curChar_;
            }
            from_ = next_;
        }
    }

    private static int stack(String _string, int _from, AnalyzedPageEl _page, String _packageName, FileBlock _file) {
        boolean constTextString_ = false;
        boolean constTextChar_ = false;
        boolean constString_ = false;
        boolean constChar_ = false;
        boolean constText_ = false;
        StackDelimiters stack_ = new StackDelimiters();
        StackOperators parsBrackets_ = new StackOperators();
        char prevOp_ = ElResolver.SPACE;
        int from_ = _from;
        int len_ = _string.length();
        while (from_ < len_) {
            char curChar_ = _string.charAt(from_);
            if (constTextString_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_STRING
                        &&StringExpUtil.nextCharIs(_string,from_+1,len_, ElResolver.DELIMITER_STRING)
                        &&StringExpUtil.nextCharIs(_string,from_+2,len_, ElResolver.DELIMITER_STRING)) {
                    constTextString_ = false;
                    from_ += 3;
                    continue;
                }
                from_++;
                continue;
            }
            if (constString_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_STRING) {
                    constString_ = false;
                }
                from_++;
                continue;
            }
            if (constTextChar_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_CHAR
                        &&StringExpUtil.nextCharIs(_string,from_+1,len_, ElResolver.DELIMITER_CHAR)
                        &&StringExpUtil.nextCharIs(_string,from_+2,len_, ElResolver.DELIMITER_CHAR)) {
                    constTextChar_ = false;
                    from_ += 3;
                    continue;
                }
                from_++;
                continue;
            }
            if (constChar_) {
                if (curChar_ == ElResolver.ESCAPE_META_CHAR) {
                    from_++;
                    from_++;
                    continue;
                }
                if (curChar_ == ElResolver.DELIMITER_CHAR) {
                    constChar_ = false;
                }
                from_++;
                continue;
            }
            if (constText_) {
                if (curChar_ == ElResolver.DELIMITER_TEXT) {
                    if (from_ + 1 >= len_ ||_string.charAt(from_ + 1) != ElResolver.DELIMITER_TEXT) {
                        constText_ = false;
                        from_++;
                        continue;
                    }
                    from_++;
                }
                from_++;
                continue;
            }
            if (curChar_ == ElResolver.DELIMITER_STRING) {
                if (ElResolverCommon.isRepeated(_string, from_, len_, ElResolver.DELIMITER_STRING)) {
                    constTextString_ = true;
                    from_+=3;
                    continue;
                }
                constString_ = true;
                from_++;
                continue;
            }
            if (curChar_ == ElResolver.DELIMITER_CHAR) {
                if (ElResolverCommon.isRepeated(_string, from_, len_, ElResolver.DELIMITER_CHAR)) {
                    constTextChar_ = true;
                    from_+=3;
                    continue;
                }
                constChar_ = true;
                from_++;
                continue;
            }
            if (curChar_ == ElResolver.DELIMITER_TEXT) {
                constText_ = true;
                from_++;
                continue;
            }
            int next_ = processAfterInstuctionKeyWordQuick(_string, from_,stack_, _page);
            if (next_ > from_) {
                from_ = next_;
                continue;
            }
            if (StringExpUtil.isTypeLeafChar(curChar_)) {
                next_ = processWordsQuick(_string,from_,prevOp_,curChar_,stack_, _page, _packageName, _file);
                from_ = next_;
                continue;
            }
            if (prevOp_ != '.' && curChar_ == ElResolver.DOT_VAR) {
                int n_ = StringExpUtil.nextPrintChar(from_ + 1, len_, _string);
                if (!StringExpUtil.nextCharIs(_string,n_,len_, ElResolver.DOT_VAR)) {
                    if (ElResolverCommon.isDigitOrDot(_string,n_)) {
                        KeyWords keyWords_ = _page.getKeyWords();
                        NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, from_ + 1, len_, _string, true);
                        from_ = res_.getNextIndex();
                        continue;
                    }
                }
            }
            next_ = processOperatorsQuick(parsBrackets_,stack_,from_,curChar_, _string, _page, _packageName, _file);
            if (next_ < 0) {
                break;
            }
            if (!StringUtil.isWhitespace(curChar_)) {
                prevOp_ = curChar_;
            }
            from_ = next_;
        }
        return from_;
    }

    private static int processAfterInstuctionKeyWordQuick(String _string, int _i, StackDelimiters _stack, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = _i;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordBool_ = keyWords_.getKeyWordBool();
        String keyWordCast_ = keyWords_.getKeyWordCast();
        String keyWordExplicit_ = keyWords_.getKeyWordExplicit();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordFalse_ = keyWords_.getKeyWordFalse();
        String keyWordFirstopt_ = keyWords_.getKeyWordFirstopt();
        String keyWordId_ = keyWords_.getKeyWordId();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordLambda_ = keyWords_.getKeyWordLambda();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        String keyWordSwitch_ = keyWords_.getKeyWordSwitch();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordTrue_ = keyWords_.getKeyWordTrue();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        String keyWordValues_ = keyWords_.getKeyWordValues();
        String keyWordVararg_ = keyWords_.getKeyWordVararg();
        String keyWordDefault_ = keyWords_.getKeyWordDefaultValue();
        String keyWordDefaultValue_ = keyWords_.getKeyWordDefaultValue();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        //
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordInstanceof_)) {
            int next_ = i_ + keyWordInstanceof_.length();
            next_ = ElResolverCommon.incrInstanceOf(_string, len_, next_);
            i_ = next_;
            return i_;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordSwitch_)) {
            int j_ = i_+keyWordSwitch_.length();
            String afterSwitch_ = _string.substring(j_);
            Ints annotationsIndexes_ = new Ints();
            StringList annotations_ = new StringList();
            Ints annotationsIndexesParam_ = new Ints();
            StringList annotationsParam_ = new StringList();
            if (afterSwitch_.trim().startsWith("[")) {
                int k_ = afterSwitch_.indexOf('[') + 1;
                int count_ = 1;
                int lenSw_ = afterSwitch_.length();
                while (k_ < lenSw_) {
                    char ch_ = afterSwitch_.charAt(k_);
                    if (ch_ == '[') {
                        count_++;
                    }
                    if (count_ == 1 && ch_ == ':') {
                        int l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,k_+1);
                        int gl_ = _page.getLocalizer().getCurrentLocationIndex();
                        if (afterSwitch_.startsWith("@",l_)) {
                            ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(l_),j_+l_+ gl_);
                            parse_.parse();
                            annotationsIndexes_ = parse_.getAnnotationsIndexes();
                            annotations_ = parse_.getAnnotations();
                            l_ = parse_.getIndex() - j_ - gl_;
                            l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,l_);
                        }
                        if (afterSwitch_.startsWith(":",l_)) {
                            int m_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,l_+1);
                            if (afterSwitch_.startsWith("@",m_)) {
                                ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(m_),j_+m_+ gl_);
                                parse_.parse();
                                annotationsIndexesParam_ = parse_.getAnnotationsIndexes();
                                annotationsParam_ = parse_.getAnnotations();
                                m_ = parse_.getIndex() - j_ - gl_;
                                m_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,m_);
                            }
                            l_ = m_;
                        }
                        if (afterSwitch_.startsWith("]",l_)) {
                            k_ = l_;
                            break;
                        }
                    }
                    if (ch_ == ']') {
                        count_--;
                        if (count_ == 0) {
                            break;
                        }
                    }
                    k_++;
                }
                int next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_+k_+1);
                if (_string.startsWith("(",next_)) {
                    _stack.getStringsSwitch().add("");
                    _stack.getIndexesSwitch().add(next_);
                    _stack.getCallings().add(next_);
                    _stack.getAnnotationsIndexesSw().add(annotationsIndexes_);
                    _stack.getAnnotationsSw().add(annotations_);
                    _stack.getAnnotationsIndexesSwPar().add(annotationsIndexesParam_);
                    _stack.getAnnotationsSwPar().add(annotationsParam_);
                    return next_;
                }
                return j_+k_;
            }
            int next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_);
            if (_string.startsWith("(",next_)) {
                _stack.getStringsSwitch().add("");
                _stack.getIndexesSwitch().add(next_);
                _stack.getAnnotationsIndexesSw().add(annotationsIndexes_);
                _stack.getAnnotationsSw().add(annotations_);
                _stack.getAnnotationsIndexesSwPar().add(annotationsIndexesParam_);
                _stack.getAnnotationsSwPar().add(annotationsParam_);
                _stack.getCallings().add(next_);
                return next_;
            }
            return j_;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordNew_)) {
            int j_ = i_+keyWordNew_.length();
            boolean foundLeftPar_ = false;
            boolean foundLeft_ = false;
            while (j_ < len_) {
                char curLoc_ = _string.charAt(j_);
                if (!StringUtil.isWhitespace(curLoc_)) {
                    if (curLoc_ == ElResolver.ANN_ARR_LEFT) {
                        foundLeft_ = true;
                        j_++;
                    }
                    if (curLoc_ == ElResolver.PAR_LEFT || curLoc_ == ElResolver.ARR_LEFT) {
                        foundLeftPar_ = true;
                        if (curLoc_ == ElResolver.PAR_LEFT ) {
                            _stack.getCallings().add(j_);
                        }
                        j_++;
                    }
                    break;
                }
                j_++;
            }
            if (foundLeftPar_) {
                i_ = j_-1;
                _stack.getAnnotationsIndexes().add(new Ints());
                _stack.getAnnotations().add(new StringList());
                _stack.getStringsNew().add("");
                _stack.getIndexesNew().add(i_);
                return i_;
            }
            boolean found_ = false;
            while (j_ < len_) {
                char curLoc_ = _string.charAt(j_);
                if (!StringUtil.isWhitespace(curLoc_)) {
                    if (curLoc_ == ElResolver.ANN_ARR_RIGHT) {
                        j_++;
                        found_ = true;
                    }
                    break;
                }
                j_++;
            }
            if (foundLeft_ && !found_) {
                return j_;
            }
            j_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_);
            Ints annotationsIndexes_ = new Ints();
            StringList annotations_ = new StringList();
            if (_string.startsWith("@",j_)) {
                int gl_ = _page.getLocalizer().getCurrentLocationIndex();
                ParsedAnnotations parse_ = new ParsedAnnotations(_string.substring(j_),j_+ gl_);
                parse_.parse();
                annotationsIndexes_ = parse_.getAnnotationsIndexes();
                annotations_ = parse_.getAnnotations();
                j_ = parse_.getIndex()-gl_;
                j_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_);
            }
            if (StringExpUtil.startsWithKeyWord(_string,j_, keyWordInterfaces_)) {
                int k_ = _string.indexOf(ElResolver.PAR_LEFT, j_);
                if (k_ < 0) {
                    return j_;
                }
                k_ = _string.indexOf(ElResolver.PAR_RIGHT, k_);
                if (k_ < 0) {
                    return j_;
                }
                j_ = k_+1;
            }
            int from_ = j_;
            j_ = DefaultProcessKeyWord.extractType(_string,_stack,j_);
            _stack.getAnnotationsIndexes().add(annotationsIndexes_);
            _stack.getAnnotations().add(annotations_);
            _stack.getStringsNew().add(_string.substring(from_,j_));
            _stack.getIndexesNew().add(j_);
            return j_;
        }
        for (String s: StringUtil.wrapStringArray(
                keyWordClasschoice_,
                keyWordSuperaccess_,keyWordThisaccess_,keyWordInterfaces_,keyWordOperator_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
                int pr_ = StringExpUtil.nextPrintChar(i_+s.length(), len_, _string);
                if (!StringExpUtil.nextCharIs(_string,pr_,len_, ElResolver.PAR_LEFT)) {
                    return i_+s.length();
                }
                _stack.getCallings().add(pr_);
                int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT,pr_+1);
                if (indexParRight_ < 0) {
                    return i_+s.length();
                }
                int indexSecParLeft_ = _string.indexOf(ElResolver.PAR_LEFT,indexParRight_+1);
                if (indexSecParLeft_ < 0) {
                    return indexParRight_+1;
                }
                _stack.getCallings().add(indexSecParLeft_);
                return indexSecParLeft_;
            }
        }
        for (String s: StringUtil.wrapStringArray(keyWordCast_,keyWordExplicit_,
                keyWordVararg_,keyWordDefaultValue_,keyWordClass_,keyWordLambda_,
                keyWordId_,keyWordStatic_,keyWordStaticCall_,
                keyWordValues_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
                int pr_ = StringExpUtil.nextPrintChar(i_+s.length(), len_, _string);
                if (!StringExpUtil.nextCharIs(_string,pr_,len_, ElResolver.PAR_LEFT)) {
                    return i_+s.length();
                }
                _stack.getCallings().add(pr_);
                int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT,pr_+1);
                if (indexParRight_ < 0) {
                    return i_;
                }
                return indexParRight_+1;
            }
        }
        for (String s: StringUtil.wrapStringArray(keyWordBool_,keyWordFalse_,keyWordFirstopt_,
                keyWordSuper_,keyWordThis_,keyWordValueOf_,keyWordThat_,keyWordTrue_,keyWordDefault_)) {
            if (StringExpUtil.startsWithKeyWord(_string,i_, s)) {
                int pr_ = StringExpUtil.nextPrintChar(i_+s.length(), len_, _string);
                if (!StringExpUtil.nextCharIs(_string,pr_,len_, ElResolver.PAR_LEFT)) {
                    return i_+s.length();
                }
                _stack.getCallings().add(pr_);
                i_ = pr_;
                break;
            }
        }
        return i_;
    }

    private static int indexAfterPossibleCastQuick(String _string, int _from, Ints _callings) {
        int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT, _from +1);
        if (indexParRight_ < 0) {
            return _from;
        }
        if (indexParRight_ + 1 >= _string.length()) {
            return _from;
        }
        if (_callings.containsObj(_from)) {
            return _from;
        }

        String sub_ = _string.substring(_from + 1, indexParRight_);
        String subTrim_ = sub_.trim();
        int next_ = StringExpUtil.nextPrintChar(indexParRight_+1,_string.length(),_string);
        for (String s: StringUtil.wrapStringArray("+=","-=",
                "*=","/=","%=",
                "^=","&=","|=",
                "||","&&","?",":",
                "<",">",",","->",
                "!=","=",")","]","}")) {
            if (_string.startsWith(s,next_)) {
                return _from;
            }
        }
        if (_string.startsWith(".",next_)) {
            int n_ = StringExpUtil.nextPrintChar(next_ + 1, _string.length(), _string);
            if (!ElResolverCommon.isDigitOrDot(_string,n_)) {
                return _from;
            }
        }
        if (AnaPartTypeUtil.isCorrectType(subTrim_,new StringList())) {
            return indexParRight_ + 1;
        }
        return _from;
    }

    private static int processWordsQuick(String _string, int _i, char _prevOp, char _curChar, StackDelimiters _stack, AnalyzedPageEl _page, String _packageName, FileBlock _file) {
        int len_ = _string.length();
        int i_ = _i;
        KeyWords keyWords_ = _page.getKeyWords();
        if (_prevOp != '.' && StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, i_, len_, _string, false);
            return res_.getNextIndex();
        }
        int beginWord_ = i_;
        while (i_ < len_) {
            char locChar_ = _string.charAt(i_);
            if (!StringExpUtil.isTypeLeafChar(locChar_)) {
                break;
            }
            i_++;
        }
        int nextPar_ = StringExpUtil.nextPrintCharIs(i_, len_, _string, ElResolver.PAR_LEFT);
        if (nextPar_ > -1) {
            _stack.getCallings().add(nextPar_);
            return i_;
        }
        int bk_ = StringExpUtil.getBackPrintChar(_string, beginWord_);
        if (StringExpUtil.nextCharIs(_string,bk_,len_,'.')) {
            return i_;
        }
        int n_ = ElResolverCommon.addNamed(_string, beginWord_, i_, _stack.getNamedArgs(), _page);
        if (n_ >= i_) {
            return n_;
        }
        int dash_ = StringExpUtil.nextPrintCharIs(i_, len_, _string, '-');
        if (dash_ > -1 && StringExpUtil.nextCharIs(_string,dash_+1, len_,'>')) {
            String afterArrow_ = _string.substring(dash_+"->".length());
            String after_ = afterArrow_.trim();
            int off_ = StringUtil.getFirstPrintableCharIndex(afterArrow_);
            if (after_.startsWith("{")) {
                int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                int j_ = dash_ + off_+2;
                InputTypeCreation input_ = new InputTypeCreation();
                input_.setType(OuterBlockEnum.ANON_FCT);
                input_.setFile(_file);
                input_.setNextIndex(j_);
                input_.setNextIndexBef(dash_);
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                int k_ = res_.getNextIndex() - 1;
                return k_+1;
            }
        }
        return i_;
    }

    private static int processWordsQuickBegin(String _string, int _i, char _prevOp, char _curChar, StackDelimiters _stack, AnalyzedPageEl _page, String _packageName, FileBlock _file) {
        int len_ = _string.length();
        int i_ = _i;
        KeyWords keyWords_ = _page.getKeyWords();
        if (_prevOp != '.' && StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, i_, len_, _string, false);
            return res_.getNextIndex();
        }
        int beginWord_ = i_;
        while (i_ < len_) {
            char locChar_ = _string.charAt(i_);
            if (!StringExpUtil.isTypeLeafChar(locChar_)) {
                break;
            }
            i_++;
        }
        String word_ = _string.substring(beginWord_, i_);
        int nextPar_ = StringExpUtil.nextPrintCharIs(i_, len_, _string, ElResolver.PAR_LEFT);
        if (nextPar_ > -1) {
            _stack.getCallings().add(nextPar_);
            return i_;
        }
        int bk_ = StringExpUtil.getBackPrintChar(_string, beginWord_);
        if (StringExpUtil.nextCharIs(_string,bk_,len_,'.')) {
            return i_;
        }
        int n_ = ElResolverCommon.addNamed(_string, beginWord_, i_, _stack.getNamedArgs(), _page);
        if (n_ >= i_) {
            return n_;
        }
        int dash_ = StringExpUtil.nextPrintCharIs(i_, len_, _string, '-');
        if (dash_ > -1 && StringExpUtil.nextCharIs(_string,dash_+1, len_,'>')) {
            String afterArrow_ = _string.substring(dash_+"->".length());
            String after_ = afterArrow_.trim();
            int off_ = StringUtil.getFirstPrintableCharIndex(afterArrow_);
            int indAfterArrow_ = dash_ + off_ + 2;
            if (after_.startsWith("{")) {
                int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                ParsedFctHeader parse_ = new ParsedFctHeader();
                parse_.getOffestsParams().add(beginWord_+instrLoc_);
                parse_.getOffestsTypes().add(beginWord_+instrLoc_);
                parse_.getParametersName().add(word_);
                parse_.getParametersType().add("");
                parse_.getParametersRef().add(false);
                parse_.getAnnotationsParams().add(new StringList());
                parse_.getAnnotationsIndexesParams().add(new Ints());
                InputTypeCreation input_ = new InputTypeCreation();
                input_.setType(OuterBlockEnum.ANON_FCT);
                input_.setFile(_file);
                input_.setNextIndex(indAfterArrow_);
                input_.setNextIndexBef(dash_);
                input_.setAnnotations(parse_.getAnnotations());
                input_.setAnnotationsIndexes(parse_.getAnnotationsIndexes());
                input_.setAnnotationsParams(parse_.getAnnotationsParams());
                input_.setAnnotationsIndexesParams(parse_.getAnnotationsIndexesParams());
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    int k_ = res_.getNextIndex() - 1;
                    AnonymousResult anonymous_ = new AnonymousResult();
                    anonymous_.setResults(parse_);
                    anonymous_.setIndex(beginWord_);
                    anonymous_.setUntil(k_);
                    anonymous_.setLength(k_- dash_ +1);
                    anonymous_.setType(res_.getBlock());
                    anonymous_.setNext(k_ + 1);
                    _page.getAnonymousResults().add(anonymous_);
                    return k_ + 1;
                }
                return -1;
            }
            ParsedFctHeader parse_ = new ParsedFctHeader();
            int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
            parse_.getOffestsParams().add(beginWord_+instrLoc_);
            parse_.getOffestsTypes().add(beginWord_+instrLoc_);
            parse_.getParametersName().add(word_);
            parse_.getParametersType().add("");
            parse_.getParametersRef().add(false);
            parse_.getAnnotationsParams().add(new StringList());
            parse_.getAnnotationsIndexesParams().add(new Ints());
            int k_ = stack(_string, indAfterArrow_, _page, _packageName, _file);
            String part_ = _string.substring(indAfterArrow_,k_);
            int begAnon_ = dash_ + instrLoc_;
            int begImplRet_ = indAfterArrow_ + instrLoc_;
            NamedCalledFunctionBlock block_ = new NamedCalledFunctionBlock(begAnon_, begImplRet_, _page);
            block_.getAnnotations().addAllElts(parse_.getAnnotations());
            block_.getAnnotationsIndexes().addAllElts(parse_.getAnnotationsIndexes());
            block_.getAnnotationsParams().addAllElts(parse_.getAnnotationsParams());
            block_.getAnnotationsIndexesParams().addAllElts(parse_.getAnnotationsIndexesParams());
            block_.setBegin(begImplRet_);
            block_.setLengthHeader(1);
            block_.setFile(_file);
            String tr_ = part_.trim();
            ReturnMethod ret_ = new ReturnMethod(new OffsetStringInfo(begImplRet_, tr_), begImplRet_);
            ret_.setImplicit(true);
            ret_.setBegin(begAnon_);
            ret_.setLengthHeader(2);
            block_.appendChild(ret_);
            AnonymousResult anonymous_ = new AnonymousResult();
            anonymous_.setResults(parse_);
            anonymous_.setIndex(beginWord_);
            int withoutWhiteBoundsCount_ = part_.length() - tr_.length();
            anonymous_.setUntil(k_- withoutWhiteBoundsCount_ -1);
            anonymous_.setLength(k_- withoutWhiteBoundsCount_ - dash_);
            anonymous_.setType(block_);
            anonymous_.setNext(k_);
            _page.getAnonymousResults().add(anonymous_);
            return k_;
        }
        return i_;
    }

    private static int processOperatorsQuick(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string,
                                             AnalyzedPageEl _page, String _packageName, FileBlock _file) {
        StackOperators parsBrackets_;
        parsBrackets_ = _parsBrackets;

        int len_ = _string.length();
        int i_ = _i;
        KeyWords keyWords_ = _page.getKeyWords();
        if (_curChar == ElResolver.PAR_LEFT) {
            if (!_stack.getCallings().containsObj(i_)) {
                ParsedFctHeader parse_ = new ParsedFctHeader();
                int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                parse_.parseAnonymous(i_,_string,instrLoc_,keyWords_.getKeyWordThat());
                int rightPar_ = parse_.getNextIndex();
                if (rightPar_ > i_) {
                    String info_ = _string.substring(rightPar_+1);
                    int off_ = StringUtil.getFirstPrintableCharIndex(info_);
                    String afterArrow_ = parse_.getAfterArrow();
                    String after_ = afterArrow_.trim();
                    int deltaArr_ = off_;
                    off_ += StringUtil.getFirstPrintableCharIndex(afterArrow_);
                    if (after_.startsWith("{")) {
                        int j_ = rightPar_+1+off_+2;
                        int jBef_ = rightPar_+1+deltaArr_;
                        InputTypeCreation input_ = new InputTypeCreation();
                        input_.setType(OuterBlockEnum.ANON_FCT);
                        input_.setFile(_file);
                        input_.setNextIndex(j_);
                        input_.setNextIndexBef(jBef_);
                        input_.setAnnotations(parse_.getAnnotations());
                        input_.setAnnotationsIndexes(parse_.getAnnotationsIndexes());
                        input_.setAnnotationsParams(parse_.getAnnotationsParams());
                        input_.setAnnotationsIndexesParams(parse_.getAnnotationsIndexesParams());
                        ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                        int k_ = res_.getNextIndex() - 1;
                        return k_+1;
                    }
                    return rightPar_+1;
                }
            }

            int j_ = indexAfterPossibleCastQuick(_string,i_, _stack.getCallings());
            if (j_ > i_) {
                return j_;
            }
            _stack.getCallings().add(i_);
            parsBrackets_.addEntry(i_, _curChar);
        }
        if (_curChar == ElResolver.PAR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            ElResolverCommon.tryAddStringParts(parsBrackets_, i_, _stack);
            ElResolverCommon.tryAddAnnotationsParts(parsBrackets_, _stack);
            parsBrackets_.removeLast();
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            int bk_ = StringExpUtil.getBackPrintChar(_string, i_);
            if (StringExpUtil.nextCharIs(_string,bk_,len_, ElResolver.PAR_RIGHT)) {
                int indexLast_ = _stack.getIndexesNewEnd().indexOf(bk_);
                if (indexLast_ > -1) {
                    String beforeCall_ = _stack.getStringsNewEnd().get(indexLast_);
                    int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.ANON_TYPE);
                    input_.setFile(_file);
                    input_.setNextIndex(i_);
                    input_.setAnnotations(_stack.getAnnotationsEnd().get(indexLast_));
                    input_.setAnnotationsIndexes(_stack.getAnnotationsIndexesEnd().get(indexLast_));
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                    int j_ = res_.getNextIndex() - 1;
                    return j_+1;
                }
                int indexLastSw_ = _stack.getIndexesSwitchEnd().indexOf(bk_);
                if (indexLastSw_ > -1) {
                    String beforeCall_ = _stack.getStringsSwitchEnd().get(indexLastSw_);
                    int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.SWITCH_METHOD);
                    input_.setFile(_file);
                    input_.setNextIndex(i_);
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    input_.setAnnotations(_stack.getAnnotationsEndSw().get(indexLastSw_));
                    input_.setAnnotationsIndexes(_stack.getAnnotationsIndexesEndSw().get(indexLastSw_));
                    input_.setAnnotationsParams(new CustList<StringList>(_stack.getAnnotationsEndSwPar().get(indexLastSw_)));
                    input_.setAnnotationsIndexesParams(new CustList<Ints>(_stack.getAnnotationsIndexesEndSwPar().get(indexLastSw_)));
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                    int j_ = res_.getNextIndex() - 1;
                    return j_+1;
                }
            }
            parsBrackets_.addEntry(i_, _curChar);
        }
        if (_curChar == ElResolver.ANN_ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
        }
        if (_curChar == ElResolver.ARR_LEFT) {
            int j_ = i_ + 1;
            SkipArPart s_ = new SkipArPart();
            s_.skip(j_,_string);
            j_ = s_.getIndex();
            boolean skip_ = s_.isSkip();
            if (skip_) {
                return j_ + 1;
            }
            parsBrackets_.addEntry(i_, _curChar);
        }
        if (_curChar == ElResolver.ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
        }
        if (_curChar == ElResolver.BEGIN_TERNARY) {
            boolean ternary_ = false;
            if (StringExpUtil.nextCharIs(_string, i_ + 1, len_, ElResolver.DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(i_ + 2, len_, _string);
                if (ElResolverCommon.isDigitOrDot(_string,n_)) {
                    ternary_ = true;
                }
            } else {
                if (!StringExpUtil.nextCharIs(_string, i_ + 1, len_, ElResolver.BEGIN_TERNARY)
                        &&!StringExpUtil.nextCharIs(_string, i_ + 1, len_, ElResolver.ARR_LEFT)) {
                    ternary_ = true;
                }
            }
            if (ternary_) {
                parsBrackets_.addEntry(i_, _curChar);
            }
        }
        if (_curChar == ElResolver.END_TERNARY) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
        }
        if (_curChar == ElResolver.SEP_ARG && parsBrackets_.isEmpty()) {
            return -1;
        }
        boolean escapeOpers_ = false;
        boolean andOr_ = false;
        boolean nullSafe_ = false;
        boolean ltGt_ = false;
        if (_curChar == ElResolver.MULT_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.MOD_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.DIV_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.PLUS_CHAR){
            if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != ElResolver.PLUS_CHAR) {
                escapeOpers_ = true;
            }
        }
        if (_curChar == ElResolver.MINUS_CHAR){
            if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != ElResolver.MINUS_CHAR) {
                escapeOpers_ = true;
            }
        }
        if (_curChar == ElResolver.AND_CHAR) {
            andOr_ = true;
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.OR_CHAR) {
            andOr_ = true;
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.LOWER_CHAR) {
            escapeOpers_ = true;
            ltGt_ = true;
        }
        if (_curChar == ElResolver.XOR_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.BEGIN_TERNARY) {
            escapeOpers_ = true;
            nullSafe_ = true;
        }
        if (_curChar == ElResolver.END_TERNARY) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.GREATER_CHAR) {
            escapeOpers_ = true;
            ltGt_ = true;
        }
        if (_curChar == ElResolver.EQ_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.NEG_BOOL_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.ARR_LEFT) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.PAR_LEFT) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.SEP_ARG) {
            escapeOpers_ = true;
        }
        if (escapeOpers_) {
            int j_ = i_ + 1;
            if (ltGt_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (ltGt_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (ltGt_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (andOr_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (andOr_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                if (j_+1 < len_ && _string.charAt(j_+1) == ElResolver.EQ_CHAR) {
                    j_++;
                }
            }
            if (nullSafe_ && StringExpUtil.nextCharIs(_string, j_, len_, ElResolver.DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(j_ + 1, len_, _string);
                if (!ElResolverCommon.isDigitOrDot(_string,n_)) {
                    j_++;
                }
            }
            if (nullSafe_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (nullSafe_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (j_ < len_ && _string.charAt(j_) == ElResolver.EQ_CHAR) {
                j_++;
            }
            return j_;
        }
        if (_curChar == ElResolver.PLUS_CHAR){
            i_++;
            i_++;
            return i_;
        }
        if (_curChar == ElResolver.MINUS_CHAR){
            i_++;
            i_++;
            return i_;
        }
        i_++;
        return i_;
    }

    private static int processOperatorsQuickBegin(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string,
                                                  AnalyzedPageEl _page, String _packageName, FileBlock _file) {
        StackOperators parsBrackets_;
        parsBrackets_ = _parsBrackets;
        KeyWords keyWords_ = _page.getKeyWords();

        int len_ = _string.length();
        int i_ = _i;
        if (_curChar == ElResolver.PAR_LEFT) {
            if (!_stack.getCallings().containsObj(i_)) {
                ParsedFctHeader parse_ = new ParsedFctHeader();
                int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                parse_.parseAnonymous(i_,_string,instrLoc_,keyWords_.getKeyWordThat());
                int rightPar_ = parse_.getNextIndex();
                if (rightPar_ > i_) {
                    String info_ = _string.substring(rightPar_+1);
                    int off_ = StringUtil.getFirstPrintableCharIndex(info_);
                    String afterArrow_ = parse_.getAfterArrow();
                    String after_ = afterArrow_.trim();
                    int deltaArr_ = off_;
                    off_ += StringUtil.getFirstPrintableCharIndex(afterArrow_);
                    int indAfterArrow_ = rightPar_+1 + off_ + 2;
                    int indBeforeArrow_ = rightPar_+1+ deltaArr_;
                    if (after_.startsWith("{")) {
                        InputTypeCreation input_ = new InputTypeCreation();
                        input_.setType(OuterBlockEnum.ANON_FCT);
                        input_.setFile(_file);
                        input_.setNextIndex(indAfterArrow_);
                        input_.setNextIndexBef(indBeforeArrow_);
                        input_.setAnnotations(parse_.getAnnotations());
                        input_.setAnnotationsIndexes(parse_.getAnnotationsIndexes());
                        input_.setAnnotationsParams(parse_.getAnnotationsParams());
                        input_.setAnnotationsIndexesParams(parse_.getAnnotationsIndexesParams());
                        ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                        if (res_.isOkType()) {
                            int k_ = res_.getNextIndex() - 1;
                            AnonymousResult anonymous_ = new AnonymousResult();
                            anonymous_.setResults(parse_);
                            anonymous_.setIndex(i_);
                            anonymous_.setUntil(k_);
                            anonymous_.setLength(k_- indBeforeArrow_ +1);
                            anonymous_.setType(res_.getBlock());
                            anonymous_.setNext(k_ + 1);
                            _page.getAnonymousResults().add(anonymous_);
                            return k_ + 1;
                        }
                        return -1;
                    }
                    int k_ = stack(_string, indAfterArrow_, _page, _packageName, _file);
                    String part_ = _string.substring(indAfterArrow_,k_);
                    NamedCalledFunctionBlock block_ = new NamedCalledFunctionBlock(indBeforeArrow_ +instrLoc_, indAfterArrow_ +instrLoc_, _page);
                    block_.getAnnotations().addAllElts(parse_.getAnnotations());
                    block_.getAnnotationsIndexes().addAllElts(parse_.getAnnotationsIndexes());
                    block_.getAnnotationsParams().addAllElts(parse_.getAnnotationsParams());
                    block_.getAnnotationsIndexesParams().addAllElts(parse_.getAnnotationsIndexesParams());
                    block_.setBegin(indAfterArrow_ +instrLoc_);
                    block_.setLengthHeader(1);
                    block_.setFile(_file);
                    String trim_ = part_.trim();
                    ReturnMethod ret_ = new ReturnMethod(new OffsetStringInfo(indAfterArrow_ +instrLoc_, trim_), indAfterArrow_ +instrLoc_);
                    ret_.setImplicit(true);
                    ret_.setBegin(indBeforeArrow_ +instrLoc_);
                    ret_.setLengthHeader(2);
                    block_.appendChild(ret_);
                    AnonymousResult anonymous_ = new AnonymousResult();
                    anonymous_.setResults(parse_);
                    anonymous_.setIndex(i_);
                    int withoutWhiteBoundsCount_ = part_.length() - trim_.length();
                    anonymous_.setUntil(k_- withoutWhiteBoundsCount_ -1);
                    anonymous_.setLength(k_- withoutWhiteBoundsCount_ - indBeforeArrow_);
                    anonymous_.setType(block_);
                    anonymous_.setNext(k_);
                    _page.getAnonymousResults().add(anonymous_);
                    return k_;
                }
            }

            int j_ = indexAfterPossibleCastQuick(_string,i_, _stack.getCallings());
            if (j_ > i_) {
                return j_;
            }
            _stack.getCallings().add(i_);
            parsBrackets_.addEntry(i_, _curChar);
        }
        if (_curChar == ElResolver.PAR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            ElResolverCommon.tryAddStringParts(parsBrackets_, i_, _stack);
            ElResolverCommon.tryAddAnnotationsParts(parsBrackets_, _stack);
            parsBrackets_.removeLast();
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            int bk_ = StringExpUtil.getBackPrintChar(_string, i_);
            if (StringExpUtil.nextCharIs(_string,bk_,len_, ElResolver.PAR_RIGHT)) {
                int indexLast_ = _stack.getIndexesNewEnd().indexOf(bk_);
                if (indexLast_ > -1) {
                    String beforeCall_ = _stack.getStringsNewEnd().get(indexLast_);
                    int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.ANON_TYPE);
                    input_.setFile(_file);
                    input_.setNextIndex(i_);
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    input_.setAnnotations(_stack.getAnnotationsEnd().get(indexLast_));
                    input_.setAnnotationsIndexes(_stack.getAnnotationsIndexesEnd().get(indexLast_));
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        int j_ = res_.getNextIndex() - 1;
                        AnonymousResult anonymous_ = new AnonymousResult();
                        anonymous_.setIndex(i_);
                        anonymous_.setUntil(j_);
                        anonymous_.setLength(j_-i_+1);
                        anonymous_.setType(res_.getBlock());
                        anonymous_.setNext(j_ + 1);
                        _page.getAnonymousResults().add(anonymous_);
                        return j_ + 1;
                    }
                }
                int indexLastSw_ = _stack.getIndexesSwitchEnd().indexOf(bk_);
                if (indexLastSw_ > -1) {
                    String beforeCall_ = _stack.getStringsSwitchEnd().get(indexLastSw_);
                    int instrLoc_ = _page.getLocalizer().getCurrentLocationIndex();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.SWITCH_METHOD);
                    input_.setFile(_file);
                    input_.setNextIndex(i_);
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    input_.setAnnotations(_stack.getAnnotationsEndSw().get(indexLastSw_));
                    input_.setAnnotationsIndexes(_stack.getAnnotationsIndexesEndSw().get(indexLastSw_));
                    input_.setAnnotationsParams(new CustList<StringList>(_stack.getAnnotationsEndSwPar().get(indexLastSw_)));
                    input_.setAnnotationsIndexesParams(new CustList<Ints>(_stack.getAnnotationsIndexesEndSwPar().get(indexLastSw_)));
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _packageName, instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        int j_ = res_.getNextIndex() - 1;
                        AnonymousResult anonymous_ = new AnonymousResult();
                        anonymous_.setIndex(i_);
                        anonymous_.setUntil(j_);
                        anonymous_.setLength(j_-i_+1);
                        anonymous_.setType(res_.getBlock());
                        anonymous_.setNext(j_ + 1);
                        _page.getAnonymousResults().add(anonymous_);
                        return j_ + 1;
                    }
                }
            }
            parsBrackets_.addEntry(i_, _curChar);
        }
        if (_curChar == ElResolver.ANN_ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
        }
        if (_curChar == ElResolver.ARR_LEFT) {
            int j_ = i_ + 1;
            SkipArPart s_ = new SkipArPart();
            s_.skip(j_,_string);
            j_ = s_.getIndex();
            boolean skip_ = s_.isSkip();
            if (skip_) {
                return j_ + 1;
            }
            parsBrackets_.addEntry(i_, _curChar);
        }
        if (_curChar == ElResolver.ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
        }
        if (_curChar == ElResolver.BEGIN_TERNARY) {
            boolean ternary_ = false;
            if (StringExpUtil.nextCharIs(_string, i_ + 1, len_, ElResolver.DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(i_ + 2, len_, _string);
                if (ElResolverCommon.isDigitOrDot(_string,n_)) {
                    ternary_ = true;
                }
            } else {
                if (!StringExpUtil.nextCharIs(_string, i_ + 1, len_, ElResolver.BEGIN_TERNARY)
                        &&!StringExpUtil.nextCharIs(_string, i_ + 1, len_, ElResolver.ARR_LEFT)) {
                    ternary_ = true;
                }
            }
            if (ternary_) {
                parsBrackets_.addEntry(i_, _curChar);
            }
        }
        if (_curChar == ElResolver.END_TERNARY) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
        }
        boolean escapeOpers_ = false;
        boolean andOr_ = false;
        boolean nullSafe_ = false;
        boolean ltGt_ = false;
        if (_curChar == ElResolver.MULT_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.MOD_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.DIV_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.PLUS_CHAR){
            if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != ElResolver.PLUS_CHAR) {
                escapeOpers_ = true;
            }
        }
        if (_curChar == ElResolver.MINUS_CHAR){
            if (i_ + 1 >= len_ || _string.charAt(i_ + 1) != ElResolver.MINUS_CHAR) {
                escapeOpers_ = true;
            }
        }
        if (_curChar == ElResolver.AND_CHAR) {
            andOr_ = true;
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.OR_CHAR) {
            andOr_ = true;
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.LOWER_CHAR) {
            escapeOpers_ = true;
            ltGt_ = true;
        }
        if (_curChar == ElResolver.XOR_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.BEGIN_TERNARY) {
            escapeOpers_ = true;
            nullSafe_ = true;
        }
        if (_curChar == ElResolver.END_TERNARY) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.GREATER_CHAR) {
            escapeOpers_ = true;
            ltGt_ = true;
        }
        if (_curChar == ElResolver.EQ_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.NEG_BOOL_CHAR) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.ARR_LEFT) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.PAR_LEFT) {
            escapeOpers_ = true;
        }
        if (_curChar == ElResolver.SEP_ARG) {
            escapeOpers_ = true;
        }
        if (escapeOpers_) {
            int j_ = i_ + 1;
            if (ltGt_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (ltGt_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (ltGt_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (andOr_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (andOr_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                if (j_+1 < len_ && _string.charAt(j_+1) == ElResolver.EQ_CHAR) {
                    j_++;
                }
            }
            if (nullSafe_ && StringExpUtil.nextCharIs(_string, j_, len_, ElResolver.DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(j_ + 1, len_, _string);
                if (!ElResolverCommon.isDigitOrDot(_string,n_)) {
                    j_++;
                }
            }
            if (nullSafe_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (nullSafe_ && j_ < len_ && _string.charAt(j_) == _curChar) {
                j_++;
            }
            if (j_ < len_ && _string.charAt(j_) == ElResolver.EQ_CHAR) {
                j_++;
            }
            return j_;
        }
        if (_curChar == ElResolver.PLUS_CHAR){
            i_++;
            i_++;
            return i_;
        }
        if (_curChar == ElResolver.MINUS_CHAR){
            i_++;
            i_++;
            return i_;
        }
        i_++;
        return i_;
    }
}
