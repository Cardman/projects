package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AloneBlock;
import code.util.CustList;

public abstract class ExecInitBlock extends ExecMemberCallingsBlock implements AloneBlock {
    ExecInitBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        //
    }
}
