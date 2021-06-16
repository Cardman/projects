package code.gui;

import javax.swing.tree.TreeNode;

public interface AbstractMutableTreeNode {
    int getAntiIndex(AbstractMutableTreeNode _treeNode);
    void add(AbstractMutableTreeNode _treeNode);

    int getChildCount();

    void insert(AbstractMutableTreeNode _treeNode, int _index);

    void removeAllChildren();

    AbstractMutableTreeNode getParent();

    void remove(AbstractMutableTreeNode _treeNode);

    void remove(int _index);

    AbstractMutableTreeNode getChildAt(int _i);

    AbstractMutableTreeNode getPreviousSibling();

    AbstractMutableTreeNode getNextSibling();

    void removeFromParent();

    TreeNode[] getPath();

    void add(String _info);
}
