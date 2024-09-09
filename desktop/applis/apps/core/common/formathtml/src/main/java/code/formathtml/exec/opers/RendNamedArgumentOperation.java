package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecNamedContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendNamedArgumentOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecNamedContent namedContent;
    public RendNamedArgumentOperation(ExecOperationContent _content, ExecNamedContent _namedContent) {
        super(_content);
        namedContent = _namedContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(namedContent.getOffset(), _rendStack);
        if (getFirstChild() instanceof RendWrappOperation) {
            ArgumentsPair pairCh_ = getArgumentPair(_nodes, getFirstChild());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            ExecHelper.fwdWrapper(pair_,pairCh_);
            setQuickNoConvertSimpleArgument(pairCh_.getArgument(), _nodes,_context, _rendStack);
            return;
        }
        CustList<Struct> arguments_ = getArguments(_nodes,this);
        Struct argres_ = ExecHelper.getFirstArgument(arguments_);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    public int getIndex() {
        return namedContent.getIndex();
    }
}
