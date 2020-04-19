package code.expressionlanguage.instr;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
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
    private boolean leftParFirstOperator = false;
    private boolean instOf = false;
    private String fctName = EMPTY_STRING;
    private boolean enabledId = false;
    private boolean instance;
    private Ints laterIndexesDouble = new Ints();
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
        } else if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) {
            prio = ElResolver.UNARY_PRIO;
            operators.put(firstPrintChar_, String.valueOf(_string.charAt(firstPrintChar_)));
            index = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
        } else if (_string.charAt(firstPrintChar_) == NEG_BOOL_CHAR || _string.charAt(firstPrintChar_) == NEG_BOOL) {
            prio = ElResolver.UNARY_PRIO;
            operators.put(firstPrintChar_, String.valueOf(_string.charAt(firstPrintChar_)));
            index = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
        } else if (_d.getDelCast().contains(firstPrintChar_+_offset)) {
            prio = ElResolver.UNARY_PRIO;
            int min_ = _d.getDelCast().indexOfObj(firstPrintChar_+_offset);
            int max_ = _d.getDelCast().get(min_ + 1) - _offset;
            operators.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            int ext_ = min_ / 2;
            extracted = _d.getDelCastExtract().get(ext_);
            partsOffs.addAllElts(_d.getCastParts().get(ext_));
            index = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
        } else if (_d.getDelExplicit().contains(firstPrintChar_+_offset)) {
            prio = ElResolver.UNARY_PRIO;
            int min_ = _d.getDelExplicit().indexOfObj(firstPrintChar_+_offset);
            int max_ = _d.getDelExplicit().get(min_ + 1) - _offset;
            operators.put(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            index = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
        }
    }
    void setInstance(String _string, Analyzable _conf) {
        int firstPrintChar_ = del.getFirstPrintIndex();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        if (ContextEl.startsWithKeyWord(_string.substring(firstPrintChar_), keyWordNew_)) {
            instance = true;
        }
        if (_string.charAt(firstPrintChar_) == ANN_ARR_LEFT) {
            instance = true;
        }
    }
    void setState(int _offset, String _string, Delimiters _d) {
        int firstPrintChar_ = del.getFirstPrintIndex();
        int lastPrintChar_ = del.getLastPrintIndex();
        int len_ = lastPrintChar_ + 1;
        char curChar_ = _string.charAt(index);
        if (_d.getDimsAddonIndexes().containsObj(index+_offset)) {
            laterIndexesDouble.add(index);
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
                    operators.put(index, EMPTY_STRING);
                }
            }
            parsBrackets.put(index, curChar_);
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
                        addOperIfBegin(operators, index, firstPrintChar_, ANN_ARR);
                    }
                }
            }
            parsBrackets.put(index, curChar_);
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
                        operators.clear();
                        addOperIfBegin(operators, index, firstPrintChar_, ARR);
                    }
                }
            }
            parsBrackets.put(index, curChar_);
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
        if (curChar_ == BEGIN_TERNARY) {
            if (parsBrackets.isEmpty() && prio > ElResolver.TERNARY_PRIO) {
                operators.clear();
                leftParFirstOperator = false;
                operators.put(index, String.valueOf(curChar_));
            }
            parsBrackets.put(index, curChar_);
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
        StringBuilder builtOperator_ = new StringBuilder();
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int increment_ = 1;
        if (curChar_ == DOT_VAR) {
            builtOperator_.append(DOT_VAR);
            if (prio == ElResolver.FCT_OPER_PRIO) {
                clearOperators_ = true;
                foundOperator_ = true;
            }
        }
        if (curChar_ == NEG_BOOL_CHAR || curChar_ == EQ_CHAR) {
            boolean nextIs_ = StringExpUtil.nextCharIs(_string, index + 1, len_, EQ_CHAR);
            if (curChar_ == NEG_BOOL_CHAR || nextIs_) {
                builtOperator_.append(curChar_);
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
                increment_ = 2;
            } else {
                builtOperator_.append(curChar_);
                if (isGreaterThanAff(prio)) {
                    clearOperators_ = true;
                    prio = ElResolver.AFF_PRIO;
                    foundOperator_ = true;
                }
                increment_ = 1;
            }
        }
        int prioOpMult_ = 0;
        boolean andOr_ = false;
        if (curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) {
            if (StringExpUtil.nextCharIs(_string, index + 1, len_, curChar_)) {
                prioOpMult_ = ElResolver.SHIFT_PRIO;
            }
        }
        if (curChar_ == MINUS_CHAR || curChar_ == PLUS_CHAR) {
            prioOpMult_ = ElResolver.ADD_PRIO;
        } else if (curChar_ == MULT_CHAR || curChar_ == DIV_CHAR || curChar_ == MOD_CHAR) {
            prioOpMult_ = ElResolver.MULT_PRIO;
        } else if (curChar_ == AND_CHAR) {
            prioOpMult_ = ElResolver.AND_PRIO;
            andOr_ = true;
        } else if (curChar_ == OR_CHAR) {
            prioOpMult_ = ElResolver.OR_PRIO;
            andOr_ = true;
        } else if (curChar_ == XOR_CHAR) {
            prioOpMult_ = ElResolver.BIT_XOR_PRIO;
        }
        if (prioOpMult_ > 0) {
            builtOperator_.append(curChar_);
            boolean processDefaultOp_ = true;
            if (index + 1 < len_) {
                char nextChar_ = _string.charAt(index + 1);
                int delta_ = 0;
                if (prioOpMult_ == ElResolver.SHIFT_PRIO) {
                    builtOperator_.append(nextChar_);
                    if (StringExpUtil.nextCharIs(_string, index + 2, len_, nextChar_)) {
                        delta_++;
                        builtOperator_.append(nextChar_);
                        if (StringExpUtil.nextCharIs(_string, index + 3, len_, nextChar_)) {
                            delta_++;
                            builtOperator_.append(nextChar_);
                        }
                    }
                }
                if (isIncrementOperator(curChar_, prioOpMult_, nextChar_)) {
                    if (prio > ElResolver.POST_INCR_PRIO) {
                        clearOperators_ = true;
                        prio = ElResolver.POST_INCR_PRIO;
                        foundOperator_ = true;
                    }
                    increment_ = 2;
                    builtOperator_.append(nextChar_);
                    processDefaultOp_ = false;
                } else if (isLogicAndOr(curChar_, andOr_, nextChar_)) {
                    builtOperator_.append(curChar_);
                    if (StringExpUtil.nextCharIs(_string,index+2,len_,EQ_CHAR)) {
                        increment_ = 3;
                        if (isGreaterThanAff(prio)) {
                            clearOperators_ = true;
                            prio = ElResolver.AFF_PRIO;
                            foundOperator_ = true;
                        }
                        builtOperator_.append(EQ_CHAR);
                    } else {
                        if (prio > prioOpMult_) {
                            prio = prioOpMult_;
                        }
                        if (prio == prioOpMult_) {
                            clearOperators_ = true;
                            foundOperator_ = true;
                        }
                        increment_ = 2;
                    }
                    processDefaultOp_ = false;
                } else if (isAffectation(nextChar_, prioOpMult_, _string, delta_+index + 2, len_)) {
                    increment_ = 2;
                    if (prioOpMult_ == ElResolver.SHIFT_PRIO) {
                        increment_++;
                    }
                    if (isGreaterThanAff(prio)) {
                        clearOperators_ = true;
                        prio = ElResolver.AFF_PRIO;
                        foundOperator_ = true;
                    }
                    builtOperator_.append(EQ_CHAR);
                    processDefaultOp_ = false;
                } else {
                    if (curChar_ == AND_CHAR) {
                        prioOpMult_ = ElResolver.BIT_AND_PRIO;
                    } else if (curChar_ == OR_CHAR) {
                        prioOpMult_ = ElResolver.BIT_OR_PRIO;
                    }
                    if (prioOpMult_ == ElResolver.SHIFT_PRIO) {
                        increment_++;
                        increment_ += delta_;
                    }
                }
            }
            if (processDefaultOp_) {
                if (prio > prioOpMult_) {
                    prio = prioOpMult_;
                }
                if (prio == prioOpMult_) {
                    clearOperators_ = true;
                    foundOperator_ = true;
                }
            }
        }
        int min_ = _d.getDelInstanceof().indexOfObj(index+_offset);
        if (min_ >= 0 && min_ % 2 == 0) {
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
            builtOperator_.append(op_);
            increment_ = op_.length();
        }
        if ((curChar_ == LOWER_CHAR || curChar_ == GREATER_CHAR) && prioOpMult_ != ElResolver.SHIFT_PRIO) {
            builtOperator_.append(curChar_);
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
                char nextChar_ = _string.charAt(index + 1);
                builtOperator_.append(nextChar_);
                increment_++;
            }
        }
        if (foundOperator_) {
            if (clearOperators_) {
                operators.clear();
            }
            leftParFirstOperator = false;
            fctName = EMPTY_STRING;
            instance = false;
            enPars = false;
            enabledId = false;
            operators.put(index,builtOperator_.toString());
        }
        index += increment_;
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
        return _prio > ElResolver.AFF_PRIO && _prio != ElResolver.TERNARY_PRIO;
    }

    private static void addOperIfBegin(IntTreeMap< String> _operators, int _i, int _first, String _arr) {
        if (_first == _i) {
            _operators.put(_i, _arr);
        } else {
            _operators.put(_i, EMPTY_STRING);
        }
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
    private static boolean isAffectation(char _nextChar, int _prioOpMult, String _string, int _lastCharIndex,int _len) {
        boolean aff_ = false;
        if (_nextChar == EQ_CHAR) {
            aff_ = true;
        } else if (_prioOpMult == ElResolver.SHIFT_PRIO && StringExpUtil.nextCharIs(_string, _lastCharIndex, _len, EQ_CHAR)) {
            aff_ = true;
        }
        return aff_;
    }
    private static boolean isIncrementOperator(char _curChar, int _prioOpMult,
                                               char _nextChar) {
        return _prioOpMult == ElResolver.ADD_PRIO && _nextChar == _curChar;
    }

    private static boolean isLogicAndOr(char _curChar, boolean _andOr,
                                        char _nextChar) {
        return _andOr && _nextChar == _curChar;
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
