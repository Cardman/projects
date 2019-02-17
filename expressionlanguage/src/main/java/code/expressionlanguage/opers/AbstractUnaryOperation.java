package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;

public abstract class AbstractUnaryOperation extends ReflectableOpering {

    public AbstractUnaryOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(Analyzable _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.size() != 1) {
            LgNames stds_ = _conf.getStandards();
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setIndexFile(_conf.getCurrentLocationIndex());
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
