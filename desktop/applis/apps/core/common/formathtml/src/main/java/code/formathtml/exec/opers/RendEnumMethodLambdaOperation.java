package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendEnumMethodLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;
    private final ExecRootBlock declaring;

    public RendEnumMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent, ExecRootBlock _declaring) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
        declaring = _declaring;
    }
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        Argument res_ = new Argument(ExecEnumMethodLambdaOperation.newLambda(getLambdaCommonContent(),lambdaMethodContent,previous_, clArg_, declaring));
        setSimpleArgument(res_, _nodes, _context, _stack, _rendStack);
    }
}
