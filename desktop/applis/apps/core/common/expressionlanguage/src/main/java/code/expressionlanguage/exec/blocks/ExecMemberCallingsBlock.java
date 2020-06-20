package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.blocks.FunctionBlock;

public abstract class ExecMemberCallingsBlock extends ExecBracedBlock implements FunctionBlock {
    ExecMemberCallingsBlock(OffsetsBlock _offset) {
        super(_offset);
    }
}
