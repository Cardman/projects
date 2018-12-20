package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;


public abstract class QuickOperation extends ReflectableOpering {

    public QuickOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (!_conf.isOkNumOp()) {
            return;
        }
        CustList<OperationNode> children_ = getChildrenNodes();
        Argument f_ = children_.first().getArgument();
        if (f_ == null) {
            return;
        }
        Struct v_ = f_.getStruct();
        if (!(v_ instanceof BooleanStruct)) {
            return;
        }
        if (((BooleanStruct)v_).getInstance() == absorbingValue()) {
            setSimpleArgumentAna(f_, _conf);
        } else {
            Argument s_ = children_.last().getArgument();
            if (s_ == null) {
                return;
            }
            v_ = s_.getStruct();
            if (!(v_ instanceof BooleanStruct)) {
                return;
            }
            setSimpleArgumentAna(s_, _conf);
        }
    }
    @Override
    public final void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        chidren_.first().getResultClass().setUnwrapObject(booleanPrimType_);
        chidren_.last().getResultClass().setUnwrapObject(booleanPrimType_);
        String booleanType_ = stds_.getAliasBoolean();
        for (OperationNode o: chidren_) {
            ClassArgumentMatching clMatch_;
            clMatch_ = o.getResultClass();
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl(), _conf);
            if (!clMatch_.isBoolType(_conf)) {
                ClassArgumentMatching cl_ = o.getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(booleanType_);
                un_.setOperands(cl_);
                _conf.getClasses().addError(un_);
                _conf.setOkNumOp(false);
            }
        }
        setResultClass(chidren_.last().getResultClass());
    }

    @Override
    public final Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode first_ = chidren_.first();
        Argument f_ = ElUtil.getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (absorbingStruct().sameReference(abs_)) {
            setQuickSimpleArgument(f_, _conf, _nodes);
            return f_;
        }
        OperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = ElUtil.getArgument(_nodes,last_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isOkNumOp()) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgumentAna(a_, _conf);
    }

    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgument(a_, _conf);
    }

    abstract boolean absorbingValue();
    public abstract BooleanStruct absorbingStruct();
}
