package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractAtomicRef;
import code.util.CustList;

public final class BreakPointCondition {
    private final AbsKeyPoint keyPoint;
    private final int kindPoint;
    private final int phasePoint;
    private final AbstractAtomicBoolean disableWhenHit;
    private final AbstractAtomicBoolean enabled;
    private final AbstractAtomicBoolean hit;
    private final AbsCollection<BreakPointCondition> others;
    private final AbstractAtomicRef<StrResultContextLambda> lda;
    private final AbstractAtomicInteger countModulo;
    private final AbstractAtomicInteger count;
    private final AbstractAtomicInteger pref;
    private final AbsCollection<AbsCallContraints> exclude;
    private final AbsCollection<AbsCallContraints> include;
    public BreakPointCondition(AbstractInterceptorStdCaller _i, AbsKeyPoint _key, int _kind, int _phase) {
        this.keyPoint = _key;
        this.kindPoint = _kind;
        this.phasePoint = _phase;
        exclude = _i.newExecFileBlockTraceIndexCollection();
        include = _i.newExecFileBlockTraceIndexCollection();
        others = _i.newBreakPointConditionCollection();
        lda = _i.newAtLda();
        disableWhenHit = _i.newAtBool();
        enabled = _i.newAtBool();
        enabled.set(true);
        hit = _i.newAtBool();
        countModulo = _i.newAtInt();
        count = _i.newAtInt();
        pref = _i.newAtInt();
    }

    public AbstractAtomicInteger getPref() {
        return pref;
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
    public void result(ResultContextLambda _p, String _str) {
        StrResultContextLambda s_ = new StrResultContextLambda();
        s_.result(_p, _str);
        lda.set(s_);
    }

    public ResultContextLambda getResult() {
        return lda.get().getResult();
    }

    public String getResultStr() {
        return lda.get().getResultStr();
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
