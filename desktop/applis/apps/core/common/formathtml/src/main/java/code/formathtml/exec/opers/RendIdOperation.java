package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendIdOperation extends RendAbstractUnaryOperation {

    public RendIdOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode o_ = RendAffectationOperation.getIdOp(this);
        Argument a_ = getArgument(_nodes,o_);
        boolean simple_ = false;
        if (o_ instanceof RendSettableElResult) {
            RendSettableElResult s_ = (RendSettableElResult) o_;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        ArgumentsPair pairCh_ = getArgumentPair(_nodes, o_);
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        pair_.setWrapper(pairCh_.getWrapper());
        if (simple_) {
            setQuickNoConvertSimpleArgument(a_, _nodes, _context, _stack);
        } else {
            setSimpleArgument(a_, _nodes, _context, _stack, _rendStack);
        }
    }
}
