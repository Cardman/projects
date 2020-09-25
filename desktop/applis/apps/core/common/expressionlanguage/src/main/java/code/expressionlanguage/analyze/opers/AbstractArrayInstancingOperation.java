package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaArrayInstancingContent;

public abstract class AbstractArrayInstancingOperation extends InvokingOperation {
    private AnaArrayInstancingContent arrayInstancingContent;

    public AbstractArrayInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrayInstancingContent = new AnaArrayInstancingContent(getOperations().getFctName());
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
