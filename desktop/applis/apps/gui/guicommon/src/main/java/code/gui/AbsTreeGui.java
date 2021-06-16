package code.gui;

import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public interface AbsTreeGui {
    void select(AbstractMutableTreeNode _node);
    void reload(AbstractMutableTreeNode _node);
    void reloadRoot();
    AbstractMutableTreeNode getSelected();

    TreePath selectEvt();

    boolean isRootVisible();

    void setRootVisible(boolean _value);

    CustComponent getTree();

    void addTreeSelectionListener(TreeSelectionListener _sel);

    void removeFromParent();

    void removeAllChildren();

    TreePath getSelectionPath();

    void add(String _info);
}
