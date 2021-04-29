package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.HiddenCache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
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
        MethodMetaInfo metaInfo_ = buildMetaInfo(_common,_meth, _conf,null, _ownerType, _meth.getMethod().getConstraints(), _meth.getPair());
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_meth,_clArg, _ownerType);
    }

    public static Struct newAnonymousLambda(ExecLambdaCommonContent _common, ExecLambdaAnoContent _anonCont, Argument _previous, ContextEl _conf,
                                            String _name, AbstractPageEl _lastPage, StackCall _stackCall) {
        String clArg_ = _name;
        String ownerType_ = _common.getFoundClass();
        ownerType_ = _stackCall.formatVarType(ownerType_);
        clArg_ = _stackCall.formatVarType(clArg_);
        MethodMetaInfo metaInfo_ = buildMetaInfo(_common,null, _conf,new HiddenCache(_lastPage), ownerType_, _anonCont.getMethod().getConstraints(), _anonCont.getPair());
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_anonCont,clArg_, ownerType_);
    }

    private static MethodMetaInfo buildMetaInfo(ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, ContextEl _conf, Cache _cache, String _ownerType, MethodId _id, ExecTypeFunction _pair) {
        return new MethodMetaInfo(_conf,_cache,_common,_meth,_ownerType, _id, _pair);
    }


}
