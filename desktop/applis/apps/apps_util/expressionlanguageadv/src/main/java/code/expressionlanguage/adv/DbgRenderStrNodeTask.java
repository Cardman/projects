package code.expressionlanguage.adv;

import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractThreadFactory;

public final class DbgRenderStrNodeTask implements Runnable {
    private final RenderPointPair renderPointPairs;
    private final AbsTreeGui treeGui;
    private final AbstractMutableTreeNodeCore<String> trNode;
    private final DbgNodeStruct node;
    private final AbsCompoFactory compoFactory;
    private final AbstractThreadFactory threadFactory;

    public DbgRenderStrNodeTask(RenderPointPair _list, AbsTreeGui _treeGui, AbstractMutableTreeNodeCore<String> _t, DbgNodeStruct _n, AbsCompoFactory _c, AbstractThreadFactory _th) {
        this.renderPointPairs = _list;
        this.treeGui = _treeGui;
        this.trNode = _t;
        this.node = _n;
        this.compoFactory = _c;
        this.threadFactory = _th;
    }

    @Override
    public void run() {
        TreeNodeRenderUtil.renderNode(renderPointPairs,treeGui,trNode, node,compoFactory, threadFactory);
    }
}
