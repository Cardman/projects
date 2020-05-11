package code.expressionlanguage.instr;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.common.ExpPartDelimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.Ints;
import code.util.StringList;

final class AfterUnaryParts {
    private static final String EMPTY_STRING = "";

    private static final String ARR = "[";
    private static final String ANN_ARR = "{";

    private static final char NEG_BOOL_CHAR = '!';

    private static final char MULT_CHAR = '*';

    private static final char DIV_CHAR = '/';

    private static final char MOD_CHAR = '%';

    private static final char PLUS_CHAR = '+';

    private static final char MINUS_CHAR = '-';

    private static final char LOWER_CHAR = '<';

    private static final char GREATER_CHAR = '>';

    private static final char EQ_CHAR = '=';

    private static final char AND_CHAR = '&';

    private static final char OR_CHAR = '|';
    private static final char XOR_CHAR = '^';
    private static final char NEG_BOOL = '~';
    private static final char BEGIN_TERNARY = '?';
    private static final char END_TERNARY = ':';
    private static final char ARR_LEFT = '[';
    private static final char ARR_RIGHT = ']';
    private static final char ANN_ARR_LEFT = '{';
    private static final char ANN_ARR_RIGHT = '}';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char DOT_VAR = '.';

    private final IntTreeMap<String> operators = new IntTreeMap<String>();
    private final IntTreeMap<Character> parsBrackets = new IntTreeMap<Character>();
    private int prio = ElResolver.FCT_OPER_PRIO;
    private String extracted = "";
    private final CustList<PartOffset> partsOffs = new CustList<PartOffset>();
    private int index;

    private boolean enPars = true;
    private boolean leftParFirstOperator;
    private boolean instOf;
    private String fctName = EMPTY_STRING;
    private boolean enabledId;
    private boolean instance;
    private boolean errorDot;
    private final Ints laterIndexesDouble = new Ints();
    private final ExpPartDelimiters del;

    AfterUnaryParts(int _offset, String _string, ExpPartDelimiters _del, Delimiters _d) {
        del = _del;
        int firstPrintChar_ = del.getFirstPrintIndex();
        int lastPrintChar_ = del.getLastPrintIndex();
        boolean preIncr_ = false;
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) {
            if (firstPrintChar_ + 1 < _string.length()) {
                if (_string.charAt(firstPrintChar_) == _string.charAt(firstPrintChar_ + 1)) {
                    preIncr_ = true;
                }
            }
        }
        if (preIncr_) {
            prio = ElResolver.UNARY_PRIO;
            String ch_ = String.valueOf(_string.charAt(firstPrintChar_));
            operators.put(firstPrintChar_, StringList.concat(EMPTY_STRING,ch_,ch_));
            index = incrementUnary(_string, firstPrintChar_ + 2, lastPrintChar_, _offset, _d);
            return;
        }
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR
                || _string.charAt(firstPrintChar_) == NEG_BOOL_CHAR || _string.charAt(firstPrintChar_) == NEG_BOOL) {
            prio = ElResolver.UNARY_PRIO;
            operators.put(firstPrintChar_, String.valueOf(_string.charAt(firstPrintChar_)));
            index = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
            return;
        }
        if (_d.getDelCast().contains(firstPrintChar_ + _offset)) {
            prio = ElResolver.UNARY_PRIO;
            int min_ = _d.getDelCast().indexOfObj(firstPrintChar_ + _offset);
            int max_ = _d.getDelCast().get(min_ + 1) - _offset;
            operators.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            int ext_ = min_ / 2;
            extracted = _d.getDelCastExtract().get(ext_);
            partsOffs.addAllElts(_d.getCastParts().get(ext_));
            index = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
            return;
        }
        if (_d.getDelExplicit().contains(firstPrintChar_ + _offset)) {
            prio = ElResolver.UNARY_PRIO;
            int min_ = _d.getDelExplicit().indexOfObj(firstPrintChar_ + _offset);
            int max_ = _d.getDelExplicit().get(min_ + 1) - _offset;
            operators.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            index = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
            return;
        }
        index = firstPrintChar_;
    }
    void setInstance(String _string, Analyzable _conf) {
        int firstPrintChar_ = del.getFirstPrintIndex();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        if (StringExpUtil.startsWithKeyWord(_string,firstPrintChar_, keyWordNew_)) {
            instance = true;
        }
        if (_string.charAt(firstPrintChar_) == ANN_ARR_LEFT) {
            instance = true;
        }
    }
    void setState(int _offset, String _string, Delimiters _d) {
        int firstPrintChar_ = del.getFirstPrintIndex();
        char curChar_ = _string.charAt(index);
        if (_d.getDimsAddonIndexes().containsObj(index+_offset)) {
            laterIndexesDouble.add(index);
            index++;
            return;
        }
        if (!_d.getAllowedOperatorsIndexes().containsObj(index+_offset)) {
            index++;
            return;
        }
        if (curChar_ == PAR_LEFT) {
            if (parsBrackets.isEmpty() && prio == ElResolver.FCT_OPER_PRIO) {
                if (enPars) {
                    leftParFirstOperator = true;
                    fctName = _string.substring(CustList.FIRST_INDEX, index);
                    operators.put(index, String.valueOf(PAR_LEFT));
                } else if (enabledId) {
                    instance = false;
                    operators.clear();
                    errorDot = true;
                    operators.put(index, EMPTY_STRING);
                }
            }
            parsBrackets.put(index, curChar_);
            index++;
            return;
        }
        if (curChar_ == SEP_ARG) {
            if (parsBrackets.size() == 0) {
                if (prio > ElResolver.DECL_PRIO) {
                    operators.clear();
                }
                instance = false;
                enabledId = false;
                enPars = false;
                operators.put(index, String.valueOf(SEP_ARG));
                prio = ElResolver.DECL_PRIO;
            } else if (parsBrackets.size() == 1 && prio == ElResolver.FCT_OPER_PRIO && enPars){
                addCommaOperIfNotEmpty(operators, index,PAR_LEFT,ARR_LEFT,ANN_ARR_LEFT);
            }
            index++;
            return;
        }
        if (curChar_ == PAR_RIGHT) {
            parsBrackets.removeKey(parsBrackets.lastKey());
            if (parsBrackets.isEmpty() && prio == ElResolver.FCT_OPER_PRIO && enPars) {
                addOperIfNotEmpty(operators, index, PAR_LEFT,PAR_RIGHT);
                enPars = false;
                enabledId = true;
            }
            index++;
            return;
        }
        if (curChar_ == ANN_ARR_LEFT) {
            if (parsBrackets.isEmpty()) {
                if (ElResolver.FCT_OPER_PRIO == prio) {
                    leftParFirstOperator = false;
                    if (instance) {
                        fctName = _string.substring(firstPrintChar_, index);
                        if (operators.isEmpty()) {
                            operators.put(index, ANN_ARR);
                        }
                    } else {
                        fctName = EMPTY_STRING;
                        operators.clear();
                        operators.put(index,EMPTY_STRING);
                        errorDot = true;
                    }
                }
            }
            parsBrackets.put(index, curChar_);
            index++;
            return;
        }
        if (curChar_ == ANN_ARR_RIGHT) {
            parsBrackets.removeKey(parsBrackets.lastKey());
            if (parsBrackets.isEmpty() && prio == ElResolver.FCT_OPER_PRIO) {
                addOperIfNotEmpty(operators, index, ANN_ARR_LEFT,ANN_ARR_RIGHT);
                enPars = false;
                enabledId = true;
            }
            index++;
            return;
        }
        if (curChar_ == ARR_LEFT) {
            if (parsBrackets.isEmpty()) {
                if (ElResolver.FCT_OPER_PRIO == prio) {
                    leftParFirstOperator = false;

                    if (instance) {
                        if (operators.isEmpty()) {
                            fctName = _string.substring(firstPrintChar_, index);
                            operators.put(index, ARR);
                        } else {
                            String op_ = operators.firstValue();
                            if (StringList.quickEq(op_,ARR)) {
                                operators.put(index, ARR);
                            } else {
                                fctName = EMPTY_STRING;
                                instance = false;
                                operators.clear();
                                operators.put(index, EMPTY_STRING);
                            }
                        }
                    } else {
                        fctName = EMPTY_STRING;
                        if (firstPrintChar_ == index) {
                            operators.clear();
                            operators.put(index, ARR);
                        } else {
                            if (_string.charAt(index - 1) != '?') {
                                operators.clear();
                                operators.put(index, EMPTY_STRING);
                            }
                        }
                    }
                }
            }
            parsBrackets.put(index, curChar_);
            index++;
            return;
        }
        if (curChar_ == ARR_RIGHT) {
            parsBrackets.removeKey(parsBrackets.lastKey());
            if (parsBrackets.isEmpty() && prio == ElResolver.FCT_OPER_PRIO) {
                addOperIfNotEmpty(operators, index, ARR_LEFT,ARR_RIGHT);
                enPars = false;
                enabledId = true;
            }
            index++;
            return;
        }
        int lastPrintChar_ = del.getLastPrintIndex();
        int len_ = lastPrintChar_ + 1;
        if (curChar_ == BEGIN_TERNARY) {
            boolean ternary_ = false;
            boolean dot_ = false;
            if (StringExpUtil.nextCharIs(_string, index + 1, len_, DOT_VAR)) {
                int n_ = StringExpUtil.nextPrintChar(index + 2, len_, _string);
                if (ElResolver.isDigitOrDot(_string,n_)) {
                    ternary_ = true;
                } else {
                    dot_ = true;
                }
            } else {
                if (StringExpUtil.nextCharIs(_string, index + 1, len_, ARR_LEFT)) {
                    dot_ = true;
                } else if (!StringExpUtil.nextCharIs(_string, index + 1, len_, BEGIN_TERNARY)) {
                    ternary_ = true;
                }
            }
            if (ternary_) {
                if (parsBrackets.isEmpty() && prio > ElResolver.TERNARY_PRIO) {
                    operators.clear();
                    leftParFirstOperator = false;
                    operators.put(index, String.valueOf(curChar_));
                }
                parsBrackets.put(index, curChar_);
                index++;
                return;
            }
            if (parsBrackets.isEmpty()&&dot_&&prio != ElResolver.DECL_PRIO) {
                StringBuilder builtOperator_ = new StringBuilder();
                builtOperator_.append(curChar_);
                if (StringExpUtil.nextCharIs(_string, index + 1, len_, DOT_VAR)) {
                    builtOperator_.append(DOT_VAR);
                }
                boolean clearOperators_ = false;
                boolean foundOperator_ = false;
                if (prio == ElResolver.FCT_OPER_PRIO) {
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
                addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
                return;
            }
        }
        if (curChar_ == END_TERNARY) {
            parsBrackets.removeKey(parsBrackets.lastKey());
            if (parsBrackets.isEmpty() && prio > ElResolver.TERNARY_PRIO) {
                operators.put(index, String.valueOf(curChar_));
                enPars = false;
                enabledId = true;
                prio = ElResolver.TERNARY_PRIO;
            }
            index++;
            return;
        }
        if (!parsBrackets.isEmpty()) {
            index++;
            return;
        }
        if (prio == ElResolver.DECL_PRIO) {
            index++;
            return;
        }
        addNumOperators(_offset, _string, _d, curChar_);
    }

    private void addNumOperators(int _offset, String _string, Delimiters _d, char _curChar) {
        int lastPrintChar_ = del.getLastPrintIndex();
        int len_ = lastPrintChar_ + 1;
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int min_ = _d.getDelInstanceof().indexOfObj(index+_offset);
        if (isPairPositive(min_)) {
            int next_ = _d.getDelInstanceof().get(min_+1) - _offset;
            instOf = true;
            if (prio > ElResolver.CMP_PRIO) {
                clearOperators_ = true;
                prio = ElResolver.CMP_PRIO;
            }
            if (prio == ElResolver.CMP_PRIO) {
                foundOperator_ = true;
            }
            String op_ = _string.substring(index, next_);
            addPossibleNumOp(op_, clearOperators_, foundOperator_);
            return;
        }
        StringBuilder builtOperator_ = new StringBuilder();
        if (_curChar == DOT_VAR) {
            builtOperator_.append(DOT_VAR);
            if (prio == ElResolver.FCT_OPER_PRIO) {
                clearOperators_ = true;
                foundOperator_ = true;
            }
            addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
            return;
        }
        if (_curChar == NEG_BOOL_CHAR || _curChar == EQ_CHAR) {
            boolean nextIs_ = StringExpUtil.nextCharIs(_string, index + 1, len_, EQ_CHAR);
            if (_curChar == NEG_BOOL_CHAR || nextIs_) {
                builtOperator_.append(_curChar);
                if (nextIs_) {
                    builtOperator_.append(EQ_CHAR);
                }
                if (prio > ElResolver.EQ_PRIO) {
                    prio = ElResolver.EQ_PRIO;
                }
                if (prio == ElResolver.EQ_PRIO) {
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
            } else {
                builtOperator_.append(_curChar);
                if (isGreaterThanAff(prio)) {
                    clearOperators_ = true;
                    prio = ElResolver.AFF_PRIO;
                    foundOperator_ = true;
                }
            }
            addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
            return;
        }
        builtOperator_.append(_curChar);
        if (index + 1 >= len_) {
            changeSingleCharPrioAddPossible(_curChar, builtOperator_);
            return;
        }
        char nextChar_ = _string.charAt(index + 1);
        if (_curChar == LOWER_CHAR || _curChar == GREATER_CHAR) {
            if (_curChar == nextChar_) {
                int delta_ = 0;
                builtOperator_.append(nextChar_);
                if (StringExpUtil.nextCharIs(_string, index + 2, len_, nextChar_)) {
                    delta_++;
                    builtOperator_.append(nextChar_);
                    if (StringExpUtil.nextCharIs(_string, index + 3, len_, nextChar_)) {
                        delta_++;
                        builtOperator_.append(nextChar_);
                    }
                }
                if (StringExpUtil.nextCharIs(_string,delta_+index+2,len_,EQ_CHAR)) {
                    addAffNumOp(builtOperator_);
                    return;
                }
                if (prio > ElResolver.SHIFT_PRIO) {
                    prio = ElResolver.SHIFT_PRIO;
                }
                if (prio == ElResolver.SHIFT_PRIO) {
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
                addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
                return;
            }
            if (prio > ElResolver.CMP_PRIO) {
                clearOperators_ = true;
                prio = ElResolver.CMP_PRIO;
            }
            if (prio == ElResolver.CMP_PRIO) {
                //Event if there is a previous operator, add the current operator
                //Sample: 1<2<3 produces an error
                foundOperator_ = true;
            }
            if (StringExpUtil.nextCharIs(_string, index + 1, len_, EQ_CHAR)) {
                builtOperator_.append(_string.charAt(index + 1));
            }
            addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
            return;
        }
        if (_curChar == PLUS_CHAR || _curChar == MINUS_CHAR) {
            if (_curChar == nextChar_) {
                if (prio > ElResolver.POST_INCR_PRIO) {
                    clearOperators_ = true;
                    prio = ElResolver.POST_INCR_PRIO;
                    foundOperator_ = true;
                }
                builtOperator_.append(nextChar_);
                addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
                return;
            }
        }
        if (isLogicAndOr(_curChar, nextChar_)) {
            builtOperator_.append(_curChar);
            if (StringExpUtil.nextCharIs(_string,index+2,len_,EQ_CHAR)) {
                if (isGreaterThanAff(prio)) {
                    clearOperators_ = true;
                    prio = ElResolver.AFF_PRIO;
                    foundOperator_ = true;
                }
                builtOperator_.append(EQ_CHAR);
            } else {
                int prioOpMult_;
                if (_curChar == AND_CHAR) {
                    prioOpMult_ = ElResolver.AND_PRIO;
                } else {
                    prioOpMult_ = ElResolver.OR_PRIO;
                }
                if (prio > prioOpMult_) {
                    prio = prioOpMult_;
                }
                if (prio == prioOpMult_) {
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
            }
            addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
            return;
        }
        if (_curChar == BEGIN_TERNARY) {
            builtOperator_.append(_curChar);
            if (StringExpUtil.nextCharIs(_string,index+2,len_,EQ_CHAR)) {
                if (isGreaterThanAff(prio)) {
                    clearOperators_ = true;
                    prio = ElResolver.AFF_PRIO;
                    foundOperator_ = true;
                }
                builtOperator_.append(EQ_CHAR);
            } else {
                int prioOpMult_ = ElResolver.NULL_SAFE_PRIO;
                if (prio > prioOpMult_) {
                    prio = prioOpMult_;
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
            }
            addPossibleNumOp(builtOperator_.toString(), clearOperators_, foundOperator_);
            return;
        }
        if (nextChar_ == EQ_CHAR){
            addAffNumOp(builtOperator_);
            return;
        }
        changeSingleCharPrioAddPossible(_curChar, builtOperator_);
    }

    private void changeSingleCharPrioAddPossible(char _curChar, StringBuilder _op) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int prioOpMult_ = getSingleCharPrio(_curChar);
        if (prio > prioOpMult_) {
            prio = prioOpMult_;
        }
        if (prio == prioOpMult_) {
            clearOperators_ = true;
            foundOperator_ = true;
        }
        addPossibleNumOp(_op.toString(), clearOperators_, foundOperator_);
    }

    private void addAffNumOp(StringBuilder _op) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (isGreaterThanAff(prio)) {
            clearOperators_ = true;
            prio = ElResolver.AFF_PRIO;
            foundOperator_ = true;
        }
        _op.append(EQ_CHAR);
        addPossibleNumOp(_op.toString(), clearOperators_, foundOperator_);
    }

    private static boolean isAndOrChar(char _curChar) {
        boolean andOr_ = false;
        if (_curChar == AND_CHAR || _curChar == OR_CHAR) {
            andOr_ = true;
        }
        return andOr_;
    }

    private static boolean isPairPositive(int _nb) {
        return _nb >= 0 && _nb % 2 == 0;
    }

    private void addPossibleNumOp(String _op, boolean _clearOpers, boolean _foundOper) {
        if (_foundOper) {
            if (_clearOpers) {
                operators.clear();
            }
            leftParFirstOperator = false;
            fctName = EMPTY_STRING;
            instance = false;
            enPars = false;
            enabledId = false;
            operators.put(index,_op);
        }
        index += _op.length();
    }

    private int getSingleCharPrio(char _curChar) {
        int prioOpMult_ = 0;
        if (_curChar == MINUS_CHAR || _curChar == PLUS_CHAR) {
            prioOpMult_ = ElResolver.ADD_PRIO;
            return prioOpMult_;
        }
        if (_curChar == MULT_CHAR || _curChar == DIV_CHAR || _curChar == MOD_CHAR) {
            prioOpMult_ = ElResolver.MULT_PRIO;
            return prioOpMult_;
        }
        if (_curChar == AND_CHAR) {
            prioOpMult_ = ElResolver.BIT_AND_PRIO;
            return prioOpMult_;
        }
        if (_curChar == OR_CHAR) {
            prioOpMult_ = ElResolver.BIT_OR_PRIO;
            return prioOpMult_;
        }
        if (_curChar == XOR_CHAR) {
            prioOpMult_ = ElResolver.BIT_XOR_PRIO;
            return prioOpMult_;
        }
        return prioOpMult_;
    }

    private static int incrementUnary(String _string, int _from, int _to, int _offset, Delimiters _d) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ != MINUS_CHAR) {
                if (ch_ != NEG_BOOL) {
                    if (ch_ != PLUS_CHAR) {
                        if (ch_ != NEG_BOOL_CHAR) {
                            if (!Character.isWhitespace(ch_)) {
                                int sum_ = _offset + j_;
                                int indexCast_ = _d.getDelCast().indexOfObj(sum_);
                                if (indexCast_ > -1) {
                                    int next_ = _d.getDelCast().get(indexCast_ + 1);
                                    next_ -= _offset;
                                    j_ = next_ + 1;
                                    continue;
                                }
                                indexCast_ = _d.getDelExplicit().indexOfObj(sum_);
                                if (indexCast_ > -1) {
                                    int next_ = _d.getDelExplicit().get(indexCast_ + 1);
                                    next_ -= _offset;
                                    j_ = next_ + 1;
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            j_++;
        }
        return j_;
    }

    private static boolean isGreaterThanAff(int _prio) {
        return _prio > ElResolver.AFF_PRIO && _prio != ElResolver.TERNARY_PRIO && _prio != ElResolver.NULL_SAFE_PRIO;
    }

    private static void addCommaOperIfNotEmpty(IntTreeMap<String> _operators, int _i, char... _open) {
        String oper_ = _operators.firstValue();
        int len_ = oper_.length();
        for (char c: _open) {
            if (StringExpUtil.nextCharIs(oper_,0, len_,c)) {
                _operators.put(_i, String.valueOf(SEP_ARG));
                break;
            }
        }
    }


    private static void addOperIfNotEmpty(IntTreeMap< String> _operators, int _i, char _open, char _op) {
        String oper_ = _operators.firstValue();
        if (StringExpUtil.nextCharIs(oper_,0, oper_.length(),_open)) {
            _operators.put(_i, String.valueOf(_op));
        }
    }

    private static boolean isLogicAndOr(char _curChar,
                                        char _nextChar) {
        boolean andOr_ = isAndOrChar(_curChar);
        return andOr_ && _nextChar == _curChar;
    }

    boolean isErrorDot() {
        return errorDot;
    }

    int getIndex() {
        return index;
    }

    CustList<PartOffset> getPartsOffs() {
        return partsOffs;
    }

    int getPrio() {
        return prio;
    }

    IntTreeMap<String> getOperators() {
        return operators;
    }

    String getExtracted() {
        return extracted;
    }

    Ints getLaterIndexesDouble() {
        return laterIndexesDouble;
    }

    String getFctName() {
        return fctName;
    }

    boolean isInstance() {
        return instance;
    }

    boolean isInstOf() {
        return instOf;
    }

    boolean isLeftParFirstOperator() {
        return leftParFirstOperator;
    }
}
