package code.expressionlanguage.types;

import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;

public interface AbstractAnnotationAnalysis {
    boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq);
}
