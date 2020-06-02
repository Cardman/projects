package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;


public abstract class QuickOperation extends MethodOperation {

    private boolean okNum;

    public QuickOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    final void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    public static void tryGetResult(ContextEl _conf, MethodOperation _to, boolean _abs, boolean _okNum) {
        if (!_okNum) {
            return;
        }
        CustList<OperationNode> children_ = _to.getChildrenNodes();
        Argument f_ = children_.first().getArgument();
        Argument s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        Struct v_ = f_.getStruct();
        if (_abs) {
            if (BooleanStruct.isTrue(v_)) {
                _to.setSimpleArgumentAna(f_, _conf);
                return;
            }
        } else {
            if (BooleanStruct.isFalse(v_)) {
                _to.setSimpleArgumentAna(f_, _conf);
                return;
            }
        }
        if (s_ == null) {
            return;
        }
        _to.setSimpleArgumentAna(s_, _conf);
    }
    @Override
    public final void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        chidren_.first().getResultClass().setUnwrapObject(booleanPrimType_);
        chidren_.last().getResultClass().setUnwrapObject(booleanPrimType_);
        chidren_.first().cancelArgument();
        chidren_.last().cancelArgument();
        okNum = true;
        for (OperationNode o: chidren_) {
            ClassArgumentMatching clMatch_;
            clMatch_ = o.getResultClass();
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl(), _conf);
            if (!clMatch_.isBoolType(_conf)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                //first operator char or second operator char
                un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                        StringList.join(clMatch_.getNames(),"&"));
                _conf.getAnalyzing().getLocalizer().addError(un_);
                _conf.getAnalyzing().setOkNumOp(false);
                okNum = false;
            }
        }
        setResultClass(new ClassArgumentMatching(chidren_.last().getResultClass()));
    }

    public boolean isOkNum() {
        return okNum;
    }

}
