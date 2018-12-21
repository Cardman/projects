package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.QuickOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;


public abstract class ExecQuickOperation extends ExecReflectableOpering {

    public ExecQuickOperation(QuickOperation _q) {
        super(_q);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (!_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecDynOperationNode> children_ = getChildrenNodes();
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
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgumentAna(a_, _conf);
    }

    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgument(a_, _conf);
    }

    abstract boolean absorbingValue();
    public abstract BooleanStruct absorbingStruct();
}
