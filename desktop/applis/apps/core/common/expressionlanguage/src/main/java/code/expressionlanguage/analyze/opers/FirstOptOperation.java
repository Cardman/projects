package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.*;

public final class FirstOptOperation extends AbstractUnaryOperation {

    private int offset;
    public FirstOptOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (m_ == null ||!m_.isCallMethodCtor()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_conf.getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getKeyWords().getKeyWordFirstopt());
            _conf.getAnalyzing().getLocalizer().addError(varg_);
            getErrs().add(varg_.getBuiltError());
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (isFirstChildInParent()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_conf.getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getKeyWords().getKeyWordFirstopt());
            _conf.getAnalyzing().getLocalizer().addError(varg_);
            getErrs().add(varg_.getBuiltError());
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode child_ = getFirstChild();
        setResultClass(new ClassArgumentMatching(child_.getResultClass()));
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        setArg(_conf,this);
    }

    private static void setArg(ContextEl _conf, FirstOptOperation _par) {
        CustList<OperationNode> chidren_ = _par.getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        _par.setSimpleArgumentAna(arguments_.first(), _conf);
    }
    public int getOffset() {
        return offset;
    }
}
