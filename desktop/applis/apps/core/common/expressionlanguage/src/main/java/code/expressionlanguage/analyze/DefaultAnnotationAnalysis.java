package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.opers.AnnotationInstanceOperation;
import code.expressionlanguage.analyze.opers.AssocationOperation;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.core.StringUtil;

public final class DefaultAnnotationAnalysis {

    private DefaultAnnotationAnalysis() {
    }

    public static boolean isAnnotAnalysis(AnalyzedPageEl _cont, OperationNode _op, OperationsSequence _seq) {
        boolean ok_ = false;
        if ((AbsBk.isAnnotBlock(_cont.getCurrentBlock()) && _op == null)
                || _op instanceof AssocationOperation
                || _op instanceof AnnotationInstanceOperation) {
            ok_ = true;
        }
        if (!ok_) {
            return false;
        }
        String op_ = _seq.getOperators().firstValue();
        return StringUtil.quickEq(op_, Character.toString('{'));
    }
}
