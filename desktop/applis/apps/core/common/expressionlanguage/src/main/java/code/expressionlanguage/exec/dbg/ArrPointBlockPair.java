package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ArrPointBlockPair implements AbsPairPoint {
    private final ExcPointBlockKey ep;
    private final ArrPoint value;

    public ArrPointBlockPair(int _ex, String _cl, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.ep = new ExcPointBlockKey(_ex, _cl);
        this.value = new ArrPoint(_v, this, ep);
        this.value.setEnabled(_enabled);
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyzeArr(_d,this,_r,_a,_g,_p);
    }

    @Override
    public String keyStr() {
        return getEp().keyStr();
    }

    public ExcPointBlockKey getEp() {
        return ep;
    }

    public ArrPoint getValue() {
        return value;
    }
}
