package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecNativeOperatorLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;
    private final CommonOperSymbol operSymbol;
    public ExecNativeOperatorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent, CommonOperSymbol _o) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
        operSymbol = _o;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        String clArg_ = formatVarTypeRes(_stack);
        Struct res_ = newLambda(getLambdaCommonContent(),lambdaMethodContent,previous_, clArg_, operSymbol);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, Struct _previous,
                                   String _clArg, CommonOperSymbol _c) {
        return new LambdaMethodStruct(NullStruct.NULL_VALUE,_previous,_common,_meth,_clArg, _c);
    }


}
