package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ExcPoint {
    private boolean enabled;
    private ConditionReturn conditionReturn;
    private final BreakPointCondition result;
    public ExcPoint(AbstractInterceptorStdCaller _i){
        result = new BreakPointCondition(_i);
    }

    public void resetCount() {
        result.getEnabled().set(true);
        result.setCount(0);
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public ConditionReturn getConditionReturn() {
        return conditionReturn;
    }

    public void setConditionReturn(ConditionReturn _c) {
        this.conditionReturn = _c;
    }

    public BreakPointCondition getResult() {
        return result;
    }
}
