package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecDefaultOperation extends ExecAbstractUnaryOperation {

    private final int offset;
    public ExecDefaultOperation(ExecOperationContent _opCont, int _offset) {
        super(_opCont);
        offset = _offset;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf, _stack);
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf, StackCall _stackCall) {
        setRelOffsetPossibleLastPage(offset, _stackCall);
        return new Argument(ExecClassArgumentMatching.convertFormattedWide(ExecTemplates.getFirstArgument(_arguments).getStruct(), _conf, getResultClass().getNames(), _stackCall));
    }
}
