package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.core.StringUtil;

public final class StandardFieldOperation extends AbsStandardFieldOperation {
    public StandardFieldOperation(OperationsSequence _op) {
        super(_op);
    }

    @Override
    public int getDelta() {
        return StringUtil.getFirstPrintableCharIndex(getValue());
    }
    @Override
    public boolean isBaseAccess() {
        return true;
    }

}
