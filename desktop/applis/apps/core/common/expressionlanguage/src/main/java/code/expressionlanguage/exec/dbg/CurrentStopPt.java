package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;

public final class CurrentStopPt {
    private final BreakPointCondition breakPointCondition;
    private final ResultContextLambda result;
    private final int number;

    public CurrentStopPt(BreakPointCondition _bpc, ResultContextLambda _r, int _n) {
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

    public int getNumber() {
        return number;
    }
}
