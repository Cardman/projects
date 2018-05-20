package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;


public abstract class QuickOperation extends PrimitiveBoolOperation {

    public QuickOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
        CustList<OperationNode> children_ = getChildrenNodes();
        Argument f_ = children_.first().getArgument();
        if (f_ == null) {
            return;
        }
        Object v_ = f_.getObject();
        if (!(v_ instanceof Boolean)) {
            return;
        }
        if (((Boolean)v_).booleanValue() == absorbingValue()) {
            setSimpleArgumentAna(f_, _conf);
        } else {
            Argument s_ = children_.last().getArgument();
            if (s_ == null) {
                return;
            }
            v_ = s_.getObject();
            if (!(v_ instanceof Boolean)) {
                return;
            }
            setSimpleArgumentAna(s_, _conf);
        }
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        Argument f_ = children_.first().getArgument();
        if (f_ == null) {
            return;
        }
        Object v_ = f_.getObject();
        if (!(v_ instanceof Boolean)) {
            return;
        }
        if (((Boolean)v_).booleanValue() == absorbingValue()) {
            setSimpleArgumentAna(f_, _conf);
        } else {
            Argument s_ = children_.last().getArgument();
            if (s_ == null) {
                return;
            }
            v_ = s_.getObject();
            if (!(v_ instanceof Boolean)) {
                return;
            }
            setSimpleArgumentAna(s_, _conf);
        }
    }
    @Override
    public final void analyze(Analyzable _conf,
            String _fieldName) {
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
            if (!clMatch_.matchClass(booleanPrimType_)) {
                if (!clMatch_.matchClass(booleanType_)) {
                    ClassArgumentMatching cl_ = o.getResultClass();
                    UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                    un_.setRc(_conf.getCurrentLocation());
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setExpectedResult(booleanType_);
                    un_.setOperands(new StringList(cl_.getName()));
                    _conf.getClasses().getErrorsDet().add(un_);
                }
            }
        }
        setResultClass(chidren_.last().getResultClass());
    }

    @Override
    public final Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = _nodes.getVal(last_).getArgument();
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
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
}
