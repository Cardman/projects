package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendTypeConstructorLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecLambdaConstructorContent lambdaConstructorContent;
    private final ExecTypeFunction pair;

    public RendTypeConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaConstructorContent _lambdaConstructorContent, ExecTypeFunction _pair) {
        super(_opCont, _lamCont);
        lambdaConstructorContent = _lambdaConstructorContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = getFoundClass();
        Argument res_ = new Argument(ExecTypeConstructorLambdaOperation.newLambda(previous_, _context, ownerType_, lambdaConstructorContent.getRealId(), getReturnFieldType(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), pair));
        setSimpleArgument(res_, _nodes, _context, _stack, _rendStack);
    }
}
