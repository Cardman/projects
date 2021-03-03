package code.maths.litteral;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;


public final class MathResolver {

    static final int BAD_PRIO = -1;
    static final int OR_PRIO = 1;
    static final int AND_PRIO = 2;
    static final int EQ_PRIO = 3;
    static final int CMP_PRIO = 4;
    static final int ADD_PRIO = 5;
    static final int MULT_PRIO = 6;
    static final int UNARY_PRIO = 7;
    static final int FCT_OPER_PRIO = 8;

    static final String TRUE = "V";


    static final String FALSE = "F";
    static final char DOT = '.';
    static final char SEP_RATE = '/';
    static final char PAR_LEFT = '(';
    static final char PAR_RIGHT = ')';
    static final char SEP_ARG = ',';
    static final char DELIMITER_STRING_BEGIN = '{';

    static final char NEG_BOOL_CHAR = '!';

    static final char MULT_CHAR= '*';
    static final char DIV_CHAR= ':';
    static final char PLUS_CHAR= '+';

    static final char MINUS_CHAR = '-';

    static final char LOWER_CHAR = '<';

    static final char GREATER_CHAR = '>';

    static final char EQ_CHAR = '=';

    static final char AND_CHAR = '&';

    static final char OR_CHAR = '|';

    private MathResolver(){
    }

    static MbDelimiters checkSyntax(String _string, ErrorStatus _error) {
        MbDelimiters d_ = new MbDelimiters();
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < len_) {
            if (!StringUtil.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        int beginIndex_ = i_;
        if (i_ >= len_) {
            _error.setIndex(i_);
            _error.setError(true);
            _error.setString(_string);
            return d_;
        }
        MathStringState m_ = new MathStringState();
        StringBuilder elt_ = new StringBuilder();
        while (m_.getIndex() < len_) {
            loop(_string, _error, d_, len_, beginIndex_, m_, elt_);
        }
        return redirect(_string, _error, d_, m_.getParsBrackets(), m_.isConstString(), m_.getIndex());
    }

    private static void loop(String _string, ErrorStatus _error, MbDelimiters _d, int _len, int _beginIndex, MathStringState _m, StringBuilder _elt) {
        char curChar_ = _string.charAt(_m.getIndex());
        if (_m.isConstString()) {
            procStr(_string, _error, _d, _len, _m, _elt, curChar_);
            return;
        }
        if (MathExpUtil.isWordChar(curChar_)) {
            _m.setIndex(processWordChar(_string, _d, _len, _m.getIndex(), curChar_));
            return;
        }
        if (curChar_ == DOT) {
            int j_ = addNumberInfo(_d, _m.getIndex() + 1, _m.getIndex(), _string);
            _d.getNbInfos().last().insert(0,DOT);
            _m.setIndex(j_);
            return;
        }
        if (curChar_ == DELIMITER_STRING_BEGIN) {
            _m.setConstString();
            _d.getDelStringsChars().add(_m.getIndex());
            _d.getStringInfo().add(new StringList());
        }
        if (curChar_ == PAR_LEFT) {
            _m.setParsBrackets(_m.getParsBrackets()+1);
        }
        if (curChar_ == PAR_RIGHT) {
            if (_m.getParsBrackets()==0) {
                _error.setIndex(_m.getIndex());
                _error.setError(true);
                _error.setString(_string);
                _m.setIndex(_len);
                return;
            }
            _m.setParsBrackets(_m.getParsBrackets()-1);
        }
        if (curChar_ == SEP_ARG && _m.getParsBrackets()==0) {
            _error.setIndex(_m.getIndex());
            _error.setError(true);
            _error.setString(_string);
            _m.setIndex(_len);
            return;
        }
        _m.setIndex(procOper(_string, _d, _len, _m.getIndex(), _beginIndex, curChar_));
    }

    private static void procStr(String _string, ErrorStatus _error, MbDelimiters _d, int _len, MathStringState _m, StringBuilder _elt, char _curChar) {
        if (_m.exit(_elt, _d, _curChar, _string, _error)) {
            _m.setIndex(_len);
        }
    }

    private static MbDelimiters redirect(String _string, ErrorStatus _error, MbDelimiters _d, int _parsBrackets, boolean _constString, int _i) {
        if (_constString) {
            _error.setIndex(_i);
            _error.setError(true);
            _error.setString(_string);
            return _d;
        }
        if (_parsBrackets != 0) {
            _error.setIndex(_i);
            _error.setError(true);
            _error.setString(_string);
            return _d;
        }
        return _d;
    }

    private static int procOper(String _string, MbDelimiters _d, int _len, int _i, int _beginIndex, char _curChar) {
        int i_ = _i;
        boolean escapeOpers_ = false;
        boolean addOp_ = true;
        if (_curChar == PLUS_CHAR || _curChar == MINUS_CHAR || _curChar == NEG_BOOL_CHAR) {
            escapeOpers_ = true;
            if (_beginIndex == i_) {
                addOp_ = false;
            }
        }
        if (normalOp(_curChar)) {
            escapeOpers_ = true;
        }
        if (escapeOpers_) {
            return procEscOp(_string, _d, _len, i_, addOp_);
        }
        if (_curChar == PAR_RIGHT) {
            _d.getAllowedOperatorsIndexes().add(i_);
        }
        i_++;
        return i_;
    }

    private static boolean normalOp(char _curChar) {
        return mult(_curChar) || andOr(_curChar) || cmp(_curChar) || call(_curChar);
    }

    private static boolean call(char _curChar) {
        return _curChar == PAR_LEFT || _curChar == SEP_ARG;
    }

    private static boolean cmp(char _curChar) {
        return _curChar == LOWER_CHAR || _curChar == GREATER_CHAR || _curChar == EQ_CHAR;
    }

    private static boolean andOr(char _curChar) {
        return _curChar == AND_CHAR || _curChar == OR_CHAR;
    }

    private static boolean mult(char _curChar) {
        return _curChar == MULT_CHAR || _curChar == DIV_CHAR;
    }

    private static int procEscOp(String _string, MbDelimiters _d, int _len, int _i, boolean _addOp) {
        int j_ = _i + 1;
        if (j_ < _len && _string.charAt(j_) == EQ_CHAR) {
            j_++;
        }
        while (j_ < _len) {
            char curLoc_ = _string.charAt(j_);
            if (!StringUtil.isWhitespace(curLoc_) && curLoc_ != PLUS_CHAR && curLoc_ != MINUS_CHAR && curLoc_ != NEG_BOOL_CHAR) {
                break;
            }
            j_++;
        }
        if (_addOp) {
            _d.getAllowedOperatorsIndexes().add(_i);
        }
        return j_;
    }

    private static int processWordChar(String _string, MbDelimiters _d, int _len, int _from, char _curChar) {
        if (MathExpUtil.isDigit(_curChar)) {
            return addNumberInfo(_d, _from, _from, _string);
        }
        MbVariableInfo var_ = new MbVariableInfo();
        var_.setFirstChar(_from);
        StringBuilder name_ = new StringBuilder();
        int i_ = _from;
        while (i_ < _len) {
            char last_ = _string.charAt(i_);
            if (!MathExpUtil.isWordChar(last_)) {
                break;
            }
            name_.append(last_);
            i_++;
        }
        var_.setLastChar(i_);
        var_.setName(name_.toString());
        _d.getVariables().add(var_);
        return i_;
    }

    private static int addNumberInfo(MbDelimiters _d, int _from, int _begin, String _string) {
        StringBuilder nbInfo_ = new StringBuilder();
        int len_ = _string.length();
        int i_ = _from;
        boolean stop_ = false;
        while (i_ < len_) {
            char cur_ = _string.charAt(i_);
            if (!MathExpUtil.isDigit(cur_)) {
                if (cur_ == DOT || cur_ == SEP_RATE) {
                    nbInfo_.append(cur_);
                    i_++;
                } else {
                    stop_ = true;
                }
                break;
            }
            nbInfo_.append(cur_);
            i_++;
        }
        if (i_ >= len_ || stop_) {
            _d.getDelNumbers().add(_begin);
            _d.getDelNumbers().add(i_);
            _d.getNbInfos().add(nbInfo_);
            return i_;
        }
        while (i_ < len_) {
            char cur_ = _string.charAt(i_);
            if (!MathExpUtil.isDigit(cur_)) {
                break;
            }
            nbInfo_.append(cur_);
            i_++;
        }
        _d.getDelNumbers().add(_begin);
        _d.getDelNumbers().add(i_);
        _d.getNbInfos().add(nbInfo_);
        return i_;
    }
    static MbOperationsSequence getOperationsSequence(int _offset, String _string,
                                                      MbDelimiters _d) {
        int len_ = _string.length();
        int i_ = StringUtil.getFirstPrintableCharIndex(_string);
        if (i_ < 0) {
            MbOperationsSequence op_ = new MbOperationsSequence();
            op_.setOperators(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        int lastPrintChar_ = len_ - 1;
        while (StringUtil.isWhitespace(_string.charAt(lastPrintChar_))) {
            lastPrintChar_--;
        }
        len_ = lastPrintChar_+1;
        int begin_ = _d.getDelStringsChars().indexOfNb((long) i_ + _offset);
        int end_ = _d.getDelStringsChars().indexOfNb((long) lastPrintChar_ + _offset);
        if (delimits(begin_, end_)) {
            MbOperationsSequence op_ = new MbOperationsSequence();
            op_.setIndexCst(begin_/2);
            op_.setConstType(MbConstType.STRING);
            op_.setOperators(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        begin_ = _d.getDelNumbers().indexOfNb((long)_offset + i_);
        end_ = _d.getDelNumbers().indexOfNb((long)_offset + lastPrintChar_ + 1L);
        if (delimits(begin_, end_)) {
            MbOperationsSequence op_ = new MbOperationsSequence();
            op_.setIndexCst(begin_/2);
            op_.setConstType(MbConstType.NUMBER);
            op_.setOperators(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        String sub_ = _string.substring(i_, len_);
        if (trFalse(sub_)) {
            MbOperationsSequence op_ = new MbOperationsSequence();
            op_.setOperators(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        for (MbVariableInfo v: _d.getVariables()) {
            if (v.getFirstChar() == _offset + i_) {
                int iVar_ = v.getLastChar();
                if (iVar_ != _offset + lastPrintChar_ + 1) {
                    break;
                }
                MbOperationsSequence op_ = new MbOperationsSequence();
                op_.setConstType(MbConstType.LOC_VAR);
                op_.setOperators(new StrTypes());
                op_.setupValue(v.getName());
                return op_;
            }
        }
        MathAfUnaryParts mat_ = new MathAfUnaryParts(_string,i_, lastPrintChar_);
        while (mat_.getIndex() < len_) {
            mat_.loop(_offset, _string, _d);
        }
        MbOperationsSequence op_ = new MbOperationsSequence();
        op_.setPriority(mat_.getPrio());
        op_.setOperators(mat_.getOperators());
        op_.setFctName(mat_.getFctName());
        op_.setupValues(_string);
        return op_;
    }

    private static boolean trFalse(String _sub) {
        return StringUtil.quickEq(_sub,TRUE) || StringUtil.quickEq(_sub, FALSE);
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin > IndexConstants.INDEX_NOT_FOUND_ELT && _begin + 1 == _end;
    }

}
