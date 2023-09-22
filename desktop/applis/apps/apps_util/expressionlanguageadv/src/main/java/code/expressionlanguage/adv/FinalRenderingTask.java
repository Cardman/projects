package code.expressionlanguage.adv;

import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;

public final class FinalRenderingTask implements Runnable {
    private final AbsTreeGui treeGui;
    private final AbstractMutableTreeNodeCore<String> node;
    private final String render;

    public FinalRenderingTask(AbsTreeGui _tree, AbstractMutableTreeNodeCore<String> _n, String _r) {
        this.treeGui = _tree;
        this.node = _n;
        this.render = _r;
    }

    @Override
    public void run() {
        treeGui.info(node,render);
    }
}
