package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecCloneMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;

    public ExecCloneMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = getCommonArgument(previous_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), getAncestor(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.getMethod().getConstraints(), _stackCall));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType, int _ancestor, boolean _polymorph, MethodId _constraints, StackCall _stackCall) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _stackCall.formatVarType(ownerType_);
        clArg_ = _stackCall.formatVarType(clArg_);
        return newLambda(_previous, _conf, ownerType_, _returnFieldType, _ancestor, _polymorph, lambdaMethodContent.isAbstractMethod(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), _constraints);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String _ownerType, String _returnFieldType,
                                   int _ancestor, boolean _polymorph, boolean _abstractMethod, boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, String _fileName, MethodId _constraints) {
        LambdaMethodStruct l_ = new LambdaMethodStruct(_clArg, _ownerType, _polymorph, _shiftArgument, _ancestor, _abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(MethodAccessKind.INSTANCE);
        MethodMetaInfo metaInfo_ = buildMeta(_conf, _returnFieldType, _fileName, _ownerType, _constraints);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }

    private static MethodMetaInfo buildMeta(ContextEl _conf, String _returnFieldType, String _fileName, String _ownerType, MethodId _id) {
        MethodId fid_ = ExecutingUtil.tryFormatId(_ownerType, _conf, _id);
        String from_ = StringExpUtil.getPrettyArrayType(_conf.getStandards().getContent().getCoreNames().getAliasObject());
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String formCl_ = ExecutingUtil.tryFormatType(idCl_, _ownerType, _conf);
        MethodModifier met_ = MethodModifier.NORMAL;
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, from_, _id, met_, _returnFieldType, fid_, formCl_);
        metaInfo_.setFileName(_fileName);
        return metaInfo_;
    }


}
