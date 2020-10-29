package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public abstract class ExecStdNumericOperation extends ExecNumericOperation {

    private String oper;

    public ExecStdNumericOperation(ExecOperationContent _opCont, int _opOffset, String _op) {
        super(_opCont, _opOffset);
        oper = _op;
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        Argument a_ = getFirstArgument(_nodes,this);
        Argument c_ = getLastArgument(_nodes,this);
        setRelOffsetPossibleLastPage(getOpOffset(), _conf);
        Argument r_ = calculateOper(a_, oper, c_, _conf);
        setSimpleArgument(r_, _conf, _nodes);
    }
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont);


}
