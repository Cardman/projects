package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsShortListTree;
import code.gui.AbstractMutableTreeNodeCore;
import code.util.CustList;
import code.util.Ints;

public final class TreeRenderPointBlockPairEvent implements AbsShortListTree {
    private final CustList<RenderPointPair> list;
    private final ResultContext resultContext;
    private final FramePoints frame;

    public TreeRenderPointBlockPairEvent(CustList<RenderPointPair> _bpc, FramePoints _f, ResultContext _res) {
        this.list = _bpc;
        this.resultContext = _res;
        this.frame = _f;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        Ints is_ = new Ints();
        TreeBreakPointBlockPairEvent.indexes(frame.getTree(),null,_node, is_);
        if (is_.size() < 2) {
            return;
        }
        frame.guiContentBuild(list,frame.getRenderList().getValue(is_.get(0)).get(is_.get(1)),resultContext);
    }

}
