package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.sml.Element;

public abstract class AbruptBlock extends Leaf {

    public AbruptBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    AbruptBlock(ContextEl _importingPage, int _indexChild, BracedBlock _m,
            OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        _anEl.completeAbrupt(this);
        _anEl.completeAbruptGroup(this);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        buildEmptyEl(_an);
    }
}
