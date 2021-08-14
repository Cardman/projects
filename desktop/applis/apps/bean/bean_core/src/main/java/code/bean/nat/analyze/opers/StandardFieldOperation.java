package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.util.core.IndexConstants;

public final class StandardFieldOperation {
    private final NatOperationsSequence operations;
    public StandardFieldOperation(NatOperationsSequence _op) {
        operations = _op;
    }

    private NatOperationsSequence getOperations() {
        return operations;
    }

    public static String getFrom(AnalyzedPageEl _page, SettableAbstractFieldNatOperation _settable) {
        String cl_;
        if (_settable.isIntermediateDottedOperation()) {
            cl_ = _settable.getPreviousResultClass();
        } else {
            cl_ = _page.getGlobalClass();
        }
        return cl_;
    }

    public String getFieldName() {
        NatOperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        return originalStr_.trim();
    }

}
