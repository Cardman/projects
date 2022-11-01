package cards.gui.dialogs.events;

import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.NodeHelp;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.util.Ints;

public class ListenerClickTree implements AbsShortListTree {

    private final NodeHelp node;

    private final RenderedPage editor;

    private final AbsTreeGui tree;

    public ListenerClickTree(NodeHelp _node,
            RenderedPage _editor, AbsTreeGui _tree) {
        node = _node;
        editor = _editor;
        tree = _tree;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        AbstractMutableTreeNode sel_ = tree.selectEvt();
        if (sel_ == null) {
            return;
        }
        Ints indices_ = MutableTreeNodeUtil.getIndexes(sel_);
        ElementHelp element_ = node.element(indices_).getElementLocal();
        editor.initialize(element_.getNavigation(),element_.getMetaDocument());
    }
}
