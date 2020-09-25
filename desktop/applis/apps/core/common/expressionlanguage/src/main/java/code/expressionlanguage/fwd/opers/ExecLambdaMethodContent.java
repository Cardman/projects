package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ForwardInfos;

public final class ExecLambdaMethodContent {
    private final ClassMethodId method;
    private final boolean polymorph;
    private final boolean abstractMethod;
    private final boolean directCast;
    private final boolean expCast;
    private final ExecAnnotableParametersBlock functionBlock;
    private final ExecNamedFunctionBlock function;
    private final ExecRootBlock declaring;

    public ExecLambdaMethodContent(ClassMethodId _method, AnaLambdaMethodContent _meth, AnaLambdaMemberNumberContent _cont, AnalyzedPageEl _page) {
        method = _method;
        polymorph = _meth.isPolymorph();
        abstractMethod = _meth.isAbstractMethod();
        directCast = _meth.isDirectCast();
        expCast = _meth.isExpCast();
        functionBlock = ForwardInfos.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _page);
        function = ForwardInfos.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _page);
        declaring = ForwardInfos.fetchType(_cont.getRootNumber(), _page);
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

    public ExecAnnotableParametersBlock getFunctionBlock() {
        return functionBlock;
    }

    public ExecNamedFunctionBlock getFunction() {
        return function;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }
}
