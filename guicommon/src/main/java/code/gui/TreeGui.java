package code.gui;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public final class TreeGui extends CustComponent {
    private DefaultMutableTreeNode root;
    private JTree tree;
    private DefaultTreeModel model;
    private DefaultTreeSelectionModel selectionModel;

    public TreeGui(DefaultMutableTreeNode _t) {
        root = _t;
        model = new DefaultTreeModel(_t);
        tree = new JTree(model);
        selectionModel = new DefaultTreeSelectionModel();
        tree.setSelectionModel(selectionModel);
        setSelectionModel();
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean rootVisible) {
        tree.setRootVisible(rootVisible);
    }

    public void setSelectionPath(TreePath path) {
        tree.setSelectionPath(path);
    }

    public Object getLastSelectedPathComponent() {
        return tree.getLastSelectedPathComponent();
    }

    public TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public void addTreeSelectionListener(TreeSelectionListener tsl) {
        tree.addTreeSelectionListener(tsl);
    }

    public String getToolTipText() {
        return tree.getToolTipText();
    }

    public void reload() {
        Object obj_ = tree.getLastSelectedPathComponent();
        if (obj_ instanceof TreeNode) {
            model.reload((TreeNode) obj_);
        } else {
            model.reload();
        }
    }
    @Override
    public JComponent getComponent() {
        return tree;
    }

    private void setSelectionModel() {
        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
}
