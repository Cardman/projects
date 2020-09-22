package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class ExecLeaf extends ExecBlock implements WithEl {
    ExecLeaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public ExecBlock getFirstChild() {
        return null;
    }
}
