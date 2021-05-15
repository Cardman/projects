package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.HiddenCache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaAnoContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecAnonymousLambdaOperation extends ExecAbstractLambdaOperation {
    private final ExecLambdaAnoContent lambdaAnoContent;

    public ExecAnonymousLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaAnoContent _lambdaAnoContent) {
        super(_opCont, _lamCont);
        lambdaAnoContent = _lambdaAnoContent;
    }

    public static Struct newAnonymousLambda(ExecLambdaCommonContent _common, ExecLambdaAnoContent _anonCont, Argument _previous, ContextEl _conf,
                                            String _name, AbstractPageEl _lastPage, StackCall _stackCall) {
        String clArg_ = _name;
        ExecFormattedRootBlock ownerType_ = _common.getFormattedType();
        ownerType_ = _stackCall.formatVarType(ownerType_);
        clArg_ = _stackCall.formatVarType(clArg_);
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_conf, new HiddenCache(_lastPage), _common, null, ownerType_, _anonCont.getMethod().getConstraints(), _anonCont.getPair());
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_anonCont,clArg_, ownerType_.getFormatted());
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = new Argument(newAnonymousLambda(getLambdaCommonContent(),lambdaAnoContent,previous_, _conf,
                getResultClass().getSingleNameOrEmpty(), _stack.getLastPage(), _stack));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
