package code.formathtml.exec;

import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendAbstractDotOperation extends RendMethodOperation implements RendCalculableOperation {
    public RendAbstractDotOperation(OperationNode _op) {
        super(_op);
    }
    public RendAbstractDotOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }

    public void calculateDot(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        boolean simple_;
        if (getParent() instanceof RendAffectationOperation) {
            RendAffectationOperation aff_ = (RendAffectationOperation) getParent();
            simple_ = aff_.getSettable() == chidren_.last();
        } else {
            simple_ = false;
        }
        if (simple_) {
            setQuickNoConvertSimpleArgument(getArgument(_nodes,chidren_.last()), _conf,_nodes);
        } else {
            setSimpleArgument(getArgument(_nodes,chidren_.last()), _conf,_nodes);
        }
    }
}
