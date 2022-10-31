package code.gui;

public interface AbstractMutableTreeNodeCore {
    AbstractMutableTreeNodeCore getParent();

    AbstractMutableTreeNodeCore getFirstChild();

    AbstractMutableTreeNodeCore getNextSibling();

    void setParent(AbstractMutableTreeNodeCore _v);

    void setFirstChild(AbstractMutableTreeNodeCore _v);

    void setNextSibling(AbstractMutableTreeNodeCore _v);
}
