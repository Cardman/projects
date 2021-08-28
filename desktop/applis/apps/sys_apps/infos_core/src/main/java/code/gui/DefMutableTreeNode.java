package code.gui;

import code.util.core.StringUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public final class DefMutableTreeNode implements AbstractMutableTreeNode {
    private final DefaultMutableTreeNode node;

    private final UsObj userObject;

    public DefMutableTreeNode(String _name) {
        String value_ = StringUtil.nullToEmpty(_name);
        UsObj us_ = new UsObj(this,value_);
        node = new DefaultMutableTreeNode(us_);
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
        try {
            return node.getIndex(((DefMutableTreeNode) _treeNode).node());
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public AbstractMutableTreeNode add(String _info) {
        try {
            DefMutableTreeNode defMutableTreeNode_ = new DefMutableTreeNode(_info);
            node.add(defMutableTreeNode_.node);
            return defMutableTreeNode_;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(AbstractMutableTreeNode _treeNode) {
        try {
            node.add(((DefMutableTreeNode) _treeNode).node());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getChildCount() {
        try {
            return node.getChildCount();
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int insert(AbstractMutableTreeNode _treeNode, int _index) {
        try {
            node.insert(((DefMutableTreeNode) _treeNode).node(), _index);
            return _index;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int removeAllChildren() {
        try {
            int count_ = node.getChildCount();
            node.removeAllChildren();
            return count_;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public AbstractMutableTreeNode getParent() {
        try {
            DefaultMutableTreeNode parent_ = (DefaultMutableTreeNode) node.getParent();
            return build(parent_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int remove(AbstractMutableTreeNode _treeNode) {
        try {
            int index_ = node.getIndex(((DefMutableTreeNode) _treeNode).node());
            node.remove(((DefMutableTreeNode) _treeNode).node());
            return index_;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public AbstractMutableTreeNode remove(int _index) {
        try {
            DefaultMutableTreeNode parent_ = (DefaultMutableTreeNode) node.getChildAt(_index);
            node.remove(_index);
            return build(parent_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode getChildAt(int _i) {
        try {
            DefaultMutableTreeNode parent_ = (DefaultMutableTreeNode) node.getChildAt(_i);
            return build(parent_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode getPreviousSibling() {
        try {
            DefaultMutableTreeNode parent_ = node.getPreviousSibling();
            return build(parent_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode getNextSibling() {
        try {
            DefaultMutableTreeNode parent_ = node.getNextSibling();
            return build(parent_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode removeFromParent() {
        try {
            DefaultMutableTreeNode parent_ = (DefaultMutableTreeNode) node.getParent();
            node.removeFromParent();
            return build(parent_);
        } catch (Exception e) {
            return null;
        }
    }

    static DefMutableTreeNode build(DefaultMutableTreeNode _node) {
        return new DefMutableTreeNode(_node,(UsObj)_node.getUserObject());
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
