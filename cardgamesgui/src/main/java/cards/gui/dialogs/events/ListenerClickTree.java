package cards.gui.dialogs.events;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import code.formathtml.DefaultInitialization;
import code.formathtml.util.BeanLgNames;
import code.gui.SessionEditorPane;
import code.util.CustList;
import code.util.Numbers;
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.NodeHelp;

public class ListenerClickTree implements TreeSelectionListener {

    private NodeHelp node;

    private SessionEditorPane editor;

    private JTree tree;

    public ListenerClickTree(NodeHelp _node,
            SessionEditorPane _editor, JTree _tree) {
        node = _node;
        editor = _editor;
        tree = _tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent _e) {
        Object information_ = tree.getLastSelectedPathComponent();
        if (information_ == null) {
            return;
        }
        CustList<DefaultMutableTreeNode> chemin_;
        chemin_ = new CustList<DefaultMutableTreeNode>();
        for (Object o2_ : _e.getPath().getPath()) {
            chemin_.add((DefaultMutableTreeNode)o2_);
        }
        DefaultMutableTreeNode noeudParent_;
        DefaultMutableTreeNode noeud_;
        Numbers<Integer> indices_;
        if (!chemin_.isEmpty()) {
            indices_ = new Numbers<Integer>();
            int pathLength_ = chemin_.size();
            for (int indice_ = CustList.SECOND_INDEX; indice_ < pathLength_; indice_++) {
                noeudParent_ = chemin_.getPrev(indice_);
                noeud_ = chemin_.get(indice_);
                indices_.add(noeudParent_.getIndex(noeud_));
            }
        } else {
            indices_ = new Numbers<Integer>();
        }
        ElementHelp element_ = node.element(indices_).getElementLocal();
        BeanLgNames bean_ = new BeanLgNames();
        DefaultInitialization.basicStandards(bean_);
        editor.reInitSession(element_.getFile(), bean_);
    }
}
