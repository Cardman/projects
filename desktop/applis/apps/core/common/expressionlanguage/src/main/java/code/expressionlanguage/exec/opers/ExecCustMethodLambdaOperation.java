package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.opers.ExecLambdaAnoContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecCustMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;

    public ExecCustMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),lambdaMethodContent,previous_, _conf, ownerType_, clArg_));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, Argument _previous, ContextEl _conf, String _ownerType,
                                   String _clArg) {
        MethodMetaInfo metaInfo_ = buildMetaInfo(_common, _conf, _ownerType, _meth.getMethod().getConstraints(), _meth.isStaticCall(), _meth.isAbstractMethod());
        metaInfo_.setExpCast(_meth.isExpCast());
        metaInfo_.setFileName(_common.getFileName());
        metaInfo_.setCallee(_meth.getPair().getFct());
        metaInfo_.setPair(_meth.getPair());
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_meth,_clArg, _ownerType);
    }

    public static Struct newAnonymousLambda(ExecLambdaCommonContent _common, ExecLambdaAnoContent _anonCont, Argument _previous, ContextEl _conf,
                                            String _name, AbstractPageEl _lastPage, StackCall _stackCall) {
        String clArg_ = _name;
        String ownerType_ = _common.getFoundClass();
        ownerType_ = _stackCall.formatVarType(ownerType_);
        clArg_ = _stackCall.formatVarType(clArg_);
        MethodMetaInfo metaInfo_ = buildMetaInfo(_common, _conf, ownerType_, _anonCont.getMethod().getConstraints(), _anonCont.getMethod().getConstraints().getKind() == MethodAccessKind.STATIC_CALL, false);
        metaInfo_.setExpCast(false);
        metaInfo_.setFileName(_common.getFileName());
        metaInfo_.setCallee(_anonCont.getPair().getFct());
        metaInfo_.setPair(_anonCont.getPair());
        metaInfo_.setCache(new Cache(_lastPage));
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_anonCont,clArg_, ownerType_);
    }

    private static MethodMetaInfo buildMetaInfo(ExecLambdaCommonContent _common, ContextEl _conf, String _ownerType, MethodId _id, boolean _staticCall, boolean _abstractMethod) {
        MethodId fid_ = MetaInfoUtil.tryFormatId(_ownerType, _conf, _id);
        String className_;
        if (_staticCall) {
            className_ = _ownerType;
        } else {
            className_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        }
        String from_ = className_;
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        String formCl_ = MetaInfoUtil.tryFormatType(idCl_, _ownerType, _conf);
        MethodModifier met_;
        if (_abstractMethod) {
            met_ = MethodModifier.ABSTRACT;
        } else if (fid_.getKind() == MethodAccessKind.STATIC) {
            met_ = MethodModifier.STATIC;
        } else if (fid_.getKind() == MethodAccessKind.STATIC_CALL) {
            met_ = MethodModifier.STATIC_CALL;
        } else {
            met_ = MethodModifier.NORMAL;
        }
        return new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, from_, _id, met_, _common.getReturnFieldType(), fid_, formCl_);
    }


}
