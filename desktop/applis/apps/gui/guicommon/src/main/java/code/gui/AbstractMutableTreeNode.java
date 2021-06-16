package code.gui;

import javax.swing.tree.TreeNode;

public interface AbstractMutableTreeNode {
    int getAntiIndex(AbstractMutableTreeNode _treeNode);
    boolean add(AbstractMutableTreeNode _treeNode);

    int getChildCount();

    int insert(AbstractMutableTreeNode _treeNode, int _index);

    int removeAllChildren();

    AbstractMutableTreeNode getParent();

    int remove(AbstractMutableTreeNode _treeNode);

    AbstractMutableTreeNode remove(int _index);

    AbstractMutableTreeNode getChildAt(int _i);

    AbstractMutableTreeNode getPreviousSibling();

    AbstractMutableTreeNode getNextSibling();

    AbstractMutableTreeNode removeFromParent();

    TreeNode[] getPath();

    AbstractMutableTreeNode add(String _info);

    String getUserObject();
}
