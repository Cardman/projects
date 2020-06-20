package code.formathtml.util;

import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.types.AbstractAnnotationAnalysis;

public final class AdvancedAnnotationAnalysis implements AbstractAnnotationAnalysis {

    @Override
    public boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq) {
        return false;
    }
}
