package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AbsChoiceFieldOperation implements AnaSettableAbstractFieldOperation {

    private AnaResultPartType partOffsets = new AnaResultPartType();
    private final String value;

    protected AbsChoiceFieldOperation(OperationsSequence _op) {
        value = _op.getValues().getValue(IndexConstants.FIRST_INDEX);
    }

    protected String className(AnalyzedPageEl _page) {
        String className_ = value.substring(0,value.lastIndexOf(OperationNode.PAR_RIGHT));
        int lenPref_ = className_.indexOf(OperationNode.PAR_LEFT)+1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
        partOffsets = ResolvingTypes.resolveCorrectType(lenPref_ + loc_, className_, _page);
        className_ = partOffsets.getResult(_page);
        return className_;
    }
    @Override
    public String getFieldName() {
        String str_ = value.trim();
        StringList classMethod_ = StringUtil.splitChars(str_, OperationNode.PAR_RIGHT);
        return classMethod_.last().trim();
    }

    @Override
    public int getDelta() {
        int ind_ = value.lastIndexOf(OperationNode.PAR_RIGHT);
        return ind_ +1+ StringUtil.getFirstPrintableCharIndex(value.substring(ind_+1));
    }

    @Override
    public boolean isBaseAccess() {
        return true;
    }

    @Override
    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

}
