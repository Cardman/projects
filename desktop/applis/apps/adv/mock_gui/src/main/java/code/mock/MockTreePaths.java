package code.mock;

import code.gui.AbsTreePath;
import code.gui.AbsTreePaths;
import code.gui.AbstractMutableTreeNode;
import code.util.CustList;

public final class MockTreePaths implements AbsTreePaths {
    private final CustList<AbsTreePath> nodes;

    public MockTreePaths(CustList<AbsTreePath> _n) {
        this.nodes = _n;
    }

    @Override
    public int getLength() {
        return nodes.size();
    }

    @Override
    public AbsTreePath elt(int _index) {
        return elt(null,_index);
    }

    @Override
    public AbsTreePath elt(AbstractMutableTreeNode _root, int _index) {
        return nodes.get(_index);
    }

    @Override
    public void set(int _index, AbsTreePath _path) {
        nodes.set(_index, _path);
    }

    @Override
    public void add(AbsTreePath _path) {
        nodes.add(_path);
    }

    @Override
    public void remove(int _path) {
        nodes.remove(_path);
    }
}
