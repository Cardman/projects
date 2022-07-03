package code.mock;

import code.gui.AbstractMutableTreeNode;
import code.util.IdList;
import code.util.core.StringUtil;

public final class MockMutableTreeNode implements AbstractMutableTreeNode {
    private final String userObject;
    private MockMutableTreeNode parent;
    private MockMutableTreeNode firstChild;
    private MockMutableTreeNode nextSibling;
    private boolean accessible;

    public MockMutableTreeNode(String _name) {
        userObject = StringUtil.nullToEmpty(_name);
    }

    @Override
    public int getIndex() {
        if (parent == null) {
            return -1;
        }
        return parent.children().indexOfObj(this);
    }

    @Override
    public int getAntiIndex(AbstractMutableTreeNode _m) {
        return children().indexOfObj((MockMutableTreeNode) _m);
    }

    @Override
    public boolean add(AbstractMutableTreeNode _m) {
        append((MockMutableTreeNode) _m);
        return false;
    }

    private void append(MockMutableTreeNode _m) {
        _m.removeFromParent();
        IdList<MockMutableTreeNode> ch_ = children();
        _m.parent = this;
        if (ch_.isEmpty()) {
            firstChild = _m;
        } else {
            ch_.last().nextSibling= _m;
        }
    }

    @Override
    public int getChildCount() {
        return children().size();
    }

    @Override
    public int insert(AbstractMutableTreeNode _m, int _i) {
        _m.removeFromParent();
        int index_ = 0;
        MockMutableTreeNode prev_ = null;
        MockMutableTreeNode curr_ = firstChild;
        while (curr_ != null) {
            if (index_ == _i) {
                ((MockMutableTreeNode) _m).parent = this;
                insert((MockMutableTreeNode) _m, prev_, curr_);
                return _i;
            }
            prev_ = curr_;
            curr_ = curr_.nextSibling;
            index_++;
        }
        if (index_ == _i) {
            ((MockMutableTreeNode) _m).parent = this;
            insert((MockMutableTreeNode) _m, prev_, null);
            return _i;
        }
        return -1;
    }

    private void insert(MockMutableTreeNode _m, MockMutableTreeNode _prev, MockMutableTreeNode _curr) {
        if (_prev == null) {
            _m.nextSibling = firstChild;
            firstChild = _m;
        } else {
            _m.nextSibling = _curr;
            _prev.nextSibling = _m;
        }
    }

    @Override
    public int removeAllChildren() {
        int ch_ = getChildCount();
        for (MockMutableTreeNode m: children()) {
            m.parent = null;
            m.nextSibling = null;
        }
        firstChild = null;
        return ch_;
    }

    @Override
    public AbstractMutableTreeNode getParent() {
        return parent;
    }

    @Override
    public int remove(AbstractMutableTreeNode _m) {
        int index_ = 0;
        MockMutableTreeNode prev_ = null;
        MockMutableTreeNode curr_ = firstChild;
        while (curr_ != null) {
            if (curr_ == _m) {
                clear(prev_, curr_);
                return index_;
            }
            prev_ = curr_;
            curr_ = curr_.nextSibling;
            index_++;
        }
        return -1;
    }

    @Override
    public AbstractMutableTreeNode remove(int _index) {
        int index_ = 0;
        MockMutableTreeNode prev_ = null;
        MockMutableTreeNode curr_ = firstChild;
        while (curr_ != null) {
            if (index_ == _index) {
                clear(prev_, curr_);
                return curr_;
            }
            prev_ = curr_;
            curr_ = curr_.nextSibling;
            index_++;
        }
        return null;
    }

    private void clear(MockMutableTreeNode _prev, MockMutableTreeNode _curr) {
        if (_prev == null) {
            firstChild = _curr.nextSibling;
        } else {
            _prev.nextSibling = _curr.nextSibling;
        }
        _curr.nextSibling = null;
        _curr.parent = null;
    }

    @Override
    public AbstractMutableTreeNode getChildAt(int _i) {
        IdList<MockMutableTreeNode> ch_ = children();
        if (ch_.isValidIndex(_i)) {
            return ch_.get(_i);
        }
        return null;
    }

    @Override
    public AbstractMutableTreeNode getPreviousSibling() {
        int index_ = getIndex();
        if (index_ < 1) {
            return null;
        }
        IdList<MockMutableTreeNode> ch_ = parent.children();
        return ch_.get(index_ - 1);
    }

    @Override
    public AbstractMutableTreeNode getNextSibling() {
        return nextSibling;
    }

    @Override
    public AbstractMutableTreeNode removeFromParent() {
        if (parent != null) {
            AbstractMutableTreeNode old_ = getParent();
            MockMutableTreeNode prev_ = (MockMutableTreeNode) getPreviousSibling();
            if (prev_ == null) {
                parent.firstChild = nextSibling;
                parent = null;
                nextSibling = null;
                return old_;
            }
            prev_.nextSibling = nextSibling;
            parent = null;
            nextSibling = null;
            return old_;
        }
        return null;
    }

    @Override
    public AbstractMutableTreeNode add(String _info) {
        MockMutableTreeNode m_ = new MockMutableTreeNode(_info);
        append(m_);
        return m_;
    }

    @Override
    public AbstractMutableTreeNode original() {
        return this;
    }

    @Override
    public String getUserObject() {
        return userObject;
    }
    private IdList<MockMutableTreeNode> children() {
        IdList<MockMutableTreeNode> ch_ = new IdList<MockMutableTreeNode>();
        MockMutableTreeNode curr_ = firstChild;
        while (curr_ != null) {
            ch_.add(curr_);
            curr_ = curr_.nextSibling;
        }
        return ch_;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean _a) {
        this.accessible = _a;
    }
}
