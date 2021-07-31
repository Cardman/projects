package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class StandardFieldOperation implements AnaSettableAbstractFieldOperation {
    private OperationsSequence operations;
    public StandardFieldOperation(OperationsSequence _op) {
        operations = _op;
    }

    private OperationsSequence getOperations() {
        return operations;
    }

    @Override
    public AnaClassArgumentMatching getFrom(AnalyzedPageEl _page,SettableAbstractFieldOperation _settable) {
        AnaClassArgumentMatching cl_;
        if (_settable.isIntermediateDottedOperation()) {
            cl_ = _settable.getPreviousResultClass();
        } else {
            cl_ = new AnaClassArgumentMatching(_page.getGlobalClass());
        }
        return cl_;
    }

    @Override
    public String getFieldName() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        return originalStr_.trim();
    }

    @Override
    public int getDelta() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        return StringUtil.getFirstPrintableCharIndex(originalStr_);
    }
    @Override
    public boolean isBaseAccess() {
        return true;
    }

    @Override
    public boolean isSuperAccess() {
        return true;
    }

    @Override
    public AnaResultPartType getPartOffsets() {
        return new AnaResultPartType();
    }

}
