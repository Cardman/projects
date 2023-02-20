package code.vi.prot.impl.gui;

import code.gui.*;
import code.util.CustList;
import code.util.IdMap;

import javax.swing.*;
import javax.swing.tree.*;

public final class TreeGui extends CustComponent implements AbsTreeGui {
    private final JTree tree;
    private final DefaultTreeModel model;
    private final DefaultTreeSelectionModel selectionModel;
    private final AbstractMutableTreeNode root;
    private final IdMap<AbsShortListTree,DefTreeSelectionListener> list = new IdMap<AbsShortListTree, DefTreeSelectionListener>();

    public TreeGui(AbstractMutableTreeNode _t) {
        root = _t;
        model = new DefaultTreeModel(convert(_t));
        tree = new JTree(model);
        selectionModel = new DefaultTreeSelectionModel();
        tree.setSelectionModel(selectionModel);
        setSelectionModel();
    }

    public static MutableTreeNode convert(AbstractMutableTreeNodeCore _t) {
        return ((DefMutableTreeNode)_t).node();
    }

    @Override
    public AbstractMutableTreeNode getRoot() {
        return root;
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean _rootVisible) {
        tree.setRootVisible(_rootVisible);
    }

    public AbstractMutableTreeNode selectEvt() {
        TreePath selectionPath_ = getSelectionPath();
        return selectedEvt(selectionPath_);
    }
    public static DefMutableTreeNode selected(AbstractMutableTreeNode _root,TreePath _path) {
        try {
            DefMutableTreeNode res_ = DefMutableTreeNode.build((DefaultMutableTreeNode) _path.getLastPathComponent());
            return (DefMutableTreeNode) MutableTreeNodeCoreUtil.getElt(_root,MutableTreeNodeUtil.getIndexes(res_));
        } catch (Exception e) {
            return null;
        }
    }
    public AbstractMutableTreeNode selectedEvt(TreePath _path) {
        return selected(root,_path);
    }

    private TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public void select(AbstractMutableTreeNodeCore _node) {
        tree.setSelectionPath(getTreePath(convert(_node)));
    }

    @Override
    public void addTreeSelectionListener(AbsShortListTree _sel) {
        DefTreeSelectionListener value_ = new DefTreeSelectionListener(_sel,root);
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
    public void reload(AbstractMutableTreeNodeCore _node) {
        model.reload(convert(_node));
    }

    private TreePath getTreePath(TreeNode _node) {
        return new TreePath(model.getPathToRoot(_node));
    }

    @Override
    public JComponent getNatComponent() {
        return tree;
    }

    private void setSelectionModel() {
        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

}
