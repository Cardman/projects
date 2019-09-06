package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class Leaf extends Block implements WithEl {

    Leaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public final Block getFirstChild() {
        return null;
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
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
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void checkTree(Analyzable _an, AnalyzingEl _anEl) {
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
    }
}
