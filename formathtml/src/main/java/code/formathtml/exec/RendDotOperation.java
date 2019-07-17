package code.formathtml.exec;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.DotOperation;
import code.util.CustList;

public final class RendDotOperation extends RendMethodOperation implements RendCalculableOperation {

    public RendDotOperation(DotOperation _d) {
        super(_d);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
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
            setQuickSimpleArgument(chidren_.last().getArgument(), _conf);
        } else {
            setSimpleArgument(chidren_.last().getArgument(), _conf);
        }
    }
}
