package code.mock;

import code.gui.AbsTreePath;
import code.gui.AbstractMutableTreeNode;

public final class MockTreePath implements AbsTreePath {
    private final AbstractMutableTreeNode elt;

    public MockTreePath(AbstractMutableTreeNode _e) {
        this.elt = _e;
    }

    @Override
    public AbstractMutableTreeNode data() {
        return elt;
    }
}
