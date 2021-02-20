package code.maths.litteral;

import code.util.core.IndexConstants;
import code.util.core.StringUtil;

class MathAfUnaryParts {

    private final StrTypes operators = new StrTypes();
    private int parsBrackets;
    private int prio = MathResolver.FCT_OPER_PRIO;
    private int index;
    private boolean useFct;
    private String fctName = "";
    MathAfUnaryParts(String _string,int _index,int _firstPrintChar, int _lastPrintChar) {
        index = _index;
        String opUn_ = Character.toString(_string.charAt(_firstPrintChar));
        if (areUnary(_string, _firstPrintChar)) {
            prio = MathResolver.UNARY_PRIO;
            operators.addEntry(_firstPrintChar, opUn_);
            index = incrementUnary(_string, _firstPrintChar + 1, _lastPrintChar);
        }
    }

    void loop(int _offset, String _string,
              Delimiters _d) {
        char curChar_ = _string.charAt(index);
        if (!_d.getAllowedOperatorsIndexes().containsObj((long)index+_offset)) {
            index++;
            return;
        }

        if (curChar_ == MathResolver.PAR_LEFT) {
            if (parsBrackets == 0 && prio == MathResolver.FCT_OPER_PRIO) {
                useFct = true;
                fctName = _string.substring(IndexConstants.FIRST_INDEX, index);
                operators.clear();
                operators.addEntry(index, Character.toString(MathResolver.PAR_LEFT));
            }
            parsBrackets++;
        }
        if (curChar_ == MathResolver.SEP_ARG && parsBrackets == 1 && prio == MathResolver.FCT_OPER_PRIO) {
            operators.addEntry(index, Character.toString(MathResolver.SEP_ARG));
        }
        if (curChar_ == MathResolver.PAR_RIGHT) {
            procParRight();
            return;
        }
        if (parsBrackets != 0) {
            index++;
            return;
        }
        procNumOps(_string, curChar_);
    }

    private void procParRight() {
        parsBrackets--;
        if (parsBrackets==0 && prio == MathResolver.FCT_OPER_PRIO) {
            operators.addEntry(index, Character.toString(MathResolver.PAR_RIGHT));
        }
        index++;
    }

    private void procNumOps(String _string, char _curChar) {
        StringBuilder builtOperator_ = new StringBuilder();
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int increment_ = 1;
        if (cmpEq(_curChar)) {
            builtOperator_.append(_curChar);
            if (_curChar == MathResolver.NEG_BOOL_CHAR && index + 1 < _string.length()) {
                char nextChar_ = _string.charAt(index + 1);
                if (nextChar_ == MathResolver.EQ_CHAR) {
                    builtOperator_.append(MathResolver.EQ_CHAR);
                    increment_++;
                }
            }
            if (prio > MathResolver.EQ_PRIO) {
                clearOperators_ = true;
                prio = MathResolver.EQ_PRIO;
            }
            if (prio == MathResolver.EQ_PRIO) {
                foundOperator_ = true;
            }
        }
        procOp(_string, _curChar, builtOperator_, clearOperators_, foundOperator_, increment_);
    }

    private void procOp(String _string, char _curChar, StringBuilder _builtOperator, boolean _clearOperators, boolean _foundOperator, int _increment) {
        boolean clearOperators_ = _clearOperators;
        boolean foundOperator_ = _foundOperator;
        int increment_ = _increment;
        int prioOpMult_ = getPrio(_curChar);
        if (prioOpMult_ > 0) {
            _builtOperator.append(_curChar);
            reducePrio(prioOpMult_);
            if (prio == prioOpMult_) {
                clearOperators_ = true;
                foundOperator_ = true;
            }
        }
        if (cmp(_curChar)) {
            _builtOperator.append(_curChar);
            if (prio > MathResolver.CMP_PRIO) {
                clearOperators_ = true;
                prio = MathResolver.CMP_PRIO;
            }
            if (prio == MathResolver.CMP_PRIO) {
                foundOperator_ = true;
            }
            char nextChar_ = _string.charAt(index + 1);
            if (nextChar_ == MathResolver.EQ_CHAR) {
                _builtOperator.append(nextChar_);
                increment_++;
            }
        }
        tryAddOp(_builtOperator, clearOperators_, foundOperator_, increment_);
    }

    private static boolean cmpEq(char _curChar) {
        return _curChar == MathResolver.NEG_BOOL_CHAR || _curChar == MathResolver.EQ_CHAR;
    }

    private static boolean cmp(char _curChar) {
        return _curChar == MathResolver.LOWER_CHAR || _curChar == MathResolver.GREATER_CHAR;
    }

    private void reducePrio(int _prioOpMult) {
        if (prio > _prioOpMult) {
            prio = _prioOpMult;
        }
    }

    private void tryAddOp(StringBuilder _builtOperator, boolean _clearOperators, boolean _foundOperator, int _increment) {
        if (_foundOperator) {
            if (_clearOperators) {
                useFct = false;
                fctName = "";
                operators.clear();
            }
            operators.addEntry(index, _builtOperator.toString());
        }
        index += _increment;
    }

    private static boolean areUnary(String _string, int _i) {
        return _string.charAt(_i) == MathResolver.MINUS_CHAR || _string.charAt(_i) == MathResolver.PLUS_CHAR || _string.charAt(_i) == MathResolver.NEG_BOOL_CHAR;
    }

    private static int getPrio(char _curChar) {
        int prioOpMult_ = 0;
        if (_curChar == MathResolver.MINUS_CHAR || _curChar == MathResolver.PLUS_CHAR) {
            prioOpMult_ = MathResolver.ADD_PRIO;
        } else if (_curChar == MathResolver.MULT_CHAR || _curChar == MathResolver.DIV_CHAR) {
            prioOpMult_ = MathResolver.MULT_PRIO;
        } else if (_curChar == MathResolver.AND_CHAR) {
            prioOpMult_ = MathResolver.AND_PRIO;
        } else {
            if (_curChar == MathResolver.OR_CHAR) {
                prioOpMult_ = MathResolver.OR_PRIO;
            }
        }
        return prioOpMult_;
    }
    private static int incrementUnary(String _string, int _from, int _to) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ != MathResolver.MINUS_CHAR && ch_ != MathResolver.PLUS_CHAR && ch_ != MathResolver.NEG_BOOL_CHAR && !StringUtil.isWhitespace(ch_)) {
                break;
            }
            j_++;
        }
        return j_;
    }

    int getPrio() {
        return prio;
    }

    StrTypes getOperators() {
        return operators;
    }

    String getFctName() {
        return fctName;
    }

    boolean isUseFct() {
        return useFct;
    }

    int getIndex() {
        return index;
    }
}
