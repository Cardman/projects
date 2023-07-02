package code.gui;

public interface AbstractMutableTreeNode extends AbstractMutableTreeNodeNav{
    int getIndex();
    boolean add(AbstractMutableTreeNodeCore _treeNode);

    int insert(AbstractMutableTreeNodeCore _treeNode, int _index);

    int removeAllChildren();

    AbstractMutableTreeNode getParentReal();

    int remove(AbstractMutableTreeNodeCore _treeNode);

    AbstractMutableTreeNode remove(int _index);

    AbstractMutableTreeNode removeFromParent();

    AbstractMutableTreeNode add(String _info);

    String getUserObject();

    void setUserObject(String _str);
}
