package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;

public interface AbstractAnnotationAnalysis {
    boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq);
}
