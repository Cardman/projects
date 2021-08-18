package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.util.core.IndexConstants;

public final class NatStandardFieldOperation {
    private final NatOperationsSequence operations;
    public NatStandardFieldOperation(NatOperationsSequence _op) {
        operations = _op;
    }

    private NatOperationsSequence getOperations() {
        return operations;
    }

    public static String getFrom(NatAnalyzedCode _page, SettableAbstractFieldNatOperation _settable) {
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
