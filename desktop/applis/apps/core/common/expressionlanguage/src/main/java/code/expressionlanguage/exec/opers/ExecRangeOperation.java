package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.util.IdMap;

public final class ExecRangeOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final int opOffset;
    public ExecRangeOperation(ExecOperationContent _m, int _opOffset) {
        super(_m);
        opOffset = _opOffset;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument a_ = getFirstArgument(_nodes,this);
        Argument c_;
        if (getChildrenNodes().size() == 2) {
            c_ = getLastArgument(_nodes,this);
        } else {
            ExecMethodOperation par_ = getParent();
            while (par_ instanceof ExecIdOperation) {
                par_ = par_.getParent();
            }
            Argument arr_ = getPreviousArgument(_nodes, par_);
            if (arr_.getStruct() instanceof ArrayStruct) {
                c_ = new Argument(new IntStruct(((ArrayStruct)arr_.getStruct()).getLength()));
            } else {
                c_ = a_;
            }
        }
        setRelOffsetPossibleLastPage(opOffset, _stack);
        Argument r_ = ApplyCoreMethodUtil.range(_conf,_stack,a_.getStruct(),c_.getStruct());
        setSimpleArgument(r_, _conf, _nodes, _stack);
    }
}
