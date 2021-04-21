package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.util.IdMap;

public final class ExecRangeOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final int opOffset;
    private final boolean implicitMiddle;
    public ExecRangeOperation(ExecOperationContent _m, int _opOffset, boolean _implicitMiddle) {
        super(_m);
        opOffset = _opOffset;
        implicitMiddle = _implicitMiddle;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument a_ = getFirstArgument(_nodes,this);
        Argument r_;
        if (getChildrenNodes().size() == 3) {
            Argument b_ = Argument.getNullableValue(ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(getChildrenNodes(),1)).getArgument());
            Argument c_ = getLastArgument(_nodes, this);
            setRelOffsetPossibleLastPage(opOffset, _stack);
            r_ = ApplyCoreMethodUtil.range(_conf,_stack,a_.getStruct(),b_.getStruct(),c_.getStruct());
        } else if (getChildrenNodes().size() == 2) {
            Argument c_ = getLastArgument(_nodes, this);
            setRelOffsetPossibleLastPage(opOffset, _stack);
            if (implicitMiddle) {
                r_ = ApplyCoreMethodUtil.rangeUnlimitStep(_conf,_stack,a_.getStruct(),c_.getStruct());
            } else {
                r_ = ApplyCoreMethodUtil.range(_conf,_stack,a_.getStruct(),c_.getStruct());
            }
        } else {
            setRelOffsetPossibleLastPage(opOffset, _stack);
            r_ = ApplyCoreMethodUtil.range(_conf,_stack,a_.getStruct());
        }
        setSimpleArgument(r_, _conf, _nodes, _stack);
    }
}
