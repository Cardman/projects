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
        StringBuilder built_ = new StringBuilder();
        boolean clear_ = false;
        boolean found_ = false;
        int increment_ = 1;
        if (MathExpUtil.cmpEq(_curChar)) {
            built_.append(_curChar);
            if (prio > MatCommonCst.EQ_PRIO) {
                clear_ = true;
                prio = MatCommonCst.EQ_PRIO;
            }
            if (prio == MatCommonCst.EQ_PRIO) {
                found_ = true;
            }
        }
        procOp(_string, _curChar, built_, clear_, found_, increment_);
    }

    private void procOp(String _string, char _curChar, StringBuilder _built, boolean _clearOperators, boolean _foundOperator, int _increment) {
        boolean clear_ = _clearOperators;
        boolean found_ = _foundOperator;
        int increment_ = _increment;
        int prioSymbol_ = getPrio(_curChar);
        if (prioSymbol_ > 0) {
            _built.append(_curChar);
            reducePrio(prioSymbol_);
            if (prio == prioSymbol_) {
                clear_ = true;
                found_ = true;
            }
        }
        if (MathExpUtil.cmpStr(_curChar)) {
            _built.append(_curChar);
            if (prio > MatCommonCst.CMP_PRIO) {
                clear_ = true;
                prio = MatCommonCst.CMP_PRIO;
            }
            if (prio == MatCommonCst.CMP_PRIO) {
                found_ = true;
            }
            char nextChar_ = _string.charAt(current + 1);
            if (nextChar_ == MatCommonCst.EQ_CHAR) {
                _built.append(nextChar_);
                increment_++;
            }
        }
        tryAddOp(_built, clear_, found_, increment_);
    }

    private void reducePrio(int _prioSymbol) {
        if (prio > _prioSymbol) {
            prio = _prioSymbol;
        }
    }

    private void tryAddOp(StringBuilder _builtOperator, boolean _clear, boolean _found, int _increment) {
        if (_found) {
            if (_clear) {
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
