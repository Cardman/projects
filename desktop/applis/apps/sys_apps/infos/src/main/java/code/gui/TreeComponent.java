package code.gui;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;

public final class TreeComponent extends CustComponent {
    private final JTree tree;

    TreeComponent(JTree _t) {
        tree = _t;
    }
    @Override
    protected JComponent getNatComponent() {
        return tree;
    }

    void setSelectionModel(DefaultTreeSelectionModel _sel) {
        tree.setSelectionModel(_sel);
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean _rootVisible) {
        tree.setRootVisible(_rootVisible);
    }

    public TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public void setSelectionPath(TreePath _treePath) {
        tree.setSelectionPath(_treePath);
    }

    public void addTreeSelectionListener(TreeSelectionListener _sel) {
        tree.addTreeSelectionListener(_sel);
    }

    public void removeTreeSelectionListener(TreeSelectionListener _sel) {
        tree.removeTreeSelectionListener(_sel);
    }
}
