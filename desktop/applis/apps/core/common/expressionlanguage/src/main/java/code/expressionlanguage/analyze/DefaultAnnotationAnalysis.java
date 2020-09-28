package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AnnotationMethodBlock;
import code.expressionlanguage.analyze.opers.AnnotationInstanceOperation;
import code.expressionlanguage.analyze.opers.AssocationOperation;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.StringList;

public final class DefaultAnnotationAnalysis implements AbstractAnnotationAnalysis {
    private final AnalyzedPageEl context;

    public DefaultAnnotationAnalysis(AnalyzedPageEl context) {
        this.context = context;
    }

    @Override
    public boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq) {
        return isAnnotAnalysis(context,_op,_seq);
    }

    private static boolean isAnnotAnalysis(AnalyzedPageEl _cont, OperationNode _op, OperationsSequence _seq) {
        boolean ok_ = false;
        if ((_cont.getCurrentBlock() instanceof AnnotationMethodBlock && _op == null)
                || _op instanceof AssocationOperation
                || _op instanceof AnnotationInstanceOperation) {
            ok_ = true;
        }
        if (!ok_) {
            return false;
        }
        String op_ = _seq.getOperators().firstValue();
        return StringList.quickEq(op_, String.valueOf('{'));
    }
}
