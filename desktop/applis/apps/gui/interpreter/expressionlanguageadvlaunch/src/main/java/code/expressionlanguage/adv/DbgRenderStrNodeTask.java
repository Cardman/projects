package code.expressionlanguage.adv;

import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.initialize.AbstractLightProgramInfos;

public final class DbgRenderStrNodeTask implements Runnable {
    private final RenderPointInfosPreference renderPointPairs;
    private final AbsTreeGui treeGui;
    private final AbstractMutableTreeNodeCore<String> trNode;
    private final DbgNodeStruct node;
    private final AbstractLightProgramInfos compoFactory;

    public DbgRenderStrNodeTask(RenderPointInfosPreference _list, AbsTreeGui _treeGui, AbstractMutableTreeNodeCore<String> _t, DbgNodeStruct _n, AbstractLightProgramInfos _c) {
        this.renderPointPairs = _list;
        this.treeGui = _treeGui;
        this.trNode = _t;
        this.node = _n;
        this.compoFactory = _c;
    }

    @Override
    public void run() {
        TreeNodeRenderUtil.renderNode(renderPointPairs,treeGui,trNode, node,compoFactory);
    }
}
