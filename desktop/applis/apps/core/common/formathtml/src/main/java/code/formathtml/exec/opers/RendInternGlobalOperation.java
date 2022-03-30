package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendInternGlobalOperation extends RendLeafOperation implements RendCalculableOperation {
    private final int off;
    public RendInternGlobalOperation(ExecOperationContent _content, int _off) {
        super(_content);
        off = _off;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(off, _rendStack);
        Struct struct_ = _rendStack.getInternGlobal();
        Argument arg_ = new Argument(struct_);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
