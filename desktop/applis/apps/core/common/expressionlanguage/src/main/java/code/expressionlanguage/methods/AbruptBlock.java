package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class AbruptBlock extends Leaf implements BuildableElMethod {

    AbruptBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void abrupt(ContextEl _an, AnalyzingEl _anEl) {
        _anEl.completeAbruptGroup(this);
    }

}
