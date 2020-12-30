package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.LambdaConstructorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecStdConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    private final ConstructorId realId;
    private final StandardType standardType;

    public ExecStdConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ConstructorId _realId, StandardType _standardType) {
        super(_opCont, _lamCont);
        realId = _realId;
        standardType = _standardType;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = getCommonArgument(previous_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), realId, getReturnFieldType(), _stackCall));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, ConstructorId _realId, String _returnFieldType, StackCall _stackCall) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _stackCall.formatVarType(ownerType_);
        clArg_ = _stackCall.formatVarType(clArg_);
        return newLambda(_previous, _conf, ownerType_, _realId, _returnFieldType, isShiftArgument(), isSafeInstance(), clArg_, getFileName(), standardType);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String _ownerType, ConstructorId _realId, String _returnFieldType,
                                   boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, String _fileName, StandardType _standardType) {
        LambdaConstructorStruct l_ = new LambdaConstructorStruct(_clArg, _ownerType, _shiftArgument);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(_safeInstance);
        String className_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        ConstructorId fid_ = ExecutingUtil.tryFormatId(_ownerType, _conf, _realId);
        ConstructorMetaInfo met_ = new ConstructorMetaInfo(_ownerType,AccessEnum.PUBLIC, _realId, _returnFieldType, fid_, className_);
        met_.setFileName(_fileName);
        met_.setStandardType(_standardType);
        l_.setMetaInfo(met_);
        return l_;
    }

}
