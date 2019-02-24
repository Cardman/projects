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
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.last();
        Argument a_ = getArgument(_nodes,o_);
        boolean simple_;
        if (getParent() instanceof ExecSemiAffectationOperation) {
            simple_ = false;
        } else if (getParent() instanceof ExecAffectationOperation) {
            ExecAffectationOperation aff_ = (ExecAffectationOperation) getParent();
            if (aff_.getSettable() == chidren_.last()) {
                simple_ = true;
            } else {
                simple_ = false;
            }
        } else {
            simple_ = false;
        }
        if (simple_) {
            setQuickSimpleArgument(a_, _conf, _nodes);
        } else {
            setSimpleArgument(a_, _conf, _nodes);
        }
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        DotOperation.setArg(this,_conf);
    }

}
