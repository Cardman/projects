package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.WithEl;

public abstract class ExecLeaf extends ExecBlock implements WithEl {
    ExecLeaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public ExecBlock getFirstChild() {
        return null;
    }
}
