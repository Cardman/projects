package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendMethodLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;

    public RendMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        Argument res_ = new Argument(ExecMethodLambdaOperation.newLambda(getLambdaCommonContent(),lambdaMethodContent,previous_, clArg_));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
