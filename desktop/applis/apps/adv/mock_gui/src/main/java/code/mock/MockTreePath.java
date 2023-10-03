package code.mock;

import code.gui.AbsTreeGui;
import code.gui.AbsTreePath;
import code.gui.AbstractMutableTreeNodeCore;
import code.util.CustList;

public final class MockTreePath implements AbsTreePath {
    private final AbstractMutableTreeNodeCore<String> elt;

    public MockTreePath(AbstractMutableTreeNodeCore<String> _e) {
        this.elt = _e;
    }

    @Override
    public int getLength() {
        return elts().size();
    }

    @Override
    public AbsTreePath parent(AbsTreeGui _tree) {
        AbstractMutableTreeNodeCore<String> par_ = elt.getParent();
        if (par_ == null) {
            return null;
        }
        return new MockTreePath(par_);
    }

    private CustList<AbsTreePath> elts() {
        CustList<AbsTreePath> s_ = new CustList<AbsTreePath>();
        AbstractMutableTreeNodeCore<String> c_ = elt;
        while (c_ != null) {
            s_.add(0,new MockTreePath(c_));
            c_ = c_.getParentReal();
        }
        return s_;
    }
    @Override
    public AbstractMutableTreeNodeCore<String> data() {
        return elt;
    }
}
