package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecLambdaMethodContent {
    private final ClassMethodId method;
    private final boolean polymorph;
    private final boolean abstractMethod;
    private final boolean directCast;
    private final boolean expCast;
    private final boolean clonedMethod;
    private final ExecNamedFunctionBlock function;
    private final ExecRootBlock declaring;
    private final ExecTypeFunction pair;

    public ExecLambdaMethodContent(ClassMethodId _method, AnaLambdaMethodContent _meth, MemberId _id, Forwards _forwards) {
        method = _method;
        polymorph = _meth.isPolymorph();
        abstractMethod = _meth.isAbstractMethod();
        directCast = _meth.isDirectCast();
        expCast = _meth.isExpCast();
        clonedMethod = _meth.isClonedMethod();
        pair = FetchMemberUtil.fetchTypeFunction(_id, _forwards);
        function = FetchMemberUtil.fetchFunctionOp(_id, _forwards);
        declaring = FetchMemberUtil.fetchType(_id, _forwards);
    }

    public ExecTypeFunction getPair() {
        return pair;
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

    public ExecNamedFunctionBlock getFunction() {
        return function;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }
}
