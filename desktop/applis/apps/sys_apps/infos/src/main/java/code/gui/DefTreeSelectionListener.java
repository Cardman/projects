package code.gui;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public final class DefTreeSelectionListener implements TreeSelectionListener {
    private final AbsShortListTree shortListTree;

    public DefTreeSelectionListener(AbsShortListTree _shortListTree) {
        this.shortListTree = _shortListTree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent _e) {
        shortListTree.valueChanged(new DefMutableTreeNode(TreeGui.selected(_e.getPath())));
    }
}
