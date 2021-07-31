package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ChoiceFieldOperation implements AnaSettableAbstractFieldOperation {

    private AnaResultPartType partOffsets = new AnaResultPartType();
    private OperationsSequence operations;

    public ChoiceFieldOperation(OperationsSequence _op) {
        operations = _op;
    }

    private OperationsSequence getOperations() {
        return operations;
    }

    @Override
    public AnaClassArgumentMatching getFrom(AnalyzedPageEl _page,SettableAbstractFieldOperation _settable) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String className_ = originalStr_.substring(0,originalStr_.lastIndexOf(OperationNode.PAR_RIGHT));
        int lenPref_ = className_.indexOf(OperationNode.PAR_LEFT)+1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
        partOffsets = ResolvingTypes.resolveCorrectType(lenPref_ + loc_, className_, _page);
        className_ = partOffsets.getResult(_page);
        return new AnaClassArgumentMatching(className_);
    }

    @Override
    public String getFieldName() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        StringList classMethod_ = StringUtil.splitChars(str_, OperationNode.PAR_RIGHT);
        return classMethod_.last().trim();
    }

    @Override
    public int getDelta() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        int ind_ = originalStr_.lastIndexOf(OperationNode.PAR_RIGHT);
        return ind_ +1+ StringUtil.getFirstPrintableCharIndex(originalStr_.substring(ind_+1));
    }

    @Override
    public boolean isBaseAccess() {
        return true;
    }

    @Override
    public boolean isSuperAccess() {
        return false;
    }

    @Override
    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }
}
