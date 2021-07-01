package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(off, _rendStack);
        SimplePageEl ip_ = _rendStack.getPageEl();
        Struct struct_ = ip_.getGlobalStruct();
        Argument arg_ = new Argument(struct_);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
