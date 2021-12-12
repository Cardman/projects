package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;

public final class ExecNullSafeOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final int opOffset;
    private final StringList names;

    public ExecNullSafeOperation(ExecOperationContent _opCont, int _opOffset,StringList _names) {
        super(_opCont);
        opOffset = _opOffset;
        names = _names;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelativeOffsetPossibleLastPage(opOffset, _stack);
        Argument f_ = getFirstArgument(_nodes,this);
        Struct abs_ = f_.getStruct();
        if (abs_ != NullStruct.NULL_VALUE) {
            f_ = new Argument(ExecClassArgumentMatching.convertFormatted(abs_,_conf, names, _stack));
            setQuickConvertSimpleArgument(f_, _conf, _nodes, _stack);
            return;
        }
        Argument a_ = getLastArgument(_nodes,this);
        a_ = new Argument(ExecClassArgumentMatching.convertFormatted(a_.getStruct(),_conf, names, _stack));
        setSimpleArgument(a_, _conf, _nodes, _stack);

    }
}
