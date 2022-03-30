package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendFieldLambdaOperation extends RendAbstractLambdaOperation {


    private final ExecLambdaFieldContent lambdaFieldContent;

    public RendFieldLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaFieldContent _lambdaFieldContent) {
        super(_opCont, _lamCont);
        lambdaFieldContent = _lambdaFieldContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_rendStack);
        Argument res_ = new Argument(ExecFieldLambdaOperation.newLambda(getLambdaCommonContent(),lambdaFieldContent,previous_, ownerType_, clArg_));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
