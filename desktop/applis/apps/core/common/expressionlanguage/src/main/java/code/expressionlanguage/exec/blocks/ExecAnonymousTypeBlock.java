package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.analyze.blocks.RootBlock;

public class ExecAnonymousTypeBlock extends ExecRootBlock implements ExecUniqueRootedBlock {
    public ExecAnonymousTypeBlock(RootBlock _offset) {
        super(_offset);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }
}
