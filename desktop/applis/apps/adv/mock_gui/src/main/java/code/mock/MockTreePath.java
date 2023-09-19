package code.mock;

import code.gui.AbsTreePath;
import code.gui.AbstractMutableTreeNodeCore;

public final class MockTreePath implements AbsTreePath {
    private final AbstractMutableTreeNodeCore<String> elt;

    public MockTreePath(AbstractMutableTreeNodeCore<String> _e) {
        this.elt = _e;
    }

    @Override
    public AbstractMutableTreeNodeCore<String> data() {
        return elt;
    }
}
