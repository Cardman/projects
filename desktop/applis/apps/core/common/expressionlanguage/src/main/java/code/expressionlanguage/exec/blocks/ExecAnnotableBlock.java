package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.methods.ReducableOperations;
import code.util.CustList;

public interface ExecAnnotableBlock extends ReducableOperations {
    CustList<CustList<ExecOperationNode>> getAnnotationsOps();
}
