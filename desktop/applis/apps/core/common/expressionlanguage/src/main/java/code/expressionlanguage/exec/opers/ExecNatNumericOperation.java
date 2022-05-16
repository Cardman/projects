package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecNatNumericOperation extends ExecNumericOperation {

    private final ExecOperSymbol pair;

    public ExecNatNumericOperation(ExecOperSymbol _pair, ExecOperationContent _opCont, int _opOffset) {
        super(_opCont, _opOffset);
        pair = _pair;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument a_ = getFirstArgument(_nodes,this);
        Argument c_ = getLastArgument(_nodes,this);
        setRelOffsetPossibleLastPage(getOpOffset(), _stack);
        Struct r_ = pair.calculateOperator(a_.getStruct(), c_.getStruct(), getResultClass().getUnwrapObjectNb(), _conf, _stack);
        setSimpleArgument(new Argument(r_), _conf, _nodes, _stack);
    }

}
