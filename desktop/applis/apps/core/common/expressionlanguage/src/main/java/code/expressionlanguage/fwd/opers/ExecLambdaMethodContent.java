package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class ExecLambdaMethodContent {
    private final ClassMethodId method;
    private final boolean polymorph;
    private final boolean abstractMethod;
    private final boolean directCast;
    private final boolean expCast;
    private final boolean clonedMethod;
    private final ExecTypeFunction pair;

    public ExecLambdaMethodContent(ClassMethodId _method, AnaLambdaMethodContent _meth, ExecTypeFunction _pair) {
        method = _method;
        polymorph = _meth.isPolymorph();
        abstractMethod = _meth.isAbstractMethod();
        directCast = _meth.isDirectCast();
        expCast = _meth.isExpCast();
        clonedMethod = _meth.isClonedMethod();
        pair = _pair;
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isExpCast() {
        return expCast;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public boolean isClonedMethod() {
        return clonedMethod;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }
}
