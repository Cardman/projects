package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ForwardInfos;

public final class ExecLambdaConstructorContent {
    private final ConstructorId realId;
    private final ExecNamedFunctionBlock functionBlock;
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock function;
    public ExecLambdaConstructorContent(ConstructorId _realId, AnaLambdaMemberNumberContent _cont, AnalyzedPageEl _page) {
        realId = _realId;
        functionBlock = ForwardInfos.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _page);
        rootBlock = ForwardInfos.fetchType(_cont.getRootNumber(), _page);
        function = ForwardInfos.fetchFunction(_cont.getRootNumber(), _cont.getMemberNumber(), _cont.getOperatorNumber(), _page);
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
