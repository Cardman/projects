package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendStdMethodLambdaOperation extends RendAbstractLambdaOperation {

    private final ClassMethodId method;
    private final StandardMethod function;

    public RendStdMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ClassMethodId _method, StandardMethod _standardMethod) {
        super(_opCont, _lamCont);
        method = _method;
        function = _standardMethod;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_rendStack);
        Argument res_ = new Argument(ExecStdMethodLambdaOperation.newLambda(getLambdaCommonContent(),previous_, ownerType_, clArg_, function, method.getConstraints()));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }

}
