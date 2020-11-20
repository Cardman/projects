package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface ExecAnnotableBlock {
    CustList<CustList<ExecOperationNode>> getAnnotationsOps();
}
