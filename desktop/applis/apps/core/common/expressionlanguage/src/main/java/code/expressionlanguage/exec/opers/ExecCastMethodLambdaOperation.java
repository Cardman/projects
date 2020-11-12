package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
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

public final class ExecCastMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private ExecLambdaMethodContent lambdaMethodContent;

    public ExecCastMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
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
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), getAncestor(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.getMethod().getConstraints()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType, int _ancestor, boolean _polymorph, MethodId _constraints) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        return newLambda(_previous, _conf, ownerType_, _returnFieldType, _ancestor, _polymorph, lambdaMethodContent.isAbstractMethod(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), _constraints);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String _ownerType, String _returnFieldType,
                                   int _ancestor, boolean _polymorph, boolean _abstractMethod, boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, String _fileName, MethodId _constraints) {
        LambdaMethodStruct l_ = new LambdaMethodStruct(_clArg, _ownerType, _polymorph, _shiftArgument, _ancestor, _abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(true);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(MethodAccessKind.STATIC);
        MethodMetaInfo metaInfo_ = buildMeta(_conf, _returnFieldType, _fileName, _ownerType, _constraints);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }

    private static MethodMetaInfo buildMeta(ContextEl _conf, String _returnFieldType, String _fileName, String _ownerType, MethodId _id) {
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String formCl_ = ExecutingUtil.tryFormatType(idCl_, _ownerType, _conf);
        MethodModifier met_ = MethodModifier.STATIC;
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, _ownerType, _id, met_, _returnFieldType, _id, formCl_);
        metaInfo_.setDirectCast(true);
        metaInfo_.setFileName(_fileName);
        return metaInfo_;
    }


}
