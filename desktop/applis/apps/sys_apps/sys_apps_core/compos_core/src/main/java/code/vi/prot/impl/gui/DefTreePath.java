package code.vi.prot.impl.gui;

import code.gui.AbsTreePath;
import code.gui.AbstractMutableTreeNode;

import javax.swing.tree.TreePath;

public final class DefTreePath implements AbsTreePath {
    private final AbstractMutableTreeNode path;
    private final TreePath real;

    public DefTreePath(AbstractMutableTreeNode _p, TreePath _r) {
        this.path = _p;
        this.real = _r;
    }

    @Override
    public AbstractMutableTreeNode data() {
        return path;
    }

    public TreePath getReal() {
        return real;
    }
}
