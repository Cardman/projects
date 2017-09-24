package code.maths.litteral;
import code.maths.litteral.exceptions.BadMathExpressionException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;


public final class MathResolver {

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

    private static final char[] OPERATORS_CHARS = new char[]{'!','+','-','*',':','>','=','<','&','|',',','(',')'};
    private MathResolver(){
    }

    static Delimiters checkSyntax(String _string, int _elOffest) {
        Delimiters d_ = new Delimiters();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        boolean constString_ = false;
        boolean escapedMeta_ = false;
        int len_ = _string.length();
        if (len_ == CustList.SIZE_EMPTY) {
            throw new BadMathExpressionException(_string);
        }
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ >= len_) {
            throw new BadMathExpressionException(_string);
        }
        while (i_ < len_) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        i_ = CustList.FIRST_INDEX;
        int beginCharString_ = 0;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (constString_) {
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        if (i_ + 1 >= len_) {
                            throw new BadMathExpressionException(_string);
                        }
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING_END) {
                        constString_ = false;
                        d_.getDelimitersStringsChars().put(beginCharString_, i_);
                        i_++;
                        continue;
                    }
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING_END) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING_SEP) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == ESCAPE_META_CHAR) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                throw new BadMathExpressionException(_string);
            }
            if (StringList.isWordChar(curChar_)) {
                if (i_ + 1 < len_) {
                    if (Character.isWhitespace(_string.charAt(i_ + 1))) {
                        int j_ = i_ + 2;
                        while (j_ < len_) {
                            if (Character.isWhitespace(_string.charAt(j_))) {
                                j_++;
                                continue;
                            }
                            if (StringList.isWordChar(_string.charAt(j_))) {
                                throw new BadMathExpressionException(_string);
                            }
                            break;
                        }
                    }
                }
                while (i_ < len_) {
                    if (!StringList.isWordChar(_string.charAt(i_))) {
                        break;
                    }
                    i_++;
                }
                continue;
            }
            if (curChar_ == ESCAPE_META_CHAR) {
                throw new BadMathExpressionException(_string);
            }
            if (curChar_ == DELIMITER_STRING_BEGIN) {
                constString_ = true;
                beginCharString_ = i_;
            }
            if (curChar_ == NEG_BOOL_CHAR || curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
                int j_ = i_ + 1;
                boolean exist_ = false;
                while (j_ < len_) {
                    if (Character.isWhitespace(_string.charAt(j_))) {
                        exist_ = true;
                        j_++;
                        continue;
                    }
                    if (_string.charAt(j_) == EQ_CHAR && exist_) {
                        throw new BadMathExpressionException(_string);
                    }
                    break;
                }
            }
            if (curChar_ == PAR_LEFT) {
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == PAR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    throw new BadMathExpressionException(_string);
                }
                if (parsBrackets_.lastValue() != PAR_LEFT) {
                    throw new BadMathExpressionException(_string);
                }
                d_.getCallings().put(parsBrackets_.lastKey(), i_);
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    throw new BadMathExpressionException(_string);
                }
            }
            i_++;
        }
        if (constString_) {
            throw new BadMathExpressionException(_string);
        }
        if (!parsBrackets_.isEmpty()) {
            throw new BadMathExpressionException(_string);
        }
        d_.setIndexBegin(_elOffest);
        d_.setIndexEnd(i_-1);
        return d_;
    }
    static void secondCheckSyntax(String _string, Delimiters _d) {
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (_d.inStringOrCharConst(i_)) {
                i_++;
                continue;
            }
            boolean contained_ = false;
            for (char c: OPERATORS_CHARS) {
                if (c == curChar_) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, PLUS_CHAR)) {
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, MINUS_CHAR)) {
                i_++;
                continue;
            }
            if (curChar_ == PAR_LEFT || curChar_ == SEP_ARG || curChar_ == AND_CHAR || curChar_ == OR_CHAR) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    i_++;
                    continue;
                }
            }
            if (onlySpacesTo(_string, i_, len_, PAR_LEFT)) {
                if (curChar_ == PAR_RIGHT) {
                    throw new BadMathExpressionException(_string);
                }
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, PAR_RIGHT)) {
                if (curChar_ == PAR_LEFT || curChar_ == PAR_RIGHT) {
                    i_++;
                    continue;
                }
                throw new BadMathExpressionException(_string);
            }
            if (curChar_ == PAR_RIGHT) {
                i_++;
                continue;
            }
            if (curChar_ == NEG_BOOL_CHAR) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    i_++;
                    continue;
                }
                if (onlySpacesTo(_string, i_, len_, EQ_CHAR)) {
                    i_++;
                    continue;
                }
            }
            if (curChar_ == GREATER_CHAR || curChar_ == LOWER_CHAR) {
                if (onlySpacesTo(_string, i_, len_, EQ_CHAR)) {
                    i_++;
                    continue;
                }
            }
            if (curChar_ == EQ_CHAR) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    i_++;
                    continue;
                }
            }
            for (char c: OPERATORS_CHARS) {
                if (onlySpacesTo(_string, i_, len_, c)) {
                    throw new BadMathExpressionException(_string);
                }
            }
            i_++;
        }
    }
    private static boolean onlySpacesTo(String _string, int _index, int _length, char _end) {
        int i_ = _index;
        int len_ = _length;
        if (i_ + 1 < len_) {
            int j_ = i_ + 1;
            while (j_ < len_) {
                if (!Character.isWhitespace(_string.charAt(j_))) {
                    break;
                }
                j_++;
            }
            if (j_ < len_) {
                if (_string.charAt(j_) == _end) {
                    return true;
                }
            }
        }
        return false;
    }

    static OperationsSequence getOperationsSequence(int _offset, String _string,
            StringMap<String> _conf, Delimiters _d) {
        NatTreeMap<Integer,String> operators_;
        operators_ = new NatTreeMap<Integer,String>();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        char usedCaller_ = 0;
        char usedEnder_ = 0;
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
        int firstPrintChar_ = i_;
        int lastPrintChar_ = len_ - 1;
        while (lastPrintChar_ >= 0) {
            if (!Character.isWhitespace(_string.charAt(lastPrintChar_))) {
                break;
            }
            lastPrintChar_--;
        }
        len_ = lastPrintChar_+1;
        if (isFloatingNumber(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, TRUE ,firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, FALSE ,firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isVariable(_string, _conf.getKeys(), firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, firstPrintChar_, lastPrintChar_, DELIMITER_STRING_BEGIN, DELIMITER_STRING_END)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        boolean constString_ = false;
        boolean escapedMeta_ = false;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (constString_) {
                if (!escapedMeta_) {
                    if (curChar_ == ESCAPE_META_CHAR) {
                        escapedMeta_ = true;
                        i_++;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING_END) {
                        constString_ = false;
                        i_++;
                        continue;
                    }
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING_END) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == DELIMITER_STRING_SEP) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                if (curChar_ == ESCAPE_META_CHAR) {
                    escapedMeta_ = false;
                    i_++;
                    continue;
                }
                i_++;
                continue;
            }
            if (curChar_ == DELIMITER_STRING_BEGIN) {
                constString_ = true;
            }
            if (StringList.isWordChar(curChar_)) {
                while (i_ < len_) {
                    if (!StringList.isWordChar(_string.charAt(i_))) {
                        break;
                    }
                    i_++;
                }
                continue;
            }
            if (curChar_ == PAR_LEFT) {
                parsBrackets_.put(i_, curChar_);
                usedCaller_ = curChar_;
            }
            if (curChar_ == PAR_RIGHT) {
                usedEnder_ = curChar_;
                parsBrackets_.removeKey(parsBrackets_.lastKey());
            }
            if (parsBrackets_.isEmpty() && i_ + 2 <= len_) {
                String builtOperator_ = EMPTY_STRING;
                boolean clearOperators_ = false;
                boolean foundOperator_ = false;
                char nextChar_ = _string.charAt(i_ + 1);
                int increment_ = 1;
                if (curChar_ == NEG_BOOL_CHAR) {
                    builtOperator_ += NEG_BOOL_CHAR;
                    if (i_ == firstPrintChar_) {
                        foundOperator_ = true;
                        prio_ = UNARY_PRIO;
                    } else {
                        if (prio_ > EQ_PRIO) {
                            clearOperators_ = true;
                            prio_ = EQ_PRIO;
                            increment_++;
                            builtOperator_ += EQ_CHAR;
                        }
                        if (prio_ == EQ_PRIO) {
                            foundOperator_ = true;
                        }
                    }
                    if (foundOperator_) {
                        increment_ = getIncrement(_string, nextChar_ == EQ_CHAR, i_+1, lastPrintChar_);
                    }
                }
                int prioOpMult_ = 0;
                if (curChar_ == MINUS_CHAR || curChar_ == PLUS_CHAR) {
                    prioOpMult_ = ADD_PRIO;
                } else if (curChar_ == MULT_CHAR || curChar_ == DIV_CHAR) {
                    prioOpMult_ = MULT_PRIO;
                } else if (curChar_ == AND_CHAR) {
                    prioOpMult_ = AND_PRIO;
                } else if (curChar_ == EQ_CHAR) {
                    prioOpMult_ = EQ_PRIO;
                } else if (curChar_ == OR_CHAR) {
                    prioOpMult_ = OR_PRIO;
                }
                if (prioOpMult_ > 0) {
                    builtOperator_ += curChar_;
                    if (i_ == firstPrintChar_) {
                        foundOperator_ = true;
                        prio_ = UNARY_PRIO;
                    } else {
                        if (prio_ > prioOpMult_) {
                            clearOperators_ = true;
                            prio_ = prioOpMult_;
                        }
                        if (prio_ == prioOpMult_) {
                            foundOperator_ = true;
                        }
                    }
                    increment_ = getIncrement(_string, false, i_+1, lastPrintChar_);
                }
                if (curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
                    builtOperator_ += curChar_;
                    if (prio_ > CMP_PRIO) {
                        clearOperators_ = true;
                        prio_ = CMP_PRIO;
                    }
                    if (prio_ == CMP_PRIO) {
                        foundOperator_ = true;
                    }
                    if (foundOperator_) {
                        if (nextChar_ == EQ_CHAR) {
                            builtOperator_ += nextChar_;
                        }
                        increment_ = getIncrement(_string, nextChar_ == EQ_CHAR, i_+1, lastPrintChar_);
                    }
                }
                if (foundOperator_) {
                    if (clearOperators_) {
                        operators_.clear();
                    }
                    operators_.put(i_,builtOperator_);
                }
                i_ += increment_;
                continue;
            }
            i_++;
        }
        if (prio_ == CMP_PRIO) {
            if (operators_.size() != CustList.ONE_ELEMENT) {
                throw new BadMathExpressionException(_string);
            }
        }
        if (prio_ == EQ_PRIO) {
            if (operators_.size() != CustList.ONE_ELEMENT) {
                throw new BadMathExpressionException(_string);
            }
        }
        OperationsSequence op_ = new OperationsSequence();
        op_.setPriority(prio_);
        op_.setOperators(operators_);
        if (prioMax_ == prio_) {
            if (usedCaller_ != 0) {
                int indexUsedCaller_ = _string.indexOf(usedCaller_);
                int index_ = indexUsedCaller_ + 1;
                int end_ = _string.lastIndexOf(usedEnder_);
                NatTreeMap<Integer,String> newOperators_;
                newOperators_ = new NatTreeMap<Integer,String>();
                newOperators_.put(indexUsedCaller_, String.valueOf(usedCaller_));
                for (int i = index_; i < end_; i++) {
                    char curChar_ = _string.charAt(i);
                    if (constString_) {
                        if (!escapedMeta_) {
                            if (curChar_ == ESCAPE_META_CHAR) {
                                escapedMeta_ = true;
                                continue;
                            }
                            if (curChar_ == DELIMITER_STRING_END) {
                                constString_ = false;
                                continue;
                            }
                            continue;
                        }
                        escapedMeta_ = false;
                        continue;
                    }
                    if (curChar_ == DELIMITER_STRING_BEGIN) {
                        constString_ = true;
                    }
                    if (curChar_ == PAR_LEFT) {
                        parsBrackets_.put(i, curChar_);
                    }
                    if (curChar_ == PAR_RIGHT) {
                        parsBrackets_.removeKey(parsBrackets_.lastKey());
                    }
                    if (curChar_ == SEP_ARG) {
                        if (parsBrackets_.isEmpty()) {
                            newOperators_.put(i, String.valueOf(SEP_ARG));
                        }
                    }
                }
                newOperators_.put(end_, String.valueOf(usedEnder_));
                String fctName_ = _string.substring(CustList.FIRST_INDEX, _string.indexOf(usedCaller_));
                if (fctName_.trim().isEmpty() && newOperators_.size() > 2) {
                    throw new BadMathExpressionException(_string);
                }
                op_.setFctName(_string.substring(CustList.FIRST_INDEX, _string.indexOf(usedCaller_)));
                op_.setUseFct(true);
                op_.setOperators(newOperators_);
            }
        }
        op_.setupValues(_string);
        op_.addOffset(_offset);
        op_.setDelimiter(_d);
        return op_;
    }

    static int getIncrement(String _string, boolean _preIncrement, int _from, int _to) {
        int increment_ = 1;
        int j_ = _from;
        if (_preIncrement) {
            j_++;
            increment_++;
        }
        while (j_ <= _to) {
            if (_string.charAt(j_) != MINUS_CHAR) {
                if (_string.charAt(j_) != NEG_BOOL_CHAR) {
                    break;
                }
            }
            increment_++;
            j_++;
        }
        return increment_;
    }

    static boolean isFloatingNumber(String _string, int _from, int _to) {
        int i_ = _from;
        if (!Character.isDigit(_string.charAt(i_))) {
            if (_string.charAt(i_) != MINUS_CHAR) {
                return false;
            }
            i_++;
            if (i_ <= _to) {
                if (!Character.isDigit(_string.charAt(i_))) {
                    return false;
                }
            }
        }
        int nbDots_ = 0;
        while (i_ <= _to) {
            if (!Character.isDigit(_string.charAt(i_))) {
                if (Character.isLetter(_string.charAt(i_))) {
                    if (nbDots_ == 0) {
                        return false;
                    }
                    i_++;
                    continue;
                }
                if (_string.charAt(i_) != DOT && _string.charAt(i_) != SEP_RATE || nbDots_ > 0) {
                    return false;
                }
                nbDots_++;
                i_++;
                continue;
            }
            i_++;
        }
        return true;
    }
    static boolean isVariable(String _string, StringList _variables, int _from, int _to) {
        int i_ = _from;
        StringBuilder str_ = new StringBuilder();
        while (i_ <= _to) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            str_.append(_string.charAt(i_));
            i_++;
        }
        if (i_ <= _to) {
            return false;
        }
        return _variables.containsObj(str_.toString());
    }
    static boolean isConstant(String _string, String _token, int _from, int _to) {
        int i_ = _from;
        StringBuilder str_ = new StringBuilder();
        while (i_ <= _to) {
            if (!StringList.isWordChar(_string.charAt(i_))) {
                break;
            }
            str_.append(_string.charAt(i_));
            i_++;
        }
        if (i_ <= _to) {
            return false;
        }
        return StringList.quickEq(_token, str_.toString());
    }
    static boolean isConstant(String _string, int _from, int _to, char _delimiter, char _delimiterEnd) {
        int i_ = _from;
        if (_string.charAt(i_) != _delimiter) {
            return false;
        }
        i_++;
        boolean escaped_ = false;
        while (i_ < _to) {
            if (escaped_) {
                i_++;
                escaped_ = false;
                continue;
            }
            if (_string.charAt(i_) == ESCAPE_META_CHAR) {
                escaped_ = true;
            }
            if (_string.charAt(i_) == _delimiterEnd) {
                return false;
            }
            i_++;
        }
        if (_string.charAt(_to) != _delimiterEnd) {
            return false;
        }
        return true;
    }
}
