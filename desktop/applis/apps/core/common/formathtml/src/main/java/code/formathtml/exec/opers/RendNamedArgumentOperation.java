package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecNamedContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendNamedArgumentOperation extends RendAbstractUnaryOperation {

    private final ExecNamedContent namedContent;
    public RendNamedArgumentOperation(ExecOperationContent _content, ExecNamedContent _namedContent) {
        super(_content);
        namedContent = _namedContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        if (getFirstChild() instanceof RendWrappOperation) {
            ArgumentsPair pairCh_ = getArgumentPair(_nodes, getFirstChild());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(pairCh_.getWrapper());
            setQuickNoConvertSimpleArgument(Argument.createVoid(), _nodes,_context, _stack);
            return;
        }
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ namedContent.getOffset(), _rendStack);
        Argument argres_ = ExecHelper.getFirstArgument(arguments_);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

    int getIndex() {
        return namedContent.getIndex();
    }
}
