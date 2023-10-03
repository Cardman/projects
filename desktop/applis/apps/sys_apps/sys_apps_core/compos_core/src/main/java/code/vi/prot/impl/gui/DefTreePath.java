package code.vi.prot.impl.gui;

import code.gui.AbsTreeGui;
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
    public int getLength() {
        return real.getPath().length;
    }

    @Override
    public AbsTreePath parent(AbsTreeGui _tree) {
        try {
            TreePath path_ = real.getParentPath();
            DefTreePath tp_ = new DefTreePath(path.getParent(), path_);
            tp_.getLength();
            return new DefTreePath(_tree.translate(tp_), path_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNodeCore<String> data() {
        return path;
    }

    public TreePath getReal() {
        return real;
    }
}
