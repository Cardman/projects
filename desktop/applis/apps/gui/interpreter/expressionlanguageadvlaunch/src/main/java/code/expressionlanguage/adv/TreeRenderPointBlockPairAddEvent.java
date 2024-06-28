package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class TreeRenderPointBlockPairAddEvent implements AbsActionListener {
    private final CustList<RenderPointPair> list;
    private final ResultContext resultContext;
    private final FramePoints frame;

    public TreeRenderPointBlockPairAddEvent(CustList<RenderPointPair> _bpc, ResultContext _res, FramePoints _f) {
        this.list = _bpc;
        this.resultContext = _res;
        this.frame = _f;
    }

    @Override
    public void action() {
        frame.guiContentBuild(list, null,resultContext);
    }
}
