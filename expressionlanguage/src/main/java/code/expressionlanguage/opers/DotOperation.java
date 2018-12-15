package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;

public final class DotOperation extends MethodOperation {

    public DotOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.isEmpty()) {
            String obj_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(obj_));
            return;
        }
        setResultClass(chidren_.last().getResultClass());
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode o_ = chidren_.last();
        Argument a_ = _nodes.getVal(o_).getArgument();
        boolean simple_ = false;
        if (getParent() instanceof SemiAffectationOperation) {
            simple_ = false;
        } else if (getParent() instanceof AffectationOperation) {
            AffectationOperation aff_ = (AffectationOperation) getParent();
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
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.isEmpty()) {
            return;
        }
        setSimpleArgumentAna(chidren_.last().getArgument(), _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        boolean simple_ = false;
        if (getParent() instanceof SemiAffectationOperation) {
            simple_ = false;
        } else if (getParent() instanceof AffectationOperation) {
            AffectationOperation aff_ = (AffectationOperation) getParent();
            if (aff_.getSettable() == chidren_.last()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickSimpleArgument(chidren_.last().getArgument(), _conf);
        } else {
            setSimpleArgument(chidren_.last().getArgument(), _conf);
        }
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
