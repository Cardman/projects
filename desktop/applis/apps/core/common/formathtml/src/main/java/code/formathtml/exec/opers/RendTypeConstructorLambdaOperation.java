package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecTypeConstructorLambdaOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendTypeConstructorLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecLambdaConstructorContent lambdaConstructorContent;

    public RendTypeConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaConstructorContent _lambdaConstructorContent) {
        super(_opCont, _lamCont);
        lambdaConstructorContent = _lambdaConstructorContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Struct previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_rendStack);
        Struct res_ = ExecTypeConstructorLambdaOperation.newLambda(getLambdaCommonContent(),lambdaConstructorContent,previous_, ownerType_, clArg_);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
