package code.gui;

import code.util.CustList;

public interface AbsTreeGui {
    void select(AbstractMutableTreeNodeCore _node);
    void reload(AbstractMutableTreeNodeCore _node);
    void reloadRoot();
    AbstractMutableTreeNode getRoot();
    AbstractMutableTreeNode getSelected();
    AbstractMutableTreeNode selectEvt();

    boolean isRootVisible();

    void setRootVisible(boolean _value);

    AbsCustComponent getTree();

    void addTreeSelectionListener(AbsShortListTree _sel);
    int removeTreeSelectionListener(AbsShortListTree _sel);
    CustList<AbsShortListTree> getTreeSelectionListeners();

    void removeFromParent();

    void removeAllChildren();

    void add(String _info);
}
