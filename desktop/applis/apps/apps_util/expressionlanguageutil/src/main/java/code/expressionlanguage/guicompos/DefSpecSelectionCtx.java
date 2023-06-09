package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.SpecSelectionCtx;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public final class DefSpecSelectionCtx implements SpecSelectionCtx {
    private final AbstractAtomicBoolean interrupt;
    private final CommonExecutionInfos executionInfos;
    private final StringList args;

    public DefSpecSelectionCtx(AbstractAtomicBoolean _stop, CommonExecutionInfos _executionInfos, StringList _a) {
        interrupt = _stop;
        executionInfos = _executionInfos;
        args = _a;
    }

    @Override
    public String convertStr(Struct _struct) {
        GuiContextEl r_ = new GuiContextEl(getInterrupt(),null, getExecutionInfos(), getArgs());
        RunnableStruct.setupThread(r_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, r_);
        String value_ = ProcessMethod.convertStr(_struct, r_, stackCall_);
        r_.getCustInit().prExc(r_, stackCall_);
        return value_;
    }

    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public StringList getArgs() {
        return args;
    }

    public AbstractAtomicBoolean getInterrupt() {
        return interrupt;
    }
}
