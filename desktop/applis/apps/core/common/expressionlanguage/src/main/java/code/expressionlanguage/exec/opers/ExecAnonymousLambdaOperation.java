package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.ExecLambdaAnoContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecAnonymousLambdaOperation extends ExecAbstractLambdaOperation {
    private final ExecLambdaAnoContent lambdaAnoContent;
    private final ExecAnonymousFunctionBlock functionBlock;
    private final ExecAnonymousFunctionBlock function;
    public ExecAnonymousLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecAnonymousFunctionBlock _r, ExecLambdaAnoContent _lambdaAnoContent) {
        super(_opCont, _lamCont);
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
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), lambdaAnoContent.getMethod().getConstraints()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType, MethodId _constraints) {
        return ExecMethodLambdaOperation.newAnonymousLambda(_previous, _conf, _foundClass, _returnFieldType, isShiftArgument(), isSafeInstance(),
                getResultClass().getSingleNameOrEmpty(), _conf.getLastPage(), getFileName(),functionBlock,function, lambdaAnoContent.getDeclaring(), _constraints);
    }
}
