package code.expressionlanguage.fwd;

import code.expressionlanguage.exec.blocks.ExecAbstractFileBlock;
import code.util.CustList;

public interface AbstractExecFileListBuilder {
    CustList<ExecAbstractFileBlock> build();
}
