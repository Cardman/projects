package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.methods.ReducableOperations;
import code.util.CustList;

public interface ExecAnnotableParametersBlock extends ExecAnnotableBlock {
    CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams();
}
