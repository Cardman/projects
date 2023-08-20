package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;
import code.util.core.BoolVal;

public final class CurrentStopPt {
    private final BreakPointCondition breakPointCondition;
    private final ResultContextLambda result;
    private final BoolVal number;

    public CurrentStopPt(BreakPointCondition _bpc, ResultContextLambda _r, BoolVal _n) {
        this.breakPointCondition = _bpc;
        this.result = _r;
        this.number = _n;
    }

    public BreakPointCondition getBreakPointCondition() {
        return breakPointCondition;
    }

    public ResultContextLambda getResult() {
        return result;
    }

    public BoolVal getNumber() {
        return number;
    }
}
