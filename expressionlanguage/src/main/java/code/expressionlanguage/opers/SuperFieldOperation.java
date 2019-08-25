package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class SuperFieldOperation extends
        SettableAbstractFieldOperation {

    public SuperFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        ClassArgumentMatching cl_;
        if (isIntermediateDottedOperation()) {
            cl_ = getPreviousResultClass();
        } else {
            cl_ = new ClassArgumentMatching(_conf.getGlobalClass());
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

}
