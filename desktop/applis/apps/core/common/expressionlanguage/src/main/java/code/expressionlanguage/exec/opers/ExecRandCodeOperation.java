package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.structs.LongStruct;
import code.util.IdMap;

public final class ExecRandCodeOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final int opOffset;
    public ExecRandCodeOperation(ExecOperationContent _m, int _opOffset) {
        super(_m);
        opOffset = _opOffset;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument a_ = getFirstArgument(_nodes,this);
        setRelOffsetPossibleLastPage(opOffset, _stack);
        setSimpleArgument(new Argument(new LongStruct(a_.getStruct().randCode())), _conf, _nodes, _stack);
    }
}