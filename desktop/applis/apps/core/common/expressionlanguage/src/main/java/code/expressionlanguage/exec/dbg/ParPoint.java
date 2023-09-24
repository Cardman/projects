package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ParPoint {
    public static final int PP =6;
    public static final int BPC_GET = 0;
    private boolean enabled;
    private boolean get;
    private final BreakPointCondition resultGet;
    public ParPoint(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key){
        resultGet = new BreakPointCondition(_i, _superKey, _key, PP, BPC_GET);
        setGet(true);
    }

    public void resetCount() {
        resultGet.resetCount();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isGet() {
        return get;
    }

    public void setGet(boolean _t) {
        this.get = _t;
    }

    public BreakPointCondition getResultGet() {
        return resultGet;
    }


}
