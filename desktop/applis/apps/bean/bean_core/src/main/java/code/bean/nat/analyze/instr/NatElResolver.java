package code.bean.nat.analyze.instr;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.StackOperators;
import code.expressionlanguage.common.StringExpUtil;
import code.formathtml.analyze.AnalyzingDoc;
import code.maths.litteralcom.StrTypes;
import code.util.Ints;
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

    public static NatDelimiters checkSyntax(String _string, int _elOffest, AnalyzingDoc _anaDoc) {
        NatDelimiters d_ = new NatDelimiters();
        return commonCheck(_string, _elOffest, d_,_anaDoc);
    }

    private static NatDelimiters commonCheck(String _string, int _minIndex, NatDelimiters _d, AnalyzingDoc _anaDoc) {

        StackOperators parsBrackets_;
        parsBrackets_ = new StackOperators();
        int len_ = _string.length();
        int i_ = _minIndex;
        Ints callings_ = new Ints();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            int nextInd_ = processAfterInstuctionKeyWord(_string, _anaDoc, i_);
            if (nextInd_ > i_) {
                i_ = nextInd_;
            } else {
                if (StringExpUtil.isTypeLeafChar(curChar_)) {
                    nextInd_ = processWords(_string, _d, i_, callings_);
                }
                if (nextInd_ > i_) {
                    i_ = nextInd_;
                } else {
                    i_ = processOperators(_string, _d, i_, parsBrackets_, callings_);
                }
            }
        }
        return _d;
    }

    private static int processAfterInstuctionKeyWord(String _string, AnalyzingDoc _anaDoc, int _nextIndex) {
        if (_anaDoc.isInternGlobal() && StringExpUtil.startsWithKeyWord(_string, _nextIndex, NatRenderAnalysis.INTERN)) {
            int afterSuper_ = _nextIndex + NatRenderAnalysis.INTERN.length();
            return _string.indexOf('.', afterSuper_);
        }
        return _nextIndex;
    }

    private static int processWords(String _string, NatDelimiters _d, int _nextIndex, Ints _callings) {
        int len_ = _string.length();
        int i_ = _nextIndex;
        int beginWord_ = i_;
        i_ = ElResolver.incrAfterWord(i_,_string);
        String word_ = _string.substring(beginWord_, i_);
        int nextPar_ = StringExpUtil.nextPrintCharIs(i_, len_, _string, PAR_LEFT);
        if (nextPar_ > -1) {
            _callings.add(nextPar_);
        } else {
            NatVariableInfo info_ = new NatVariableInfo();
            info_.setFirstNatChar(beginWord_);
            info_.setLastNatChar(i_);
            info_.setName(word_);
            _d.getVariables().add(info_);
        }
        return i_;
    }

    private static int processOperators(String _string,
                                        NatDelimiters _dout, int _nextIndex, StackOperators _parsBrackets, Ints _callings) {
        StackOperators parsBrackets_;
        parsBrackets_ = _parsBrackets;

        int len_ = _string.length();
        int i_ = _nextIndex;
        char curChar_ = _string.charAt(i_);
        if (curChar_ == PAR_LEFT) {
            int j_ = indexAfterPossibleCast(_string, i_, _dout, _callings);
            if (j_ > i_) {
                i_ = j_;
                return i_;
            }
            _callings.add(i_);
            parsBrackets_.addEntry(i_, curChar_);
        }
        if (curChar_ == PAR_RIGHT) {
            parsBrackets_.removeLast();
        }
        if (curChar_ == ANN_ARR_RIGHT) {
            _dout.setIndexEnd(i_-1);
            return len_;
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
            int k_ = indexAfterPossibleCast(_string, j_, _dout, _callings);
            if (k_ == j_) {
                _callings.add(k_);
            }
            j_ = k_;
            if (addOp_) {
                _dout.getAllowedOperatorsIndexes().add(i_);
            }
            i_ = j_;
            return i_;
        }
        _dout.getAllowedOperatorsIndexes().add(i_);
        i_++;
        return i_;
    }

    public static NatOperationsSequence getOperationsSequence(int _offset, String _string,
                                                              NatDelimiters _d) {
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
        op_.setPrioNat(prio_);
        op_.setOpersNat(operators_);
        op_.setFctName(fctName_);
        op_.setupValues(_string);
        op_.setDelimiterNat(_d);
        return op_;
    }

    private static NatOperationsSequence tryGetSequence(int _offset, String _string,
                                                        NatDelimiters _d) {
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        int lastPrintChar_ = len_ - 1;
        for (NatVariableInfo v: _d.getVariables()) {
            if (v.getFirstNatChar() == _offset + i_) {
                int iVar_ = v.getLastNatChar();
                if (iVar_ != _offset + lastPrintChar_ + 1) {
                    break;
                }
                NatOperationsSequence op_ = new NatOperationsSequence();
                op_.setOpersNat(new StrTypes());
                op_.setValue(v.getName(), i_);
                op_.setDelimiterNat(_d);
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
            op_.setVarIndex(true);
            op_.setOpersNat(new StrTypes());
            op_.setValue(name_, i_);
            op_.setDelimiterNat(_d);
            return op_;
        }
        return null;
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin + 1 == _end;
    }

    private static int indexAfterPossibleCast(String _string, int _from, NatDelimiters _d, Ints _callings) {
        int indexParRight_ = _string.indexOf(PAR_RIGHT, _from +1);
        if (_callings.containsObj(_from)||indexParRight_ <0) {
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
