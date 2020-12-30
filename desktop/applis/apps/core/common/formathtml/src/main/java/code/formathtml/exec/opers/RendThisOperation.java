package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.SimplePageEl;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendThisOperation extends RendLeafOperation implements RendCalculableOperation {

    private final int off;

    public RendThisOperation(ExecOperationContent _content, int _off) {
        super(_content);
        off = _off;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument arg_ = getCommonArgument(_rendStack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

    Argument getCommonArgument(RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _rendStackCall);
        SimplePageEl ip_ = _rendStackCall.getPageEl();
        Struct struct_ = ip_.getGlobalStruct();
        return new Argument(struct_);
    }

}
