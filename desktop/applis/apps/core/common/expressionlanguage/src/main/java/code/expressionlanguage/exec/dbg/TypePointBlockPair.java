package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class TypePointBlockPair implements AbsPairPoint {
    private final BreakPointBlockKey bp;
    private final TypePoint value;

    public TypePointBlockPair(ExecFileBlock _file, int _nf, int _offset, AbstractInterceptorStdCaller _v, boolean _enabled) {
        bp = new BreakPointBlockKey(_file, _nf, _offset);
        this.value = new TypePoint(_v, this, bp);
        this.value.setEnabled(_enabled);
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyze(_d,this,_r,_a,_g,_p);
    }

    @Override
    public String keyStr() {
        return getBp().keyStr();
    }

    public BreakPointBlockKey getBp() {
        return bp;
    }

    public TypePoint getValue() {
        return value;
    }
}
