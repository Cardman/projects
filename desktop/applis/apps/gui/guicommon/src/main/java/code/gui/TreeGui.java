package code.gui;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public final class TreeGui extends CustComponent {
    private final JTree tree;
    private final DefaultTreeModel model;
    private final DefaultTreeSelectionModel selectionModel;
    private AbstractMutableTreeNode selected;

    public TreeGui(AbstractMutableTreeNode _t) {
        selected = _t;
        model = new DefaultTreeModel(((DefMutableTreeNode)_t).node());
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

    public TreePath selectEvt() {
        TreePath selectionPath_ = getSelectionPath();
        selected = new DefMutableTreeNode(selected(selectionPath_));
        return selectionPath_;
    }
    //Nul => Nul & !Nul => !Nul
    public static MutableTreeNode selected(TreePath _path) {
        if (_path == null) {
            return null;
        }
        return (MutableTreeNode)_path.getLastPathComponent();
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
    public TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public void select(AbstractMutableTreeNode _node) {
        tree.setSelectionPath(getTreePath(((DefMutableTreeNode)_node).node()));
    }
    public void addTreeSelectionListener(TreeSelectionListener _tsl) {
        tree.addTreeSelectionListener(_tsl);
    }

    public void reload() {
        if (selectEvt() != null) {
            model.reload(((DefMutableTreeNode) selected).node());
        } else {
            model.reload();
        }
    }
    private TreePath getTreePath(TreeNode _node) {
        return new TreePath(model.getPathToRoot(_node));
    }
    @Override
    protected JComponent getComponent() {
        return tree;
    }

    private void setSelectionModel() {
        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    public void add(AbstractMutableTreeNode _mutableTreeNode) {
        selected.add(_mutableTreeNode);
    }
}
