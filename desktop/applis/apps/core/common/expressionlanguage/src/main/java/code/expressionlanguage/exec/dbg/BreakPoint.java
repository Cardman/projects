package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class BreakPoint {
    private boolean enabled;
    private boolean enabledChgtType;
    private boolean instanceType = true;
    private boolean staticType;
    private final BreakPointCondition resultStd;
    private final BreakPointCondition resultStatic;
    private final BreakPointCondition resultInstance;
    public BreakPoint(AbstractInterceptorStdCaller _i){
        resultStd = new BreakPointCondition(_i);
        resultStatic = new BreakPointCondition(_i);
        resultInstance = new BreakPointCondition(_i);
    }

    public void resetCount() {
        resultStd.getEnabled().set(true);
        resultStd.setCount(0);
        resultStatic.getEnabled().set(true);
        resultStatic.setCount(0);
        resultInstance.getEnabled().set(true);
        resultInstance.setCount(0);
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isEnabledChgtType() {
        return enabledChgtType;
    }

    public void setEnabledChgtType(boolean _e) {
        this.enabledChgtType = _e;
    }

    public boolean isInstanceType() {
        return instanceType;
    }

    public void setInstanceType(boolean _i) {
        this.instanceType = _i;
    }

    public boolean isStaticType() {
        return staticType;
    }

    public void setStaticType(boolean _s) {
        this.staticType = _s;
    }

    public BreakPointCondition getResultStd() {
        return resultStd;
    }

    public BreakPointCondition getResultStatic() {
        return resultStatic;
    }

    public BreakPointCondition getResultInstance() {
        return resultInstance;
    }

}