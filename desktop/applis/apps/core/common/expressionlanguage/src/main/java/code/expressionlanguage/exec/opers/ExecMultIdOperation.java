package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecMultIdOperation extends ExecAbstractUnaryOperation {

    public ExecMultIdOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument a_ = getArgument(_nodes,getFirstChild());
        setSimpleArgument(a_, _conf, _nodes);
    }
}
