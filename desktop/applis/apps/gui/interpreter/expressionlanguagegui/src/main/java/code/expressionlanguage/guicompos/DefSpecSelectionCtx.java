package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.SpecSelectionCtx;

public final class DefSpecSelectionCtx implements SpecSelectionCtx {
    private final CommonExecutionInfos executionInfos;

    public DefSpecSelectionCtx(CommonExecutionInfos _executionInfos) {
        executionInfos = _executionInfos;
    }

    @Override
    public ContextEl ctx() {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, executionInfos);
        RunnableStruct.setupThread(r_);
        return r_;
    }
}
