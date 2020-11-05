package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecLambdaMethodContent {
    private final ClassMethodId method;
    private final boolean polymorph;
    private final boolean abstractMethod;
    private final boolean directCast;
    private final boolean expCast;
    private final ExecNamedFunctionBlock functionBlock;
    private final ExecNamedFunctionBlock function;
    private final ExecRootBlock declaring;

    public ExecLambdaMethodContent(ClassMethodId _method, AnaLambdaMethodContent _meth, AnaLambdaMemberNumberContent _cont, Forwards _forwards) {
        method = _method;
        polymorph = _meth.isPolymorph();
        abstractMethod = _meth.isAbstractMethod();
        directCast = _meth.isDirectCast();
        expCast = _meth.isExpCast();
        functionBlock = FetchMemberUtil.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _forwards);
        function = FetchMemberUtil.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _forwards);
        declaring = FetchMemberUtil.fetchType(_cont.getRootNumber(), _forwards);
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

    public ExecNamedFunctionBlock getFunctionBlock() {
        return functionBlock;
    }

    public ExecNamedFunctionBlock getFunction() {
        return function;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }
}
