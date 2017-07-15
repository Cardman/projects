package code.maths.litteral;
import code.maths.litteral.exceptions.BadMathExpressionException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
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
    static final char TRUE_CHAR = 'V';

    static final String TRUE = NumericString.EMPTY_STRING + TRUE_CHAR;

    static final char FALSE_CHAR = 'F';

    static final String FALSE = NumericString.EMPTY_STRING + FALSE_CHAR;
    private static final char DOT = '.';
    private static final char SEP_RATE = '/';
    private static final char ESCAPE_META_CHAR = '\\';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char DELIMITER_STRING_BEGIN = '{';
    private static final char DELIMITER_STRING_SEP = ';';
    private static final char DELIMITER_STRING_END = '}';

    private static final String FCT = "(";
    private static final OperationPriority FCT_OPER = new OperationPriority(FCT, FCT_OPER_PRIO);

    private static final char NEG_BOOL_CHAR = '!';
    private static final String NEG_BOOL = "!";
    private static final OperationPriority NEG_BOOL_OPER = new OperationPriority(NEG_BOOL, UNARY_PRIO);

    private static final String UNARY_PLUS = "+";
    private static final OperationPriority UNARY_PLUS_OPER = new OperationPriority(UNARY_PLUS, UNARY_PRIO);

    private static final String UNARY_MINUS = "-";
    private static final OperationPriority UNARY_MINUS_OPER = new OperationPriority(UNARY_MINUS, UNARY_PRIO);

    private static final String MULT = "*";
    private static final OperationPriority MULT_OPER = new OperationPriority(MULT, MULT_PRIO);

    private static final String DIV = ":";
    private static final OperationPriority DIV_OPER = new OperationPriority(DIV, MULT_PRIO);

//    private static final char PLUS_CHAR = '+';
    private static final String PLUS = "+";
    private static final OperationPriority PLUS_OPER = new OperationPriority(PLUS, ADD_PRIO);

    private static final char MINUS_CHAR = '-';
    private static final String MINUS = "-";
    private static final OperationPriority MINUS_OPER = new OperationPriority(MINUS, ADD_PRIO);

    private static final String LOWER_EQ = "<=";
    private static final OperationPriority LOWER_EQ_OPER = new OperationPriority(LOWER_EQ, CMP_PRIO);

    private static final char LOWER_CHAR = '<';
    private static final String LOWER = "<";
    private static final OperationPriority LOWER_OPER = new OperationPriority(LOWER, CMP_PRIO);

    private static final String GREATER_EQ = ">=";
    private static final OperationPriority GREATER_EQ_OPER = new OperationPriority(GREATER_EQ, CMP_PRIO);

    private static final char GREATER_CHAR = '>';
    private static final String GREATER = ">";
    private static final OperationPriority GREATER_OPER = new OperationPriority(GREATER, CMP_PRIO);

    private static final char EQ_CHAR = '=';
    private static final String EQ = "=";
    private static final OperationPriority EQ_OPER = new OperationPriority(EQ, EQ_PRIO);

    private static final String DIFF = "!=";
    private static final OperationPriority DIFF_OPER = new OperationPriority(DIFF, EQ_PRIO);

    private static final String AND = "&";
    private static final OperationPriority AND_OPER = new OperationPriority(AND, AND_PRIO);

    private static final String OR = "|";
    private static final OperationPriority OR_OPER = new OperationPriority(OR, OR_PRIO);
    private static final char[] OPERATORS_CHARS = new char[]{'!','+','-','*',':','>','=','<','&','|',',','(',')'};
    private MathResolver(){
    }

    static CustList<OperationPriority> getOperations() {
        CustList<OperationPriority> prios_ = new CustList<OperationPriority>();
        prios_.add(FCT_OPER);
        prios_.add(NEG_BOOL_OPER);
        prios_.add(UNARY_PLUS_OPER);
        prios_.add(UNARY_MINUS_OPER);
        prios_.add(MULT_OPER);
        prios_.add(DIV_OPER);
        prios_.add(PLUS_OPER);
        prios_.add(MINUS_OPER);
        prios_.add(LOWER_EQ_OPER);
        prios_.add(GREATER_EQ_OPER);
        prios_.add(LOWER_OPER);
        prios_.add(GREATER_OPER);
        prios_.add(EQ_OPER);
        prios_.add(DIFF_OPER);
        prios_.add(AND_OPER);
        prios_.add(OR_OPER);
        return prios_;
    }

    static CustList<OperationPriority> getOperationsByPriority(int _prio) {
        CustList<OperationPriority> prios_ = new CustList<OperationPriority>();
        for (OperationPriority o: getOperations()) {
            if (o.getPriority() == _prio) {
                prios_.add(o);
            }
        }
        return prios_;
    }

    static CustList<OperationPriority> getOperationsByLowerPriority(int _prio) {
        CustList<OperationPriority> prios_ = new CustList<OperationPriority>();
        for (OperationPriority o: getOperations()) {
            if (o.getPriority() <= _prio) {
                prios_.add(o);
            }
        }
        return prios_;
    }

    static int getMaxPriority() {
        Numbers<Integer> prios_ = new Numbers<Integer>();
        for (OperationPriority o: getOperations()) {
            prios_.add(o.getPriority());
        }
        return prios_.getMaximum();
    }

    static Delimiters checkSyntax(String _string, int _elOffest) {
        Delimiters d_ = new Delimiters();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
//        boolean printableChar_ = false;
        boolean constString_ = false;
        boolean escapedMeta_ = false;
        int len_ = _string.length();
        if (len_ == CustList.SIZE_EMPTY) {
            throw new BadMathExpressionException(_string);
        }
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
//                minIndexDot_ = i_;
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
//            if (curChar_ == GET_VAR && parsBrackets_.isEmpty()) {
//                foundSemiColumn_ = true;
//            }
            if (curChar_ == NEG_BOOL.charAt(0) || curChar_ == LOWER.charAt(0) || curChar_ == GREATER.charAt(0)) {
                int j_ = i_ + 1;
                boolean exist_ = false;
                while (j_ < len_) {
                    if (Character.isWhitespace(_string.charAt(j_))) {
                        exist_ = true;
                        j_++;
                        continue;
                    }
                    if (_string.charAt(j_) == EQ.charAt(0) && exist_) {
                        throw new BadMathExpressionException(_string);
                    }
                    break;
                }
            }
            if (curChar_ == PAR_LEFT) {
                //                usedCaller_ = curChar_;
                parsBrackets_.put(i_, curChar_);
            }
            if (curChar_ == PAR_RIGHT) {
                if (parsBrackets_.isEmpty()) {
                    throw new BadMathExpressionException(_string);
                }
                if (parsBrackets_.getValue(parsBrackets_.size() - 1) != PAR_LEFT) {
                    throw new BadMathExpressionException(_string);
                }
                //                usedEnder_ = curChar_;
                d_.getCallings().put(parsBrackets_.getKey(parsBrackets_.size() - 1), i_);
                parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
            }
//            if (curChar_ == FIRST_VAR_ARG) {
//                if (parsBrackets_.isEmpty()) {
//                    int j_ = i_ + 1;
//                    boolean foundPrintableSpace_ = false;
//                    while (j_ < len_) {
//                        if (!Character.isWhitespace(_string.charAt(j_))) {
//                            foundPrintableSpace_ = true;
//                            break;
//                        }
//                        j_++;
//                    }
//                    if (foundPrintableSpace_) {
//                        throw new BadMathExpressionException(_string);
//                    }
//                } else {
//                    if (parsBrackets_.getValue(parsBrackets_.size() - 1) != PAR_LEFT) {
//                        throw new BadMathExpressionException(_string);
//                    }
//                    int lastKey_ = parsBrackets_.getKey(parsBrackets_.size() - 1);
//                    boolean allWhiteSpace_ = true;
//                    for (int i = lastKey_+1; i < i_; i++) {
//                        if (!Character.isWhitespace(_string.charAt(i))) {
//                            allWhiteSpace_ = false;
//                            break;
//                        }
//                    }
//                    if (allWhiteSpace_) {
//                        firstVarArg_ = true;
//                    }
//                }
//                /*if (parsBrackets_.isEmpty()) {
//                    throw new BadMathExpressionException(_string);
//                }
//                if (parsBrackets_.getValue(parsBrackets_.size() - 1) != PAR_LEFT) {
//                    throw new BadMathExpressionException(_string);
//                }
//                int lastKey_ = parsBrackets_.getKey(parsBrackets_.size() - 1);
//                boolean allWhiteSpace_ = true;
//                for (int i = lastKey_+1; i < i_; i++) {
//                    if (!Character.isWhitespace(_string.charAt(i))) {
//                        allWhiteSpace_ = false;
//                        break;
//                    }
//                }
//                if (allWhiteSpace_) {
//                    firstVarArg_ = true;
//                }*/
//            }
//            if (curChar_ == ARR_LEFT) {
//                if (!firstVarArg_ && !instance_) {
//                    parsBrackets_.put(i_, curChar_);
//                }
//            }
//            if (curChar_ == ARR_RIGHT) {
//                if (parsBrackets_.isEmpty()) {
//                    throw new BadMathExpressionException(_string);
//                }
//                if (parsBrackets_.getValue(parsBrackets_.size() - 1) != ARR_LEFT) {
//                    throw new BadMathExpressionException(_string);
//                }
//                d_.getCallings().put(parsBrackets_.getKey(parsBrackets_.size() - 1), i_);
//                parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
//            }
            if (curChar_ == SEP_ARG) {
                if (parsBrackets_.isEmpty()) {
                    throw new BadMathExpressionException(_string);
                }
//                firstVarArg_ = false;
            }
            //if (parsBrackets_.isEmpty()) {
//                if (i_ + 1 <= len_) {
//                    if (_string.substring(i_, i_ + 1).startsWith(DOT_OPER.getOperation())) {
//                        if (i_ + 1 >= len_) {
//                            if (!foundSemiColumn_) {
//                                throw new BadMathExpressionException(_string);
//                            }
//                        }
//                    }
//                }
//                if (_string.substring(i_, i_ + 1).startsWith(DOT)) {
//                    if (i_ + 1 >= len_) {
//                        if (!foundSemiColumn_) {
//                            throw new BadMathExpressionException(_string);
//                        }
//                    }
//                }
            //}
            i_++;
        }
//        if (firstVarArg_) {
//            throw new BadMathExpressionException(_string);
//        }
        if (constString_) {
            throw new BadMathExpressionException(_string);
        }
//        if (constChar_) {
//            throw new BadMathExpressionException(_string);
//        }
        if (!parsBrackets_.isEmpty()) {
            throw new BadMathExpressionException(_string);
        }
//        if (instance_) {
//            throw new BadMathExpressionException(_string);
//        }
        d_.setIndexBegin(_elOffest);
        d_.setIndexEnd(i_-1);
//        d_.setIndexEnd(len_-1);
//        secondCheckSyntax(_string, _conf, d_);
        return d_;
    }
    static void secondCheckSyntax(String _string, Delimiters _d) {
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
//        boolean constString_ = false;
//        boolean escapedMeta_ = false;
//        while (i_ < len_) {
//            if (!Character.isWhitespace(_string.charAt(i_))) {
////                minIndexDot_ = i_;
//                break;
//            }
//            i_++;
//        }
//        int firstPrintableWordChar_ = i_;
//        while (i_ < len_) {
//            if (!StringList.isWordChar(_string.charAt(i_))) {
//                break;
//            }
//            i_++;
//        }
//        if (i_ < len_ && _string.charAt(i_) == GET_VAR) {
//            i_++;
//            while (i_ < len_) {
//                if (_string.charAt(i_) == GET_VAR) {
//                    i_++;
//                    continue;
//                }
//                if (_string.charAt(i_) == DOT_VAR) {
//                    i_++;
//                    continue;
//                }
//                break;
//            }
//            while (i_ < len_) {
//                if (!Character.isWhitespace(_string.charAt(i_))) {
//                    break;
//                }
//                i_++;
//            }
//        } else {
//            i_ = firstPrintableWordChar_;
//        }
//        if (_string.substring(i_).startsWith(INSTANCE)) {
//            if (i_ + INSTANCE.length() < len_ && !StringList.isWordChar(_string.charAt(i_+INSTANCE.length()))) {
//                instance_ = true;
//            }
//        }
        i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
//            if (constString_) {
//                if (!escapedMeta_) {
//                    if (curChar_ == ESCAPE_META_CHAR) {
//                        escapedMeta_ = true;
//                        i_++;
//                        continue;
//                    }
//                    if (curChar_ == DELIMITER_STRING_END) {
//                        constString_ = false;
//                        i_++;
//                        continue;
//                    }
//                    i_++;
//                    continue;
//                }
//                if (curChar_ == DELIMITER_STRING_END) {
//                    escapedMeta_ = false;
//                    i_++;
//                    continue;
//                }
//                if (curChar_ == DELIMITER_STRING_SEP) {
//                    escapedMeta_ = false;
//                    i_++;
//                    continue;
//                }
//                if (curChar_ == ESCAPE_META_CHAR) {
//                    escapedMeta_ = false;
//                    i_++;
//                    continue;
//                }
//                i_++;
//                continue;
////                throw new BadMathExpressionException(_string);
//            }
//            if (curChar_ == DELIMITER_STRING_BEGIN) {
//                constString_ = true;
//            }
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
//                if (curChar_ == FIRST_VAR_ARG) {
//                    if (onlySpacesTo(_string, i_, len_, SEP_ARG)) {
//                        i_++;
//                        continue;
//                    }
//                    if (onlySpacesTo(_string, i_, len_, PAR_RIGHT)) {
//                        i_++;
//                        continue;
//                    }
//                    if (onlySpacesTo(_string, i_, len_, ARR_LEFT)) {
//                        i_++;
//                        continue;
//                    }
//                    if (onlySpacesFrom(_string, i_, PAR_LEFT)) {
//                        i_++;
//                        continue;
//                    }
//                    throw new BadMathExpressionException(_string);
//                }
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, PLUS.charAt(0))) {
                i_++;
                continue;
            }
            if (onlySpacesTo(_string, i_, len_, MINUS.charAt(0))) {
                i_++;
                continue;
            }
            if (curChar_ == PAR_LEFT || curChar_ == SEP_ARG || curChar_ == AND.charAt(0) || curChar_ == OR.charAt(0)) {
                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
                    i_++;
                    continue;
                }
            }
//            if (curChar_ == ARR_LEFT) {
//                for (char c: OPERATORS_CHARS) {
//                    if (c == ARR_LEFT) {
//                        continue;
//                    }
//                    if (onlySpacesTo(_string, i_, len_, c)) {
//                        throw new BadMathExpressionException(_string);
//                    }
//                }
//                i_++;
//                continue;
//            }
//            if (curChar_ == ARR_RIGHT) {
//                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
//                    throw new BadMathExpressionException(_string);
//                }
//                if (onlySpacesTo(_string, i_, len_, GET_VAR)) {
//                    throw new BadMathExpressionException(_string);
//                }
//                i_++;
//                continue;
//            }
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
//                if (curChar_ == DOT_VAR || curChar_ == GET_VAR) {
//                    i_++;
//                    continue;
//                }
                throw new BadMathExpressionException(_string);
            }
//            if (onlySpacesTo(_string, i_, len_, ARR_LEFT)) {
//                if (curChar_ == PAR_RIGHT) {
//                    i_++;
//                    continue;
//                }
//                if (curChar_ == DOT_VAR || curChar_ == GET_VAR) {
//                    i_++;
//                    continue;
//                }
//                throw new BadMathExpressionException(_string);
//            }
//            if (onlySpacesTo(_string, i_, len_, ARR_RIGHT)) {
//                if (curChar_ == ARR_RIGHT || curChar_ == PAR_RIGHT) {
//                    i_++;
//                    continue;
//                }
//                if (curChar_ == DOT_VAR || curChar_ == GET_VAR) {
//                    i_++;
//                    continue;
//                }
//                throw new BadMathExpressionException(_string);
//            }
            if (curChar_ == PAR_RIGHT) {
//                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
//                    throw new BadMathExpressionException(_string);
//                }
//                if (onlySpacesTo(_string, i_, len_, GET_VAR)) {
//                    throw new BadMathExpressionException(_string);
//                }
                i_++;
                continue;
            }
//            if (curChar_ == GET_VAR) {
//                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
//                    throw new BadMathExpressionException(_string);
//                }
//                i_++;
//                continue;
//            }
//            if (curChar_ == DOT_VAR) {
//                if (onlySpacesTo(_string, i_, len_, DOT_VAR)) {
//                    throw new BadMathExpressionException(_string);
//                }
//                if (onlySpacesTo(_string, i_, len_, NEG_BOOL_CHAR)) {
//                    throw new BadMathExpressionException(_string);
//                }
//                i_++;
//                continue;
//            }
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
//            if (curChar_ == DIV.charAt(0) || curChar_ == MULT.charAt(0)) {
//                if (onlySpacesTo(_string, i_, len_, DIV.charAt(0))) {
//                    throw new BadMathExpressionException(_string);
//                }
//                if (onlySpacesTo(_string, i_, len_, MULT.charAt(0))) {
//                    throw new BadMathExpressionException(_string);
//                }
//            }
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
//    private static boolean onlySpacesFrom(String _string, int _index, char _begin) {
//        int i_ = _index;
//        if (i_ > 0) {
//            int j_ = i_ - 1;
//            while (j_ >= 0) {
//                if (!Character.isWhitespace(_string.charAt(j_))) {
//                    break;
//                }
//                j_--;
//            }
//            if (j_ >= 0) {
//                if (_string.charAt(j_) == _begin) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    static OperationsSequence getOperationsSequence(int _offset, String _string,
            StringMap<String> _conf, Delimiters _d) {
        NatTreeMap<Integer,String> operators_;
        operators_ = new NatTreeMap<Integer,String>();
        NatTreeMap<Integer,Character> parsBrackets_;
        parsBrackets_ = new NatTreeMap<Integer,Character>();
        Character usedCaller_ = null;
        Character usedEnder_ = null;
        boolean instance_ = false;
        int prioMax_ = getMaxPriority();
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
        i_= CustList.FIRST_INDEX;
        int lastPrintChar_ = len_ - 1;
        while (lastPrintChar_ >= 0) {
            if (!Character.isWhitespace(_string.charAt(lastPrintChar_))) {
//                minIndexDot_ = i_;
                break;
            }
            lastPrintChar_--;
        }
        len_ = lastPrintChar_+1;
        if (isFloatingNumber(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string, false, false);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isIntegerNumber(_string, firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string, false, false);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, TRUE ,firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string, false, true);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, FALSE ,firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string, false, true);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isVariable(_string, _conf.getKeys(), firstPrintChar_, lastPrintChar_)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string, false, false);
            op_.addOffset(_offset);
            op_.setDelimiter(_d);
            return op_;
        }
        if (isConstant(_string, firstPrintChar_, lastPrintChar_, DELIMITER_STRING_BEGIN, DELIMITER_STRING_END)) {
            OperationsSequence op_ = new OperationsSequence();
            op_.setOperators(new NatTreeMap<Integer, String>());
            op_.setupValues(_string, false, false);
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
                if (FCT_OPER_PRIO < prio_) {
                    prio_ = FCT_OPER_PRIO;
                    operators_.clear();
                    operators_.put(i_, FCT);
                }
                parsBrackets_.put(i_, curChar_);
                usedCaller_ = curChar_;
            }
            if (curChar_ == PAR_RIGHT) {
                usedEnder_ = curChar_;
                parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
                if (parsBrackets_.isEmpty() && prio_ == FCT_OPER_PRIO) {
                    operators_.put(i_, String.valueOf(PAR_RIGHT));
                }
            }
            if (parsBrackets_.isEmpty()) {
                for (OperationPriority op_: getOperationsByLowerPriority(prio_)) {
                    if (i_ + 2 > len_) {
                        break;
                    }
                    if (instance_) {
                        break;
                    }
                    if (_string.substring(i_, i_ + 2).startsWith(op_.getOperation())) {
                        if (op_ == UNARY_PLUS_OPER || op_ == UNARY_MINUS_OPER || op_ == NEG_BOOL_OPER) {
                            if (i_ > firstPrintChar_) {
                                continue;
                            }
                        }
                        if (op_ == MINUS_OPER || op_ == PLUS_OPER) {
                            if (isUnary(_string, firstPrintChar_, i_)) {
                                continue;
                            }
                        }
                        if (op_ == EQ_OPER) {
                            if (i_ > 0) {
                                if (_string.charAt(i_ - 1) == NEG_BOOL_CHAR) {
                                    continue;
                                }
                                if (_string.charAt(i_ - 1) == LOWER_CHAR) {
                                    continue;
                                }
                                if (_string.charAt(i_ - 1) == GREATER_CHAR) {
                                    continue;
                                }
                            }
                        }
                        if (op_.getPriority() == prio_) {
                            operators_.put(i_, op_.getOperation());
                        } else {
                            operators_.clear();
                            operators_.put(i_, op_.getOperation());
                        }
                        prio_ = op_.getPriority();
                        break;
                    }
                }
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
            if (usedCaller_ != null) {
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
                        parsBrackets_.removeKey(parsBrackets_.getKey(parsBrackets_.size() - 1));
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
        op_.setupValues(_string, prio_ == UNARY_PRIO, false);
        op_.addOffset(_offset);
        op_.setDelimiter(_d);
        return op_;
    }

    static boolean isIntegerNumber(String _string, int _from, int _to) {
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
        while (i_ <= _to) {
            if (!Character.isDigit(_string.charAt(i_))) {
                if (Character.isLetter(_string.charAt(i_))) {
                    i_++;
                    continue;
                }
                return false;
            }
            i_++;
        }
        return true;
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
                if ((_string.charAt(i_) != DOT && _string.charAt(i_) != SEP_RATE) || nbDots_ > 0) {
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

    static boolean isUnary(String _string, int _min, int _i) {
        int i_ = _i;
        if (i_ > _min) {
            if (StringList.isWordChar(_string.charAt(i_ - 1))) {
                return false;
            }
            if (_string.charAt(i_ - 1) == PAR_RIGHT) {
                return false;
            }
//            if (_string.charAt(i_ - 1) == ARR_RIGHT) {
//                return false;
//            }
            if (_string.charAt(i_ - 1) == DOT) {
                return false;
            }
//            if (_string.charAt(i_ - 1) == GET_VAR) {
//                return false;
//            }
            if (_string.charAt(i_ - 1) == DELIMITER_STRING_END) {
                return false;
            }
            if (_string.charAt(i_ - 1) == DELIMITER_STRING_BEGIN) {
                return false;
            }
            /*if (_string.charAt(i_ - 1) == DIV.charAt(CustList.FIRST_INDEX)) {
                return true;
            }
            if (_string.charAt(i_ - 1) == MULT.charAt(CustList.FIRST_INDEX)) {
                return true;
            }
            if (_string.charAt(i_ - 1) == PLUS_CHAR) {
                return true;
            }
            if (_string.charAt(i_ - 1) == MINUS_CHAR) {
                return true;
            }*/
            /*if (_op == _minus) {
                if (_string.charAt(i_ - 1) == PLUS_CHAR) {
                    return true;
                }
            }
            if (_op == _plus) {
                if (_string.charAt(i_ - 1) == MINUS_CHAR) {
                    return true;
                }
            }
            if (_string.charAt(i_ - 1) != MINUS_CHAR && _op == _minus) {
                return false;
            }
            if (_string.charAt(i_ - 1) != PLUS_CHAR && _op == _plus) {
                return false;
            }*/
        }
        return true;
    }
    static boolean procWordFirstChar(String _string, int _i, String _word, int _max) {
        int len_ = _max;
        if (_i + _word.length() <= len_) {
//            int j_ = i_ - 1;
//            char previous_ = _string.charAt(j_);
            int j_ = _i + 1;
            while (j_ < len_) {
                char next_ = _string.charAt(j_);
                if (!StringList.isWordChar(next_)) {
                    break;
                }
                j_++;
            }
            boolean process_ = true;
//            if (StringList.isWordChar(previous_)) {
//                process_ = false;
//            }
            if (_i + _word.length() < len_) {
                if (StringList.isWordChar(_string.charAt(_i + _word.length()))) {
                    process_ = false;
                }
            }
            if (_i + _word.length() <= len_) {
                if (!_string.substring(_i, _i + _word.length()).startsWith(_word)) {
                    process_ = false;
                }
            }
            return process_;
        }
        return false;
    }

    static int indexOfNotDigit(String _string, int _i, int _max) {
        int j_ = _i;
        int len_ = _max;
        while (j_ < len_) {
            if (!Character.isDigit(_string.charAt(j_))) {
                return j_;
            }
            j_++;
        }
        return -1;
    }
    static int indexOfNotSpace(String _string, int _i, int _max) {
        int j_ = _i;
        int len_ = _max;
        while (j_ < len_) {
            if (!Character.isWhitespace(_string.charAt(j_))) {
                return j_;
            }
            j_++;
        }
        return -1;
    }
}
