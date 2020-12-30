package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecStdMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private final ClassMethodId method;
    private final StandardMethod function;

    public ExecStdMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ClassMethodId _method, StandardMethod _standardMethod) {
        super(_opCont, _lamCont);
        method = _method;
        function = _standardMethod;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = getCommonArgument(previous_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), method.getConstraints(), _stackCall));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType, MethodId _constraints, StackCall _stackCall) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _stackCall.formatVarType(ownerType_);
        clArg_ = _stackCall.formatVarType(clArg_);
        return newLambda(_previous, _conf, ownerType_, _returnFieldType, isShiftArgument(), isSafeInstance(), clArg_, function, _constraints);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String _ownerType,
                                   String _returnFieldType,
                                   boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, StandardMethod _function, MethodId _constraints) {
        LambdaMethodStruct l_ = new LambdaMethodStruct(_clArg, _ownerType, false, _shiftArgument, 0, false);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(_constraints.getKind());
        MethodId fid_ = ExecutingUtil.tryFormatId(_ownerType, _conf, _constraints);
        String className_;
        className_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String from_ = className_;
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String formCl_ = ExecutingUtil.tryFormatType(idCl_, _ownerType, _conf);
        MethodModifier met_ = _function.getModifier();
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, from_, _constraints, met_, _returnFieldType, fid_, formCl_);
        metaInfo_.setStdCallee(_function);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }


}
