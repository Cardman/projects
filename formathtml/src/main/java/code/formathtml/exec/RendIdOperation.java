package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.IdOperation;
import code.util.CustList;

public final class RendIdOperation extends RendAbstractUnaryOperation {

    public RendIdOperation(IdOperation _i) {
        super(_i);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode o_ = chidren_.first();
        Argument a_ = o_.getArgument();
        boolean simple_ = false;
        if (o_ instanceof RendSettableElResult) {
            RendSettableElResult s_ = (RendSettableElResult) o_;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickSimpleArgument(a_, _conf);
        } else {
            setSimpleArgument(a_, _conf);
        }
    }
}
