package code.formathtml.util;

import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.types.AbstractAnnotationAnalysis;
import code.expressionlanguage.types.AbstractCurrentConstraints;
import code.util.StringList;
import code.util.StringMap;

public final class AdvancedAnnotationAnalysis implements AbstractAnnotationAnalysis {

    @Override
    public boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq) {
        return false;
    }
}
