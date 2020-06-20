package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;

public interface ExecAccessingImportingBlock extends AccessedBlock {
    boolean isTypeHidden(ExecRootBlock _class, ContextEl _analyzable);

}
