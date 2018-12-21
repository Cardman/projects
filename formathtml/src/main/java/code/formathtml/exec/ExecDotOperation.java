package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.DotOperation;
import code.util.CustList;

public final class ExecDotOperation extends ExecReflectableOpering {

    public ExecDotOperation(DotOperation _d) {
        super(_d);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        if (chidren_.isEmpty()) {
            return;
        }
        setSimpleArgumentAna(chidren_.last().getArgument(), _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        boolean simple_ = false;
        if (getParent() instanceof ExecSemiAffectationOperation) {
            simple_ = false;
        } else if (getParent() instanceof ExecAffectationOperation) {
            ExecAffectationOperation aff_ = (ExecAffectationOperation) getParent();
            if (aff_.getSettable() == chidren_.last()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickSimpleArgument(chidren_.last().getArgument(), _conf);
        } else {
            setSimpleArgument(chidren_.last().getArgument(), _conf);
        }
    }
}
