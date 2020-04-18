package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IntTreeMap;

public abstract class AbstractUnaryOperation extends MethodOperation {

    public AbstractUnaryOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(Analyzable _conf) {
        if (isFirstKo()) {
            CustList<OperationNode> children_ = getChildrenNodes();
            LgNames stds_ = _conf.getStandards();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            //first operator part
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getSplitDiff(),
                    Integer.toString(1),
                    Integer.toString(children_.size()));
            _conf.addError(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        analyzeUnary(_conf);
    }
    boolean isFirstKo() {
        CustList<OperationNode> children_ = getChildrenNodes();
        return children_.size() != 1;
    }
    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    public abstract void analyzeUnary(Analyzable _conf);
    @Override
    public final void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        setArg(_conf, this);
    }

    public static void setArg(Analyzable _conf, ParentOperable _current) {
        CustList<Operable> children_ = _current.getChildrenOperable();
        if (children_.size() != 1) {
            return;
        }
        for (Operable o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        _current.quickCalculate(_conf);
    }
}
