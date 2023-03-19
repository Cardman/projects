package code.mock;

import code.gui.*;
import code.util.CustList;
import code.util.IdList;

public final class MockTreeGui extends MockCustComponent implements AbsTreeGui {
    private boolean rootVisible;
    private AbsTreePaths selectionPaths = new MockTreePaths(new CustList<AbsTreePath>());
    private final AbstractMutableTreeNode root;
    private final IdList<AbsShortListTree> list = new IdList<AbsShortListTree>();

    public MockTreeGui(AbstractMutableTreeNode _t) {
        root = _t;
    }

    @Override
    public AbstractMutableTreeNode getRoot() {
        return root;
    }

    @Override
    public void select(AbstractMutableTreeNodeCore _m) {
        selectedPaths(new MockTreePaths(new CustList<AbsTreePath>(getTreePath((MockMutableTreeNode) _m))));
        for (AbsShortListTree l: getTreeSelectionListeners()) {
            l.valueChanged(_m);
        }
    }

    @Override
    public void reload(AbstractMutableTreeNodeCore _m) {
        loop(_m);
    }

    @Override
    public void reloadRoot() {
        loop(getRoot());
    }

    private static void loop(AbstractMutableTreeNodeCore _root) {
        AbstractMutableTreeNodeCore cur_ = _root;
        while (cur_ != null) {
            ((MockMutableTreeNode)cur_).setAccessible(true);
            AbstractMutableTreeNodeCore ch_ = ((MockMutableTreeNode)cur_).getChildAt(0);
            if (ch_ != null) {
                cur_ = ch_;
                continue;
            }
            cur_ = next(cur_,_root);
        }
    }
    private static AbstractMutableTreeNodeCore next(AbstractMutableTreeNodeCore _current, AbstractMutableTreeNodeCore _root) {
        AbstractMutableTreeNodeCore n_ = _current;
        while (n_ != null) {
            AbstractMutableTreeNodeCore next_ = n_.getNextSibling();
            if (next_ != null) {
                return next_;
            }
            AbstractMutableTreeNodeCore parent_ = n_.getParent();
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
    public AbstractMutableTreeNode selectEvt() {
        AbsTreePaths s_ = selectedPaths();
        if (s_.getLength() == 1) {
            return s_.elt(0).data();
        }
        return null;
    }

    @Override
    public AbstractMutableTreeNode translate(AbsTreePath _tr) {
        return _tr.data();
    }

    @Override
    public AbsTreePath translate(AbstractMutableTreeNode _tr) {
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
    public AbsTreePaths emptyList() {
        return new MockTreePaths(new CustList<AbsTreePath>());
    }
}
