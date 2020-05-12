package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.StringList;

public final class InternVariableOperation extends LeafOperation {

    private String variableName = EMPTY_STRING;

    public InternVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        variableName = str_;
        LocalVariable locVar_ = _conf.getAnalyzing().getInternVars().getVal(str_);
        String c_ = locVar_.getClassName();
        setResultClass(new ClassArgumentMatching(c_));
    }


    public String getVariableName() {
        return variableName;
    }
}
