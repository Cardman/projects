package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecNamedContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecNamedArgumentOperation extends ExecAbstractUnaryOperation {

    private final ExecNamedContent namedContent;
    public ExecNamedArgumentOperation(ExecOperationContent _opCont, ExecNamedContent _namedContent) {
        super(_opCont);
        namedContent = _namedContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (getFirstChild() instanceof ExecWrappOperation) {
            ArgumentsPair pairCh_ = ExecHelper.getArgumentPair(_nodes, getFirstChild());
            ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
            pair_.setWrapper(pairCh_.getWrapper());
            setQuickNoConvertSimpleArgument(Argument.createVoid(), _conf, _nodes, _stack);
            return;
        }
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(namedContent.getOffset(), _stack);
        Argument argres_ = ExecHelper.getFirstArgument(arguments_);
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

    public int getIndex() {
        return namedContent.getIndex();
    }
}
