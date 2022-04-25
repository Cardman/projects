package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.PageElContent;
import code.expressionlanguage.exec.util.HiddenCache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.CallersInfo;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecAnonymousLambdaOperation extends ExecAbstractLambdaOperation {
    private final ExecLambdaMethodContent lambdaAnoContent;

    public ExecAnonymousLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaAnoContent) {
        super(_opCont, _lamCont);
        lambdaAnoContent = _lambdaAnoContent;
    }

    public Struct newAnonymousLambda(CallersInfo _info, ExecLambdaCommonContent _common, ExecLambdaMethodContent _anonCont, Argument _previous,
                                     AbstractPageEl _lastPage, StackCall _stackCall) {
        String clArg_ = formatVarTypeRes(_stackCall);
        return newAnonymousLambda(_info, _common, _anonCont, _previous, _lastPage.getContentEx(), clArg_);
    }

    public static LambdaMethodStruct newAnonymousLambda(CallersInfo _info, ExecLambdaCommonContent _common, ExecLambdaMethodContent _anonCont, Argument _previous, PageElContent _lastPage, String _clArg) {
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_info,new HiddenCache(_lastPage), _common, _anonCont);
        return new LambdaMethodStruct(metaInfo_, _previous, _common, _anonCont, _clArg);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = new Argument(newAnonymousLambda(format(lambdaAnoContent,_stack),getLambdaCommonContent(),lambdaAnoContent,previous_,
                _stack.getLastPage(), _stack));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
