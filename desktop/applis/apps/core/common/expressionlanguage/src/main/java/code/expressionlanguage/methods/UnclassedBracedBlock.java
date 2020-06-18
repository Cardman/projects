package code.expressionlanguage.methods;

import code.expressionlanguage.files.OffsetsBlock;

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
