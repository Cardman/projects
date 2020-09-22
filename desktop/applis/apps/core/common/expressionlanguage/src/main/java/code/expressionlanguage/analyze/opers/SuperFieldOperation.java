package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;
import code.util.StringList;

public final class SuperFieldOperation extends
        SettableAbstractFieldOperation {

    public SuperFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page) {
        AnaClassArgumentMatching cl_;
        if (isIntermediateDottedOperation()) {
            cl_ = getPreviousResultClass();
        } else {
            cl_ = new AnaClassArgumentMatching(_page.getGlobalClass());
        }
        return cl_;
    }

    @Override
    String getFieldName() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        return originalStr_.trim();
    }

    @Override
    public int getDelta() {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getDelta();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        return relativeOff_+StringList.getFirstPrintableCharIndex(originalStr_);
    }

    @Override
    boolean isBaseAccess() {
        return false;
    }

    @Override
    boolean isSuperAccess() {
        return true;
    }

    @Override
    public CustList<PartOffset> getPartOffsets() {
        return new CustList<PartOffset>();
    }
}
