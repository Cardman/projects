package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.sml.Element;
import code.util.IdMap;

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
    public final void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        _anEl.completeAbrupt(this);
        _anEl.completeAbruptGroup(this);
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        IdMap<ContinueBlock, Loop> continuables_ = _anEl.getContinuables();
        if (this instanceof BreakBlock) {
            breakables_.put((BreakBlock)this, _anEl.getParentsBreakables().last());
        }
        if (this instanceof ContinueBlock) {
            continuables_.put((ContinueBlock)this, _anEl.getParentsContinuables().last());
        }
    }
}
