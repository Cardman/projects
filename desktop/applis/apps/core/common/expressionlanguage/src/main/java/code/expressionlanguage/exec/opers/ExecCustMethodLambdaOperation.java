package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecCustMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private ExecLambdaMethodContent lambdaMethodContent;

    public ExecCustMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
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
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), getAncestor(), lambdaMethodContent.isDirectCast(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.getMethod().getConstraints()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType, int _ancestor, boolean _directCast, boolean _polymorph, MethodId _constraints) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        return newLambda(_previous, _conf, ownerType_, _returnFieldType, _ancestor, _directCast, _polymorph, lambdaMethodContent.isAbstractMethod(), lambdaMethodContent.isExpCast(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), _constraints, lambdaMethodContent.getPair());
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String _ownerType, String _returnFieldType,
                                    int _ancestor, boolean _directCast, boolean _polymorph, boolean _abstractMethod, boolean _expCast, boolean _shiftArgument, boolean _safeInstance,
                                    String _clArg, String _fileName, MethodId _constraints, ExecTypeFunction _pair) {
        LambdaMethodStruct l_ = new LambdaMethodStruct(_clArg, _ownerType, _polymorph, _shiftArgument, _ancestor, _abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(_directCast);
        l_.setExpCast(_expCast);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(_constraints.getKind());
        MethodMetaInfo metaInfo_ = buildMeta(_conf, _returnFieldType, _directCast, _expCast, _fileName, _ownerType, _constraints, l_, _pair);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }

    public static Struct newAnonymousLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType,
                                            boolean _shiftArgument, boolean _safeInstance,
                                            String _name, AbstractPageEl _lastPage, String _fileName, MethodId _constraints, ExecTypeFunction _pair) {
        String clArg_ = _name;
        String ownerType_ = _foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, false, _shiftArgument, 0, false);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(false);
        l_.setExpCast(false);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(_constraints.getKind());
        MethodMetaInfo metaInfo_ = buildMeta(_conf, _returnFieldType, false, false, _fileName, ownerType_, _constraints, l_, _pair);
        metaInfo_.setCache(new Cache(_lastPage));
        l_.setMetaInfo(metaInfo_);
        return l_;
    }

    private static MethodMetaInfo buildMeta(ContextEl _conf, String _returnFieldType, boolean _directCast, boolean _expCast, String _fileName, String _ownerType, MethodId _id, LambdaMethodStruct _l, ExecTypeFunction _pair) {
        MethodId fid_ = ExecutingUtil.tryFormatId(_ownerType, _conf, _id);
        String className_;
        if (_l.isStaticCall()) {
            className_ = _ownerType;
        } else {
            className_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        }
        String from_ = className_;
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String formCl_ = ExecutingUtil.tryFormatType(idCl_, _ownerType, _conf);
        MethodModifier met_;
        if (_l.isAbstractMethod()) {
            met_ = MethodModifier.ABSTRACT;
        } else if (fid_.getKind() == MethodAccessKind.STATIC) {
            met_ = MethodModifier.STATIC;
        } else if (fid_.getKind() == MethodAccessKind.STATIC_CALL) {
            met_ = MethodModifier.STATIC_CALL;
        } else {
            met_ = MethodModifier.NORMAL;
        }
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, from_, _id, met_, _returnFieldType, fid_, formCl_);
        metaInfo_.setDirectCast(_directCast);
        metaInfo_.setExpCast(_expCast);
        metaInfo_.setFileName(_fileName);
        metaInfo_.setAnnotableBlock(_pair.getFct());
        metaInfo_.setCallee(_pair.getFct());
        metaInfo_.setCalleeInv(_pair.getFct());
        metaInfo_.setDeclaring(_pair.getType());
        metaInfo_.setPair(_pair);
        return metaInfo_;
    }


}
