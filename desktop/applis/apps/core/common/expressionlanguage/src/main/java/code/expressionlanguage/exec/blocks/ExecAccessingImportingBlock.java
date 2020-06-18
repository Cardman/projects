package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.AccessedBlock;

public interface ExecAccessingImportingBlock extends AccessedBlock {
    boolean isTypeHidden(ExecRootBlock _class, ContextEl _analyzable);

}
