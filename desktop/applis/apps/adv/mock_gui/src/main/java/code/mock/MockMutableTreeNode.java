package code.mock;

import code.gui.AbstractMutableTreeNode;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.MutableTreeNodeCore;
import code.gui.MutableTreeNodeCoreUtil;
import code.util.core.StringUtil;

public final class MockMutableTreeNode implements AbstractMutableTreeNode {
    private final String userObject;
    private final MutableTreeNodeCore navMock = new MutableTreeNodeCore();
    private boolean accessible;

    public MockMutableTreeNode(String _name) {
        userObject = StringUtil.nullToEmpty(_name);
    }

    @Override
    public int getIndex() {
        return MutableTreeNodeCoreUtil.getIndex(this);
    }

    @Override
    public int getAntiIndex(AbstractMutableTreeNode _m) {
        return MutableTreeNodeCoreUtil.getAntiIndex(this,_m);
    }

    @Override
    public boolean add(AbstractMutableTreeNode _m) {
        MutableTreeNodeCoreUtil.add(this, _m);
        return false;
    }

    @Override
    public int getChildCount() {
        return MutableTreeNodeCoreUtil.children(this).size();
    }

    @Override
    public int insert(AbstractMutableTreeNode _m, int _i) {
        return MutableTreeNodeCoreUtil.insert(this,_m,_i);
    }

    @Override
    public int removeAllChildren() {
        return MutableTreeNodeCoreUtil.removeAllChildren(this);
    }

    @Override
    public AbstractMutableTreeNode getParent() {
        return getParentReal();
    }

    @Override
    public AbstractMutableTreeNode getParentReal() {
        return (AbstractMutableTreeNode) navMock.getParent();
    }

    @Override
    public void setParent(AbstractMutableTreeNodeCore _v) {
        navMock.setParent(_v);
    }

    @Override
    public void setFirstChild(AbstractMutableTreeNodeCore _v) {
        navMock.setFirstChild(_v);
    }

    @Override
    public AbstractMutableTreeNodeCore getFirstChild() {
        return navMock.getFirstChild();
    }

    @Override
    public int remove(AbstractMutableTreeNode _m) {
        return MutableTreeNodeCoreUtil.remove(this,_m);
    }

    @Override
    public AbstractMutableTreeNode remove(int _index) {
        return (AbstractMutableTreeNode) MutableTreeNodeCoreUtil.remove(this,_index);
    }

    @Override
    public AbstractMutableTreeNode getChildAt(int _i) {
        return (AbstractMutableTreeNode) MutableTreeNodeCoreUtil.getChildAt(this,_i);
    }

    @Override
    public AbstractMutableTreeNode getPreviousSibling() {
        return (AbstractMutableTreeNode) MutableTreeNodeCoreUtil.getPreviousSibling(this);
    }

    @Override
    public AbstractMutableTreeNode getNextSibling() {
        return (AbstractMutableTreeNode) navMock.getNextSibling();
    }

    @Override
    public void setNextSibling(AbstractMutableTreeNodeCore _v) {
        navMock.setNextSibling(_v);
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
