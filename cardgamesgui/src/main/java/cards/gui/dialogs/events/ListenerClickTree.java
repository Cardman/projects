package cards.gui.dialogs.events;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.NodeHelp;
import cards.gui.dialogs.help.beans.GeneralHelpLgNames;
import code.formathtml.util.BeanLgNames;
import code.gui.TreeGui;
import code.gui.document.RenderedPage;
import code.util.CustList;
import code.util.Ints;

public class ListenerClickTree implements TreeSelectionListener {

    private NodeHelp node;

    private RenderedPage editor;

    private TreeGui tree;

    public ListenerClickTree(NodeHelp _node,
            RenderedPage _editor, TreeGui _tree) {
        node = _node;
        editor = _editor;
        tree = _tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent _e) {
        TreePath sel_ = tree.getSelectionPath();
        if (sel_ == null) {
            return;
        }
        CustList<DefaultMutableTreeNode> chemin_;
        chemin_ = new CustList<DefaultMutableTreeNode>();
        for (Object o2_ : sel_.getPath()) {
            chemin_.add((DefaultMutableTreeNode)o2_);
        }
        DefaultMutableTreeNode noeudParent_;
        DefaultMutableTreeNode noeud_;
        Ints indices_;
        if (!chemin_.isEmpty()) {
            indices_ = new Ints();
            int pathLength_ = chemin_.size();
            for (int indice_ = CustList.SECOND_INDEX; indice_ < pathLength_; indice_++) {
                noeudParent_ = chemin_.getPrev(indice_);
                noeud_ = chemin_.get(indice_);
                indices_.add(noeudParent_.getIndex(noeud_));
            }
        } else {
            indices_ = new Ints();
        }
        ElementHelp element_ = node.element(indices_).getElementLocal();
        BeanLgNames bean_ = new GeneralHelpLgNames();
        editor.reInitSession(element_.getFile(), bean_);
    }
}
