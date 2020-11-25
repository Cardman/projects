package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecNamedContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecNamedArgumentOperation extends ExecAbstractUnaryOperation {

    private ExecNamedContent namedContent;
    public ExecNamedArgumentOperation(ExecOperationContent _opCont, ExecNamedContent _namedContent) {
        super(_opCont);
        namedContent = _namedContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        if (getFirstChild() instanceof ExecWrappOperation) {
            ArgumentsPair pairCh_ = ExecTemplates.getArgumentPair(_nodes, getFirstChild());
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(pairCh_.getWrapper());
            setQuickNoConvertSimpleArgument(Argument.createVoid(), _conf, _nodes);
            return;
        }
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelOffsetPossibleLastPage(namedContent.getOffset(), _conf);
        return ExecTemplates.getFirstArgument(_arguments);
    }

    public int getIndex() {
        return namedContent.getIndex();
    }
}
