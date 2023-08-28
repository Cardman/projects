package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractAtomicInteger;
import code.util.CustList;

public final class BreakPointCondition {
    private final AbsKeyPoint keyPoint;
    private final int kindPoint;
    private final int phasePoint;
    private final AbstractAtomicBoolean disableWhenHit;
    private final AbstractAtomicBoolean enabled;
    private final AbstractAtomicBoolean hit;
    private final AbsCollection<BreakPointCondition> others;
    private ResultContextLambda result;
    private String resultStr = "";
    private final AbstractAtomicInteger countModulo;
    private final AbstractAtomicInteger count;
    private final AbsCollection<AbsCallContraints> exclude;
    private final AbsCollection<AbsCallContraints> include;
    public BreakPointCondition(AbstractInterceptorStdCaller _i, AbsKeyPoint _key, int _kind, int _phase) {
        this.keyPoint = _key;
        this.kindPoint = _kind;
        this.phasePoint = _phase;
        exclude = _i.newExecFileBlockTraceIndexCollection();
        include = _i.newExecFileBlockTraceIndexCollection();
        others = _i.newBreakPointConditionCollection();
        disableWhenHit = _i.newAtBool();
        enabled = _i.newAtBool();
        enabled.set(true);
        hit = _i.newAtBool();
        countModulo = _i.newAtInt();
        count = _i.newAtInt();
    }

    public void setAll(CustList<BreakPointCondition> _elts) {
        setAll(others,_elts);
    }
    public static void setAll(AbsCollection<BreakPointCondition> _collection, CustList<BreakPointCondition> _elts) {
        AbsCollection<BreakPointCondition> conv_ = _collection.intercept().newBreakPointConditionCollection();
        int s_ = _elts.size();
        for (int i = 0; i < s_; i++) {
            conv_.add(_elts.get(i));
        }
        _collection.setAll(conv_);
    }

    public String keyStr() {
        return pad(kindPoint)+keyPoint.keyStr()+"\\"+phasePoint;
    }
    public static String pad(int _i) {
        if (_i < 10) {
            return "0"+_i;
        }
        return Integer.toString(_i);
    }

    public void resetCount() {
        getEnabled().set(true);
        getHit().set(false);
        getCount().set(0);
    }
    public int incr() {
        return getCount().incrementAndGet();
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

    public AbstractAtomicInteger getCountModulo() {
        return countModulo;
    }

    public AbstractAtomicInteger getCount() {
        return count;
    }

    public AbstractAtomicBoolean getDisableWhenHit() {
        return disableWhenHit;
    }

    public AbsCollection<BreakPointCondition> getOthers() {
        return others;
    }

    public AbstractAtomicBoolean getEnabled() {
        return enabled;
    }

    public AbstractAtomicBoolean getHit() {
        return hit;
    }
}
