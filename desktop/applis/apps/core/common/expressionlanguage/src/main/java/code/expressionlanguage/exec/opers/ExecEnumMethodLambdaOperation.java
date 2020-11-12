package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
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

public final class ExecEnumMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private ExecLambdaMethodContent lambdaMethodContent;
    private ExecRootBlock declaring;

    public ExecEnumMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent, ExecRootBlock _declaring) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
        declaring = _declaring;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous) {
        return new Argument(newLambda(_previous, getFoundClass(), getReturnFieldType(), getAncestor(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.getMethod().getConstraints()));
    }

    private Struct newLambda(Argument _previous, String _foundClass, String _returnFieldType, int _ancestor, boolean _polymorph, MethodId _constraints) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        return newLambda(_previous, _foundClass, _returnFieldType, _ancestor, _polymorph, lambdaMethodContent.isAbstractMethod(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), _constraints, declaring);
    }

    public static Struct newLambda(Argument _previous, String _ownerType, String _returnFieldType,
                                   int _ancestor, boolean _polymorph, boolean _abstractMethod, boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, String _fileName, MethodId _constraints, ExecRootBlock _type) {
        LambdaMethodStruct l_ = new LambdaMethodStruct(_clArg, _ownerType, _polymorph, _shiftArgument, _ancestor, _abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(MethodAccessKind.STATIC);
        MethodMetaInfo metaInfo_ = buildMeta(_returnFieldType, _fileName, _ownerType, _constraints, _type);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }

    private static MethodMetaInfo buildMeta(String _returnFieldType, String _fileName, String _ownerType, MethodId _id, ExecRootBlock _type) {
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        MethodModifier met_ = MethodModifier.STATIC;
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, idCl_, _id, met_, _returnFieldType, _id, idCl_);
        metaInfo_.setFileName(_fileName);
        metaInfo_.pair(_type,null);
        return metaInfo_;
    }


}
