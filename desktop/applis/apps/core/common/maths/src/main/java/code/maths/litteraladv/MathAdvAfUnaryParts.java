package code.maths.litteraladv;

import code.maths.litteralcom.MatCommonCst;
import code.maths.litteralcom.MathExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class MathAdvAfUnaryParts {

    private final StrTypes opers = new StrTypes();
    private final MaStackOperators dels = new MaStackOperators();
    private int prio = MatCommonCst.FCT_OPER_PRIO;
    private int current;
    private String fct = "";
    MathAdvAfUnaryParts(String _string, int _index, int _lastPrintChar) {
        current = _index;
        String opUn_ = Character.toString(_string.charAt(_index));
        if (MathExpUtil.unary(_string.charAt(_index))) {
            prio = MatCommonCst.UNARY_PRIO;
            opers.addEntry(_index, opUn_);
            current = incrementUnary(_string, _index + 1, _lastPrintChar);
        }
    }

    void loop(int _offset, String _string,
              MaDelimiters _d) {
        char curChar_ = _string.charAt(current);
        if (!_d.getOperatorsIndexes().containsObj((long) current +_offset)) {
            current++;
            return;
        }

        if (curChar_ == MatCommonCst.PAR_LEFT) {
            if (delFct()) {
                fct = _string.substring(IndexConstants.FIRST_INDEX, current);
                opers.clear();
                opers.addEntry(current, Character.toString(MatCommonCst.PAR_LEFT));
            }
            dels.add(current,curChar_);
        }
        if (comma(curChar_)) {
            opers.addEntry(current, Character.toString(MatCommonCst.SEP_ARG));
        }
        if (rightDel(curChar_)) {
            procRight(curChar_);
            return;
        }
        if (curChar_ == MatCommonCst.ARR_LEFT) {
            if (delFct()) {
                fct = "";
                opers.clear();
                opers.addEntry(current, "[");
            }
            dels.add(current, curChar_);
        }
        if (!dels.empty()) {
            current++;
            return;
        }
        procNumOps(_string, curChar_);
    }

    private boolean comma(char _curChar) {
        return _curChar == MatCommonCst.SEP_ARG && dels.nb() == 1 && prio == MatCommonCst.FCT_OPER_PRIO;
    }

    private static boolean rightDel(char _curChar) {
        return _curChar == MatCommonCst.PAR_RIGHT || _curChar == MatCommonCst.ARR_RIGHT;
    }

    private void procRight(char _rightPart) {
        dels.remove();
        if (delFct()) {
            opers.addEntry(current, Character.toString(_rightPart));
        }
        current++;
    }

    private boolean delFct() {
        return dels.empty() && prio == MatCommonCst.FCT_OPER_PRIO;
    }

    private void procNumOps(String _string, char _curChar) {
        StringBuilder builtOperator_ = new StringBuilder();
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int increment_ = 1;
        if (MathExpUtil.cmpEq(_curChar)) {
            builtOperator_.append(_curChar);
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
            char nextChar_ = _string.charAt(current + 1);
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
                fct = "";
                opers.clear();
            }
            opers.addEntry(current, _builtOperator.toString());
        }
        current += _increment;
    }

    private static int getPrio(char _curChar) {
        return MatCommonCst.getPrio(_curChar);
    }
    private static int incrementUnary(String _string, int _from, int _to) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (!MathExpUtil.unary(ch_) && !StringUtil.isWhitespace(ch_)) {
                break;
            }
            j_++;
        }
        return j_;
    }

    int getPrio() {
        return prio;
    }

    StrTypes getOpers() {
        return opers;
    }

    String getFct() {
        return fct;
    }

    int getCurrent() {
        return current;
    }
}
