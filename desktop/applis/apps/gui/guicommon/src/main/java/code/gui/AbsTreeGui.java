package code.gui;

public interface AbsTreeGui {
    void select(AbstractMutableTreeNode _node);
    void reload(AbstractMutableTreeNode _node);
    void reloadRoot();
    AbstractMutableTreeNode getSelected();
    boolean selectEvt();

    boolean isRootVisible();

    void setRootVisible(boolean _value);

    CustComponent getTree();

    void addTreeSelectionListener(AbsShortListTree _sel);

    void removeFromParent();

    void removeAllChildren();

    void add(String _info);
}
