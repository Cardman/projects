package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EqList;

public abstract class AbstractUnaryOperation extends MethodOperation {

    public AbstractUnaryOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(Analyzable _conf) {
        OperationNode first_ = getFirstChild();
        if (first_ == null || first_.getNextSibling() != null) {
            LgNames stds_ = _conf.getStandards();
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        analyzeUnary(_conf);
    }
    public abstract void analyzeUnary(Analyzable _conf);
    @Override
    public final void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.isEmpty()) {
            return;
        }
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.isEmpty()) {
            return;
        }
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }

    @Override
    public final ConstructorId getConstId() {
        return null;
    }
}
