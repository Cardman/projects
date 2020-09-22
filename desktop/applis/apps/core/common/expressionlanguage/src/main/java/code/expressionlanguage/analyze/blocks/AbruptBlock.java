package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class AbruptBlock extends Leaf implements BuildableElMethod {

    AbruptBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        _anEl.completeAbruptGroup(this);
    }

}
