package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendRangeOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;
    private final boolean implicitMiddle;
    public RendRangeOperation(ExecOperationContent _content, int _opOffset, boolean _implicitMiddle) {
        super(_content);
        opOffset = _opOffset;
        implicitMiddle = _implicitMiddle;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode opOne_ = getFirstNode(this);
        RendDynOperationNode opTwo_ = getLastNode(this);
        Argument a_ = getArgument(_nodes,opOne_);
        Argument r_;
        if (getChildrenNodes().size() == 3) {
            Argument b_ = Argument.getNullableValue(getArgumentPair(_nodes, getNode(getChildrenNodes(),1)).getArgument());
            Argument c_ = getArgument(_nodes, opTwo_);
            setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _rendStack);
            r_ = ApplyCoreMethodUtil.range(_context,_rendStack.getStackCall(),a_.getStruct(),b_.getStruct(),c_.getStruct());
        } else if (getChildrenNodes().size() == 2) {
            Argument c_ = getArgument(_nodes, opTwo_);
            setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _rendStack);
            if (implicitMiddle) {
                r_ = ApplyCoreMethodUtil.rangeUnlimitStep(_context,_rendStack.getStackCall(),a_.getStruct(),c_.getStruct());
            } else {
                r_ = ApplyCoreMethodUtil.range(_context,_rendStack.getStackCall(),a_.getStruct(),c_.getStruct());
            }
        } else {
            setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _rendStack);
            r_ = ApplyCoreMethodUtil.range(_context,_rendStack.getStackCall(),a_.getStruct());
        }
        setSimpleArgument(r_, _nodes, _context, _rendStack);
    }
}
