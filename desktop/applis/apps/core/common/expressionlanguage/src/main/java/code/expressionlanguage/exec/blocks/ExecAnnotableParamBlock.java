package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.util.CustList;

public interface ExecAnnotableParamBlock extends ExecAnnotableBlock {
    CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams();
}
