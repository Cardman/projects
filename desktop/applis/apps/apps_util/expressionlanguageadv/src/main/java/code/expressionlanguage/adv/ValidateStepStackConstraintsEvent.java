package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExecFileBlockTraceIndex;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ValidateStepStackConstraintsEvent implements AbsActionListener {
    private final ResultContext resultContext;
    private final FramePoints framePoints;

    public ValidateStepStackConstraintsEvent(ResultContext _res, FramePoints _fr) {
        this.resultContext = _res;
        this.framePoints = _fr;
    }

    @Override
    public void action() {
        StackConstraintsForm form_ = framePoints.getStackConstraintsForm();
        ExecFileBlockTraceIndex.setAll(resultContext.getBreakPointsBlock().getExclude(),form_.getMustNotBe());
        ExecFileBlockTraceIndex.setAll(resultContext.getBreakPointsBlock().getInclude(),form_.getMustBe());
    }
}
