package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.stds.StandardType;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendStdConstructorLambdaOperation extends RendAbstractLambdaOperation {

    private final ConstructorId realId;
    private final StandardType standardType;

    public RendStdConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ConstructorId _realId, StandardType _standardType) {
        super(_opCont, _lamCont);
        realId = _realId;
        standardType = _standardType;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes();
        ExecFormattedRootBlock ownerType_ = getFoundClass();
        Argument res_ = new Argument(ExecStdConstructorLambdaOperation.newLambda(getLambdaCommonContent(),previous_, ownerType_, realId, clArg_, standardType));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
