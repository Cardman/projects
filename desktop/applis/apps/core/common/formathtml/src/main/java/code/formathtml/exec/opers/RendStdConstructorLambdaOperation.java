package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecStdConstructorLambdaOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendStdConstructorLambdaOperation extends RendAbstractLambdaOperation {

    private final ConstructorId realId;
    private final StandardType standardType;
    private final StandardConstructor standardConstructor;

    public RendStdConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ConstructorId _realId, StandardType _standardType, StandardConstructor _standardConstructor) {
        super(_opCont, _lamCont);
        realId = _realId;
        standardType = _standardType;
        standardConstructor = _standardConstructor;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Struct previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_rendStack);
        Struct res_ = ExecStdConstructorLambdaOperation.newLambda(getLambdaCommonContent(),previous_, ownerType_, realId, clArg_, standardType,standardConstructor);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
