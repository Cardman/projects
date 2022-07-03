package code.gui;

public interface AbstractMutableTreeNode {
    int getIndex();
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

    AbstractMutableTreeNode add(String _info);

    AbstractMutableTreeNode original();

    String getUserObject();
}
