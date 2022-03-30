package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendLeafOperation;
import code.formathtml.exec.opers.RendPossibleIntermediateDotted;
import code.util.IdMap;

public abstract class NatAbstractFieldOperation extends RendLeafOperation implements NatRendCalculableOperation, RendPossibleIntermediateDotted {

    private final NatExecFieldOperationContent fieldOperationContent;

    protected NatAbstractFieldOperation(ExecOperationContent _content, NatExecFieldOperationContent _fieldOperationContent) {
        super(_content);
        fieldOperationContent = _fieldOperationContent;
    }

    protected static Argument getPreviousArg(RendDynOperationNode _dyn,RendPossibleIntermediateDotted _possible, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, NatRendStackCall _rendStackCall) {
        Argument previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, _dyn);
        } else {
            previous_ = _rendStackCall.getLastPage().getGlobalArgument();
        }
        return previous_;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return fieldOperationContent.isIntermediate();
    }


}
