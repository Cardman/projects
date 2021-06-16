package code.gui;

import code.util.core.StringUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public final class DefMutableTreeNode implements AbstractMutableTreeNode {
    private final DefaultMutableTreeNode node;

    public DefMutableTreeNode(String _name) {
        node = new DefaultMutableTreeNode(StringUtil.nullToEmpty(_name));
    }

    public DefMutableTreeNode(MutableTreeNode _name) {
        node = (DefaultMutableTreeNode) _name;
        userObject();
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
            DefaultMutableTreeNode mut_ = new DefaultMutableTreeNode(StringUtil.nullToEmpty(_info));
            node.add(mut_);
            return new DefMutableTreeNode(mut_);
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
            return new DefMutableTreeNode((MutableTreeNode)node.getParent());
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
            TreeNode tr_ = node.getChildAt(_index);
            node.remove(_index);
            return new DefMutableTreeNode((MutableTreeNode) tr_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode getChildAt(int _i) {
        try {
            return new DefMutableTreeNode((MutableTreeNode) node.getChildAt(_i));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode getPreviousSibling() {
        try {
            return new DefMutableTreeNode(node.getPreviousSibling());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode getNextSibling() {
        try {
            return new DefMutableTreeNode(node.getNextSibling());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNode removeFromParent() {
        try {
            TreeNode parent_ = node.getParent();
            node.removeFromParent();
            return new DefMutableTreeNode((MutableTreeNode) parent_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getUserObject() {
        try {
            return userObject();
        } catch (Exception e) {
            return "";
        }
    }

    private String userObject() {
        return String.valueOf(node.getUserObject());
    }
}
