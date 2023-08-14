package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.threads.AbstractAtomicBoolean;

public final class BreakPointCondition {
    private final AbstractAtomicBoolean disableWhenHit;
    private final AbstractAtomicBoolean enabled;
    private ResultContextLambda result;
    private String resultStr = "";
    private int countModulo;
    private int count;
    private final AbsCollection<AbsCallContraints> exclude;
    private final AbsCollection<AbsCallContraints> include;
    public BreakPointCondition(AbstractInterceptorStdCaller _i) {
        exclude = _i.newExecFileBlockTraceIndexCollection();
        include = _i.newExecFileBlockTraceIndexCollection();
        disableWhenHit = _i.newAtBool();
        enabled = _i.newAtBool();
        enabled.set(true);
    }

    public AbsCollection<AbsCallContraints> getExclude() {
        return exclude;
    }

    public AbsCollection<AbsCallContraints> getInclude() {
        return include;
    }

    public ResultContextLambda getResult() {
        return result;
    }

    public void setResult(ResultContextLambda _p) {
        this.result = _p;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String _p) {
        this.resultStr = _p;
    }

    public int getCountModulo() {
        return countModulo;
    }

    public void setCountModulo(int _p) {
        this.countModulo = _p;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int _p) {
        this.count = _p;
    }

    public AbstractAtomicBoolean getDisableWhenHit() {
        return disableWhenHit;
    }

    public AbstractAtomicBoolean getEnabled() {
        return enabled;
    }
}
