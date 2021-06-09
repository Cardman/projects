package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.util.CustList;

public interface ExecAnnotableParamBlock {
    CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams();
    CustList<ExecAnnotContent> getAnnotationsOps();
}
