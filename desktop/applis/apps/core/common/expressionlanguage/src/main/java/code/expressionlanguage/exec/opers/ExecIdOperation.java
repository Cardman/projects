package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecIdOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    public ExecIdOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecOperationNode o_ = ExecAbstractAffectOperation.dotted(ExecAbstractAffectOperation.deepSearchId(getFirstChild()));
        Struct a_ = getArgument(_nodes,o_);
        boolean simple_ = false;
        if (o_ instanceof ExecSettableElResult) {
            ExecSettableElResult s_ = (ExecSettableElResult) o_;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        ArgumentsPair pairCh_ = ExecHelper.getArgumentPair(_nodes, o_);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        if (simple_) {
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes, _stack);
        } else {
            setSimpleArgument(a_, _conf, _nodes, _stack);
        }
    }
}
