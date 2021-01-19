package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.MetaInfoUtil;
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
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        Argument res_ = new Argument(newLambda(previous_, _conf, ownerType_, getReturnFieldType(), isShiftArgument(), isSafeInstance(), clArg_, function, method.getConstraints()));
        setSimpleArgument(res_, _conf, _nodes, _stack);
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
        MethodId fid_ = MetaInfoUtil.tryFormatId(_ownerType, _conf, _constraints);
        String className_;
        className_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String from_ = className_;
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String formCl_ = MetaInfoUtil.tryFormatType(idCl_, _ownerType, _conf);
        MethodModifier met_ = _function.getModifier();
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, from_, _constraints, met_, _returnFieldType, fid_, formCl_);
        metaInfo_.setStdCallee(_function);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }


}
