package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.QuickOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;


public abstract class ExecQuickOperation extends ExecReflectableOpering {

    public ExecQuickOperation(QuickOperation _q) {
        super(_q);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (!_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecOperationNode> children_ = getChildrenNodes();
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
    public final Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode first_ = chidren_.first();
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (absorbingStruct().sameReference(abs_)) {
            setQuickSimpleArgument(f_, _conf, _nodes);
            return f_;
        }
        ExecOperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgumentAna(a_, _conf);
    }

    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgument(a_, _conf);
    }

    abstract boolean absorbingValue();
    public abstract BooleanStruct absorbingStruct();
}
