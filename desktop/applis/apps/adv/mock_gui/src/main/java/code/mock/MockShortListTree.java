package code.mock;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;

public final class MockShortListTree implements AbsShortListTree {
    private final int nb;
    private final MockListTreeWindow mockListTreeWindow;

    public MockShortListTree(int _nb, MockListTreeWindow _m) {
        this.nb = _nb;
        this.mockListTreeWindow = _m;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _node) {
        mockListTreeWindow.action(nb,_node);
    }
}
