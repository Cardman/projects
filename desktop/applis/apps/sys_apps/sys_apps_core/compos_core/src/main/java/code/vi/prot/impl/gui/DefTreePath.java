package code.vi.prot.impl.gui;

import code.gui.AbsTreePath;
import code.gui.AbstractMutableTreeNodeCore;

import javax.swing.tree.TreePath;

public final class DefTreePath implements AbsTreePath {
    private final AbstractMutableTreeNodeCore<String> path;
    private final TreePath real;

    public DefTreePath(AbstractMutableTreeNodeCore<String> _p, TreePath _r) {
        this.path = _p;
        this.real = _r;
    }

    @Override
    public AbstractMutableTreeNodeCore<String> data() {
        return path;
    }

    public TreePath getReal() {
        return real;
    }
}
