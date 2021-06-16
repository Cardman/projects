package code.gui;

import javax.swing.*;
import javax.swing.tree.*;

public final class TreeGui implements AbsTreeGui {
    private final TreeComponent tree;
    private final DefaultTreeModel model;
    private final DefaultTreeSelectionModel selectionModel;
    private AbstractMutableTreeNode selected;

    public TreeGui(AbstractMutableTreeNode _t) {
        selected = _t;
        model = new DefaultTreeModel(convert(_t));
        tree = new TreeComponent(new JTree(model));
        selectionModel = new DefaultTreeSelectionModel();
        tree.setSelectionModel(selectionModel);
        setSelectionModel();
    }

    public static MutableTreeNode convert(AbstractMutableTreeNode _t) {
        return ((DefMutableTreeNode)_t).node();
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean _rootVisible) {
        tree.setRootVisible(_rootVisible);
    }

    public AbstractMutableTreeNode selectEvt() {
        TreePath selectionPath_ = getSelectionPath();
        selected = selectedEvt(selectionPath_);
        return selected;
    }
    public static DefMutableTreeNode selected(TreePath _path) {
        try {
            return new DefMutableTreeNode((MutableTreeNode)_path.getLastPathComponent());
        } catch (Exception e) {
            return new DefMutableTreeNode("");
        }
    }
    public static DefMutableTreeNode selectedEvt(TreePath _path) {
        try {
            return new DefMutableTreeNode((MutableTreeNode)_path.getLastPathComponent());
        } catch (Exception e) {
            return null;
        }
    }
    public AbstractMutableTreeNode getSelected() {
        return selected;
    }

    public void removeFromParent() {
        selected.removeFromParent();
    }

    public void removeAllChildren() {
        selected.removeAllChildren();
    }
    private TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public void select(AbstractMutableTreeNode _node) {
        tree.setSelectionPath(getTreePath(convert(_node)));
    }

    @Override
    public void addTreeSelectionListener(AbsShortListTree _sel) {
        tree.addTreeSelectionListener(new DefTreeSelectionListener(_sel));
    }

    @Override
    public void reloadRoot() {
        model.reload();
    }

    @Override
    public void reload(AbstractMutableTreeNode _node) {
        model.reload(convert(_node));
    }

    private TreePath getTreePath(TreeNode _node) {
        return new TreePath(model.getPathToRoot(_node));
    }

    public CustComponent getTree() {
        return tree;
    }

    private void setSelectionModel() {
        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    @Override
    public void add(String _info) {
        selected.add(new DefMutableTreeNode(_info));
    }

}
