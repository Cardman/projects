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
    private final int lastPrintChar;
    private String fct = "";
    MathAdvAfUnaryParts(String _string, int _index, int _lastPrintChar) {
        current = _index;
        lastPrintChar = _lastPrintChar;
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
        if (commaExt(curChar_)) {
            if (prio > MatCommonCst.DECL_PRIO) {
                opers.clear();
            }
            prio = MatCommonCst.DECL_PRIO;
            opers.addEntry(current, Character.toString(curChar_));
        }
        if (comma(curChar_)||commaPt(curChar_)) {
            opers.addEntry(current, Character.toString(curChar_));
        }
        if (rightDel(curChar_)) {
            procRight(curChar_);
            return;
        }
        procLeftNoPar(curChar_,MatCommonCst.ARR_LEFT);
        procLeftNoPar(curChar_,MatCommonCst.BRA_LEFT);
        if (decl()) {
            current++;
            return;
        }
        procNumOps(_string, curChar_);
    }

    private boolean decl() {
        return !dels.empty()||prio == MatCommonCst.DECL_PRIO;
    }

    private void procLeftNoPar(char _curChar, char _leftChar) {
        if (_curChar == _leftChar) {
            if (delFct()) {
                fct = "";
                opers.clear();
                opers.addEntry(current, Character.toString(_leftChar));
            }
            dels.add(current,_curChar);
        }
    }
    private boolean comma(char _curChar) {
        return _curChar == MatCommonCst.SEP_ARG && dels.nb() == 1 && dels.oper() != '{' && prio == MatCommonCst.FCT_OPER_PRIO;
    }
    private boolean commaExt(char _curChar) {
        return _curChar == MatCommonCst.SEP_ARG && dels.nb() == 0;
    }

    private boolean commaPt(char _curChar) {
        return _curChar == MatCommonCst.SEP_ARG_BRA && dels.nb() == 1 && dels.oper() == '{' && prio == MatCommonCst.FCT_OPER_PRIO;
    }
    private static boolean rightDel(char _curChar) {
        return _curChar == MatCommonCst.PAR_RIGHT || _curChar == MatCommonCst.ARR_RIGHT || _curChar == MatCommonCst.BRA_RIGHT;
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
        if (_curChar == '!' && okFact(_string)) {
            built_.append(_curChar);
            if (prio > MatCommonCst.FACT_PRIO) {
                prio = MatCommonCst.FACT_PRIO;
            }
            if (prio == MatCommonCst.FACT_PRIO) {
                clear_ = true;
                found_ = true;
            }
        } else {
            if (_curChar == '=' && MathExpUtil.charIs(_string,lastPrintChar+1,current+1,'>')) {
                procEvt(_curChar,built_, false, false,increment_);
                return;
            }
            if (MathExpUtil.cmpEq(_curChar)) {
                procEq(_string,_curChar);
                return;
            }
        }
        procOp(_string, _curChar, built_, clear_, found_, increment_);
    }

    private void procEq(String _string, char _curChar) {
        boolean clear_ = false;
        boolean found_ = false;
        int increment_ = 1;
        StringBuilder built_ = new StringBuilder();
        built_.append(_curChar);
        if (prio > MatCommonCst.EQ_PRIO) {
            clear_ = true;
            prio = MatCommonCst.EQ_PRIO;
        }
        if (prio == MatCommonCst.EQ_PRIO) {
            found_ = true;
        }
        procOp(_string, _curChar, built_, clear_, found_, increment_);
    }
    private boolean okFact(String _string) {
        return current == lastPrintChar || nextPrintIs(_string);
    }

    private boolean nextPrintIs(String _string) {
        int next_ = MaParser.skipWhite(_string, lastPrintChar + 1, current + 1);
        return MathExpUtil.charIs(_string,lastPrintChar + 1,next_,'!')
                || MathExpUtil.charIs(_string,lastPrintChar + 1,next_,'=');
    }
    private void procOp(String _string, char _curChar, StringBuilder _built, boolean _clearOperators, boolean _foundOperator, int _increment) {
        boolean clear_ = _clearOperators;
        boolean found_ = _foundOperator;
        int prioSymbol_ = getPrio(_curChar);
        if (prioSymbol_ > 0) {
            _built.append(_curChar);
            reducePrio(prioSymbol_);
            if (prio == prioSymbol_) {
                clear_ = true;
                found_ = true;
            }
        }
        if (_curChar == '<'&&MathExpUtil.charIs(_string,lastPrintChar + 1,current+1,'>')) {
            procEvt(_curChar,_built,_clearOperators,_foundOperator,_increment);
            return;
        }
        if (MathExpUtil.cmpStr(_curChar)) {
            procCmp(_string, _curChar, _built, _clearOperators, _foundOperator, _increment);
            return;
        }
        tryAddOp(_built, clear_, found_, _increment);
    }

    private void procCmp(String _string, char _curChar, StringBuilder _built,
                         boolean _clearOperators, boolean _foundOperator, int _increment) {
        boolean clear_ = _clearOperators;
        boolean found_ = _foundOperator;
        int increment_ = _increment;
        _built.append(_curChar);
        if (prio > MatCommonCst.CMP_PRIO) {
            clear_ = true;
            prio = MatCommonCst.CMP_PRIO;
        }
        found_ = possibleMod(MatCommonCst.CMP_PRIO,found_);
        if (MathExpUtil.charIs(_string,lastPrintChar+1,current+1,MatCommonCst.EQ_CHAR)) {
            _built.append(MatCommonCst.EQ_CHAR);
            increment_++;
        }
        tryAddOp(_built, clear_, found_, increment_);
    }
    private void procEvt(char _curChar, StringBuilder _built,
                         boolean _clearOperators, boolean _foundOperator, int _increment) {
        boolean clear_ = _clearOperators;
        boolean found_ = _foundOperator;
        _built.append(_curChar);
        _built.append('>');
        if (prio > MatCommonCst.ASS_PRIO) {
            clear_ = true;
            prio = MatCommonCst.ASS_PRIO;
        }
        found_ = possibleMod(MatCommonCst.ASS_PRIO,found_);
        tryAddOp(_built, clear_, found_, _increment+1);
    }
    private boolean possibleMod(int _prio, boolean _found) {
        if (prio == _prio) {
            return true;
        }
        return _found;
    }
    private void reducePrio(int _prioSymbol) {
        if (prio > _prioSymbol) {
            prio = _prioSymbol;
        }
    }

    private void tryAddOp(StringBuilder _built, boolean _clear, boolean _found, int _increment) {
        if (_found) {
            if (_clear) {
                fct = "";
                opers.clear();
            }
            opers.addEntry(current, _built.toString());
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
