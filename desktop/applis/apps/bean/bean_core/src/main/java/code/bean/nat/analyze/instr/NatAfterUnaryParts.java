package code.bean.nat.analyze.instr;

import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.analyze.instr.StackOperators;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;

final class NatAfterUnaryParts {
    private static final String EMPTY_STRING = "";

    private static final char NEG_BOOL_CHAR = '!';

    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char DOT_VAR = '.';

    private final StrTypes operators = new StrTypes();
    private final StackOperators parsBrackets = new StackOperators();
    private int prio = NatElResolver.FCT_OPER_PRIO;
    private int index;

    private boolean enPars = true;
    private String fctName = EMPTY_STRING;

    NatAfterUnaryParts(String _string) {
        int firstPrintChar_ = 0;
        if (_string.charAt(firstPrintChar_) == NEG_BOOL_CHAR) {
            prio = NatElResolver.UNARY_PRIO;
            operators.addEntry(firstPrintChar_, Character.toString(_string.charAt(firstPrintChar_)));
            index = firstPrintChar_ + 1;
        }
    }

    void setState(int _offset, String _string, Delimiters _d) {
        char curChar_ = _string.charAt(index);
        if (!_d.getAllowedOperatorsIndexes().containsObj((long)index+_offset)) {
            index++;
            return;
        }
        if (curChar_ == PAR_LEFT) {
            if (en(0)) {
                fctName = _string.substring(IndexConstants.FIRST_INDEX, index);
                operators.addEntry(index, Character.toString(PAR_LEFT));
            }
            parsBrackets.addEntry(index, curChar_);
            index++;
            return;
        }
        if (curChar_ == SEP_ARG) {
            if (en(1)){
                addCommaOperIfNotEmpty(operators, index);
            }
            index++;
            return;
        }
        if (curChar_ == PAR_RIGHT) {
            parsBrackets.removeLast();
            if (en(0)) {
                addOperIfNotEmpty(operators, index, PAR_RIGHT);
                enPars = false;
            }
            index++;
            return;
        }
        if (!parsBrackets.isEmpty()) {
            index++;
            return;
        }
        addNumOperators(curChar_);
    }

    private boolean en(int _i) {
        return parsBrackets.size() == _i && prio == NatElResolver.FCT_OPER_PRIO && enPars;
    }

    private void addNumOperators(char _curChar) {
        boolean foundOperator_ = false;
        StringBuilder builtOperator_ = new StringBuilder();
        if (_curChar == DOT_VAR) {
            builtOperator_.append(DOT_VAR);
            if (prio == NatElResolver.FCT_OPER_PRIO) {
                foundOperator_ = true;
            }
            addPossibleNumOp(builtOperator_.toString(), foundOperator_);
            return;
        }
        builtOperator_.append(_curChar);
        prio = NatElResolver.AFF_PRIO;
        addPossibleNumOp(builtOperator_.toString(), true);
    }

    private void addPossibleNumOp(String _op, boolean _foundOper) {
        if (_foundOper) {
            operators.clear();
            fctName = EMPTY_STRING;
            enPars = false;
            operators.addEntry(index,_op);
        }
        index += _op.length();
    }

    private static void addCommaOperIfNotEmpty(StrTypes _operators, int _i) {
        _operators.addEntry(_i, Character.toString(SEP_ARG));
    }


    private static void addOperIfNotEmpty(StrTypes _operators, int _i, char _op) {
        _operators.addEntry(_i, Character.toString(_op));
    }

    int getIndex() {
        return index;
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

}
