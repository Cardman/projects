package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private ExecLambdaMethodContent lambdaMethodContent;

    public ExecMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getAncestor(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.getMethod().getConstraints()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, int _ancestor, boolean _polymorph, MethodId _constraints) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        return newLambda(_previous, ownerType_, _ancestor, _polymorph, lambdaMethodContent.isAbstractMethod(), isShiftArgument(), isSafeInstance(), clArg_, _constraints);
    }

    public static Struct newLambda(Argument _previous, String _ownerType,
                                   int _ancestor, boolean _polymorph, boolean _abstractMethod, boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, MethodId _constraints) {
        LambdaMethodStruct l_ = new LambdaMethodStruct(_clArg, _ownerType, _polymorph, _shiftArgument, _ancestor, _abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(MethodAccessKind.INSTANCE);
        return l_;
    }


}
