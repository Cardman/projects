package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
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
        Argument c_;
        if (getChildrenNodes().size() == 2) {
            c_ = getArgument(_nodes,opTwo_);
        } else {
            RendMethodOperation par_ = getParent();
            while (par_ instanceof RendIdOperation) {
                par_ = par_.getParent();
            }
            Argument arr_ = getPreviousArgument(_nodes, par_);
            if (arr_.getStruct() instanceof ArrayStruct) {
                c_ = new Argument(new IntStruct(((ArrayStruct)arr_.getStruct()).getLength()));
            } else {
                c_ = a_;
            }
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _rendStack);
        Argument r_ = ApplyCoreMethodUtil.range(_context,_stack,a_.getStruct(),c_.getStruct());
        setSimpleArgument(r_, _nodes, _context, _stack, _rendStack);
    }
}
