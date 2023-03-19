package code.vi.prot.impl.gui;

import code.gui.AbsTreePath;
import code.gui.AbsTreePaths;
import code.gui.AbstractMutableTreeNode;
import code.util.CustList;

import javax.swing.tree.TreePath;

public final class DefTreePaths implements AbsTreePaths {
    private final CustList<TreePath> nodes;
    private final AbstractMutableTreeNode root;

    public DefTreePaths(TreePath[] _n, AbstractMutableTreeNode _r) {
        this.nodes = new CustList<TreePath>(_n);
        this.root = _r;
    }
    @Override
    public int getLength() {
        return nodes.size();
    }

    @Override
    public void add(AbsTreePath _path) {
        nodes.add(((DefTreePath)_path).getReal());
    }

    @Override
    public void remove(int _path) {
        nodes.remove(_path);
    }

    @Override
    public void set(int _index, AbsTreePath _path) {
        nodes.set(_index,((DefTreePath)_path).getReal());
    }

    public TreePath[] getNodes() {
        return nodes.list().toArray(new TreePath[0]);
    }

    @Override
    public AbsTreePath elt(int _index) {
        TreePath tr_ = nodes.get(_index);
        return new DefTreePath(TreeGui.selected(root,tr_),tr_);
    }
}
