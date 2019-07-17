package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.UnaryBooleanOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;

public final class RendUnaryBooleanOperation extends RendAbstractUnaryOperation {

    public RendUnaryBooleanOperation(UnaryBooleanOperation _u) {
        super(_u);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        BooleanStruct o_ = (BooleanStruct) arg_.getStruct();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        Boolean b_ = o_.getInstance();
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf);
    }

}
