package code.formathtml.exec;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.DotOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendDotOperation extends RendMethodOperation implements RendCalculableOperation {

    public RendDotOperation(DotOperation _d) {
        super(_d);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        boolean simple_;
        if (getParent() instanceof RendSemiAffectationOperation) {
            simple_ = false;
        } else if (getParent() instanceof RendAffectationOperation) {
            RendAffectationOperation aff_ = (RendAffectationOperation) getParent();
            simple_ = aff_.getSettable() == chidren_.last();
        } else {
            simple_ = false;
        }
        if (simple_) {
            setQuickSimpleArgument(getArgument(_nodes,chidren_.last()), _conf,_nodes);
        } else {
            setSimpleArgument(getArgument(_nodes,chidren_.last()), _conf,_nodes);
        }
    }
}
