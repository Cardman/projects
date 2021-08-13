package code.bean.nat.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.analyze.AnalyzingDoc;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;


public final class NatElResolver {

    public static final int AFF_PRIO = 3;
    public static final int UNARY_PRIO = 17;
    public static final int FCT_OPER_PRIO = 19;

    static final char ARR_LEFT = '[';
    static final char ARR_RIGHT = ']';
    static final char ANN_ARR_RIGHT = '}';
    static final char PAR_LEFT = '(';
    static final char PAR_RIGHT = ')';
    static final char SEP_ARG = ',';


    static final String ARR = "[";

    static final char NEG_BOOL_CHAR = '!';

    static final char EQ_CHAR = '=';



    private NatElResolver() {
    }

    public static Delimiters checkSyntaxDelimiters(String _string, int _minIndex, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        Delimiters d_ = new Delimiters();
        d_.setPartOfString(true);
        NatFullFieldRetriever ret_ = new NatFullFieldRetriever(d_, _page);
        return commonCheck(_string, _minIndex, ret_, d_,_anaDoc, _page);
    }

    public static Delimiters checkSyntax(String _string, int _elOffest, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        Delimiters d_ = new Delimiters();
        d_.setLength(_string.length());
        NatFullFieldRetriever ret_ = new NatFullFieldRetriever(d_, _page);
        return commonCheck(_string, _elOffest, ret_, d_,_anaDoc, _page);
    }

    private static Delimiters commonCheck(String _string, int _minIndex, NatFullFieldRetriever _ret, Delimiters _d, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        boolean partOfString_ = _d.isPartOfString();

        StackOperators parsBrackets_;
        parsBrackets_ = new StackOperators();
        ResultAfterOperators resOpers_ = new ResultAfterOperators();
        resOpers_.setParsBrackets(parsBrackets_);
        resOpers_.setPartOfString(partOfString_);
        int len_ = _string.length();
        int i_ = _minIndex;
        int lastDoubleDot_ = i_;
        boolean beginOrEnd_ = false;
        boolean ctorCall_ = false;
        int nbChars_ = 0;
        ResultAfterInstKeyWord resKeyWords_ = new ResultAfterInstKeyWord();
        resKeyWords_.setNextIndex(i_);
        ResultAfterDoubleDotted resWords_ = new ResultAfterDoubleDotted();
        resWords_.setNextIndex(i_);
        resWords_.setLastDoubleDot(i_);
        resWords_.setCallCtor(false);
        resOpers_.setDoubleDotted(resWords_);
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            resKeyWords_.setNextIndex(i_);
            resKeyWords_.setCallCtor(ctorCall_);
            processAfterInstuctionKeyWord(_string, resKeyWords_, _anaDoc, _page);
            int nextInd_ = resKeyWords_.getNextIndex();
            if (nextInd_ > i_) {
                i_ = nextInd_;
                ctorCall_ = resKeyWords_.isCallCtor();
            } else {
                if (StringExpUtil.isTypeLeafChar(curChar_)) {
                    resWords_.setNextIndex(i_);
                    resWords_.setLastDoubleDot(lastDoubleDot_);
                    resWords_.setCallCtor(ctorCall_);
                    processWords(_string, _d, _ret,resWords_);
                    nextInd_ = resWords_.getNextIndex();
                    lastDoubleDot_ = resWords_.getLastDoubleDot();
                }
                if (nextInd_ > i_) {
                    i_ = nextInd_;
                } else {
                    resOpers_.setPartOfString(partOfString_);
                    resOpers_.setBeginOrEnd(beginOrEnd_);
                    resOpers_.setConstTextChar(false);
                    resOpers_.setConstTextString(false);
                    resOpers_.setConstChar(false);
                    resOpers_.setConstString(false);
                    resOpers_.setConstText(false);
                    resOpers_.setNbChars(nbChars_);
                    resOpers_.getDoubleDotted().setNextIndex(i_);
                    resOpers_.getDoubleDotted().setLastDoubleDot(lastDoubleDot_);
                    resOpers_.getDoubleDotted().setCallCtor(ctorCall_);
                    processOperators(_string, _d, resOpers_);
                    beginOrEnd_ = resOpers_.isBeginOrEnd();
                    nbChars_ = resOpers_.getNbChars();
                    partOfString_ = resOpers_.isPartOfString();

                    i_ = resOpers_.getDoubleDotted().getNextIndex();
                    lastDoubleDot_ = resOpers_.getDoubleDotted().getLastDoubleDot();
                    ctorCall_ = resOpers_.getDoubleDotted().isCallCtor();
                }
            }
        }
        return _d;
    }

    private static void processAfterInstuctionKeyWord(String _string, ResultAfterInstKeyWord _out, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        int i_ = _out.getNextIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        if (_anaDoc.isInternGlobal() && StringExpUtil.startsWithKeyWord(_string, i_, keyWordIntern_)) {
            int afterSuper_ = i_ + keyWordIntern_.length();
            _out.setNextIndex(_string.indexOf('.', afterSuper_));
        }
    }

    private static void processWords(String _string, Delimiters _d, NatFullFieldRetriever _ret, ResultAfterDoubleDotted _out) {
        int len_ = _string.length();
        int i_ = _out.getNextIndex();
        ResultAfterInstKeyWord resTmp_ = new ResultAfterInstKeyWord();
        resTmp_.setNextIndex(i_);
        int beginWord_ = i_;
        while (i_ < len_) {
            char locChar_ = _string.charAt(i_);
            if (!StringExpUtil.isTypeLeafChar(locChar_)) {
                break;
            }
            i_++;
        }
        String word_ = _string.substring(beginWord_, i_);
        int nextPar_ = StringExpUtil.nextPrintCharIs(i_, len_, _string, PAR_LEFT);
        if (nextPar_ > -1) {
            _d.getStack().getCallings().add(nextPar_);
            _out.setNextIndex(i_);
            return;
        }
        int bk_ = StringExpUtil.getBackPrintChar(_string, beginWord_);
        if (StringExpUtil.nextCharIs(_string,bk_,len_,'.')) {
            ConstType type_;
            type_ = ConstType.WORD;
            VariableInfo info_ = new VariableInfo();
            info_.setKind(type_);
            info_.setFirstChar(beginWord_);
            info_.setLastChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
            _out.setNextIndex(i_);
            return;
        }
        _ret.processFieldsStaticAccess(beginWord_, word_, i_);
        _out.setNextIndex(i_);
    }

    private static void processOperators(String _string,
                                         Delimiters _dout, ResultAfterOperators _out) {
        StackOperators parsBrackets_;
        parsBrackets_ = _out.getParsBrackets();

        int len_ = _string.length();
        ResultAfterDoubleDotted doubleDotted_ = _out.getDoubleDotted();
        int i_ = doubleDotted_.getNextIndex();
        char curChar_ = _string.charAt(i_);
        StackDelimiters stack_ = _dout.getStack();
        if (curChar_ == PAR_LEFT) {
            int j_ = indexAfterPossibleCast(_string, i_, _dout);
            if (j_ > i_) {
                i_ = j_;
                doubleDotted_.setNextIndex(i_);
                return;
            }
            stack_.getCallings().add(i_);
            parsBrackets_.addEntry(i_, curChar_);
        }
        if (curChar_ == PAR_RIGHT) {
            parsBrackets_.removeLast();
        }
        if (curChar_ == ANN_ARR_RIGHT) {
            _out.setPartOfString(false);
            _dout.setIndexEnd(i_-1);
            doubleDotted_.setNextIndex(len_);
            return;
        }
        boolean escapeOpers_ = false;
        boolean addOp_ = true;
        if (curChar_ == EQ_CHAR) {
            escapeOpers_ = true;
        }
        if (curChar_ == NEG_BOOL_CHAR) {
            escapeOpers_ = true;
            addOp_ = false;
        }
        if (curChar_ == PAR_LEFT) {
            escapeOpers_ = true;
        }
        if (curChar_ == SEP_ARG) {
            escapeOpers_ = true;
        }
        if (escapeOpers_) {
            int j_ = i_ + 1;
            int k_ = indexAfterPossibleCast(_string, j_, _dout);
            if (k_ == j_) {
                stack_.getCallings().add(k_);
            }
            j_ = k_;
            if (addOp_) {
                _dout.getAllowedOperatorsIndexes().add(i_);
            }
            i_ = j_;
            doubleDotted_.setNextIndex(i_);
            return;
        }
        _dout.getAllowedOperatorsIndexes().add(i_);
        i_++;
        doubleDotted_.setNextIndex(i_);
    }

    public static NatOperationsSequence getOperationsSequence(int _offset, String _string,
                                                              Delimiters _d) {
        NatOperationsSequence seq_ = tryGetSequence(_offset, _string, _d);
        if (seq_ != null) {
            return seq_;
        }
        NatExpPartDelimiters del_ = new NatExpPartDelimiters(_string);
        int lastPrintChar_ = del_.getLastPrintIndex();
        int len_ = lastPrintChar_ + 1;
        NatAfterUnaryParts af_ = new NatAfterUnaryParts(_string);
        int i_ = af_.getIndex();
        while (i_ < len_) {
            af_.setState(_offset,_string,_d);
            i_ = af_.getIndex();
        }
        int prio_ = af_.getPrio();
        StrTypes operators_;
        operators_ = af_.getOperators();
        String fctName_ = af_.getFctName();
        NatOperationsSequence op_ = new NatOperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setFctName(fctName_);
        op_.setupValues(_string);
        op_.setDelimiter(_d);
        return op_;
    }

    private static NatOperationsSequence tryGetSequence(int _offset, String _string,
                                                        Delimiters _d) {
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        int lastPrintChar_ = len_ - 1;
        for (VariableInfo v: _d.getVariables()) {
            if (v.getFirstChar() == _offset + i_) {
                int iVar_ = v.getLastChar();
                if (iVar_ != _offset + lastPrintChar_ + 1) {
                    break;
                }
                NatOperationsSequence op_ = new NatOperationsSequence();
                op_.setConstType(v.getKind());
                op_.setOperators(new StrTypes());
                op_.setValue(v.getName(), i_);
                op_.setDelimiter(_d);
                return op_;
            }
        }
        int begin_ = _d.getDelLoopVars().indexOfNb((long)_offset + i_);
        int end_ = _d.getDelLoopVars().indexOfNb((long)_offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            int indexLeftArr_ = _string.indexOf(ARR_LEFT);
            int indexRightArr_ = _string.lastIndexOf(ARR_RIGHT);
            String name_ = _string.substring(indexLeftArr_+1, indexRightArr_);
            NatOperationsSequence op_ = new NatOperationsSequence();
            op_.setConstType(ConstType.LOOP_INDEX);
            op_.setOperators(new StrTypes());
            op_.setValue(name_, i_);
            op_.setDelimiter(_d);
            return op_;
        }
        return null;
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin + 1 == _end;
    }

    private static int indexAfterPossibleCast(String _string, int _from, Delimiters _d) {
        int indexParRight_ = _string.indexOf(PAR_RIGHT, _from +1);
        if (_d.getStack().getCallings().containsObj(_from)||indexParRight_ <0) {
            return _from;
        }

        String sub_ = _string.substring(_from + 1, indexParRight_);
        String subTrim_ = sub_.trim();
        if (subTrim_.startsWith(ARR)) {
            _d.getDelLoopVars().add(_from);
            _d.getDelLoopVars().add(indexParRight_);
            return indexParRight_ + 1;
        }
        return _from;
    }

}
