package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public abstract class ExecStdNumericOperation extends ExecNumericOperation {

    private final String oper;

    protected ExecStdNumericOperation(ExecOperationContent _opCont, int _opOffset, String _op) {
        super(_opCont, _opOffset);
        oper = _op;
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf, StackCall _stack) {
        Argument a_ = getFirstArgument(_nodes,this);
        Argument c_ = getLastArgument(_nodes,this);
        setRelOffsetPossibleLastPage(getOpOffset(), _stack);
        Argument r_ = calculateOper(a_, oper, c_, _conf, _stack);
        setSimpleArgument(r_, _conf, _nodes, _stack);
    }
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, StackCall _stack);


}
