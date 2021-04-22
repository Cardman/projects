package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

import code.util.IdMap;

public final class RendRandCodeOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;
    public RendRandCodeOperation(ExecOperationContent _m, int _opOffset) {
        super(_m);
        opOffset = _opOffset;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode opOne_ = getFirstNode(this);
        Argument a_ = getArgument(_nodes,opOne_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _rendStack);
        setSimpleArgument(new Argument(new LongStruct(a_.getStruct().randCode())), _nodes, _context, _stack, _rendStack);
    }
}