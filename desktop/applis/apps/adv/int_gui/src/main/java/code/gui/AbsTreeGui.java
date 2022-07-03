package code.gui;

import code.util.CustList;

public interface AbsTreeGui {
    void select(AbstractMutableTreeNode _node);
    void reload(AbstractMutableTreeNode _node);
    void reloadRoot();
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
