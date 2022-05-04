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
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class ElRetrieverAnonymous {
    private ElRetrieverAnonymous() {
    }

    public static int commonCheckQuick(String _string, int _minIndex, AnalyzedPageEl _page, ResultExpression _res) {
        _page.getAnonymousResults().clear();
        _page.getParts().clear();
        _page.getAnnotDelNew().clear();
        _page.getAnnotDelSwitch().clear();
        String currentPkg_ = _page.getCurrentPkg();
        FileBlock currentFile_ = _page.getCurrentFile();
        int next_ = stackBegin(_string, _minIndex, _page, new CurrentExpElts(currentPkg_, currentFile_, _page.getIndex(), _res.getPartsAbs()));
        _res.setAnonymousResults(new CustList<AnonymousResult>(_page.getAnonymousResults()));
        _res.setParts(new CustList<SegmentStringPart>(_page.getParts()));
        _res.setAnnotDelNew(new Ints(_page.getAnnotDelNew()));
        _res.setAnnotDelSwitch(new Ints(_page.getAnnotDelSwitch()));
        return next_;
    }

    private static int stackBegin(String _string, int _from, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        StackDelimiters stack_ = new StackDelimiters();
        StackOperators parsBrackets_ = new StackOperators();
        char prevOp_ = ElResolver.SPACE;
        int from_ = _from;
        int len_ = _string.length();
        while (from_ < len_) {
            char curChar_ = _string.charAt(from_);
            int until_ = from_;
            for (SegmentStringPart s: _curElts.getStringParts()) {
                if (s.getBegin() == _curElts.getInstrLoc() + from_) {
                    until_ = s.getEnd() - _curElts.getInstrLoc();
                    _page.getParts().add(new SegmentStringPart(from_,until_,s.getStrType()));
                    break;
                }
            }
            if (until_ > from_) {
                from_ = until_;
                continue;
            }
            if (_page.getCurrentBlock() instanceof FieldBlock
                    && parsBrackets_.isEmptyStackSymChars()
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
            int next_ = processAfterInstuctionKeyWordQuick(_string, from_,stack_, _page, _curElts);
            if (next_ > from_) {
                from_ = next_;
                continue;
            }
            if (StringExpUtil.isTypeLeafChar(curChar_)) {
                next_ = processWordsQuickBegin(_string,from_,prevOp_,curChar_,stack_, _page, _curElts);
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
                        NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, from_ + 1, _string, true);
                        from_ = res_.getNextIndex();
                        continue;
                    }
                }
            }
            next_ = processOperatorsQuickBegin(parsBrackets_,stack_,from_,curChar_, _string, _page, _curElts);
            if (next_ < 0) {
                break;
            }
            if (!StringUtil.isWhitespace(curChar_)) {
                prevOp_ = curChar_;
            }
            from_ = next_;
        }
        _page.getAnnotDelNew().addAllElts(stack_.getAnnotDelNew());
        _page.getAnnotDelSwitch().addAllElts(stack_.getAnnotDelSwitch());
        return from_;
    }

    private static int stack(String _string, int _from, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        StackDelimiters stack_ = new StackDelimiters();
        StackOperators parsBrackets_ = new StackOperators();
        char prevOp_ = ElResolver.SPACE;
        int from_ = _from;
        int len_ = _string.length();
        while (from_ < len_) {
            char curChar_ = _string.charAt(from_);

            int until_ = from_;
            for (SegmentStringPart s: _curElts.getStringParts()) {
                if (s.getBegin() == _curElts.getInstrLoc() + from_) {
                    until_ = s.getEnd() - _curElts.getInstrLoc();
                    break;
                }
            }
            if (until_ > from_) {
                from_ = until_;
                continue;
            }
            int next_ = processAfterInstuctionKeyWordQuick(_string, from_,stack_, _page, _curElts);
            if (next_ > from_) {
                from_ = next_;
                continue;
            }
            if (StringExpUtil.isTypeLeafChar(curChar_)) {
                next_ = processWordsQuick(_string,from_,prevOp_,curChar_,stack_, _page, _curElts);
                from_ = next_;
                continue;
            }
            if (prevOp_ != '.' && curChar_ == ElResolver.DOT_VAR) {
                int n_ = StringExpUtil.nextPrintChar(from_ + 1, len_, _string);
                if (!StringExpUtil.nextCharIs(_string,n_,len_, ElResolver.DOT_VAR)) {
                    if (ElResolverCommon.isDigitOrDot(_string,n_)) {
                        KeyWords keyWords_ = _page.getKeyWords();
                        NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, from_ + 1, _string, true);
                        from_ = res_.getNextIndex();
                        continue;
                    }
                }
            }
            next_ = processOperatorsQuick(parsBrackets_,stack_,from_,curChar_, _string, _page, _curElts);
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

    private static int processAfterInstuctionKeyWordQuick(String _string, int _i, StackDelimiters _stack, AnalyzedPageEl _page, CurrentExpElts _curElts) {
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
            ResultParsedAnnots annotations_ = new ResultParsedAnnots();
            ResultParsedAnnots annotationsParam_ = new ResultParsedAnnots();
            String retSwitch_ = "";
            if (afterSwitch_.trim().startsWith("[")) {
                int start_ = afterSwitch_.indexOf('[') + 1;
                int k_ = start_;
                _stack.getAnnotDelSwitch().add(k_);
                int count_ = 1;
                int lenSw_ = afterSwitch_.length();
                boolean already_ = false;
                while (k_ < lenSw_) {
                    char ch_ = afterSwitch_.charAt(k_);
                    if (ch_ == '[') {
                        count_++;
                    }
                    if (!already_&&count_ == 1 && ch_ == ':') {
                        int l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,k_+1);
                        if (afterSwitch_.startsWith("@",l_)) {
                            ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(l_),j_+l_+ _curElts.getInstrLoc());
                            parse_.parse(_curElts.getStringParts());
                            annotations_.set(parse_);
                            l_ = parse_.getIndex() - j_ - _curElts.getInstrLoc();
                            l_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,l_);
                        }
                        if (afterSwitch_.startsWith(":",l_)) {
                            int m_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,l_+1);
                            if (afterSwitch_.startsWith("@",m_)) {
                                ParsedAnnotations parse_ = new ParsedAnnotations(afterSwitch_.substring(m_),j_+m_+ _curElts.getInstrLoc());
                                parse_.parse(_curElts.getStringParts());
                                annotationsParam_.set(parse_);
                                m_ = parse_.getIndex() - j_ - _curElts.getInstrLoc();
                                m_ = DefaultProcessKeyWord.skipWhiteSpace(afterSwitch_,m_);
                            }
                            l_ = m_;
                        }
                        if (afterSwitch_.startsWith("]",l_)) {
                            retSwitch_ = afterSwitch_.substring(start_,k_);
                            k_ = l_;
                            break;
                        }
                        already_ = true;
                    }
                    if (ch_ == ']') {
                        count_--;
                        if (count_ == 0) {
                            retSwitch_ = afterSwitch_.substring(start_,k_);
                            break;
                        }
                    }
                    k_++;
                }
                _stack.getAnnotDelSwitch().add(k_);
                int next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_+k_+1);
                if (_string.startsWith("(",next_)) {
                    _stack.getStringsSwitch().add("");
                    _stack.getIndexesSwitch().add(next_);
                    _stack.getCallings().add(next_);
                    _stack.getAnnotationsSw().add(annotations_);
                    _stack.getAnnotationsSwPar().add(annotationsParam_);
                    _stack.getRetSwitch().add(retSwitch_);
                    return next_;
                }
                return j_+k_;
            }
            int next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_);
            if (_string.startsWith("(",next_)) {
                _stack.getStringsSwitch().add("");
                _stack.getIndexesSwitch().add(next_);
                _stack.getAnnotationsSw().add(annotations_);
                _stack.getAnnotationsSwPar().add(annotationsParam_);
                _stack.getCallings().add(next_);
                _stack.getRetSwitch().add(retSwitch_);
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
                _stack.getAnnotations().add(new ResultParsedAnnots());
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
            ResultParsedAnnots res_ = new ResultParsedAnnots();
            _stack.getAnnotDelNew().add(j_);
            if (_string.startsWith("@",j_)) {
                ParsedAnnotations parse_ = new ParsedAnnotations(_string.substring(j_),j_+ _curElts.getInstrLoc());
                parse_.parse(_curElts.getStringParts());
                res_.set(parse_);
                j_ = parse_.getIndex()- _curElts.getInstrLoc();
                j_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_);
            }
            _stack.getAnnotDelNew().add(j_);
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
            _stack.getAnnotations().add(res_);
            _stack.getStringsNew().add(_string.substring(from_,j_));
            _stack.getIndexesNew().add(j_);
            return j_;
        }
        if (StringExpUtil.startsWithKeyWord(_string,i_, keyWordOperator_)) {
            int pr_ = StringExpUtil.nextPrintChar(i_+keyWordOperator_.length(), len_, _string);
            if (!StringExpUtil.nextCharIs(_string,pr_,len_, ElResolver.PAR_LEFT)) {
                return i_+keyWordOperator_.length();
            }
            _stack.getCallings().add(pr_);
            int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT,pr_+1);
            if (indexParRight_ < 0) {
                return i_+keyWordOperator_.length();
            }
            int prNext_ = StringExpUtil.nextPrintChar(indexParRight_+1, len_, _string);
            if (StringExpUtil.nextCharIs(_string,prNext_,len_,':') || StringExpUtil.nextCharIs(_string,prNext_,len_,'=')) {
                int prNextNext_ = StringExpUtil.nextPrintChar(prNext_+1, len_, _string);
                for (String s: StringUtil.wrapStringArray(keyWordCast_,keyWordExplicit_)) {
                    if (StringExpUtil.startsWithKeyWord(_string,prNextNext_, s)) {
                        int impl_ = prNextNext_ + s.length();
                        int nextLeftPar_ = StringExpUtil.nextPrintCharIs(impl_,len_,_string,ElResolver.PAR_LEFT);
                        if (nextLeftPar_ < 0) {
                            return impl_;
                        }
                        _stack.getCallings().add(nextLeftPar_);
                        int nextRightPar_ = _string.indexOf(ElResolver.PAR_RIGHT, nextLeftPar_);
                        if (nextRightPar_ < 0) {
                            return impl_;
                        }
                        prNextNext_ = StringExpUtil.nextPrintChar(nextRightPar_ + 1, len_, _string);
                    }
                }
                prNext_ = prNextNext_;
            }
            if (StringExpUtil.nextCharIs(_string,prNext_,len_,ElResolver.PAR_LEFT)) {
                _stack.getCallings().add(prNext_);
                return prNext_;
            }
            return indexParRight_+1;
        }
        for (String s: StringUtil.wrapStringArray(
                keyWordClasschoice_,
                keyWordSuperaccess_,keyWordThisaccess_,keyWordInterfaces_)) {
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
                    return i_+s.length();
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

    private static int indexAfterPossibleCastQuick(String _string, int _from, Ints _callings, KeyWords _keyWords) {
        int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT, _from +1);
        if (indexParRight_ < 0) {
            return _from;
        }
        if (_callings.containsObj(_from)) {
            return _from;
        }

        String sub_ = _string.substring(_from + 1, indexParRight_);
        String subTrim_ = sub_.trim();
        int next_ = StringExpUtil.nextPrintChar(indexParRight_+1,_string.length(),_string);
        if (next_ < 0 || StringExpUtil.startsWithKeyWord(_string, next_, _keyWords.getKeyWordInstanceof())) {
            return _from;
        }
        for (String s: StringUtil.wrapStringArray("+=","-=",
                "*=","/=","%=",
                "^=","&=","|=",
                "||","&&","?",":",
                "<",">",",","->",
                "!=","=",")","[","]","}")) {
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

    private static int processWordsQuick(String _string, int _i, char _prevOp, char _curChar, StackDelimiters _stack, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        int len_ = _string.length();
        int i_ = _i;
        KeyWords keyWords_ = _page.getKeyWords();
        if (_prevOp != '.' && StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, i_, _string, false);
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
                int instrLoc_ = _curElts.getInstrLoc();
                int j_ = dash_ + off_+2;
                InputTypeCreation input_ = new InputTypeCreation();
                input_.setType(OuterBlockEnum.ANON_FCT);
                input_.setFile(_curElts.getFile());
                input_.setStringParts(_curElts.getStringParts());
                input_.setNextIndex(j_);
                input_.setNextIndexBef(dash_);
                input_.setOffset(instrLoc_);
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    int k_ = res_.getNextIndex() - 1;
                    return k_+1;
                }
            }
        }
        return i_;
    }

    private static int processWordsQuickBegin(String _string, int _i, char _prevOp, char _curChar, StackDelimiters _stack, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        int len_ = _string.length();
        int i_ = _i;
        KeyWords keyWords_ = _page.getKeyWords();
        if (_prevOp != '.' && StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, i_, _string, false);
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
                int instrLoc_ = _curElts.getInstrLoc();
                ParsedFctHeader parse_ = new ParsedFctHeader();
                parse_.getOffestsParams().add(beginWord_+instrLoc_);
                parse_.getOffestsTypes().add(beginWord_+instrLoc_);
                parse_.getParametersName().add(word_);
                parse_.getParametersType().add("");
                parse_.getParametersRef().add(BoolVal.FALSE);
                parse_.getAnnotationsParams().add(new ResultParsedAnnots());
                InputTypeCreation input_ = new InputTypeCreation();
                input_.setType(OuterBlockEnum.ANON_FCT);
                input_.setFile(_curElts.getFile());
                input_.setStringParts(_curElts.getStringParts());
                input_.setNextIndex(indAfterArrow_);
                input_.setNextIndexBef(dash_);
                input_.setAnnotations(parse_.getAnnotations());
                input_.setAnnotationsParams(parse_.getAnnotationsParams());
                input_.setOffset(instrLoc_);
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    int k_ = res_.getNextIndex() - 1;
                    AnonymousResult anonymous_ = new AnonymousResult();
                    anonymous_.setResults(new ParsedFctHeaderResult(parse_));
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
            int instrLoc_ = _curElts.getInstrLoc();
            parse_.getOffestsParams().add(beginWord_+instrLoc_);
            parse_.getOffestsTypes().add(beginWord_+instrLoc_);
            parse_.getParametersName().add(word_);
            parse_.getParametersType().add("");
            parse_.getParametersRef().add(BoolVal.FALSE);
            parse_.getAnnotationsParams().add(new ResultParsedAnnots());
            int k_ = stack(_string, indAfterArrow_, _page, _curElts);
            String part_ = _string.substring(indAfterArrow_,k_);
            int begAnon_ = dash_ + instrLoc_;
            int begImplRet_ = indAfterArrow_ + instrLoc_;
            NamedCalledFunctionBlock block_ = new NamedCalledFunctionBlock(begAnon_, begImplRet_, _page);
            block_.setAnnotations(parse_.getAnnotations());
            block_.getAnnotationsParams().addAllElts(parse_.getAnnotationsParams());
            block_.setBegin(begImplRet_);
            block_.setLengthHeader(1);
            block_.setFile(_curElts.getFile());
            String tr_ = part_.trim();
            ReturnMethod ret_ = new ReturnMethod(new OffsetStringInfo(begImplRet_, tr_), begImplRet_);
            ret_.setImplicit(true);
            ret_.setBegin(begAnon_);
            ret_.setLengthHeader(2);
            ret_.getRes().partsAbsol(_curElts.getStringParts());
            block_.appendChild(ret_);
            AnonymousResult anonymous_ = new AnonymousResult();
            anonymous_.setResults(new ParsedFctHeaderResult(parse_));
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
                                             AnalyzedPageEl _page, CurrentExpElts _curElts) {
        StackOperators parsBrackets_;
        parsBrackets_ = _parsBrackets;

        int len_ = _string.length();
        KeyWords keyWords_ = _page.getKeyWords();
        if (_curChar == ElResolver.PAR_LEFT) {
            if (!_stack.getCallings().containsObj(_i)) {
                ParsedFctHeader parse_ = new ParsedFctHeader();
                int instrLoc_ = _curElts.getInstrLoc();
                parse_.parseAnonymous(_curElts.getStringParts(), _i,_string,instrLoc_,keyWords_.getKeyWordThat());
                int rightPar_ = parse_.getNextIndex();
                if (rightPar_ > _i) {
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
                        input_.setFile(_curElts.getFile());
                        input_.setStringParts(_curElts.getStringParts());
                        input_.setNextIndex(j_);
                        input_.setNextIndexBef(jBef_);
                        input_.setAnnotations(parse_.getAnnotations());
                        input_.setAnnotationsParams(parse_.getAnnotationsParams());
                        input_.setOffset(instrLoc_);
                        ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                        if (res_.isOkType()) {
                            int k_ = res_.getNextIndex() - 1;
                            return k_+1;
                        }
                    }
                    return rightPar_+1;
                }
            }

            int j_ = indexAfterPossibleCastQuick(_string, _i, _stack.getCallings(), _page.getKeyWords());
            if (j_ > _i) {
                return j_;
            }
            _stack.getCallings().add(_i);
            parsBrackets_.addEntry(_i, _curChar);
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.PAR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            ElResolverCommon.tryAddStringParts(parsBrackets_, _i, _stack);
            ElResolverCommon.tryAddAnnotationsParts(parsBrackets_, _stack);
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            int bk_ = StringExpUtil.getBackPrintChar(_string, _i);
            if (StringExpUtil.nextCharIs(_string,bk_,len_, ElResolver.PAR_RIGHT)) {
                int indexLast_ = _stack.getIndexesNewEnd().indexOf(bk_);
                if (indexLast_ > -1) {
                    String beforeCall_ = _stack.getStringsNewEnd().get(indexLast_);
                    int instrLoc_ = _curElts.getInstrLoc();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.ANON_TYPE);
                    input_.setFile(_curElts.getFile());
                    input_.setStringParts(_curElts.getStringParts());
                    input_.setNextIndex(_i);
                    input_.setAnnotations(_stack.getAnnotationsEnd().get(indexLast_));
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    input_.setOffset(instrLoc_);
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        int j_ = res_.getNextIndex() - 1;
                        return j_+1;
                    }
                }
                int indexLastSw_ = _stack.getIndexesSwitchEnd().indexOf(bk_);
                if (indexLastSw_ > -1) {
                    String beforeCall_ = _stack.getStringsSwitchEnd().get(indexLastSw_);
                    int instrLoc_ = _curElts.getInstrLoc();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.SWITCH_METHOD);
                    input_.setFile(_curElts.getFile());
                    input_.setStringParts(_curElts.getStringParts());
                    input_.setNextIndex(_i);
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    input_.setAnnotations(_stack.getAnnotationsEndSw().get(indexLastSw_));
                    input_.setAnnotationsParams(new CustList<ResultParsedAnnots>(_stack.getAnnotationsEndSwPar().get(indexLastSw_)));
                    input_.setOffset(instrLoc_);
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        int j_ = res_.getNextIndex() - 1;
                        return j_+1;
                    }
                }
            }
            parsBrackets_.addEntry(_i, _curChar);
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ANN_ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ARR_LEFT) {
            int j_ = _i + 1;
            SkipArPart s_ = new SkipArPart();
            s_.skip(j_,_string);
            j_ = s_.getIndex();
            boolean skip_ = s_.isSkip();
            if (skip_) {
                return j_ + 1;
            }
            parsBrackets_.addEntry(_i, _curChar);
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.BEGIN_TERNARY) {
            boolean ternary_ = ElResolverCommon.isTernary(_string, len_, _i);
            if (ternary_) {
                parsBrackets_.addEntry(_i, _curChar);
            }
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.END_TERNARY) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.SEP_ARG && parsBrackets_.isEmpty()) {
            return -1;
        }
        return incrOps(_string, _i);
    }

    private static int processOperatorsQuickBegin(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string,
                                                  AnalyzedPageEl _page, CurrentExpElts _curElts) {
        StackOperators parsBrackets_;
        parsBrackets_ = _parsBrackets;
        KeyWords keyWords_ = _page.getKeyWords();

        int len_ = _string.length();
        if (_curChar == ElResolver.PAR_LEFT) {
            if (!_stack.getCallings().containsObj(_i)) {
                ParsedFctHeader parse_ = new ParsedFctHeader();
                int instrLoc_ = _curElts.getInstrLoc();
                parse_.parseAnonymous(_curElts.getStringParts(),_i,_string,instrLoc_,keyWords_.getKeyWordThat());
                int rightPar_ = parse_.getNextIndex();
                if (rightPar_ > _i) {
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
                        input_.setFile(_curElts.getFile());
                        input_.setStringParts(_curElts.getStringParts());
                        input_.setNextIndex(indAfterArrow_);
                        input_.setNextIndexBef(indBeforeArrow_);
                        input_.setAnnotations(parse_.getAnnotations());
                        input_.setAnnotationsParams(parse_.getAnnotationsParams());
                        input_.setOffset(instrLoc_);
                        ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                        if (res_.isOkType()) {
                            int k_ = res_.getNextIndex() - 1;
                            AnonymousResult anonymous_ = new AnonymousResult();
                            anonymous_.setResults(new ParsedFctHeaderResult(parse_));
                            anonymous_.setIndex(_i);
                            anonymous_.setUntil(k_);
                            anonymous_.setLength(k_- indBeforeArrow_ +1);
                            anonymous_.setType(res_.getBlock());
                            anonymous_.setNext(k_ + 1);
                            _page.getAnonymousResults().add(anonymous_);
                            return k_ + 1;
                        }
                        return -1;
                    }
                    int k_ = stack(_string, indAfterArrow_, _page, _curElts);
                    String part_ = _string.substring(indAfterArrow_,k_);
                    NamedCalledFunctionBlock block_ = new NamedCalledFunctionBlock(indBeforeArrow_ +instrLoc_, indAfterArrow_ +instrLoc_, _page);
                    block_.setAnnotations(parse_.getAnnotations());
                    block_.getAnnotationsParams().addAllElts(parse_.getAnnotationsParams());
                    block_.setBegin(indAfterArrow_ +instrLoc_);
                    block_.setLengthHeader(1);
                    block_.setFile(_curElts.getFile());
                    String trim_ = part_.trim();
                    ReturnMethod ret_ = new ReturnMethod(new OffsetStringInfo(indAfterArrow_ +instrLoc_, trim_), indAfterArrow_ +instrLoc_);
                    ret_.setImplicit(true);
                    ret_.setBegin(indBeforeArrow_ +instrLoc_);
                    ret_.setLengthHeader(2);
                    ret_.getRes().partsAbsol(_curElts.getStringParts());
                    block_.appendChild(ret_);
                    AnonymousResult anonymous_ = new AnonymousResult();
                    anonymous_.setResults(new ParsedFctHeaderResult(parse_));
                    anonymous_.setIndex(_i);
                    int withoutWhiteBoundsCount_ = part_.length() - trim_.length();
                    anonymous_.setUntil(k_- withoutWhiteBoundsCount_ -1);
                    anonymous_.setLength(k_- withoutWhiteBoundsCount_ - indBeforeArrow_);
                    anonymous_.setType(block_);
                    anonymous_.setNext(k_);
                    _page.getAnonymousResults().add(anonymous_);
                    return k_;
                }
            }

            int j_ = indexAfterPossibleCastQuick(_string, _i, _stack.getCallings(), _page.getKeyWords());
            if (j_ > _i) {
                return j_;
            }
            _stack.getCallings().add(_i);
            parsBrackets_.addEntry(_i, _curChar);
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.PAR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            ElResolverCommon.tryAddStringParts(parsBrackets_, _i, _stack);
            ElResolverCommon.tryAddAnnotationsParts(parsBrackets_, _stack);
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            int bk_ = StringExpUtil.getBackPrintChar(_string, _i);
            if (StringExpUtil.nextCharIs(_string,bk_,len_, ElResolver.PAR_RIGHT)) {
                int indexLast_ = _stack.getIndexesNewEnd().indexOf(bk_);
                if (indexLast_ > -1) {
                    String beforeCall_ = _stack.getStringsNewEnd().get(indexLast_);
                    int instrLoc_ = _curElts.getInstrLoc();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.ANON_TYPE);
                    input_.setFile(_curElts.getFile());
                    input_.setStringParts(_curElts.getStringParts());
                    input_.setNextIndex(_i);
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    input_.setAnnotations(_stack.getAnnotationsEnd().get(indexLast_));
                    input_.setOffset(instrLoc_);
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        int j_ = res_.getNextIndex() - 1;
                        AnonymousResult anonymous_ = new AnonymousResult();
                        anonymous_.setIndex(_i);
                        anonymous_.setUntil(j_);
                        anonymous_.setLength(j_- _i +1);
                        anonymous_.setType(res_.getBlock());
                        anonymous_.setNext(j_ + 1);
                        _page.getAnonymousResults().add(anonymous_);
                        return j_ + 1;
                    }
                }
                int indexLastSw_ = _stack.getIndexesSwitchEnd().indexOf(bk_);
                if (indexLastSw_ > -1) {
                    String beforeCall_ = _stack.getStringsSwitchEnd().get(indexLastSw_);
                    int instrLoc_ = _curElts.getInstrLoc();
                    InputTypeCreation input_ = new InputTypeCreation();
                    input_.setType(OuterBlockEnum.SWITCH_METHOD);
                    input_.setFile(_curElts.getFile());
                    input_.setStringParts(_curElts.getStringParts());
                    input_.setNextIndex(_i);
                    input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                    input_.setAnnotations(_stack.getAnnotationsEndSw().get(indexLastSw_));
                    input_.setAnnotationsParams(new CustList<ResultParsedAnnots>(_stack.getAnnotationsEndSwPar().get(indexLastSw_)));
                    input_.setOffset(instrLoc_);
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        int j_ = res_.getNextIndex() - 1;
                        AnonymousResult anonymous_ = new AnonymousResult();
                        anonymous_.setIndex(_i);
                        anonymous_.setUntil(j_);
                        anonymous_.setLength(j_- _i +1);
                        anonymous_.setType(res_.getBlock());
                        anonymous_.setNext(j_ + 1);
                        anonymous_.setRetSwitch(_stack.getRetSwitchList().get(indexLastSw_));
                        _page.getAnonymousResults().add(anonymous_);
                        return j_ + 1;
                    }
                }
            }
            parsBrackets_.addEntry(_i, _curChar);
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ANN_ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ARR_LEFT) {
            int j_ = _i + 1;
            SkipArPart s_ = new SkipArPart();
            s_.skip(j_,_string);
            j_ = s_.getIndex();
            boolean skip_ = s_.isSkip();
            if (skip_) {
                return j_ + 1;
            }
            parsBrackets_.addEntry(_i, _curChar);
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.ARR_RIGHT) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.BEGIN_TERNARY) {
            boolean ternary_ = ElResolverCommon.isTernary(_string, len_, _i);
            if (ternary_) {
                parsBrackets_.addEntry(_i, _curChar);
            }
            return incrOps(_string, _i);
        }
        if (_curChar == ElResolver.END_TERNARY) {
            if (parsBrackets_.isEmpty()) {
                return -1;
            }
            parsBrackets_.removeLast();
            return incrOps(_string, _i);
        }
        return incrOps(_string, _i);
    }

    private static int incrOps(String _string, int _i) {
        IncrOperatorPart incr_ = new IncrOperatorPart();
        return incr_.tryAddOp(-1,_string,_i);
    }

}
