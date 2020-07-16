package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;

public abstract class ExecSwitchPartBlock extends ExecBracedBlock implements
        StackableBlock {
    ExecSwitchPartBlock(OffsetsBlock _offset) {
        super(_offset);
    }

}
