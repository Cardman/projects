package code.mock;

import code.gui.*;
import code.util.CustList;
import code.util.IdList;

public final class MockTreeGui extends MockCustComponent implements AbsTreeGui {
    private boolean rootVisible;
    private AbsTreePaths selectionPaths = new MockTreePaths(new CustList<AbsTreePath>());
    private final AbstractMutableTreeNodeCore<String> root;
    private final IdList<AbsShortListTree> list = new IdList<AbsShortListTree>();

    public MockTreeGui(AbstractMutableTreeNodeCore<String> _t) {
        root = _t;
    }

    @Override
    public AbstractMutableTreeNodeCore<String> getRoot() {
        return root;
    }

    @Override
    public void select(AbstractMutableTreeNodeCore<String> _m) {
        selectedPaths(new MockTreePaths(new CustList<AbsTreePath>(getTreePath((MockMutableTreeNode) _m))));
        for (AbsShortListTree l: getTreeSelectionListeners()) {
            l.valueChanged(_m);
        }
    }

    @Override
    public void reload(AbstractMutableTreeNodeCore<String> _m) {
        loop(_m);
    }

    @Override
    public void reloadRoot() {
        loop(getRoot());
    }

    private static void loop(AbstractMutableTreeNodeCore<String> _root) {
        AbstractMutableTreeNodeCore<String> cur_ = _root;
        while (cur_ != null) {
            ((MockMutableTreeNode)cur_).setAccessible(true);
            AbstractMutableTreeNodeCore<String> ch_ = cur_.getChildAt(0);
            if (ch_ != null) {
                cur_ = ch_;
                continue;
            }
            cur_ = next(cur_,_root);
        }
    }
    private static AbstractMutableTreeNodeCore<String> next(AbstractMutableTreeNodeCore<String> _current, AbstractMutableTreeNodeCore<String> _root) {
        AbstractMutableTreeNodeCore<String> n_ = _current;
        while (n_ != null) {
            AbstractMutableTreeNodeCore<String> next_ = n_.getNextSibling();
            if (next_ != null) {
                return next_;
            }
            AbstractMutableTreeNodeCore<String> parent_ = n_.getParent();
            if (parent_ == _root || parent_ == null) {
                n_ = null;
            } else {
                n_ = parent_;
            }
        }
        return null;
    }

    static MockTreePath getTreePath(MockMutableTreeNode _node) {
        return new MockTreePath(_node);
    }

    @Override
    public AbstractMutableTreeNodeCore<String> selectEvt() {
        return realEvt();
    }

    @Override
    public AbstractMutableTreeNodeCore<String> realEvt() {
        AbsTreePath s_ = selectedPath();
        if (s_ != null) {
            return s_.data();
        }
        return null;
    }

    @Override
    public AbsTreePath selectedPath() {
        AbsTreePaths s_ = selectedPaths();
        if (s_.getLength() == 1) {
            return s_.elt(0);
        }
        return null;
    }

    @Override
    public AbstractMutableTreeNodeCore<String> translate(AbsTreePath _tr) {
        return _tr.data();
    }

    @Override
    public AbsTreePath translate(AbstractMutableTreeNodeCore<String> _tr) {
        return new MockTreePath(_tr);
    }

    @Override
    public boolean isRootVisible() {
        return rootVisible;
    }

    @Override
    public void setRootVisible(boolean _b) {
        rootVisible = _b;
    }

    @Override
    public void addTreeSelectionListener(AbsShortListTree _l) {
        list.add(_l);
    }

    @Override
    public int removeTreeSelectionListener(AbsShortListTree _l) {
        int index_ = list.indexOfObj(_l);
        list.removeObj(_l);
        return index_;
    }

    @Override
    public CustList<AbsShortListTree> getTreeSelectionListeners() {
        return list;
    }

    @Override
    public AbsTreePaths selectedPaths() {
        return selectionPaths;
    }

    @Override
    public void selectedPaths(AbsTreePaths _p) {
        this.selectionPaths = _p;
    }

    @Override
    public void info(AbstractMutableTreeNodeCore<String> _node, String _v) {
        _node.info(_v);
    }
}
