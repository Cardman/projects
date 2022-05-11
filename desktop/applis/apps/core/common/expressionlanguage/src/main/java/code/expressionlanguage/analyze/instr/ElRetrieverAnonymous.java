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

    public static int commonCheckQuick(int _minIndex, AnalyzedPageEl _page, ResultExpression _res) {
        String currentPkg_ = _page.getCurrentPkg();
        FileBlock currentFile_ = _page.getCurrentFile();
        return stackBegin(_res.getAnalyzedString(), _minIndex, _page, new CurrentExpElts(currentPkg_, currentFile_, _page.getIndex(), _res));
    }

    private static int stackBegin(String _string, int _from, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        StackDelimiters stack_ = new StackDelimiters();
        StackOperators parsBrackets_ = new StackOperators();
        char prevOp_ = ElResolver.SPACE;
        int from_ = _from;
        int len_ = _string.length();
        while (from_ < len_) {
            char curChar_ = _string.charAt(from_);
            int nextSt_ = nextStackBegin(_string,from_,_page,_curElts,stack_,parsBrackets_,prevOp_);
            if (nextSt_ <= from_) {
                break;
            }
            if (!StringUtil.isWhitespace(curChar_)) {
                prevOp_ = curChar_;
            }
            from_ = nextSt_;
        }
        _curElts.getRes().getAnnotDelNew().addAllElts(stack_.getAnnotDelNew());
        _curElts.getRes().getAnnotDelSwitch().addAllElts(stack_.getAnnotDelSwitch());
        return from_;
    }
    private static int nextStackBegin(String _string, int _from, AnalyzedPageEl _page, CurrentExpElts _curElts, StackDelimiters _stack, StackOperators _parsBrackets, char _prevOp) {
        char curChar_ = _string.charAt(_from);
        int until_ = afterStrPart(_from,_curElts,_curElts.getRes().getParts());
        if (until_ > _from) {
            return until_;
        }
        int len_ = _string.length();
        if (_page.getCurrentBlock() instanceof FieldBlock
                && _parsBrackets.isEmptyStackSymChars()
                && StringExpUtil.isTypeLeafChar(curChar_)) {
            int bk_ = StringExpUtil.getBackPrintChar(_string, _from);
            if (bk_ < 0 || StringExpUtil.nextCharIs(_string, bk_, len_, ',')) {
                int j_ = ElResolverCommon.getWord(_string, len_, _from);
                int n_ = StringExpUtil.nextPrintChar(j_, len_, _string);
                if (n_ < 0
                        || StringExpUtil.nextCharIs(_string, n_, len_, '=') && !StringExpUtil.nextCharIs(_string, n_ + 1, len_, '=')
                        || StringExpUtil.nextCharIs(_string, n_, len_, ',')) {
                    return j_;
                }
            }
        }
        return nextStackBeginOtherWordsNbOpers(_string, _from, _page, _curElts, _stack, _parsBrackets, _prevOp);
    }

    private static int nextStackBeginOtherWordsNbOpers(String _string, int _from, AnalyzedPageEl _page, CurrentExpElts _curElts, StackDelimiters _stack, StackOperators _parsBrackets, char _prevOp) {
        int len_ = _string.length();
        char curChar_ = _string.charAt(_from);
        int next_ = processAfterInstuctionKeyWordQuick(_string, _from, _stack, _page, _curElts);
        if (next_ > _from) {
            return next_;
        }
        if (StringExpUtil.isTypeLeafChar(curChar_)) {
            return currentOrAfter(_from, processWordsQuickBegin(_string, _from, _prevOp, curChar_, _stack, _page, _curElts));
        }
        if (_prevOp != '.' && curChar_ == ElResolver.DOT_VAR) {
            int n_ = StringExpUtil.nextPrintChar(_from + 1, len_, _string);
            if (!StringExpUtil.nextCharIs(_string, n_, len_, ElResolver.DOT_VAR) && ElResolverCommon.isDigitOrDot(_string, n_)) {
                KeyWords keyWords_ = _page.getKeyWords();
                NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, _from + 1, _string, true);
                res_.setPreviousIndex(_from);
                _curElts.getRes().getNumbers().add(res_);
                return res_.getNextIndex();
            }
        }
        return currentOrAfter(_from, processOperatorsQuickBegin(_parsBrackets, _stack, _from, curChar_, _string, _page, _curElts));
    }

    private static int stack(String _string, int _from, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        StackDelimiters stack_ = new StackDelimiters();
        StackOperators parsBrackets_ = new StackOperators();
        char prevOp_ = ElResolver.SPACE;
        int from_ = _from;
        int len_ = _string.length();
        while (from_ < len_) {
            char curChar_ = _string.charAt(from_);
            int nextSt_ = nextStack(_string, from_, _page, _curElts, stack_, parsBrackets_, prevOp_);
            if (nextSt_ <= from_) {
                break;
            }
            if (!StringUtil.isWhitespace(curChar_)) {
                prevOp_ = curChar_;
            }
            from_ = nextSt_;
        }
        return from_;
    }
    private static int nextStack(String _string, int _from, AnalyzedPageEl _page, CurrentExpElts _curElts, StackDelimiters _stack, StackOperators _parsBrackets, char _prevOp) {
        char curChar_ = _string.charAt(_from);
        int until_ = afterStrPart(_from,_curElts, new CustList<SegmentStringPart>());
        if (until_ > _from) {
            return until_;
        }
        int next_ = processAfterInstuctionKeyWordQuick(_string, _from,_stack, _page, _curElts);
        if (next_ > _from) {
            return next_;
        }
        if (StringExpUtil.isTypeLeafChar(curChar_)) {
            next_ = processWordsQuick(_string,_from,_prevOp,curChar_,_stack, _page, _curElts);
            return next_;
        }
        if (_prevOp != '.' && curChar_ == ElResolver.DOT_VAR) {
            int len_ = _string.length();
            int n_ = StringExpUtil.nextPrintChar(_from + 1, len_, _string);
            if (!StringExpUtil.nextCharIs(_string, n_, len_, ElResolver.DOT_VAR) && ElResolverCommon.isDigitOrDot(_string, n_)) {
                KeyWords keyWords_ = _page.getKeyWords();
                NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, _from + 1, _string, true);
                return res_.getNextIndex();
            }
        }
        next_ = processOperatorsQuick(_parsBrackets,_stack,_from,curChar_, _string, _page, _curElts);
        return currentOrAfter(_from,next_);
    }
    private static int currentOrAfter(int _from, int _next) {
        if (_next < 0) {
            return _from;
        }
        return _next;
    }
    private static int afterStrPart(int _current, CurrentExpElts _curElts, CustList<SegmentStringPart> _dest) {
        int until_ = _current;
        for (SegmentStringPart s: _curElts.getStringParts()) {
            if (s.getBegin() == _curElts.getInstrLoc() + _current) {
                until_ = s.getEnd() - _curElts.getInstrLoc();
                _dest.add(new SegmentStringPart(_current,until_,s.getStrType()));
                break;
            }
        }
        return until_;
    }
    private static int processAfterInstuctionKeyWordQuick(String _string, int _i, StackDelimiters _stack, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        int len_ = _string.length();
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
        if (StringExpUtil.startsWithKeyWord(_string, _i, keyWordInstanceof_)) {
            int next_ = _i + keyWordInstanceof_.length();
            return ElResolverCommon.incrInstanceOf(_string, len_, next_);
        }
        if (StringExpUtil.startsWithKeyWord(_string, _i, keyWordSwitch_)) {
            return keyWordSwitch(_string, _stack, _curElts, _i, keyWordSwitch_);
        }
        if (StringExpUtil.startsWithKeyWord(_string, _i, keyWordNew_)) {
            return keyWordNew(_string, _stack, _curElts, _i, keyWordInterfaces_, keyWordNew_);
        }
        if (StringExpUtil.startsWithKeyWord(_string, _i, keyWordOperator_)) {
            return keyWordOperator(_string, _stack, _i, keyWordCast_, keyWordExplicit_, keyWordOperator_);
        }
        for (String s: StringUtil.wrapStringArray(
                keyWordClasschoice_,
                keyWordSuperaccess_,keyWordThisaccess_,keyWordInterfaces_)) {
            if (StringExpUtil.startsWithKeyWord(_string, _i, s)) {
                return keyWordClassTwoParts(_string, _stack, _i, s);
            }
        }
        for (String s: StringUtil.wrapStringArray(keyWordCast_,keyWordExplicit_,
                keyWordVararg_,keyWordDefaultValue_,keyWordClass_,keyWordLambda_,
                keyWordId_,keyWordStatic_,keyWordStaticCall_,
                keyWordValues_)) {
            if (StringExpUtil.startsWithKeyWord(_string, _i, s)) {
                return keyWordClassOnePartRight(_string, _i, _stack, s);
            }
        }
        for (String s: StringUtil.wrapStringArray(keyWordBool_,keyWordFalse_,keyWordFirstopt_,
                keyWordSuper_,keyWordThis_,keyWordValueOf_,keyWordThat_,keyWordTrue_,keyWordDefault_)) {
            if (StringExpUtil.startsWithKeyWord(_string, _i, s)) {
                return keyWordClassOnePartLeft(_string, _i, _stack, s);
            }
        }
        return _i;
    }

    private static int keyWordClassOnePartLeft(String _string, int _i, StackDelimiters _stack, String _s) {
        int len_ = _string.length();
        int pr_ = StringExpUtil.nextPrintChar(_i + _s.length(), len_, _string);
        if (!StringExpUtil.nextCharIs(_string,pr_, len_, ElResolver.PAR_LEFT)) {
            return _i + _s.length();
        }
        _stack.getCallings().add(pr_);
        return pr_;
    }

    private static int keyWordClassOnePartRight(String _string, int _i, StackDelimiters _stack, String _s) {
        int len_ = _string.length();
        int pr_ = StringExpUtil.nextPrintChar(_i + _s.length(), len_, _string);
        if (!StringExpUtil.nextCharIs(_string,pr_, len_, ElResolver.PAR_LEFT)) {
            return _i + _s.length();
        }
        _stack.getCallings().add(pr_);
        int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT,pr_+1);
        if (indexParRight_ < 0) {
            return _i + _s.length();
        }
        return indexParRight_ + 1;
    }

    private static int keyWordClassTwoParts(String _string, StackDelimiters _stack, int _current, String _s) {
        int len_ = _string.length();
        int pr_ = StringExpUtil.nextPrintChar(_current + _s.length(), len_, _string);
        if (!StringExpUtil.nextCharIs(_string,pr_, len_, ElResolver.PAR_LEFT)) {
            return _current + _s.length();
        }
        _stack.getCallings().add(pr_);
        int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT,pr_+1);
        if (indexParRight_ < 0) {
            return _current + _s.length();
        }
        int indexSecParLeft_ = _string.indexOf(ElResolver.PAR_LEFT,indexParRight_+1);
        if (indexSecParLeft_ < 0) {
            return indexParRight_ + 1;
        }
        _stack.getCallings().add(indexSecParLeft_);
        return indexSecParLeft_;
    }

    private static int keyWordOperator(String _string, StackDelimiters _stack, int _current, String _keyWordCast, String _keyWordExplicit, String _keyWordOperator) {
        int len_ = _string.length();
        int pr_ = StringExpUtil.nextPrintChar(_current + _keyWordOperator.length(), len_, _string);
        if (!StringExpUtil.nextCharIs(_string,pr_, len_, ElResolver.PAR_LEFT)) {
            return _current + _keyWordOperator.length();
        }
        _stack.getCallings().add(pr_);
        int indexParRight_ = _string.indexOf(ElResolver.PAR_RIGHT,pr_+1);
        if (indexParRight_ < 0) {
            return _current + _keyWordOperator.length();
        }
        int prNext_ = StringExpUtil.nextPrintChar(indexParRight_+1, len_, _string);
        if (StringExpUtil.nextCharIs(_string,prNext_, len_,':') || StringExpUtil.nextCharIs(_string,prNext_, len_,'=')) {
            return keyWordOperatorCoumpound(_string, _stack, _keyWordCast, _keyWordExplicit, indexParRight_, prNext_);
        }
        return keyWordOperatorEnd(_string, _stack, indexParRight_, prNext_);
    }

    private static int keyWordOperatorCoumpound(String _string, StackDelimiters _stack, String _keyWordCast, String _keyWordExplicit, int _indexParRight, int _prNext) {
        int len_ = _string.length();
        int prNextNext_ = StringExpUtil.nextPrintChar(_prNext +1, len_, _string);
        for (String s: StringUtil.wrapStringArray(_keyWordCast, _keyWordExplicit)) {
            if (StringExpUtil.startsWithKeyWord(_string,prNextNext_, s)) {
                int impl_ = prNextNext_ + s.length();
                int nextLeftPar_ = StringExpUtil.nextPrintCharIs(impl_, len_, _string,ElResolver.PAR_LEFT);
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
        return keyWordOperatorEnd(_string, _stack, _indexParRight, prNextNext_);
    }

    private static int keyWordOperatorEnd(String _string, StackDelimiters _stack, int _indexParRight, int _prNext) {
        int len_ = _string.length();
        if (StringExpUtil.nextCharIs(_string, _prNext, len_,ElResolver.PAR_LEFT)) {
            _stack.getCallings().add(_prNext);
            return _prNext;
        }
        return _indexParRight + 1;
    }

    private static int keyWordNew(String _string, StackDelimiters _stack, CurrentExpElts _curElts, int _current, String _keyWordInterfaces, String _keyWordNew) {
        int len_ = _string.length();
        int j_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _current + _keyWordNew.length());
        boolean foundLeftPar_ = false;
        boolean foundLeft_ = false;
        if (StringExpUtil.nextCharIs(_string,j_,len_,ElResolver.ANN_ARR_LEFT)) {
            foundLeft_ = true;
            j_++;
        } else if (StringExpUtil.nextCharIs(_string,j_,len_,ElResolver.PAR_LEFT)) {
            foundLeftPar_ = true;
            _stack.getCallings().add(j_);
            j_++;
        } else if (StringExpUtil.nextCharIs(_string,j_,len_,ElResolver.ARR_LEFT)) {
            foundLeftPar_ = true;
            j_++;
        }
        if (foundLeftPar_) {
            int next_ = j_-1;
            _stack.getAnnotations().add(new ResultParsedAnnots());
            _stack.getStringsNew().add("");
            _stack.getIndexesNew().add(next_);
            return next_;
        }
        j_ = DefaultProcessKeyWord.skipWhiteSpace(_string, j_);
        boolean found_ = StringExpUtil.nextCharIs(_string,j_,len_,ElResolver.ANN_ARR_RIGHT);
        if (foundLeft_ && !found_) {
            return j_;
        }
        if (found_) {
            j_++;
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
        if (StringExpUtil.startsWithKeyWord(_string,j_, _keyWordInterfaces)) {
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
        j_ = DefaultProcessKeyWord.extractType(_string, _stack,j_);
        _stack.getAnnotations().add(res_);
        _stack.getStringsNew().add(_string.substring(from_,j_));
        _stack.getIndexesNew().add(j_);
        return j_;
    }

    private static int keyWordSwitch(String _string, StackDelimiters _stack, CurrentExpElts _curElts, int _current, String _keyWordSwitch) {
        int j_ = _current + _keyWordSwitch.length();
        String afterSwitch_ = _string.substring(j_);
        if (afterSwitch_.trim().startsWith("[")) {
            return afterExpTypes(_string, _stack, _curElts, j_, afterSwitch_);
        }
        ResultParsedAnnots annotations_ = new ResultParsedAnnots();
        ResultParsedAnnots annotationsParam_ = new ResultParsedAnnots();
        int next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,j_);
        if (_string.startsWith("(",next_)) {
            _stack.getStringsSwitch().add("");
            _stack.getIndexesSwitch().add(next_);
            _stack.getAnnotationsSw().add(annotations_);
            _stack.getAnnotationsSwPar().add(annotationsParam_);
            _stack.getCallings().add(next_);
            _stack.getRetSwitch().add("");
            return next_;
        }
        return j_;
    }

    private static int afterExpTypes(String _string, StackDelimiters _stack, CurrentExpElts _curElts, int _j, String _afterSwitch) {
        ResultParsedAnnots annotations_ = new ResultParsedAnnots();
        ResultParsedAnnots annotationsParam_ = new ResultParsedAnnots();
        int start_ = _afterSwitch.indexOf('[') + 1;
        int k_ = start_;
        _stack.getAnnotDelSwitch().add(k_);
        int count_ = 1;
        int lenSw_ = _afterSwitch.length();
        boolean already_ = false;
        while (k_ < lenSw_) {
            char ch_ = _afterSwitch.charAt(k_);
            if (ch_ == '[') {
                count_++;
            }
            if (!already_&&count_ == 1 && ch_ == ':') {
                int l_ = incrAnnot(_curElts,_j,_afterSwitch,k_,annotations_,annotationsParam_);
                if (_afterSwitch.startsWith("]",l_)) {
                    int current_ = k_;
                    k_ = l_;
                    _stack.getAnnotDelSwitch().add(k_);
                    return endExpType(_string, _stack, _j, annotations_, annotationsParam_, _afterSwitch.substring(start_, current_), k_);
                }
                already_ = true;
            }
            if (ch_ == ']') {
                count_--;
                if (count_ == 0) {
                    _stack.getAnnotDelSwitch().add(k_);
                    return endExpType(_string, _stack, _j, annotations_, annotationsParam_, _afterSwitch.substring(start_, k_), k_);
                }
            }
            k_++;
        }
        _stack.getAnnotDelSwitch().add(k_);
        return endExpType(_string, _stack, _j, annotations_, annotationsParam_, "", k_);
    }
    private static int incrAnnot(CurrentExpElts _curElts, int _j, String _afterSwitch, int _k, ResultParsedAnnots _annotations, ResultParsedAnnots _annotationsParam) {
        int l_ = DefaultProcessKeyWord.skipWhiteSpace(_afterSwitch,_k+1);
        if (_afterSwitch.startsWith("@",l_)) {
            ParsedAnnotations parse_ = new ParsedAnnotations(_afterSwitch.substring(l_), _j +l_+ _curElts.getInstrLoc());
            parse_.parse(_curElts.getStringParts());
            _annotations.set(parse_);
            l_ = parse_.getIndex() - _j - _curElts.getInstrLoc();
            l_ = DefaultProcessKeyWord.skipWhiteSpace(_afterSwitch,l_);
        }
        if (_afterSwitch.startsWith(":",l_)) {
            int m_ = DefaultProcessKeyWord.skipWhiteSpace(_afterSwitch,l_+1);
            if (_afterSwitch.startsWith("@",m_)) {
                ParsedAnnotations parse_ = new ParsedAnnotations(_afterSwitch.substring(m_), _j +m_+ _curElts.getInstrLoc());
                parse_.parse(_curElts.getStringParts());
                _annotationsParam.set(parse_);
                m_ = parse_.getIndex() - _j - _curElts.getInstrLoc();
                m_ = DefaultProcessKeyWord.skipWhiteSpace(_afterSwitch,m_);
            }
            l_ = m_;
        }
        return l_;
    }

    private static int endExpType(String _string, StackDelimiters _stack, int _j, ResultParsedAnnots _annotations, ResultParsedAnnots _annotationsParam, String _retSwitch, int _k) {
        int next_ = DefaultProcessKeyWord.skipWhiteSpace(_string, _j + _k +1);
        if (_string.startsWith("(",next_)) {
            _stack.getStringsSwitch().add("");
            _stack.getIndexesSwitch().add(next_);
            _stack.getCallings().add(next_);
            _stack.getAnnotationsSw().add(_annotations);
            _stack.getAnnotationsSwPar().add(_annotationsParam);
            _stack.getRetSwitch().add(_retSwitch);
            return next_;
        }
        return _j + _k;
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
        KeyWords keyWords_ = _page.getKeyWords();
        if (_prevOp != '.' && StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, _i, _string, false);
            return res_.getNextIndex();
        }
        int afterWord_ = incrWord(_string, _i);
        int afterIncr_ = tryIncrAfterWord(_string, _i, _stack, _page, afterWord_);
        if (afterIncr_ > _i) {
            return afterIncr_;
        }
        int indBeforeArrow_ = StringExpUtil.nextPrintCharIs(afterWord_, len_, _string, '-');
        if (indBeforeArrow_ > -1 && StringExpUtil.nextCharIs(_string,indBeforeArrow_+1, len_,'>')) {
            int afArro_ = indBeforeArrow_ + "->".length();
            int indAfterArrow_ = DefaultProcessKeyWord.skipWhiteSpace(_string, afArro_);
            if (StringExpUtil.nextCharIs(_string,indAfterArrow_,len_,ElResolver.ANN_ARR_LEFT)) {
                int instrLoc_ = _curElts.getInstrLoc();
                InputTypeCreation input_ = buildStdInput(OuterBlockEnum.ANON_FCT, _curElts, indAfterArrow_);
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    return res_.getNextIndex();
                }
            }
        }
        return afterWord_;
    }

    private static int processWordsQuickBegin(String _string, int _i, char _prevOp, char _curChar, StackDelimiters _stack, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        int len_ = _string.length();
        KeyWords keyWords_ = _page.getKeyWords();
        if (_prevOp != '.' && StringExpUtil.isDigit(_curChar)) {
            NumberInfosOutput res_ = ElResolverCommon.processNb(keyWords_, _i, _string, false);
            res_.setPreviousIndex(_i);
            _curElts.getRes().getNumbers().add(res_);
            return res_.getNextIndex();
        }
        int afterWord_ = incrWord(_string, _i);
        int afterIncr_ = tryIncrAfterWord(_string, _i, _stack, _page, afterWord_);
        if (afterIncr_ > _i) {
            return afterIncr_;
        }
        int indBeforeArrow_ = StringExpUtil.nextPrintCharIs(afterWord_, len_, _string, '-');
        if (indBeforeArrow_ > -1 && StringExpUtil.nextCharIs(_string,indBeforeArrow_+1, len_,'>')) {
            int afArro_ = indBeforeArrow_ + "->".length();
            int indAfterArrow_ = DefaultProcessKeyWord.skipWhiteSpace(_string, afArro_);
            int instrLoc_ = _curElts.getInstrLoc();
            ParsedFctHeader parse_ = new ParsedFctHeader();
            parse_.getOffestsParams().add(_i +instrLoc_);
            parse_.getOffestsTypes().add(_i +instrLoc_);
            String word_ = _string.substring(_i, afterWord_);
            parse_.getParametersName().add(word_);
            parse_.getParametersType().add("");
            parse_.getParametersRef().add(BoolVal.FALSE);
            parse_.getAnnotationsParams().add(new ResultParsedAnnots());
            if (StringExpUtil.nextCharIs(_string,indAfterArrow_,len_,ElResolver.ANN_ARR_LEFT)) {
                InputTypeCreation input_ = buildInputAnonFct(_curElts, indAfterArrow_, indBeforeArrow_, parse_);
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    return resAnonLambda(res_, parse_, _i, input_, _curElts);
                }
                return -1;
            }
            return resAnonLambdaLight(_i, _string, _page, _curElts, parse_, indAfterArrow_, indBeforeArrow_);
        }
        return afterWord_;
    }
    private static int incrWord(String _string, int _current) {
        int i_ = _current;
        int len_ = _string.length();
        while (i_ < len_) {
            char locChar_ = _string.charAt(i_);
            if (!StringExpUtil.isTypeLeafChar(locChar_)) {
                break;
            }
            i_++;
        }
        return i_;
    }
    private static int tryIncrAfterWord(String _string, int _old, StackDelimiters _stack, AnalyzedPageEl _page, int _endWord) {
        int len_ = _string.length();
        int nextPar_ = StringExpUtil.nextPrintCharIs(_endWord, len_, _string, ElResolver.PAR_LEFT);
        if (nextPar_ > -1) {
            _stack.getCallings().add(nextPar_);
            return _endWord;
        }
        int bk_ = StringExpUtil.getBackPrintChar(_string, _old);
        if (StringExpUtil.nextCharIs(_string,bk_,len_,'.')) {
            return _endWord;
        }
        int n_ = ElResolverCommon.addNamed(_string, _old, _endWord, _stack.getNamedArgs(), _page);
        if (n_ >= _endWord) {
            return n_;
        }
        return _old;
    }

    private static int processOperatorsQuick(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string,
                                             AnalyzedPageEl _page, CurrentExpElts _curElts) {
        if (_curChar == ElResolver.SEP_ARG && _parsBrackets.isEmpty()) {
            return -1;
        }
        if (_curChar == ElResolver.PAR_LEFT) {
            return parLeft(_parsBrackets, _stack, _i, _curChar, _string, _page, _curElts);
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            return annArrLeft(_parsBrackets, _stack, _i, _curChar, _string, _page, _curElts);
        }
        return incr(_i, _curChar, _string,_stack, _parsBrackets);
    }

    private static int parLeft(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        KeyWords keyWords_ = _page.getKeyWords();
        if (!_stack.getCallings().containsObj(_i)) {
            ParsedFctHeader parse_ = new ParsedFctHeader();
            int instrLoc_ = _curElts.getInstrLoc();
            parse_.parseAnonymous(_curElts.getStringParts(), _i, _string,instrLoc_, keyWords_.getKeyWordThat());
            int rightPar_ = parse_.getNextIndex();
            if (rightPar_ > _i) {
                if (parse_.isAfterArrowLeftBrace()) {
                    int offAfArr_ = parse_.getArrowPlusSpaceAfter();
                    int j_ = parse_.getBeforeArrowIndex()+offAfArr_;
                    InputTypeCreation input_ = buildStdInput(OuterBlockEnum.ANON_FCT, _curElts, j_);
                    input_.setAnnotations(parse_.getAnnotations());
                    input_.setAnnotationsParams(parse_.getAnnotationsParams());
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        return res_.getNextIndex();
                    }
                }
                return rightPar_ + 1;
            }
        }

        int j_ = indexAfterPossibleCastQuick(_string, _i, _stack.getCallings(), _page.getKeyWords());
        if (j_ > _i) {
            return j_;
        }
        _stack.getCallings().add(_i);
        return incrOpsStack(_parsBrackets, _i, _curChar, _string);
    }

    private static int incrOpsStack(StackOperators _parsBrackets, int _i, char _curChar, String _string) {
        _parsBrackets.addEntry(_i, _curChar);
        return incrOps(_string, _i);
    }

    private static int annArrLeft(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        int len_ = _string.length();
        KeyWords keyWords_ = _page.getKeyWords();
        int bk_ = StringExpUtil.getBackPrintChar(_string, _i);
        if (StringExpUtil.nextCharIs(_string,bk_, len_, ElResolver.PAR_RIGHT)) {
            int indexLast_ = _stack.getIndexesNewEnd().indexOf(bk_);
            if (indexLast_ > -1) {
                String beforeCall_ = _stack.getStringsNewEnd().get(indexLast_);
                int instrLoc_ = _curElts.getInstrLoc();
                InputTypeCreation input_ = buildStdInput(OuterBlockEnum.ANON_TYPE, _curElts, _i);
                input_.setAnnotations(_stack.getAnnotationsEnd().get(indexLast_));
                input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    return res_.getNextIndex();
                }
            }
            int indexLastSw_ = _stack.getIndexesSwitchEnd().indexOf(bk_);
            if (indexLastSw_ > -1) {
                String beforeCall_ = _stack.getStringsSwitchEnd().get(indexLastSw_);
                int instrLoc_ = _curElts.getInstrLoc();
                InputTypeCreation input_ = buildStdInput(OuterBlockEnum.SWITCH_METHOD, _curElts, _i);
                input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                input_.setAnnotations(_stack.getAnnotationsEndSw().get(indexLastSw_));
                input_.setAnnotationsParams(new CustList<ResultParsedAnnots>(_stack.getAnnotationsEndSwPar().get(indexLastSw_)));
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    return res_.getNextIndex();
                }
            }
        }
        return incrOpsStack(_parsBrackets, _i, _curChar, _string);
    }

    private static InputTypeCreation buildStdInput(OuterBlockEnum _anonElt, CurrentExpElts _curElts, int _current) {
        int instrLoc_ = _curElts.getInstrLoc();
        InputTypeCreation input_ = new InputTypeCreation();
        input_.setOffset(instrLoc_);
        input_.setType(_anonElt);
        input_.setFile(_curElts.getFile());
        input_.setStringParts(_curElts.getStringParts());
        input_.setNextIndex(_current);
        return input_;
    }

    private static int processOperatorsQuickBegin(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string,
                                                  AnalyzedPageEl _page, CurrentExpElts _curElts) {
        if (_curChar == ElResolver.PAR_LEFT) {
            return parLeftBegin(_parsBrackets, _stack, _i, _curChar, _string, _page, _curElts);
        }
        if (_curChar == ElResolver.ANN_ARR_LEFT) {
            return annArrLeftBegin(_parsBrackets, _stack, _i, _curChar, _string, _page, _curElts);
        }
        return incr(_i, _curChar, _string, _stack,_parsBrackets);
    }

    private static int parLeftBegin(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        KeyWords keyWords_ = _page.getKeyWords();
        if (!_stack.getCallings().containsObj(_i)) {
            ParsedFctHeader parse_ = new ParsedFctHeader();
            int instrLoc_ = _curElts.getInstrLoc();
            parse_.parseAnonymous(_curElts.getStringParts(), _i, _string,instrLoc_, keyWords_.getKeyWordThat());
            int rightPar_ = parse_.getNextIndex();
            if (rightPar_ > _i) {
                int offAfArr_ = parse_.getArrowPlusSpaceAfter();
                int indBeforeArrow_ = parse_.getBeforeArrowIndex();
                int indAfterArrow_ =indBeforeArrow_+ offAfArr_;
                if (parse_.isAfterArrowLeftBrace()) {
                    InputTypeCreation input_ = buildInputAnonFct(_curElts, indAfterArrow_, indBeforeArrow_, parse_);
                    ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                    if (res_.isOkType()) {
                        return resAnonLambda(res_, parse_, _i, input_, _curElts);
                    }
                    return -1;
                }
                return resAnonLambdaLight(_i, _string, _page, _curElts, parse_, indAfterArrow_, indBeforeArrow_);
            }
        }

        int j_ = indexAfterPossibleCastQuick(_string, _i, _stack.getCallings(), _page.getKeyWords());
        if (j_ > _i) {
            return j_;
        }
        _stack.getCallings().add(_i);
        return incrOpsStack(_parsBrackets, _i, _curChar, _string);
    }

    private static int annArrLeftBegin(StackOperators _parsBrackets, StackDelimiters _stack, int _i, char _curChar, String _string, AnalyzedPageEl _page, CurrentExpElts _curElts) {
        KeyWords keyWords_ = _page.getKeyWords();
        int len_ = _string.length();
        int bk_ = StringExpUtil.getBackPrintChar(_string, _i);
        if (StringExpUtil.nextCharIs(_string,bk_, len_, ElResolver.PAR_RIGHT)) {
            int indexLast_ = _stack.getIndexesNewEnd().indexOf(bk_);
            if (indexLast_ > -1) {
                String beforeCall_ = _stack.getStringsNewEnd().get(indexLast_);
                int instrLoc_ = _curElts.getInstrLoc();
                InputTypeCreation input_ = buildStdInput(OuterBlockEnum.ANON_TYPE, _curElts, _i);
                input_.setNextIndexBef(_i);
                input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                input_.setAnnotations(_stack.getAnnotationsEnd().get(indexLast_));
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    return resAnonType(_i, input_, _curElts, res_);
                }
            }
            int indexLastSw_ = _stack.getIndexesSwitchEnd().indexOf(bk_);
            if (indexLastSw_ > -1) {
                String beforeCall_ = _stack.getStringsSwitchEnd().get(indexLastSw_);
                int instrLoc_ = _curElts.getInstrLoc();
                InputTypeCreation input_ = buildStdInput(OuterBlockEnum.SWITCH_METHOD, _curElts, _i);
                input_.setNextIndexBef(_i);
                input_.generatedId(beforeCall_, keyWords_.getKeyWordId());
                input_.setAnnotations(_stack.getAnnotationsEndSw().get(indexLastSw_));
                input_.setAnnotationsParams(new CustList<ResultParsedAnnots>(_stack.getAnnotationsEndSwPar().get(indexLastSw_)));
                ResultCreation res_ = FileResolver.processOuterTypeBody(input_, _curElts.getPackageName(), instrLoc_, _string, _page);
                if (res_.isOkType()) {
                    return resAnonSwitch(_i, input_, _curElts, res_, _stack.getRetSwitchList().get(indexLastSw_));
                }
            }
        }
        return incrOpsStack(_parsBrackets, _i, _curChar, _string);
    }

    private static InputTypeCreation buildInputAnonFct(CurrentExpElts _curElts, int _indAfterArrow, int _indBeforeArrow, ParsedFctHeader _parse) {
        InputTypeCreation input_ = buildStdInput(OuterBlockEnum.ANON_FCT, _curElts, _indAfterArrow);
        input_.setNextIndexBef(_indBeforeArrow);
        input_.setAnnotations(_parse.getAnnotations());
        input_.setAnnotationsParams(_parse.getAnnotationsParams());
        return input_;
    }

    private static int resAnonLambdaLight(int _i, String _string, AnalyzedPageEl _page, CurrentExpElts _curElts, ParsedFctHeader _parse, int _indAfterArrow, int _indBeforeArrow) {
        int instrLoc_ = _curElts.getInstrLoc();
        int k_ = stack(_string, _indAfterArrow, _page, _curElts);
        String part_ = _string.substring(_indAfterArrow,k_);
        int begAnon_ = _indBeforeArrow + instrLoc_;
        int begImplRet_ = _indAfterArrow + instrLoc_;
        NamedCalledFunctionBlock block_ = new NamedCalledFunctionBlock(begAnon_, begImplRet_, _page);
        block_.setAnnotations(_parse.getAnnotations());
        block_.getAnnotationsParams().addAllElts(_parse.getAnnotationsParams());
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
        anonymous_.setResults(new ParsedFctHeaderResult(_parse));
        anonymous_.setIndex(_i);
        int withoutWhiteBoundsCount_ = part_.length() - tr_.length();
        anonymous_.setUntil(k_- withoutWhiteBoundsCount_ -1);
        anonymous_.setLength(k_- withoutWhiteBoundsCount_ - _indBeforeArrow);
        anonymous_.setType(block_);
        anonymous_.setNext(k_);
        _curElts.getRes().getAnonymousResults().add(anonymous_);
        return k_;
    }

    private static int resAnonLambda(ResultCreation _res, ParsedFctHeader _parse, int _i, InputTypeCreation _input, CurrentExpElts _curElts) {
        AnonymousResult anonymous_ = resAnon(_i,_input,_res);
        anonymous_.setResults(new ParsedFctHeaderResult(_parse));
        _curElts.getRes().getAnonymousResults().add(anonymous_);
        return _res.getNextIndex();
    }

    private static int resAnonSwitch(int _i,InputTypeCreation _input, CurrentExpElts _curElts, ResultCreation _res, String _retSwitch) {
        AnonymousResult anonymous_ = resAnon(_i,_input, _res);
        anonymous_.setRetSwitch(_retSwitch);
        _curElts.getRes().getAnonymousResults().add(anonymous_);
        return _res.getNextIndex();
    }

    private static int resAnonType(int _i,InputTypeCreation _input, CurrentExpElts _curElts, ResultCreation _res) {
        AnonymousResult anonymous_ = resAnon(_i,_input, _res);
        _curElts.getRes().getAnonymousResults().add(anonymous_);
        return _res.getNextIndex();
    }

    private static AnonymousResult resAnon(int _i,InputTypeCreation _input, ResultCreation _res) {
        int j_ = _res.getNextIndex() - 1;
        AnonymousResult anonymous_ = new AnonymousResult();
        anonymous_.setIndex(_i);
        anonymous_.setUntil(j_);
        anonymous_.setLength(j_- _input.getNextIndexBef() +1);
        anonymous_.setType(_res.getBlock());
        anonymous_.setNext(j_ + 1);
        return anonymous_;
    }
    private static int incr(int _i, char _curChar, String _string, StackDelimiters _stack, StackOperators _parsBrackets) {
        if (_curChar == ElResolver.PAR_RIGHT) {
            if (_parsBrackets.isEmpty()) {
                return -1;
            }
            ElResolverCommon.tryAddStringParts(_parsBrackets, _i, _stack);
            ElResolverCommon.tryAddAnnotationsParts(_parsBrackets, _stack);
            return incrOpsUnstack(_i, _string, _parsBrackets);
        }
        if (_curChar == ElResolver.ANN_ARR_RIGHT) {
            return classicEndDel(_i, _string, _parsBrackets);
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
            return incrOpsStack(_parsBrackets, _i, _curChar, _string);
        }
        if (_curChar == ElResolver.ARR_RIGHT) {
            return classicEndDel(_i, _string, _parsBrackets);
        }
        if (_curChar == ElResolver.BEGIN_TERNARY&&ElResolverCommon.isTernary(_string, _string.length(), _i)) {
            return incrOpsStack(_parsBrackets, _i,_curChar,_string);
        }
        if (_curChar == ElResolver.END_TERNARY) {
            return classicEndDel(_i, _string, _parsBrackets);
        }
        return incrOps(_string, _i);
    }

    private static int classicEndDel(int _i, String _string, StackOperators _parsBrackets) {
        if (_parsBrackets.isEmpty()) {
            return -1;
        }
        return incrOpsUnstack(_i, _string, _parsBrackets);
    }

    private static int incrOpsUnstack(int _i, String _string, StackOperators _parsBrackets) {
        _parsBrackets.removeLast();
        return incrOps(_string, _i);
    }

    private static int incrOps(String _string, int _i) {
        IncrOperatorPart incr_ = new IncrOperatorPart();
        return incr_.tryAddOp(-1,_string,_i);
    }

}
