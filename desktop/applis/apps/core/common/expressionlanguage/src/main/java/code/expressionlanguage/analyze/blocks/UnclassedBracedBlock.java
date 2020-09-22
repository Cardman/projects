package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class UnclassedBracedBlock extends BracedBlock {
    public UnclassedBracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.canCompleteNormally(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

}
