package code.gui;

import code.util.CustList;
import code.util.IdMap;

import javax.swing.*;
import javax.swing.tree.*;

public final class TreeGui implements AbsTreeGui {
    private final TreeComponent tree;
    private final DefaultTreeModel model;
    private final DefaultTreeSelectionModel selectionModel;
    private AbstractMutableTreeNode selected;
    private final IdMap<AbsShortListTree,DefTreeSelectionListener> list = new IdMap<AbsShortListTree, DefTreeSelectionListener>();

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
            return DefMutableTreeNode.build((DefaultMutableTreeNode) _path.getLastPathComponent());
        } catch (Exception e) {
            return new DefMutableTreeNode("");
        }
    }
    public static DefMutableTreeNode selectedEvt(TreePath _path) {
        try {
            return DefMutableTreeNode.build((DefaultMutableTreeNode)_path.getLastPathComponent());
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
        DefTreeSelectionListener value_ = new DefTreeSelectionListener(_sel);
        tree.addTreeSelectionListener(value_);
        list.addEntry(_sel,value_);
    }

    @Override
    public int removeTreeSelectionListener(AbsShortListTree _sel) {
        try {
            int index_ = list.indexOfEntry(_sel);
            DefTreeSelectionListener value_ = list.getValue(index_);
            list.removeKey(_sel);
            tree.removeTreeSelectionListener(value_);
            return index_;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public CustList<AbsShortListTree> getTreeSelectionListeners() {
        return list.getKeys();
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
