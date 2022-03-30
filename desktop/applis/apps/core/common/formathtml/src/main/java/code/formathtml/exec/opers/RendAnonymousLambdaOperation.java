package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecAnonymousLambdaOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendAnonymousLambdaOperation extends RendAbstractLambdaOperation {
    private final ExecLambdaMethodContent lambdaAnoContent;
    public RendAnonymousLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaAnoContent) {
        super(_opCont, _lamCont);
        lambdaAnoContent = _lambdaAnoContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        Argument res_ = new Argument(ExecAnonymousLambdaOperation.newAnonymousLambda(format(lambdaAnoContent,_rendStack),getLambdaCommonContent(),lambdaAnoContent,previous_,
                _rendStack.getLastPage().getContent(), clArg_));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
