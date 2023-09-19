package code.vi.prot.impl.gui;

import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public final class DefTreeSelectionListener implements TreeSelectionListener {
    private final AbsShortListTree shortListTree;
    private final AbstractMutableTreeNodeCore<String> root;

    public DefTreeSelectionListener(AbsShortListTree _shortListTree, AbstractMutableTreeNodeCore<String> _r) {
        this.shortListTree = _shortListTree;
        root = _r;
    }

    @Override
    public void valueChanged(TreeSelectionEvent _e) {
        shortListTree.valueChanged(TreeGui.selected(root,_e.getPath()));
    }
}
