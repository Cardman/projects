package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.core.StringUtil;

public final class SuperFieldOperation extends AbsStandardFieldOperation{

    public SuperFieldOperation(OperationsSequence _op) {
        super(_op);
    }

    @Override
    public int getDelta() {
        int relativeOff_ = getRelativeOff();
        return relativeOff_+StringUtil.getFirstPrintableCharIndex(getValue());
    }

    @Override
    public boolean isBaseAccess() {
        return false;
    }
}
