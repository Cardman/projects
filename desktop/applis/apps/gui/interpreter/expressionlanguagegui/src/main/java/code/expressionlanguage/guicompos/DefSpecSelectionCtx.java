package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.SpecSelectionCtx;

public final class DefSpecSelectionCtx implements SpecSelectionCtx {
    private final CommonExecutionInfos executionInfos;

    public DefSpecSelectionCtx(CommonExecutionInfos _executionInfos) {
        executionInfos = _executionInfos;
    }

    @Override
    public String convertStr(Struct _struct) {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, executionInfos);
        RunnableStruct.setupThread(r_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, r_);
        String value_ = ProcessMethod.convertStr(_struct, r_, stackCall_);
        r_.getCustInit().prExc(r_, stackCall_);
        return value_;
    }
}
