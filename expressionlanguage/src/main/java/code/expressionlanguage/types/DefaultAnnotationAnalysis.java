package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.OperationNode;

public final class DefaultAnnotationAnalysis implements AbstractAnnotationAnalysis {
    private final ContextEl context;

    public DefaultAnnotationAnalysis(ContextEl context) {
        this.context = context;
    }

    @Override
    public boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq) {
        return context.isAnnotAnalysis(_op,_seq);
    }
}
