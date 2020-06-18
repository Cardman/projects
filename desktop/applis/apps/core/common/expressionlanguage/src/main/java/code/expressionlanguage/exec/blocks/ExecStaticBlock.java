package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.MethodAccessKind;

public final class ExecStaticBlock extends ExecInitBlock {
    public ExecStaticBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.STATIC;
    }
}
