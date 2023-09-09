package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.MutableTreeNodeCoreUtil;
import code.gui.events.AbsActionListener;

public final class TreeBreakPointBlockPairAddEvent implements AbsActionListener {
    private final FramePoints frame;
    private final FramePointsTree frameKeys;
    private final ResultContext resultContext;

    public TreeBreakPointBlockPairAddEvent(FramePoints _f, FramePointsTree _k, ResultContext _r) {
        this.frame = _f;
        this.frameKeys = _k;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        AbstractMutableTreeNodeCore ch_ = frameKeys.getTree().selectEvt();
        int index_ = MutableTreeNodeCoreUtil.getNullableIndex(ch_);
        if (FramePointsTree.SORT_WP == index_ || FramePointsTree.SORT_WP_ANNOT == index_) {
            frame.guiContentBuild((WatchPointBlockPair)null,resultContext);
        } else if (FramePointsTree.SORT_EP == index_) {
            frame.guiContentBuild((ExcPointBlockPair) null,resultContext);
        } else if (FramePointsTree.SORT_MP == index_) {
            frame.guiContentBuild((MethodPointBlockPair) null,resultContext);
        } else if (FramePointsTree.SORT_SP == index_) {
            frame.guiContentBuild((StdMethodPointBlockPair) null,resultContext);
        } else {
            frame.guiContentBuild((BreakPointBlockPair) null,resultContext);
        }
    }
}
