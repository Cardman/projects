package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.VarargError;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.NatTreeMap;

public final class FirstOptOperation extends AbstractUnaryOperation implements FirstOptOperable {

    private int offset;
    public FirstOptOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (!m_.isCallMethodCtor()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(FIRST_OPT);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (isFirstChild()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(FIRST_OPT);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode child_ = getFirstChild();
        setResultClass(child_.getResultClass());
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        setArg(_conf,this);
    }

    public static void setArg(Analyzable _conf, ParentOperable _par) {
        if (!_conf.isGearConst()) {
            return;
        }
        CustList<Operable> chidren_ = _par.getChildrenOperable();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (Operable o: chidren_) {
            arguments_.add(o.getArgument());
        }
        _par.setSimpleArgumentAna(arguments_.first(), _conf);
    }
    public int getOffset() {
        return offset;
    }
}
