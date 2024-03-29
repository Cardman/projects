package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;

public final class StdMethodPointBlockPair implements AbsPairPoint {
    public static final int SMP=4;
    private final StdMethodPointBlockKey sm;
    private final MethodPoint value;

    public StdMethodPointBlockPair(StandardNamedFunction _i, StandardType _t, String _k, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.sm = new StdMethodPointBlockKey(_i, _t, _k);
        this.value = new MethodPoint(_v, this, sm,SMP);
        this.value.setEnabled(_enabled);
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyze(_d,this,_r,_a,_g);
    }

    @Override
    public String keyStr() {
        return getSm().keyStr();
    }

    public StdMethodPointBlockKey getSm() {
        return sm;
    }

    public MethodPoint getValue() {
        return value;
    }
}
