package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.util.core.IndexConstants;

public abstract class AbsStandardFieldOperation implements AnaSettableAbstractFieldOperation {
    private final int relativeOff;
    private final String value;
    protected AbsStandardFieldOperation(OperationsSequence _op) {
        relativeOff = _op.getDelta();
        value = _op.getValues().getValue(IndexConstants.FIRST_INDEX);
    }

    protected String getValue() {
        return value;
    }

    protected int getRelativeOff() {
        return relativeOff;
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
        return value.trim();
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
