package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class ExecInstanceBlock extends ExecInitBlock {
    public ExecInstanceBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    protected MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
}
