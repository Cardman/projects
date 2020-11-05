package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecLambdaConstructorContent {
    private final ConstructorId realId;
    private final ExecNamedFunctionBlock functionBlock;
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock function;
    public ExecLambdaConstructorContent(ConstructorId _realId, AnaLambdaMemberNumberContent _cont, Forwards _forwards) {
        realId = _realId;
        functionBlock = FetchMemberUtil.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _forwards);
        rootBlock = FetchMemberUtil.fetchType(_cont.getRootNumber(), _forwards);
        function = FetchMemberUtil.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _forwards);
    }

    public ConstructorId getRealId() {
        return realId;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecNamedFunctionBlock getFunction() {
        return function;
    }

    public ExecNamedFunctionBlock getFunctionBlock() {
        return functionBlock;
    }
}
