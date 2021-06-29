package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.util.CustList;

public interface ExecAnnotableBlock {
    CustList<ExecAnnotContent> getAnnotationsOps();
}
