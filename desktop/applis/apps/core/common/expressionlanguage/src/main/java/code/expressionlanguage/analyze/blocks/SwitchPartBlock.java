package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.files.OffsetsBlock;

public abstract class SwitchPartBlock extends BracedBlock implements
        BuildableElMethod {

    protected SwitchPartBlock(OffsetsBlock _offset) {
        super(_offset);
    }

}
