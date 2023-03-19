package code.gui;

import code.util.CustList;

public interface AbsTreeGui extends AbsCustComponent {
    void select(AbstractMutableTreeNodeCore _node);
    void reload(AbstractMutableTreeNodeCore _node);
    void reloadRoot();
    AbstractMutableTreeNode getRoot();
    AbstractMutableTreeNode selectEvt();

    boolean isRootVisible();

    void setRootVisible(boolean _value);

    void addTreeSelectionListener(AbsShortListTree _sel);
    int removeTreeSelectionListener(AbsShortListTree _sel);
    CustList<AbsShortListTree> getTreeSelectionListeners();
    AbstractMutableTreeNode translate(AbsTreePath _tr);
    AbsTreePath translate(AbstractMutableTreeNode _tr);
    AbsTreePaths selectedPaths();
    void selectedPaths(AbsTreePaths _p);
    AbsTreePaths emptyList();
}
