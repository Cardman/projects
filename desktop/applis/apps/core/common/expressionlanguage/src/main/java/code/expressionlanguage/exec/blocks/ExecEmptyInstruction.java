package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class ExecEmptyInstruction extends ExecLeaf implements WithEl {
    public ExecEmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        processBlock(_cont);
    }
}
