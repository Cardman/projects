package code.gui;

import code.util.CustList;

public interface AbsTreeGui extends AbsCustComponent {
    void select(AbstractMutableTreeNodeCore<String> _node);
    void reload(AbstractMutableTreeNodeCore<String> _node);
    void reloadRoot();
    AbstractMutableTreeNodeCore<String> getRoot();
    AbstractMutableTreeNodeCore<String> selectEvt();
    AbstractMutableTreeNodeCore<String> realEvt();

    boolean isRootVisible();

    void setRootVisible(boolean _value);

    void addTreeSelectionListener(AbsShortListTree _sel);
    void addTreeSelectionListenerMap(AbsShortListTree _sel);
    int removeTreeSelectionListener(AbsShortListTree _sel);
    int removeTreeSelectionListenerMap(AbsShortListTree _sel);
    CustList<AbsShortListTree> getTreeSelectionListeners();
    AbstractMutableTreeNodeCore<String> translate(AbsTreePath _tr);
    AbsTreePath translate(AbstractMutableTreeNodeCore<String> _tr);
    AbsTreePath selectedPath();
    AbsTreePaths selectedPaths();
    void selectedPaths(AbsTreePaths _p);
    void info(AbstractMutableTreeNodeCore<String> _node, String _v);
}
