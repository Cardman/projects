package code.mock;

import code.gui.*;
import code.util.core.StringUtil;

public final class MockMutableTreeNode extends MutableTreeNodeNav implements AbstractMutableTreeNode {
    private final String userObject;
    private boolean accessible;

    public MockMutableTreeNode(String _name) {
        userObject = StringUtil.nullToEmpty(_name);
    }

    @Override
    public int getIndex() {
        return MutableTreeNodeCoreUtil.getIndex(this);
    }

    @Override
    public boolean add(AbstractMutableTreeNodeCore _m) {
        MutableTreeNodeCoreUtil.add(this, _m);
        return false;
    }

    @Override
    public int insert(AbstractMutableTreeNodeCore _m, int _i) {
        return MutableTreeNodeCoreUtil.insert(this,_m,_i);
    }

    @Override
    public int removeAllChildren() {
        return MutableTreeNodeCoreUtil.removeAllChildren(this);
    }

    @Override
    public AbstractMutableTreeNode getParentReal() {
        return (AbstractMutableTreeNode) getParent();
    }

    @Override
    public int remove(AbstractMutableTreeNodeCore _m) {
        return MutableTreeNodeCoreUtil.remove(this,_m);
    }

    @Override
    public AbstractMutableTreeNode remove(int _index) {
        return (AbstractMutableTreeNode) MutableTreeNodeCoreUtil.remove(this,_index);
    }

    @Override
    public AbstractMutableTreeNode removeFromParent() {
        return (AbstractMutableTreeNode) MutableTreeNodeCoreUtil.removeFromParent(this);
    }

    @Override
    public AbstractMutableTreeNode add(String _info) {
        MockMutableTreeNode m_ = new MockMutableTreeNode(_info);
        MutableTreeNodeCoreUtil.add(this,m_);
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

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean _a) {
        this.accessible = _a;
    }
}
