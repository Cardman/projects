package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.MethodAccessKind;

public final class ExecInstanceBlock extends ExecInitBlock {
    public ExecInstanceBlock(OffsetsBlock _offset) {
        super(_offset);
    }
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
}
