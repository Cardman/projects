package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface ExecAnnotableParamBlock {
    CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams();
    CustList<CustList<ExecOperationNode>> getAnnotationsOps();
}
