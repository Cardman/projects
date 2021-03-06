package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class ExecTernaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final int offsetLocal;

    public ExecTernaryOperation(ExecOperationContent _opCont, int _offsetLocal) {
        super(_opCont);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(offsetLocal, _stack);
        Argument res_ = getArgument(arguments_);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Argument getArgument(CustList<Argument> _arguments) {
        Argument arg_;
        if (BooleanStruct.isTrue(ExecHelper.getFirstArgument(_arguments).getStruct())) {
            arg_ = ExecHelper.getArgument(_arguments,IndexConstants.SECOND_INDEX);
        } else {
            arg_ = ExecHelper.getLastArgument(_arguments);
        }
        return arg_;
    }
}
