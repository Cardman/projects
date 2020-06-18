package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class Leaf extends Block {

    Leaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public final Block getFirstChild() {
        return null;
    }

    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
        if (prev_ == null) {
            if (_anEl.isReachable(br_) && br_.accessibleCondition()) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_an, _anEl);
        }
    }
    @Override
    public void abrupt(ContextEl _an, AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
    }
}
