package code.formathtml.analyze;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.AbstractAnnotationAnalysis;

public final class AdvancedAnnotationAnalysis implements AbstractAnnotationAnalysis {

    @Override
    public boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq) {
        return false;
    }
}
