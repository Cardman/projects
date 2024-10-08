package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecConstructorLambdaOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendConstructorLambdaOperation extends RendAbstractLambdaOperation {

    public RendConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
        super(_opCont, _lamCont);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Struct previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_rendStack);
        Struct res_ = ExecConstructorLambdaOperation.newLambda(getLambdaCommonContent(),previous_, ownerType_, clArg_);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
