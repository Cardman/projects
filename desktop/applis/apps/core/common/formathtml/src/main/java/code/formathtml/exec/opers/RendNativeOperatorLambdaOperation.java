package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.exec.opers.ExecNativeOperatorLambdaOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendNativeOperatorLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;
    private final CommonOperSymbol operSymbol;
    public RendNativeOperatorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent, CommonOperSymbol _o) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
        operSymbol = _o;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        Argument res_ = new Argument(ExecNativeOperatorLambdaOperation.newLambda(getLambdaCommonContent(),lambdaMethodContent,previous_, clArg_, operSymbol));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
