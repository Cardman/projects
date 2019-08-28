package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class ChoiceFieldOperation extends
        SettableAbstractFieldOperation {

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ChoiceFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String className_ = originalStr_.substring(0,originalStr_.lastIndexOf(PAR_RIGHT));
        int lenPref_ = className_.indexOf(PAR_LEFT)+1;
        className_ = className_.substring(lenPref_);
        className_ = _conf.resolveCorrectType(lenPref_,className_);
        partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
        return new ClassArgumentMatching(className_);
    }

    @Override
    String getFieldName() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        StringList classMethod_ = StringList.splitChars(str_, PAR_RIGHT);
        return classMethod_.last().trim();
    }

    @Override
    public int getDelta() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        int ind_ = originalStr_.lastIndexOf(PAR_RIGHT);
        return ind_ + StringList.getFirstPrintableCharIndex(originalStr_.substring(ind_+1));
    }

    @Override
    boolean isBaseAccess() {
        return true;
    }

    @Override
    boolean isSuperAccess() {
        return false;
    }

    @Override
    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
