package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendRangeOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;
    public RendRangeOperation(ExecOperationContent _content, int _opOffset) {
        super(_content);
        opOffset = _opOffset;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode opOne_ = getFirstNode(this);
        RendDynOperationNode opTwo_ = getLastNode(this);
        Argument a_ = getArgument(_nodes,opOne_);
        Argument r_;
        if (getChildrenNodes().size() == 2) {
            Argument c_ = getArgument(_nodes, opTwo_);
            setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _rendStack);
            r_ = ApplyCoreMethodUtil.range(_context,_stack,a_.getStruct(),c_.getStruct());
        } else {
            setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _rendStack);
            r_ = ApplyCoreMethodUtil.range(_context,_stack,a_.getStruct());
        }
        setSimpleArgument(r_, _nodes, _context, _stack, _rendStack);
    }
}
