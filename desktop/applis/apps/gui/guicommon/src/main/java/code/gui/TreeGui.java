package code.gui;

import code.util.CustList;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public final class TreeGui extends CustComponent {
    private final JTree tree;
    private final DefaultTreeModel model;
    private final DefaultTreeSelectionModel selectionModel;

    public TreeGui(DefaultMutableTreeNode _t) {
        model = new DefaultTreeModel(_t);
        tree = new JTree(model);
        selectionModel = new DefaultTreeSelectionModel();
        tree.setSelectionModel(selectionModel);
        setSelectionModel();
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean _rootVisible) {
        tree.setRootVisible(_rootVisible);
    }

    public Object getLastSelectedPathComponent() {
        return tree.getLastSelectedPathComponent();
    }

    public void select(DefaultMutableTreeNode _node) {
        TreeNode treeNode_ = _node.getParent();
        CustList<TreeNode> nodes_ = new CustList<TreeNode>();
        nodes_.add(_node);
        while (treeNode_ != null) {
            nodes_.add(0, treeNode_);
            treeNode_ = treeNode_.getParent();
        }
        tree.setSelectionPath(new TreePath(nodes_.list().toArray()));
    }
    public void addTreeSelectionListener(TreeSelectionListener _tsl) {
        tree.addTreeSelectionListener(_tsl);
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
    protected JComponent getComponent() {
        return tree;
    }

    private void setSelectionModel() {
        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
}
