package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCustMethodLambdaOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendCustMethodLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;

    public RendCustMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Struct previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        Struct res_ = ExecCustMethodLambdaOperation.newLambda(format(lambdaMethodContent,_rendStack),getLambdaCommonContent(),lambdaMethodContent,previous_, clArg_);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
