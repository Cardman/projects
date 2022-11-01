package code.gui;

public interface AbstractMutableTreeNodeNav extends AbstractMutableTreeNodeCore{
    int getAntiIndex(AbstractMutableTreeNodeCore _treeNode);
    int getChildCount();

    AbstractMutableTreeNodeCore getPreviousSibling();

    AbstractMutableTreeNodeCore getChildAt(int _i);

}
