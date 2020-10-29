package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecNullSafeOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private int opOffset;
    public ExecNullSafeOperation(ExecOperationContent _opCont, int _opOffset) {
        super(_opCont);
        opOffset = _opOffset;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument f_ = getFirstArgument(_nodes,this);
        Struct abs_ = f_.getStruct();
        if (abs_ != NullStruct.NULL_VALUE) {
            f_ = new Argument(ExecClassArgumentMatching.convertFormatted(abs_,_conf, getResultClass().getNames()));
            setQuickConvertSimpleArgument(f_, _conf, _nodes);
            return;
        }
        setRelativeOffsetPossibleLastPage(opOffset, _conf);
        Argument a_ = getLastArgument(_nodes,this);
        a_ = new Argument(ExecClassArgumentMatching.convertFormatted(a_.getStruct(),_conf, getResultClass().getNames()));
        setSimpleArgument(a_, _conf, _nodes);

    }
}
