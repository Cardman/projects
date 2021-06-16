package code.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class DefMutableTreeNode implements AbstractMutableTreeNode {
    private final DefaultMutableTreeNode node;

    public DefMutableTreeNode(String _name) {
        node = new DefaultMutableTreeNode(_name);
    }

    public DefMutableTreeNode(MutableTreeNode _name) {
        node = (DefaultMutableTreeNode) _name;
    }

    public MutableTreeNode node() {
        return node;
    }

    @Override
    public int getAntiIndex(AbstractMutableTreeNode _treeNode) {
        return node.getIndex(((DefMutableTreeNode) _treeNode).node());
    }

    @Override
    public void add(AbstractMutableTreeNode _treeNode) {
        node.add(((DefMutableTreeNode) _treeNode).node());
    }

    @Override
    public int getChildCount() {
        return node.getChildCount();
    }

    @Override
    public void insert(AbstractMutableTreeNode _treeNode, int _index) {
        node.insert(((DefMutableTreeNode) _treeNode).node(),_index);
    }

    @Override
    public void removeAllChildren() {
        node.removeAllChildren();
    }

    @Override
    public AbstractMutableTreeNode getParent() {
        return new DefMutableTreeNode((MutableTreeNode)node.getParent());
    }

    @Override
    public void remove(AbstractMutableTreeNode _treeNode) {
        node.remove(((DefMutableTreeNode) _treeNode).node());
    }

    @Override
    public void remove(int _index) {
        node.remove(_index);
    }

    @Override
    public AbstractMutableTreeNode getChildAt(int _i) {
        return new DefMutableTreeNode((MutableTreeNode) node.getChildAt(_i));
    }

    @Override
    public AbstractMutableTreeNode getPreviousSibling() {
        return new DefMutableTreeNode(node.getPreviousSibling());
    }

    @Override
    public AbstractMutableTreeNode getNextSibling() {
        return new DefMutableTreeNode(node.getNextSibling());
    }

    @Override
    public void removeFromParent() {
        node.removeFromParent();
    }

    @Override
    public TreeNode[] getPath() {
        return node.getPath();
    }
}
