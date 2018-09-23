package code.expressionlanguage.methods;

import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;

public final class NullCatchEval extends AbstractCatchEval {

    public NullCatchEval(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        boolean reachCatch_ = true;
        while (!(p_ instanceof TryEval)) {
            if (p_ instanceof NullCatchEval) {
                reachCatch_ = false;
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        if (getFirstChild() == null) {
            buildEmptyEl(_cont);
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(this);
    }

}
