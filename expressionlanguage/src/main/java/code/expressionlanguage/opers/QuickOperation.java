package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
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

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        Struct abs_ = absorbingStruct();
        tryGetResult(_conf, this, abs_, okNum);
    }

    public static void tryGetResult(Analyzable _conf, ParentOperable _to, Struct _abs, boolean _okNum) {
        if (!_okNum) {
            return;
        }
        CustList<Operable> children_ = _to.getChildrenOperable();
        Argument f_ = children_.first().getArgument();
        Argument s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        Struct v_ = f_.getStruct();
        if (v_.sameReference(_abs)) {
            _to.setSimpleArgumentAna(f_, _conf);
            return;
        }
        if (s_ == null) {
            return;
        }
        _to.setSimpleArgumentAna(s_, _conf);
    }
    @Override
    public final void analyze(Analyzable _conf) {
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
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                //first operator char or second operator char
                un_.buildError(_conf.getContextEl().getAnalysisMessages().getUnexpectedType(),
                        StringList.join(clMatch_.getNames(),"&"));
                _conf.addError(un_);
                _conf.getAnalyzing().setOkNumOp(false);
                okNum = false;
            }
        }
        setResultClass(new ClassArgumentMatching(chidren_.last().getResultClass()));
    }

    public boolean isOkNum() {
        return okNum;
    }

    public abstract BooleanStruct absorbingStruct();
}
