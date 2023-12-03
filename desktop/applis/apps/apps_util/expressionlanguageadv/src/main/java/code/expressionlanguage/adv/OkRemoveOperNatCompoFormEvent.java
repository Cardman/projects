package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkRemoveOperNatCompoFormEvent implements AbsActionListener {
    private final FrameOperNatCompoFormContent frameOperNatFormContent;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public OkRemoveOperNatCompoFormEvent(FrameOperNatCompoFormContent _f, FramePoints _p, ResultContext _res) {
        this.frameOperNatFormContent = _f;
        this.framePoints = _p;
        currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.getContext().operNatList().remove(frameOperNatFormContent.getSelectedOperNat());
        frameOperNatFormContent.setSelectedOperNat(null);
        framePoints.guiContentBuildClear();
        framePoints.refreshOperNatCompo(currentResult);
        framePoints.getCommonFrame().pack();
    }

}
