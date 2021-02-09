package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecAnonymousTypeBlock extends ExecRootBlock implements ExecUniqueRootedBlock {
    public ExecAnonymousTypeBlock(int _offsetTrim, ExecRootBlockContent _rootBlockContent, AccessEnum _access) {
        super(_offsetTrim, _rootBlockContent, _access);
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }
}
