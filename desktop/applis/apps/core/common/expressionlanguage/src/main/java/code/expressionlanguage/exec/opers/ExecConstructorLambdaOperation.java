package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    public ExecConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
        super(_opCont, _lamCont);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        Argument res_ = new Argument(newLambda(previous_, ownerType_, isShiftArgument(), isSafeInstance(), clArg_));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(Argument _previous, String _ownerType,
                                   boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg) {
        LambdaConstructorStruct l_ = new LambdaConstructorStruct(_clArg, _ownerType, _shiftArgument);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(_safeInstance);
        return l_;
    }

}
