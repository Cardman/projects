package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendCloneMethodLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;

    public RendCloneMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        ExecFormattedRootBlock ownerType_ = getFoundClass();
        Argument res_ = new Argument(ExecCloneMethodLambdaOperation.newLambda(getLambdaCommonContent(),lambdaMethodContent,previous_, ownerType_, clArg_));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
