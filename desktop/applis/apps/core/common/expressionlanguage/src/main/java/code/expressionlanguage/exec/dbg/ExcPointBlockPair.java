package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ExcPointBlockPair implements AbsPairPoint {
    private final ExcPointBlockKey ep;
    private final ExcPoint value;

    public ExcPointBlockPair(boolean _ex, String _cl, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.ep = new ExcPointBlockKey(_ex, _cl);
        this.value = new ExcPoint(_v, this, ep);
        this.value.setEnabled(_enabled);
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyzeExc(_d,this,_r,_a,_g);
    }

    @Override
    public String keyStr() {
        return getEp().keyStr();
    }

    public ExcPointBlockKey getEp() {
        return ep;
    }

    public ExcPoint getValue() {
        return value;
    }
}
