package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecLambdaAnoContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecAnonymousLambdaOperation extends ExecAbstractLambdaOperation {
    private ExecLambdaAnoContent lambdaAnoContent;
    private ExecNamedFunctionBlock functionBlock;
    private ExecNamedFunctionBlock function;
    public ExecAnonymousLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
        super(_opCont, _lamCont);
    }

    public void setExecAnonymousLambdaOperation(ExecAnonymousFunctionBlock _r, ExecLambdaAnoContent _lambdaAnoContent) {
        lambdaAnoContent = _lambdaAnoContent;
        function = _r;
        functionBlock = _r;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), lambdaAnoContent.getMethod(), getReturnFieldType()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType) {
        return ExecMethodLambdaOperation.newLambda(_previous, _conf, foundClass, method, returnFieldType, 0, false, false, false, false, isShiftArgument(), isSafeInstance(),
                getResultClass().getSingleNameOrEmpty(), _conf.getLastPage(), getFileName(),functionBlock,function, lambdaAnoContent.getDeclaring());
    }
}
