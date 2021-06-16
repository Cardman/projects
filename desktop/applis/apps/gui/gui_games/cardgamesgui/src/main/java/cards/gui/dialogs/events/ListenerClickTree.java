package cards.gui.dialogs.events;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;

import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.NodeHelp;
import code.gui.AbsTreeGui;
import code.gui.document.RenderedPage;
import code.util.CustList;
import code.util.Ints;
import code.util.core.IndexConstants;

public class ListenerClickTree implements TreeSelectionListener {

    private NodeHelp node;

    private RenderedPage editor;

    private AbsTreeGui tree;

    public ListenerClickTree(NodeHelp _node,
            RenderedPage _editor, AbsTreeGui _tree) {
        node = _node;
        editor = _editor;
        tree = _tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent _e) {
        if (tree.selectEvt() == null) {
            return;
        }
        CustList<TreeNode> chemin_;
        chemin_ = new CustList<TreeNode>();
        for (TreeNode o2_ : tree.getSelected().getPath()) {
            chemin_.add(o2_);
        }
        TreeNode noeudParent_;
        TreeNode noeud_;
        Ints indices_;
        if (!chemin_.isEmpty()) {
            indices_ = new Ints();
            int pathLength_ = chemin_.size();
            for (int indice_ = IndexConstants.SECOND_INDEX; indice_ < pathLength_; indice_++) {
                noeudParent_ = chemin_.getPrev(indice_);
                noeud_ = chemin_.get(indice_);
                indices_.add(noeudParent_.getIndex(noeud_));
            }
        } else {
            indices_ = new Ints();
        }
        ElementHelp element_ = node.element(indices_).getElementLocal();
        editor.initialize(element_.getNavigation(),element_.getMetaDocument());
    }
}
