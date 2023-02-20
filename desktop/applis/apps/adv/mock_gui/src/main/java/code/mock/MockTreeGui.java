package code.mock;

import code.gui.AbsShortListTree;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;
import code.gui.AbstractMutableTreeNodeCore;
import code.util.CustList;
import code.util.IdList;

public final class MockTreeGui extends MockCustComponent implements AbsTreeGui {
    private boolean rootVisible;
    private MockTreePath selectionPath = new MockTreePath();
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
        setSelectionPath(getTreePath((MockMutableTreeNode) _m));
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
        MockTreePath tp_ = new MockTreePath();
        MockMutableTreeNode cur_ = _node;
        while (cur_ != null) {
            tp_.getPath().add(0,cur_);
            cur_ = (MockMutableTreeNode) cur_.getParent();
        }
        return tp_;
    }

    @Override
    public AbstractMutableTreeNode selectEvt() {
        MockTreePath path_ = getSelectionPath();
        CustList<MockMutableTreeNode> p_ = path_.getPath();
        if (!p_.isEmpty()) {
            return p_.last();
        }
        return null;
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

    public MockTreePath getSelectionPath() {
        return selectionPath;
    }

    public void setSelectionPath(MockTreePath _treePath) {
        selectionPath = _treePath;
    }
}
