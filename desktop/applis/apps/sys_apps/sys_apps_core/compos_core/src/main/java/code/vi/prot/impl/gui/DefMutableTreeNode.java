package code.vi.prot.impl.gui;

import code.gui.*;
import code.util.core.StringUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public final class DefMutableTreeNode extends MutableTreeNodeNav implements AbstractMutableTreeNode {
    private final DefaultMutableTreeNode node;

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
    public AbstractMutableTreeNode add(String _info) {
        DefMutableTreeNode defMutableTreeNode_ = new DefMutableTreeNode(_info);
        node.add(defMutableTreeNode_.node);
        MutableTreeNodeCoreUtil.add(this, defMutableTreeNode_);
        return defMutableTreeNode_;
    }

    @Override
    public boolean add(AbstractMutableTreeNodeCore _treeNode) {
        boolean v_ = MutableTreeNodeCoreUtil.add(this, _treeNode);
        node.add(((DefMutableTreeNode) _treeNode).node());
        return v_;
    }

    @Override
    public int insert(AbstractMutableTreeNodeCore _treeNode, int _index) {
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
    public AbstractMutableTreeNode getParentReal() {
        try {
            return build((DefaultMutableTreeNode) node.getParent());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int remove(AbstractMutableTreeNodeCore _treeNode) {
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
