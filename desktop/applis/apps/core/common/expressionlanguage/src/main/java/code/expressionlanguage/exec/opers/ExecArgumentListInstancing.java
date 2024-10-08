package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;

public final class ExecArgumentListInstancing extends ExecMethodOperation implements AtomicExecCalculableOperation {
    public ExecArgumentListInstancing(ExecOperationContent _m) {
        super(_m);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setQuickNoConvertSimpleArgument(NullStruct.NULL_VALUE,_conf,_nodes,_stack);
    }
}
