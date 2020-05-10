package code.expressionlanguage.types;

import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.OperationNode;
import code.util.StringList;
import code.util.StringMap;

public interface AbstractAnnotationAnalysis {
    boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq);
}
