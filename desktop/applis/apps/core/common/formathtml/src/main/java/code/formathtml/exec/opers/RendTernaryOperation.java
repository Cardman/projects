package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecTernaryOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendTernaryOperation extends RendMethodOperation implements RendCalculableOperation {

    private final int offsetLocal;

    public RendTernaryOperation(ExecOperationContent _content, int _offsetLocal) {
        super(_content);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _rendStack);
        Argument res_ = ExecTernaryOperation.getArgument(arguments_);
        setSimpleArgument(res_, _nodes, _context, _stack, _rendStack);
    }

}
