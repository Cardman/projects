package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaAnoContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecAnonymousLambdaOperation extends ExecAbstractLambdaOperation {
    private final ExecLambdaAnoContent lambdaAnoContent;
    private final ExecTypeFunction pair;

    public ExecAnonymousLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaAnoContent _lambdaAnoContent, ExecTypeFunction _pair) {
        super(_opCont, _lamCont);
        pair = _pair;
        lambdaAnoContent = _lambdaAnoContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = getCommonArgument(previous_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), lambdaAnoContent.getMethod().getConstraints(), _stackCall));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType, MethodId _constraints, StackCall _stackCall) {
        return ExecCustMethodLambdaOperation.newAnonymousLambda(_previous, _conf, _foundClass, _returnFieldType, isShiftArgument(), isSafeInstance(),
                getResultClass().getSingleNameOrEmpty(), _stackCall.getLastPage(), getFileName(), _constraints, pair, _stackCall);
    }
}
