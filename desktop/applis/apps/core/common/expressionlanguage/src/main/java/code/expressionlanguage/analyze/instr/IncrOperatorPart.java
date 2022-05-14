package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.common.StringExpUtil;

public final class IncrOperatorPart {
    private static final int AND_OR = 0;
    private static final int NULL_SAFE = 1;
    private static final int LT_GT = 2;
    private int statusOp = -1;
    private int indexOp = -1;
    private int index;
    private int firstIndex;
    private int secIndex;
    private boolean enabledOp;
    private boolean unary;
    private boolean addOp = true;

    public IncrOperatorPart() {
        this(false);
    }

    public IncrOperatorPart(boolean _en) {
        this.enabledOp = _en;
    }

    public int tryAddOp(int _beginIndex, String _string, int _i){
        index = _i;
        firstIndex = _i;
        char curChar_ = _string.charAt(index);
        boolean escapeOpers_ = escOps(_beginIndex, _string);
        if (escapeOpers_) {
            return escapedOp(_string);
        }
        if (curChar_ == ElResolver.PLUS_CHAR || curChar_ == ElResolver.MINUS_CHAR) {
            return incr();
        }
        if (curChar_ == ElResolver.DOT_VAR) {
            indexOp = index;
            enabledOp = true;
            index++;
            return index;
        }
        return rightOp(curChar_);
    }

    private boolean escOps(int _beginIndex, String _string) {
        char curChar_ = _string.charAt(index);
        int len_ = _string.length();
        if (curChar_ == ElResolver.MULT_CHAR) {
            unary = true;
            return true;
        }
        if (curChar_ == ElResolver.PLUS_CHAR || curChar_ == ElResolver.MINUS_CHAR){
            unary = true;
            cancelAdd(_beginIndex);
            return index + 1 >= len_ || _string.charAt(index + 1) != curChar_;
        }
        if (curChar_ == ElResolver.AND_CHAR || curChar_ == ElResolver.OR_CHAR) {
            statusOp = AND_OR;
            return true;
        }
        if (curChar_ == ElResolver.LOWER_CHAR || curChar_ == ElResolver.GREATER_CHAR) {
            statusOp = LT_GT;
            return true;
        }
        if (curChar_ == ElResolver.BEGIN_TERNARY) {
            statusOp = NULL_SAFE;
            return true;
        }
        if (curChar_ == ElResolver.NEG_BOOL_CHAR) {
            unary = true;
            cancelAdd(_beginIndex);
            return true;
        }
        return curChar_ == ElResolver.MOD_CHAR || curChar_ == ElResolver.DIV_CHAR || curChar_ == ElResolver.XOR_CHAR
                || curChar_ == ElResolver.END_TERNARY || curChar_ == ElResolver.EQ_CHAR || curChar_ == ElResolver.ANN_ARR_LEFT
                || curChar_ == ElResolver.ARR_LEFT || curChar_ == ElResolver.PAR_LEFT || curChar_ == ElResolver.SEP_ARG;
    }

    private void cancelAdd(int _beginIndex) {
        if (_beginIndex == index) {
            addOp = false;
        }
    }

    private int escapedOp(String _string) {
        secIndex = index + 1;
        if (statusOp == LT_GT) {
            ltIncr(_string);
        } else if (statusOp == AND_OR) {
            andOrIncr(_string);
        } else if (statusOp == NULL_SAFE) {
            nullSafeIncr(_string);
        } else {
            eqIncr(_string);
        }

        tryAddOp();
        enabledOp = false;
        return secIndex;
    }

    private void eqIncr(String _string) {
        if (incOpEq(_string)) {
            unary = false;
        }
    }

    private void nullSafeIncr(String _string) {
        int len_ = _string.length();
        if (StringExpUtil.nextCharIs(_string, secIndex, len_, ElResolver.DOT_VAR)) {
            int n_ = StringExpUtil.nextPrintChar(secIndex + 1, len_, _string);
            if (!ElResolverCommon.isDigitOrDot(_string,n_)) {
                secIndex++;
            }
        }
        incOp(_string);
        incOp(_string);
        eqIncr(_string);
    }

    private void andOrIncr(String _string) {
        char curChar_ = _string.charAt(index);
        incOp(_string);
        if (incOp(_string,curChar_)) {
            if (!incOpEq(_string)) {
                secIndex--;
                addOp = false;
            }
        } else {
            eqIncr(_string);
        }

    }

    private void ltIncr(String _string) {
        incOp(_string);
        incOp(_string);
        incOp(_string);
        eqIncr(_string);
    }

    private void incOp(String _string) {
        char curChar_ = _string.charAt(index);
        incOp(_string, curChar_);
    }

    private boolean incOpEq(String _string) {
        return StringExpUtil.isBinEq(_string.substring(firstIndex,secIndex)) && incOp(_string, ElResolver.EQ_CHAR);
    }
    private boolean incOp(String _string, char _curChar) {
        int len_ = _string.length();
        if (secIndex < len_ && _string.charAt(secIndex) == _curChar) {
            secIndex++;
            return true;
        }
        return false;
    }

    private void tryAddOp() {
        if (addOp && (enabledOp||!unary)) {
            indexOp = index;
        }
    }

    private int rightOp(char _curChar) {
        if (_curChar == ElResolver.ANN_ARR_RIGHT||_curChar == ElResolver.ARR_RIGHT||_curChar == ElResolver.PAR_RIGHT) {
            indexOp = index;
            enabledOp = true;
        }
        index++;
        return index;
    }

    private int incr() {
        if (addOp) {
            indexOp = index;
        }
        index++;
        index++;
        enabledOp = true;
        return index;
    }

    public int getIndexOp() {
        return indexOp;
    }

    public boolean isEnabledOp() {
        return enabledOp;
    }
}
