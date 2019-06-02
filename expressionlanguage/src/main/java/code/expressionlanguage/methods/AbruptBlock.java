package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class AbruptBlock extends Leaf implements BuildableElMethod {

    AbruptBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        _anEl.completeAbruptGroup(this);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        buildEmptyEl(_an);
    }
}
