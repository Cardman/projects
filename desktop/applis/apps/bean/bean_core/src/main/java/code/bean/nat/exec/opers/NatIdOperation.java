package code.bean.nat.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendMethodOperation;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class NatIdOperation extends RendMethodOperation implements RendCalculableOperation {

    public NatIdOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode o_ = NatAbstractAffectOperation.getIdOp(this);
        Argument a_ = getArgument(_nodes,o_);
        ArgumentsPair pairCh_ = getArgumentPair(_nodes, o_);
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }
}
