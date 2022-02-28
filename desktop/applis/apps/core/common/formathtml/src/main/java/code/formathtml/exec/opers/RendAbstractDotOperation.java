package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public abstract class RendAbstractDotOperation extends RendMethodOperation implements RendCalculableOperation {
    protected RendAbstractDotOperation(ExecOperationContent _content) {
        super(_content);
    }

    public void calculateDot(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStackCall) {
        RendDynOperationNode last_ = getLastNode(this);
        boolean simple_;
        if (getParent() instanceof RendAffectationOperation) {
            RendAffectationOperation aff_ = (RendAffectationOperation) getParent();
            simple_ = aff_.getSettable() == last_;
        } else {
            simple_ = false;
        }
        ArgumentsPair pairCh_ = getArgumentPair(_nodes, last_);
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        ExecHelper.fwdWrapper(pair_,pairCh_);
        Argument a_ = Argument.getNullableValue(pairCh_.getArgument());
        if (simple_) {
            setQuickNoConvertSimpleArgument(a_, _nodes, _context, _rendStackCall);
        } else {
            setSimpleArgument(a_, _nodes, _context, _rendStackCall);
        }
    }
}
