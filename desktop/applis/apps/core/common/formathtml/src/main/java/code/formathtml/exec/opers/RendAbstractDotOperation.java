package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendAbstractDotOperation extends RendMethodOperation implements RendCalculableOperation {
    public RendAbstractDotOperation(ExecOperationContent _content) {
        super(_content);
    }

    public void calculateDot(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        boolean simple_;
        if (getParent() instanceof RendAffectationOperation) {
            RendAffectationOperation aff_ = (RendAffectationOperation) getParent();
            simple_ = aff_.getSettable() == chidren_.last();
        } else {
            simple_ = false;
        }
        if (simple_) {
            setQuickNoConvertSimpleArgument(getArgument(_nodes,chidren_.last()), _nodes, _context);
        } else {
            setSimpleArgument(getArgument(_nodes,chidren_.last()), _conf,_nodes, _context);
        }
    }
}
