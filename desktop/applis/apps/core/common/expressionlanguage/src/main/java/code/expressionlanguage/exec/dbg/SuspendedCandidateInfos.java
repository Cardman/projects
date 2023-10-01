package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.CheckedMethodInfos;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class SuspendedCandidateInfos {
    private final CheckedMethodInfos infos;
    private final BreakPointCondition breakPointCondition;
    private final AbstractPageEl pageEl;
    private final StopDbgEnum step;

    public SuspendedCandidateInfos(CheckedMethodInfos _i, BreakPointCondition _b, AbstractPageEl _p, StopDbgEnum _step) {
        this.infos = _i;
        this.breakPointCondition = _b;
        this.pageEl = _p;
        this.step = _step;
    }

    public StopDbgEnum getStep() {
        return step;
    }

    public CheckedMethodInfos getInfos() {
        return infos;
    }

    public BreakPointCondition getBreakPointCondition() {
        return breakPointCondition;
    }

    public AbstractPageEl getPageEl() {
        return pageEl;
    }
}
