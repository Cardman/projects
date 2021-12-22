package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendIdOperation extends RendMethodOperation implements RendCalculableOperation {

    public RendIdOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode o_ = RendAbstractAffectOperation.deepId(getFirstChild());
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
        ExecHelper.fwdWrapper(pair_,pairCh_);
        if (simple_) {
            setQuickNoConvertSimpleArgument(a_, _nodes, _context, _rendStack);
        } else {
            setSimpleArgument(a_, _nodes, _context, _rendStack);
        }
    }
}
