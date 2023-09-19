package code.vi.prot.impl.gui;

import code.gui.AbsTreePath;
import code.gui.AbsTreePaths;
import code.gui.AbstractMutableTreeNodeCore;
import code.util.CustList;

import javax.swing.tree.TreePath;

public final class DefTreePaths implements AbsTreePaths {
    private final CustList<TreePath> nodes;
    private final CustList<AbstractMutableTreeNodeCore<String>> nodesTree;

    public DefTreePaths(TreePath[] _n, CustList<AbstractMutableTreeNodeCore<String>> _t) {
        this.nodes = new CustList<TreePath>(_n);
        nodesTree = _t;
    }
    @Override
    public int getLength() {
        return nodes.size();
    }

    @Override
    public void add(AbsTreePath _path) {
        nodes.add(((DefTreePath)_path).getReal());
        nodesTree.add(_path.data());
    }

    @Override
    public void remove(int _path) {
        nodes.remove(_path);
        nodesTree.remove(_path);
    }

    @Override
    public void set(int _index, AbsTreePath _path) {
        nodes.set(_index,((DefTreePath)_path).getReal());
        nodesTree.set(_index,_path.data());
    }

    public TreePath[] getNodes() {
        return nodes.list().toArray(new TreePath[0]);
    }

    @Override
    public AbsTreePath elt(int _index) {
        TreePath tr_ = nodes.get(_index);
        return new DefTreePath(nodesTree.get(_index),tr_);
    }

    @Override
    public AbsTreePath elt(AbstractMutableTreeNodeCore<String> _root, int _index) {
        TreePath tr_ = nodes.get(_index);
        return new DefTreePath(TreeGui.selected(_root,tr_),tr_);
    }
}
