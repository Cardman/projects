package code.maths.litteral;
import code.maths.litteraladv.MaParser;
import code.maths.litteralcom.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;


public final class MathResolver {

    static final String TRUE = "V";


    static final String FALSE = "F";
    static final char DELIMITER_STRING_BEGIN = '{';

    private MathResolver(){
    }

    static MbDelimiters checkSyntax(String _string, ErrorStatus _error) {
        MbDelimiters d_ = new MbDelimiters();
        int len_ = _string.length();
        int i_ = MaParser.skipWhite(_string,len_,0);
        if (i_ >= len_) {
            _error.setIndex(i_);
            _error.setError(true);
            _error.setString(_string);
            return d_;
        }
        MathStringState m_ = new MathStringState();
        StringBuilder elt_ = new StringBuilder();
        while (m_.getIndex() < len_) {
            loop(_string, _error, d_, len_, i_, m_, elt_);
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
        if (curChar_ == MatCommonCst.DOT) {
            int j_ = addNumberInfo(_d, _m.getIndex() + 1, _m.getIndex(), _string);
            _d.getNbInfos().last().insert(0, MatCommonCst.DOT);
            _m.setIndex(j_);
            return;
        }
        if (curChar_ == DELIMITER_STRING_BEGIN) {
            _m.setConstString();
            _d.getDelStringsChars().add(_m.getIndex());
            _d.getStringInfo().add(new StringList());
        }
        if (curChar_ == MatCommonCst.PAR_LEFT) {
            _m.setParsBrackets(_m.getParsBrackets()+1);
        }
        if (curChar_ == MatCommonCst.PAR_RIGHT) {
            if (_m.getParsBrackets()==0) {
                _error.setIndex(_m.getIndex());
                _error.setError(true);
                _error.setString(_string);
                _m.setIndex(_len);
                return;
            }
            _m.setParsBrackets(_m.getParsBrackets()-1);
        }
        if (curChar_ == MatCommonCst.SEP_ARG && _m.getParsBrackets()==0) {
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
        if (MathExpUtil.unary(_curChar)) {
            escapeOpers_ = true;
            if (_beginIndex == i_) {
                addOp_ = false;
            }
        }
        if (MathExpUtil.normalOp(_curChar)) {
            escapeOpers_ = true;
        }
        if (escapeOpers_) {
            return procEscOp(_string, _d, _len, i_, addOp_);
        }
        if (_curChar == MatCommonCst.PAR_RIGHT) {
            _d.getAllowedOperatorsIndexes().add(i_);
        }
        i_++;
        return i_;
    }

    private static int procEscOp(String _string, MbDelimiters _d, int _len, int _i, boolean _addOp) {
        int j_ = _i + 1;
        if (j_ < _len && _string.charAt(j_) == MatCommonCst.EQ_CHAR) {
            j_++;
        }
        while (j_ < _len) {
            char curLoc_ = _string.charAt(j_);
            if (!StringUtil.isWhitespace(curLoc_) && curLoc_ != MatCommonCst.PLUS_CHAR && curLoc_ != MatCommonCst.MINUS_CHAR && curLoc_ != MatCommonCst.NEG_BOOL_CHAR) {
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
        MatVariableInfo var_ = new MatVariableInfo();
        var_.setFirstChar(_from);
        MatVariableResult res_ = MatVariableResult.build(_string, _len, _from);
        int i_ = res_.getIndex();
        var_.setLastChar(i_);
        var_.setName(res_.getName());
        _d.getVariables().add(var_);
        return i_;
    }

    private static int addNumberInfo(MbDelimiters _d, int _from, int _begin, String _string) {
        MatNumberResult res_ = MatNumberResult.build(_from, _string);
        int i_ = res_.getIndex();
        _d.getDelNumbers().add(_begin);
        _d.getDelNumbers().add(i_);
        _d.getNbInfos().add(res_.getNbInfo());
        return i_;
    }
    static MbOperationsSequence getOperationsSequence(int _offset, String _string,
                                                      MbDelimiters _d) {
        int i_ = StringUtil.getFirstPrintableCharIndex(_string);
        if (i_ < 0) {
            MbOperationsSequence op_ = new MbOperationsSequence();
            op_.setOperators(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        int lastPrintChar_ = StringUtil.getLastPrintableCharIndex(_string);
        int len_ = lastPrintChar_ + 1;
        int begin_ = _d.getDelStringsChars().indexOfNb((long) i_ + _offset);
        int end_ = _d.getDelStringsChars().indexOfNb((long) lastPrintChar_ + _offset);
        if (delimits(begin_, end_)) {
            MbOperationsSequence op_ = new MbOperationsSequence();
            op_.setIndexCst(begin_/2);
            op_.setConstType(MatConstType.STRING);
            op_.setOperators(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        begin_ = _d.getDelNumbers().indexOfNb((long)_offset + i_);
        end_ = _d.getDelNumbers().indexOfNb((long)_offset + lastPrintChar_ + 1L);
        if (delimits(begin_, end_)) {
            MbOperationsSequence op_ = new MbOperationsSequence();
            op_.setIndexCst(begin_/2);
            op_.setConstType(MatConstType.NUMBER);
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
        for (MatVariableInfo v: _d.getVariables()) {
            if (v.getFirstChar() == _offset + i_) {
                int iVar_ = v.getLastChar();
                if (iVar_ != _offset + lastPrintChar_ + 1) {
                    break;
                }
                MbOperationsSequence op_ = new MbOperationsSequence();
                op_.setConstType(MatConstType.LOC_VAR);
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
        op_.ad();
        return op_;
    }

    private static boolean trFalse(String _sub) {
        return StringUtil.quickEq(_sub,TRUE) || StringUtil.quickEq(_sub, FALSE);
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin > IndexConstants.INDEX_NOT_FOUND_ELT && _begin + 1 == _end;
    }

}
