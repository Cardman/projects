package code.maths.litteral;

import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MathExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

class MathAfUnaryParts {

    private final StrTypes operators = new StrTypes();
    private int parsBrackets;
    private int prio = MatCommonCst.FCT_OPER_PRIO;
    private int index;
    private String fctName = "";
    MathAfUnaryParts(String _string, int _index, int _lastPrintChar) {
        index = _index;
        String opUn_ = Character.toString(_string.charAt(_index));
        if (areUnary(_string, _index)) {
            prio = MatCommonCst.UNARY_PRIO;
            operators.addEntry(_index, opUn_);
            index = incrementUnary(_string, _index + 1, _lastPrintChar);
        }
    }

    void loop(int _offset, String _string,
              MbDelimiters _d) {
        char curChar_ = _string.charAt(index);
        if (!_d.getAllowedOperatorsIndexes().containsObj((long)index+_offset)) {
            index++;
            return;
        }

        if (curChar_ == MatCommonCst.PAR_LEFT) {
            if (delFct()) {
                fctName = _string.substring(IndexConstants.FIRST_INDEX, index);
                operators.clear();
                operators.addEntry(index, Character.toString(MatCommonCst.PAR_LEFT));
            }
            parsBrackets++;
        }
        if (curChar_ == MatCommonCst.SEP_ARG && parsBrackets == 1 && prio == MatCommonCst.FCT_OPER_PRIO) {
            operators.addEntry(index, Character.toString(MatCommonCst.SEP_ARG));
        }
        if (curChar_ == MatCommonCst.PAR_RIGHT) {
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
        if (delFct()) {
            operators.addEntry(index, Character.toString(MatCommonCst.PAR_RIGHT));
        }
        index++;
    }

    private boolean delFct() {
        return parsBrackets==0 && prio == MatCommonCst.FCT_OPER_PRIO;
    }

    private void procNumOps(String _string, char _curChar) {
        StringBuilder builtOperator_ = new StringBuilder();
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int increment_ = 1;
        if (MathExpUtil.cmpEq(_curChar)) {
            builtOperator_.append(_curChar);
            if (_curChar == MatCommonCst.NEG_BOOL_CHAR && index + 1 < _string.length()) {
                char nextChar_ = _string.charAt(index + 1);
                if (nextChar_ == MatCommonCst.EQ_CHAR) {
                    builtOperator_.append(MatCommonCst.EQ_CHAR);
                    increment_++;
                }
            }
            if (prio > MatCommonCst.EQ_PRIO) {
                clearOperators_ = true;
                prio = MatCommonCst.EQ_PRIO;
            }
            if (prio == MatCommonCst.EQ_PRIO) {
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
        if (MathExpUtil.cmpStr(_curChar)) {
            _builtOperator.append(_curChar);
            if (prio > MatCommonCst.CMP_PRIO) {
                clearOperators_ = true;
                prio = MatCommonCst.CMP_PRIO;
            }
            if (prio == MatCommonCst.CMP_PRIO) {
                foundOperator_ = true;
            }
            char nextChar_ = _string.charAt(index + 1);
            if (nextChar_ == MatCommonCst.EQ_CHAR) {
                _builtOperator.append(nextChar_);
                increment_++;
            }
        }
        tryAddOp(_builtOperator, clearOperators_, foundOperator_, increment_);
    }

    private void reducePrio(int _prioOpMult) {
        if (prio > _prioOpMult) {
            prio = _prioOpMult;
        }
    }

    private void tryAddOp(StringBuilder _builtOperator, boolean _clearOperators, boolean _foundOperator, int _increment) {
        if (_foundOperator) {
            if (_clearOperators) {
                fctName = "";
                operators.clear();
            }
            operators.addEntry(index, _builtOperator.toString());
        }
        index += _increment;
    }

    private static boolean areUnary(String _string, int _i) {
        return _string.charAt(_i) == MatCommonCst.MINUS_CHAR || _string.charAt(_i) == MatCommonCst.PLUS_CHAR || _string.charAt(_i) == MatCommonCst.NEG_BOOL_CHAR;
    }

    private static int getPrio(char _curChar) {
        return MatCommonCst.getPrio(_curChar);
    }
    private static int incrementUnary(String _string, int _from, int _to) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ != MatCommonCst.MINUS_CHAR && ch_ != MatCommonCst.PLUS_CHAR && ch_ != MatCommonCst.NEG_BOOL_CHAR && !StringUtil.isWhitespace(ch_)) {
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

    int getIndex() {
        return index;
    }
}
