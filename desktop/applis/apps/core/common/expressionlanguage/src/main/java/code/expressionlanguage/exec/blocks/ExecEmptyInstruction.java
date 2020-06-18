package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.WithEl;
import code.util.CustList;

public final class ExecEmptyInstruction extends ExecLeaf implements WithEl {
    public ExecEmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {

    }

    @Override
    public void processEl(ContextEl _cont) {
        processBlock(_cont);
    }
}
