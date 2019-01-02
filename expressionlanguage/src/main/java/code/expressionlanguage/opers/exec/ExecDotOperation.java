package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.DotOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecDotOperation extends ExecReflectableOpering {

    public ExecDotOperation(DotOperation _d) {
        super(_d);
    }


    @Override
    public Argument calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.last();
        Argument a_ = getArgument(_nodes,o_);
        boolean simple_ = false;
        if (getParent() instanceof ExecSemiAffectationOperation) {
            simple_ = false;
        } else if (getParent() instanceof ExecAffectationOperation) {
            ExecAffectationOperation aff_ = (ExecAffectationOperation) getParent();
            if (aff_.getSettable() == chidren_.last()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickSimpleArgument(a_, _conf, _nodes);
        } else {
            setSimpleArgument(a_, _conf, _nodes);
        }
        return a_;
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        if (chidren_.isEmpty()) {
            return;
        }
        setSimpleArgumentAna(chidren_.last().getArgument(), _conf);
    }
}
