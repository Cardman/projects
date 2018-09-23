package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.sml.Element;

public abstract class Leaf extends Block implements WithEl {

    Leaf(Element _el, ContextEl _importingPage, int _indexChild, BracedBlock _m) {
        super(_el, _indexChild, _m);
    }

    Leaf(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_indexChild, _m, _offset);
    }

    @Override
    public final Block getFirstChild() {
        return null;
    }

    @Override
    final boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    final boolean canBeIncrementedCurGroup() {
        return false;
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
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        // TODO Auto-generated method stub
    }
}
