package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface ExecAnnotableParamBlock extends ExecAnnotableBlock {
    CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams();
}
