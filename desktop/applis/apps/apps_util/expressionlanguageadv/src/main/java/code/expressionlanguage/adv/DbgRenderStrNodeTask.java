package code.expressionlanguage.adv;

import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractThreadFactory;

public final class DbgRenderStrNodeTask implements Runnable {
    private final AbsTreeGui treeGui;
    private final AbstractMutableTreeNodeCore<String> trNode;
    private final DbgNodeStruct node;
    private final AbsCompoFactory compoFactory;
    private final AbstractThreadFactory threadFactory;

    public DbgRenderStrNodeTask(AbsTreeGui _treeGui,AbstractMutableTreeNodeCore<String> _t, DbgNodeStruct _n, AbsCompoFactory _c, AbstractThreadFactory _th) {
        this.treeGui = _treeGui;
        this.trNode = _t;
        this.node = _n;
        this.compoFactory = _c;
        this.threadFactory = _th;
    }

    @Override
    public void run() {
        TreeNodeRenderUtil.renderNode(treeGui,trNode, node,compoFactory, threadFactory);
    }
}
