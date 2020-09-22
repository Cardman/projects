package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class Leaf extends Block {

    Leaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public final Block getFirstChild() {
        return null;
    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
        if (prev_ == null) {
            if (_anEl.isReachable(br_) && br_.accessibleCondition()) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_anEl, _page);
        }
    }
    @Override
    public void abrupt(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
    }
}
