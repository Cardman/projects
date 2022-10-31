package code.vi.prot.impl.gui;

import code.gui.AbstractMutableTreeNode;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.MutableTreeNodeCore;
import code.gui.MutableTreeNodeCoreUtil;
import code.util.core.StringUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public final class DefMutableTreeNode implements AbstractMutableTreeNode {
    private final DefaultMutableTreeNode node;

    private final MutableTreeNodeCore nav = new MutableTreeNodeCore();
    private final UsObj userObject;

    public DefMutableTreeNode(String _name) {
        String value_ = StringUtil.nullToEmpty(_name);
        UsObj us_ = new UsObj(this,value_);
        node = new DefaultMutableTreeNode(value_);
        userObject = us_;
    }

    public DefMutableTreeNode(MutableTreeNode _name, UsObj _userObject) {
        node = (DefaultMutableTreeNode) _name;
        userObject = _userObject;
    }

    public MutableTreeNode node() {
        return node;
    }

    @Override
    public int getIndex() {
        try {
            return node.getParent().getIndex(node);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int getAntiIndex(AbstractMutableTreeNode _treeNode) {
        return MutableTreeNodeCoreUtil.getAntiIndex(this,_treeNode);
    }

    @Override
    public AbstractMutableTreeNode add(String _info) {
        DefMutableTreeNode defMutableTreeNode_ = new DefMutableTreeNode(_info);
        node.add(defMutableTreeNode_.node);
        MutableTreeNodeCoreUtil.add(this, defMutableTreeNode_);
        return defMutableTreeNode_;
    }

    @Override
    public boolean add(AbstractMutableTreeNode _treeNode) {
        boolean v_ = MutableTreeNodeCoreUtil.add(this, _treeNode);
        node.add(((DefMutableTreeNode) _treeNode).node());
        return v_;
    }

    @Override
    public int getChildCount() {
        return MutableTreeNodeCoreUtil.getChildCount(this);
    }

    @Override
    public int insert(AbstractMutableTreeNode _treeNode, int _index) {
        node.insert(((DefMutableTreeNode) _treeNode).node(), _index);
        return MutableTreeNodeCoreUtil.insert(this,_treeNode,_index);
    }

    @Override
    public int removeAllChildren() {
        int nb_ = MutableTreeNodeCoreUtil.getChildCount(this);
        node.removeAllChildren();
        return nb_;
    }

    @Override
    public AbstractMutableTreeNode getParent() {
        return (AbstractMutableTreeNode) nav.getParent();
    }

    @Override
    public AbstractMutableTreeNode getParentReal() {
        try {
            return build((DefaultMutableTreeNode) node.getParent());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int remove(AbstractMutableTreeNode _treeNode) {
        int v_ = MutableTreeNodeCoreUtil.remove(this,_treeNode);
        node.remove(((DefMutableTreeNode) _treeNode).node());
        return v_;
    }

    @Override
    public AbstractMutableTreeNode remove(int _index) {
        AbstractMutableTreeNodeCore v_ = MutableTreeNodeCoreUtil.remove(this, _index);
        node.remove(_index);
        return (AbstractMutableTreeNode) v_;
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
        return (AbstractMutableTreeNode) nav.getNextSibling();
    }

    @Override
    public void setParent(AbstractMutableTreeNodeCore _v) {
        nav.setParent(_v);
    }

    @Override
    public AbstractMutableTreeNodeCore getFirstChild() {
        return nav.getFirstChild();
    }

    @Override
    public void setFirstChild(AbstractMutableTreeNodeCore _v) {
        nav.setFirstChild(_v);
    }

    @Override
    public void setNextSibling(AbstractMutableTreeNodeCore _v) {
        nav.setNextSibling(_v);
    }

    @Override
    public AbstractMutableTreeNode removeFromParent() {
        AbstractMutableTreeNode v_ = (AbstractMutableTreeNode) MutableTreeNodeCoreUtil.removeFromParent(this);
        node.removeFromParent();
        return v_;
    }

    static DefMutableTreeNode build(DefaultMutableTreeNode _node) {
        return new DefMutableTreeNode(_node,new UsObj(null,(String)_node.getUserObject()));
    }

    @Override
    public AbstractMutableTreeNode original() {
        try {
            return userObject.getNode();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getUserObject() {
        try {
            return userObject.getUserObject();
        } catch (Exception e) {
            return "";
        }
    }

}
