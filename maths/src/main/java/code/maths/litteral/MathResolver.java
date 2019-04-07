package code.maths.litteral;
import code.util.*;


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

    static final String EMPTY_STRING = "";
    static final String TRUE = "V";


    static final String FALSE = "F";
    private static final char DOT = '.';
    private static final char SEP_RATE = '/';
    private static final char ESCAPE_META_CHAR = '\\';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char DELIMITER_STRING_BEGIN = '{';
    private static final char DELIMITER_STRING_SEP = ';';
    private static final char DELIMITER_STRING_END = '}';

    private static final char NEG_BOOL_CHAR = '!';

    private static final char MULT_CHAR= '*';
    private static final char DIV_CHAR= ':';
    private static final char PLUS_CHAR= '+';

    private static final char MINUS_CHAR = '-';

    private static final char LOWER_CHAR = '<';

    private static final char GREATER_CHAR = '>';

    private static final char EQ_CHAR = '=';

    private static final char AND_CHAR = '&';

    private static final char OR_CHAR = '|';

    private MathResolver(){
    }

    static Delimiters checkSyntax(String _string, ErrorStatus _error) {
        Delimiters d_ = new Delimiters();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        boolean constString_ = false;
        boolean escapedMeta_ = false;
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
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
        i_ = CustList.FIRST_INDEX;
        StringBuilder elt_ = new StringBuilder();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (constString_) {
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        if (i_ + 1 >= len_) {
                            _error.setIndex(i_ + 1);
                            _error.setError(true);
                            _error.setString(_string);
                            return d_;
                        }
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING_END) {
                        d_.getDelStringsChars().add(i_);
                        d_.getStringInfo().last().add(elt_.toString());
                        elt_.delete(0, elt_.length());
                        constString_ = false;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING_SEP) {
                        d_.getStringInfo().last().add(elt_.toString());
                        elt_.delete(0, elt_.length());
                    } else {
                        elt_.append(curChar_);
                    }
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING_END) {
                    elt_.append(curChar_);
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING_SEP) {
                    elt_.append(curChar_);
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == ESCAPE_META_CHAR) {
                    elt_.append(curChar_);
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                _error.setIndex(i_);
                _error.setError(true);
                _error.setString(_string);
                return d_;
            }
            if (StringList.isWordChar(curChar_)) {
                if (Character.isDigit(curChar_)) {
                    i_ = addNumberInfo(d_,i_,i_,_string);
                    continue;
                }
                VariableInfo var_ = new VariableInfo();
                var_.setFirstChar(i_);
                StringBuilder name_ = new StringBuilder();
                while (i_ < len_) {
                    char last_ = _string.charAt(i_);
                    if (!StringList.isWordChar(last_)) {
                        break;
                    }
                    name_.append(last_);
                    i_++;
                }
                var_.setLastChar(i_);
                var_.setName(name_.toString());
                d_.getVariables().add(var_);
                continue;
            }
            if (curChar_ == DOT) {
                int j_ = addNumberInfo(d_, i_ + 1, i_,_string);
                d_.getNbInfos().last().insert(0,DOT);
                i_ = j_;
                continue;
            }
            if (curChar_ == DELIMITER_STRING_BEGIN) {
                constString_ = true;
                d_.getDelStringsChars().add(i_);
                d_.getStringInfo().add(new StringList());
            }
            if (curChar_ == PAR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == PAR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    _error.setIndex(i_);
                    _error.setError(true);
                    _error.setString(_string);
                    return d_;
                }
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    _error.setIndex(i_);
                    _error.setError(true);
                    _error.setString(_string);
                    return d_;
                }
            }
            boolean escapeOpers_ = false;
            boolean addOp_ = true;
            if (curChar_ == MULT_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == DIV_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == PLUS_CHAR){
                escapeOpers_ = true;
                if (beginIndex_ == i_) {
                    addOp_ = false;
                }
            }
            if (curChar_ == MINUS_CHAR){
                escapeOpers_ = true;
                if (beginIndex_ == i_) {
                    addOp_ = false;
                }
            }
            if (curChar_ == AND_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == OR_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == LOWER_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == GREATER_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == EQ_CHAR) {
                escapeOpers_ = true;
            }
            if (curChar_ == NEG_BOOL_CHAR) {
                escapeOpers_ = true;
                if (beginIndex_ == i_) {
                    addOp_ = false;
                }
            }
            if (curChar_ == PAR_LEFT) {
                escapeOpers_ = true;
            }
            if (curChar_ == SEP_ARG) {
                escapeOpers_ = true;
            }
            if (escapeOpers_) {
                int j_ = i_ + 1;
                if (j_ < len_ && _string.charAt(j_) == EQ_CHAR) {
                    j_++;
                }
                while (j_ < len_) {
                    char curLoc_ = _string.charAt(j_);
                    if (Character.isWhitespace(curLoc_)) {
                        j_++;
                        continue;
                    }
                    if (curLoc_ == PLUS_CHAR) {
                        j_++;
                        continue;
                    }
                    if (curLoc_ == MINUS_CHAR) {
                        j_++;
                        continue;
                    }
                    if (curLoc_ == NEG_BOOL_CHAR) {
                        j_++;
                        continue;
                    }
                    break;
                }
                if (addOp_) {
                    d_.getAllowedOperatorsIndexes().add(i_);
                }
                i_ = j_;
                continue;
            }
            boolean idOp_ = false;
            if (curChar_ == PAR_RIGHT) {
                idOp_ = true;
            }
            if (idOp_) {
                d_.getAllowedOperatorsIndexes().add(i_);
            }
            i_++;
        }
        if (constString_) {
            _error.setIndex(i_);
            _error.setError(true);
            _error.setString(_string);
            return d_;
        }
        if (!parsBrackets_.isEmpty()) {
            _error.setIndex(i_);
            _error.setError(true);
            _error.setString(_string);
            return d_;
        }
        return d_;
    }
    private static int addNumberInfo(Delimiters _d, int _from, int _begin,String _string) {
        StringBuilder nbInfo_ = new StringBuilder();
        int len_ = _string.length();
        int i_ = _from;
        boolean stop_ = false;
        while (i_ < len_) {
            char cur_ = _string.charAt(i_);
            if (Character.isDigit(cur_)) {
                nbInfo_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == DOT) {
                nbInfo_.append(cur_);
                i_++;
                break;
            }
            if (cur_ == SEP_RATE) {
                nbInfo_.append(cur_);
                i_++;
                break;
            }
            stop_ = true;
            break;
        }
        if (i_ >= len_ || stop_) {
            _d.getDelNumbers().add(_begin);
            _d.getDelNumbers().add(i_);
            _d.getNbInfos().add(nbInfo_);
            return i_;
        }
        while (i_ < len_) {
            char cur_ = _string.charAt(i_);
            if (Character.isDigit(cur_)) {
                nbInfo_.append(cur_);
                i_++;
                continue;
            }
            break;
        }
        _d.getDelNumbers().add(_begin);
        _d.getDelNumbers().add(i_);
        _d.getNbInfos().add(nbInfo_);
        return i_;
    }
    static OperationsSequence getOperationsSequence(int _offset, String _string,
            StringMap<String> _conf, Delimiters _d) {
        NatTreeMap<Integer,String> operators_;
        operators_ = new NatTreeMap<Integer,String>();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        int prioMax_ = FCT_OPER_PRIO;
        int prio_ = prioMax_;
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ >= len_) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValue(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        int firstPrintChar_ = i_;
        int lastPrintChar_ = len_ - 1;
        while (Character.isWhitespace(_string.charAt(lastPrintChar_))) {
            lastPrintChar_--;
        }
        len_ = lastPrintChar_+1;
        int begin_;
        int end_;
        begin_ = _d.getDelStringsChars().indexOfObj(firstPrintChar_+_offset);
        end_ = _d.getDelStringsChars().indexOfObj(lastPrintChar_+_offset);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setIndexCst(begin_/2);
            op_.setConstType(ConstType.STRING);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValue(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        begin_ = _d.getDelNumbers().indexOfObj(_offset + firstPrintChar_);
        end_ = _d.getDelNumbers().indexOfObj(_offset + lastPrintChar_ + 1);
        if (delimits(begin_, end_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setIndexCst(begin_/2);
            op_.setConstType(ConstType.NUMBER);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValue(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        String sub_ = _string.substring(firstPrintChar_, len_);
        if (StringList.quickEq(sub_,TRUE)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValue(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        if (StringList.quickEq(sub_, FALSE)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValue(_string);
            op_.setDelimiter(_d);
            return op_;
        }
        for (VariableInfo v: _d.getVariables()) {
            if (v.getFirstChar() != _offset + firstPrintChar_) {
                continue;
            }
            int iVar_ = v.getLastChar();
            if (iVar_ != _offset + lastPrintChar_ + 1) {
                break;
            }
            OperationsSequence op_ = new OperationsSequence();
            op_.setConstType(ConstType.LOC_VAR);
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValue(v.getName());
            op_.setDelimiter(_d);
            return op_;
        }
        boolean useFct_ = false;
        String fctName_ = EMPTY_STRING;
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(MINUS_CHAR));
            i_ = incrementUnary(_string,  firstPrintChar_ + 1, lastPrintChar_);
        } else if (_string.charAt(firstPrintChar_) == PLUS_CHAR) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(PLUS_CHAR));
            i_ = incrementUnary(_string,  firstPrintChar_ + 1, lastPrintChar_);
        } else if (_string.charAt(firstPrintChar_) == NEG_BOOL_CHAR) {
            prio_ = UNARY_PRIO;
            operators_.put(firstPrintChar_, String.valueOf(NEG_BOOL_CHAR));
            i_ = incrementUnary(_string,  firstPrintChar_ + 1, lastPrintChar_);
        }
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (!_d.getAllowedOperatorsIndexes().containsObj(i_+_offset)) {
                i_++;
                continue;
            }

            if (curChar_ == PAR_LEFT) {
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                    useFct_ = true;
                    fctName_ = _string.substring(CustList.FIRST_INDEX, i_);
                    operators_.clear();
                    operators_.put(i_, String.valueOf(PAR_LEFT));
                }
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == SEP_ARG && parsBrackets_.size() == 1 && prio_ == FCT_OPER_PRIO) {
                operators_.put(i_, String.valueOf(SEP_ARG));
            }
            if (curChar_ == PAR_RIGHT) {
                parsBrackets_.removeKey(parsBrackets_.lastKey());
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                    operators_.put(i_, String.valueOf(PAR_RIGHT));
                }
                i_++;
                continue;
            }
            if (!parsBrackets_.isEmpty()) {
                i_++;
                continue;
            }
            StringBuilder builtOperator_ = new StringBuilder();
            boolean clearOperators_ = false;
            boolean foundOperator_ = false;
            int increment_ = 1;
            boolean eq_ = false;
            if (curChar_ == NEG_BOOL_CHAR || curChar_ == EQ_CHAR) {
                builtOperator_.append(curChar_);
                if (curChar_ == NEG_BOOL_CHAR && i_ + 1 < _string.length()) {
                    char nextChar_ = _string.charAt(i_ + 1);
                    if (nextChar_ == EQ_CHAR) {
                        builtOperator_.append(EQ_CHAR);
                        increment_++;
                    }
                }
                eq_ = true;
            }
            if (eq_) {
                if (prio_ > EQ_PRIO) {
                    clearOperators_ = true;
                    prio_ = EQ_PRIO;
                }
                if (prio_ == EQ_PRIO) {
                    foundOperator_ = true;
                }
            }
            int prioOpMult_ = 0;
            if (curChar_ == MINUS_CHAR || curChar_ == PLUS_CHAR) {
                prioOpMult_ = ADD_PRIO;
            } else if (curChar_ == MULT_CHAR || curChar_ == DIV_CHAR) {
                prioOpMult_ = MULT_PRIO;
            } else if (curChar_ == AND_CHAR) {
                prioOpMult_ = AND_PRIO;
            } else if (curChar_ == OR_CHAR) {
                prioOpMult_ = OR_PRIO;
            }
            if (prioOpMult_ > 0) {
                builtOperator_.append(curChar_);
                if (prio_ > prioOpMult_) {
                    prio_ = prioOpMult_;
                }
                if (prio_ == prioOpMult_) {
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
            }
            if (curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
                builtOperator_.append(curChar_);
                if (prio_ > CMP_PRIO) {
                    clearOperators_ = true;
                    prio_ = CMP_PRIO;
                }
                if (prio_ == CMP_PRIO) {
                    foundOperator_ = true;
                }
                char nextChar_ = _string.charAt(i_ + 1);
                if (nextChar_ == EQ_CHAR) {
                    builtOperator_.append(nextChar_);
                    increment_++;
                }
            }
            if (foundOperator_) {
                if (clearOperators_) {
                    useFct_ = false;
                    fctName_ = EMPTY_STRING;
                    operators_.clear();
                }
                operators_.put(i_,builtOperator_.toString());
            }
            i_ += increment_;
        }
        OperationsSequence op_ = new OperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        op_.setFctName(fctName_);
        op_.setUseFct(useFct_);
        op_.setupValues(_string);
        op_.setDelimiter(_d);
        return op_;
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin > CustList.INDEX_NOT_FOUND_ELT && _begin + 1 == _end;
    }

    static int incrementUnary(String _string, int _from, int _to) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ != MINUS_CHAR) {
                if (ch_ != PLUS_CHAR) {
                    if (ch_ != NEG_BOOL_CHAR) {
                        if (!Character.isWhitespace(ch_)) {
                            break;
                        }
                    }
                }
            }
            j_++;
        }
        return j_;
    }
}
