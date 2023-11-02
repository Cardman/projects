package code.vi.prot.impl.gui;

import code.gui.*;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

import javax.swing.*;
import javax.swing.tree.*;

public final class TreeGui extends CustComponent implements AbsTreeGui {
    private final JTree tree;
    private final DefaultTreeModel model;
    private final AbstractMutableTreeNodeCore<String> root;
    private final IdMap<AbsShortListTree,DefTreeSelectionListener> list = new IdMap<AbsShortListTree, DefTreeSelectionListener>();

    public TreeGui(AbstractMutableTreeNodeCore<String> _t, int _select) {
        root = _t;
        model = new DefaultTreeModel(convert(_t));
        tree = new JTree(model);
        DefaultTreeSelectionModel sel_ = new DefaultTreeSelectionModel();
        tree.setSelectionModel(sel_);
        sel_.setSelectionMode(_select);
    }

    public static MutableTreeNode convert(AbstractMutableTreeNodeCore<String> _t) {
        return ((DefMutableTreeNode)_t).node();
    }

    @Override
    public AbstractMutableTreeNodeCore<String> getRoot() {
        return root;
    }

    public boolean isRootVisible() {
        return tree.isRootVisible();
    }

    public void setRootVisible(boolean _rootVisible) {
        tree.setRootVisible(_rootVisible);
    }

    public AbstractMutableTreeNodeCore<String> selectEvt() {
        TreePath selectionPath_ = getSelectionPath();
        return selectedEvt(selectionPath_);
    }

    @Override
    public AbstractMutableTreeNodeCore<String> realEvt() {
        TreePath selectionPath_ = getSelectionPath();
        try {
            return DefMutableTreeNode.build((DefaultMutableTreeNode) selectionPath_.getLastPathComponent());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbsTreePath selectedPath() {
        TreePath selectionPath_ = getSelectionPath();
        try {
            return new DefTreePath(DefMutableTreeNode.build((DefaultMutableTreeNode) selectionPath_.getLastPathComponent()),selectionPath_);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AbstractMutableTreeNodeCore<String> translate(AbsTreePath _tr) {
        return TreeGui.selected(root,((DefTreePath)_tr).getReal());
    }

    @Override
    public AbsTreePath translate(AbstractMutableTreeNodeCore<String> _tr) {
        return new DefTreePath(_tr, getTreePath(convert(_tr)));
    }

    public static DefMutableTreeNode selected(AbstractMutableTreeNodeCore<String> _root,TreePath _path) {
        try {
            DefMutableTreeNode res_ = DefMutableTreeNode.build((DefaultMutableTreeNode) _path.getLastPathComponent());
            return (DefMutableTreeNode) _root.getElt(res_.getIndexes());
        } catch (Exception e) {
            return null;
        }
    }
    public AbstractMutableTreeNodeCore<String> selectedEvt(TreePath _path) {
        return selected(root,_path);
    }

    @Override
    public AbsTreePaths selectedPaths() {
        try {
            TreePath[] sel_ = tree.getSelectionPaths();
            return new DefTreePaths(sel_, MutableTreeNodeUtil.list(root,new DefTreePaths(sel_,new CustList<AbstractMutableTreeNodeCore<String>>())));
        } catch (Exception e) {
            return new DefTreePaths(new TreePath[0],new CustList<AbstractMutableTreeNodeCore<String>>());
        }
    }

    @Override
    public void selectedPaths(AbsTreePaths _p) {
        tree.setSelectionPaths(((DefTreePaths)_p).getNodes());
    }

    private TreePath getSelectionPath() {
        return tree.getSelectionPath();
    }

    public void select(AbstractMutableTreeNodeCore<String> _node) {
        tree.setSelectionPath(getTreePath(convert(_node)));
    }

    @Override
    public void addTreeSelectionListener(AbsShortListTree _sel) {
        DefTreeSelectionListener value_ = new DefTreeSelectionListener(_sel,root);
        tree.addTreeSelectionListener(value_);
        list.addEntry(_sel,value_);
    }

    @Override
    public void addTreeSelectionListenerMap(AbsShortListTree _sel) {
        DefTreeSelectionListener value_ = new DefTreeSelectionListener(_sel,root);
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
    public int removeTreeSelectionListenerMap(AbsShortListTree _sel) {
        int index_ = list.indexOfEntry(_sel);
        list.removeKey(_sel);
        return index_;
    }

    @Override
    public CustList<AbsShortListTree> getTreeSelectionListeners() {
        return list.getKeys();
    }

    @Override
    public void reloadRoot() {
        tree.repaint();
        CustList<AbsShortListTree> ls_ = GuiBaseUtil.removeTreeSelectionListeners(this);
        model.reload();
        GuiBaseUtil.addTreeSelectionListeners(this,ls_);
    }

    @Override
    public void reload(AbstractMutableTreeNodeCore<String> _node) {
        tree.repaint();
        CustList<AbsShortListTree> ls_ = GuiBaseUtil.removeTreeSelectionListeners(this);
        model.reload(((DefMutableTreeNode)_node).node());
        GuiBaseUtil.addTreeSelectionListeners(this,ls_);
    }

    @Override
    public void info(AbstractMutableTreeNodeCore<String> _node, String _v) {
        _node.info(StringUtil.nullToEmpty(_v));
        model.valueForPathChanged(getTreePath(((DefMutableTreeNode)_node).node()), StringUtil.nullToEmpty(_v));
    }

    private TreePath getTreePath(TreeNode _node) {
        return new TreePath(model.getPathToRoot(_node));
    }

    @Override
    public JComponent getNatComponent() {
        return tree;
    }

}
