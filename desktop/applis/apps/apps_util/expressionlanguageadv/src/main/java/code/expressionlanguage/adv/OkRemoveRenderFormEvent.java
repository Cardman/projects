package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class OkRemoveRenderFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final FrameRenderFormContent frameExcFormContent;
    private final FramePoints framePoints;

    public OkRemoveRenderFormEvent(AbsDebuggerGui _d, FrameRenderFormContent _f, FramePoints _p) {
        this.window = _d;
        this.frameExcFormContent = _f;
        this.framePoints = _p;
    }

    @Override
    public void action() {
        int index_ = -1;
        CustList<RenderPointPair> ls_ = window.getRenderList();
        int s_ = ls_.size();
        for (int i = 0; i < s_; i++) {
            if (ls_.get(i).getExcPointBlockPair().getEp().match(frameExcFormContent.getSelectedExc().getExcPointBlockPair().getEp())) {
                index_ = i;
                break;
            }
        }
        if (index_ < 0) {
            return;
        }
        ls_.remove(index_);
        frameExcFormContent.setSelectedExc(null);
        framePoints.refreshRender(ls_);
        framePoints.getCommonFrame().pack();
    }

}
