package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaArrayInstancingContent;

public abstract class AbstractArrayInstancingOperation extends InvokingOperation {
    private final AnaArrayInstancingContent arrayInstancingContent;

    protected AbstractArrayInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrayInstancingContent = new AnaArrayInstancingContent(_op.getFctName());
    }

    public final String getMethodName() {
        return arrayInstancingContent.getMethodName();
    }

    public final void setClassName(String _className) {
        arrayInstancingContent.setClassName(_className);
    }

    public AnaArrayInstancingContent getArrayInstancingContent() {
        return arrayInstancingContent;
    }
}
