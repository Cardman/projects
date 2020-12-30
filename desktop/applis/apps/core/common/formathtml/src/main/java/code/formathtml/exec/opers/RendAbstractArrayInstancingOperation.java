package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendAbstractArrayInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecArrayInstancingContent arrayInstancingContent;

    public RendAbstractArrayInstancingOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_content, _intermediateDottedOperation);
        arrayInstancingContent = _arrayInstancingContent;
    }

    public final String getMethodName() {
        return arrayInstancingContent.getMethodName();
    }
    public final String getClassName() {
        return arrayInstancingContent.getClassName();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf, _context, _stack, _rendStack);
        Argument res_;
        res_ = argres_;
        setSimpleArgument(res_, _nodes, _context, _stack, _rendStack);
    }

    abstract Argument getArgument(CustList<Argument> _arguments,
                                  Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack);
}
