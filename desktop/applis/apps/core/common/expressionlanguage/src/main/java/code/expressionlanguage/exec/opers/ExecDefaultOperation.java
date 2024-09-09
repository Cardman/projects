package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecDefaultOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final int offset;
    private final StringList names;

    public ExecDefaultOperation(ExecOperationContent _opCont, int _offset, StringList _names) {
        super(_opCont);
        offset = _offset;
        names = _names;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        CustList<Struct> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(offset, _stack);
        Struct argres_ = ExecClassArgumentMatching.convertFormattedWide(ExecHelper.getFirstArgument(arguments_), _conf, names, _stack.getLastPage());
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

}
