package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecAnonymousTypeBlock extends ExecRootBlock{
    public ExecAnonymousTypeBlock(ExecRootBlockContent _rootBlockContent, AccessEnum _access) {
        super(_rootBlockContent, _access);
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }
}
