package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.sml.Element;
import code.util.CustList;
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
        CustList<BracedBlock> parents_ = _anEl.getParents();
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        IdMap<ContinueBlock, Loop> continuables_ = _anEl.getContinuables();
        int len_ = parents_.size() - 1;
        if (this instanceof BreakBlock) {
            for (int i = len_; i >= 0; i--) {
                BracedBlock b_ = parents_.get(i);
                if (b_ instanceof BreakableBlock) {
                    breakables_.put((BreakBlock)this, (BreakableBlock) b_);
                    break;
                }
            }
        }
        if (this instanceof ContinueBlock) {
            for (int i = len_; i >= 0; i--) {
                BracedBlock b_ = parents_.get(i);
                if (b_ instanceof Loop) {
                    continuables_.put((ContinueBlock)this, (Loop) b_);
                    break;
                }
            }
        }
    }
}
