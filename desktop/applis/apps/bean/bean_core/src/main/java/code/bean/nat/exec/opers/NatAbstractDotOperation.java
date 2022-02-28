package code.bean.nat.exec.opers;

import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendMethodOperation;
import code.util.IdMap;

public abstract class NatAbstractDotOperation extends RendMethodOperation implements RendCalculableOperation {
    protected NatAbstractDotOperation(ExecOperationContent _content) {
        super(_content);
    }

    public void calculateDot(IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        RendDynOperationNode last_ = getLastNode(this);
        ArgumentsPair pairCh_ = getArgumentPair(_nodes, last_);
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        calcArg(_nodes, getArgument(_nodes,last_));
    }
}
